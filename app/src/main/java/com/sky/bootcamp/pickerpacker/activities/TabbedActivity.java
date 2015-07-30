package com.sky.bootcamp.pickerpacker.activities;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.sky.bootcamp.pickerpacker.R;
import com.sky.bootcamp.pickerpacker.SlidingTabLayout;
import com.sky.bootcamp.pickerpacker.adapters.ViewPagerAdapter;
import com.sky.bootcamp.pickerpacker.controllers.Tabbed;
import com.sky.bootcamp.pickerpacker.database.DatabaseAccessLayer;
import com.sky.bootcamp.pickerpacker.models.OrderLine;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by bca23 on 25/07/15.
 */
public class TabbedActivity extends AppCompatActivity {

    // Declaring Your View and Variables

    Toolbar toolbar;
    ViewPager pager;
    ViewPagerAdapter adapter;
    SlidingTabLayout tabs;
    int numOfTabs = 2;
    IntentIntegrator scanIntegrator;
    int selectedTab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabbed);


        // Creating The Toolbar and setting it as the Toolbar for the activity

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);


        // Creating The ViewPagerAdapter and Passing Fragment Manager, Titles fot the Tabs and Number Of Tabs.
        adapter = new ViewPagerAdapter(getSupportFragmentManager(), this, numOfTabs);

        // Assigning ViewPager View and setting the adapter
        pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(adapter);


        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                selectedTab = position;
                pager.setCurrentItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });


        // Assiging the Sliding Tab Layout View
        tabs = (SlidingTabLayout) findViewById(R.id.tabs);
        tabs.setDistributeEvenly(true); // To make the Tabs Fixed set this true, This makes the tabs Space Evenly in Available width

        // Setting Custom Color for the Scroll bar indicator of the Tab View
        tabs.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return getResources().getColor(R.color.tabsScrollColor);
            }
        });

        // Setting the ViewPager For the SlidingTabsLayout
        tabs.setViewPager(pager);

        FloatingActionButton floatingActionButton = (FloatingActionButton) findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                scanIntegrator = new IntentIntegrator(TabbedActivity.this);
                scanIntegrator.initiateScan();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);


        adapter.notifyDataSetChanged();

        if (scanningResult != null) {

            String scanContent = scanningResult.getContents();

            OrderLine order = Tabbed.getOrderLineByBarcode(scanContent);

            if (order != null) {
                switch (selectedTab) {

                    case 0:
                        Tabbed.updateOrderline(order.getQuantityPicked() + 1, order.getQuantityPacked(), order.getId());
                        adapter.getTab1().initiateRefresh();
                        adapter.getTab2().initiateRefresh();
                        break;

                    case 1:
                        Tabbed.updateOrderline(order.getQuantityPicked(), order.getQuantityPacked() + 1, order.getId());
                        adapter.getTab1().initiateRefresh();
                        adapter.getTab2().initiateRefresh();
                        break;
                    default:
                        break;

                }

                OrderLine updatedOrder = Tabbed.getOrderLineByBarcode(scanContent);
                int stockLevel = Tabbed.getStockLevel(updatedOrder.getPmodelID());

                if (updatedOrder.getQuantityToPack() == 0) {
                    Tabbed.updateOrder("Dispatched", updatedOrder.getOrderID());
                    Tabbed.updatePModel(500 - 1, updatedOrder.getPmodelID());
                }

            }else{
                Toast toast = Toast.makeText(getApplicationContext(), "No scan data received!", Toast.LENGTH_SHORT);
                toast.show();
            }

        } else {
            Toast toast = Toast.makeText(getApplicationContext(), "No scan data received!", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_pick, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.logout) {
            // TODO implement logout with database
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        //Disable back button
    }
}