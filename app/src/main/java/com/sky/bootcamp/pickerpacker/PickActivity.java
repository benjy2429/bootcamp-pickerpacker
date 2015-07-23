package com.sky.bootcamp.pickerpacker;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.sky.bootcamp.pickerpacker.models.OrderLine;

import java.util.ArrayList;


public class PickActivity extends ActionBarActivity {

    ListView mainListView;
    OrderLineAdapter orderLineAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick);

        mainListView = (ListView) findViewById(R.id.main_listview);

        ArrayList<OrderLine> testOrders = new ArrayList<OrderLine>();
        testOrders.add(new OrderLine("iPhone 6", "123456789"));
        testOrders.add(new OrderLine("iPhone 7", "235326"));
        testOrders.add(new OrderLine("iPhone 8", "5786585689"));
        testOrders.add(new OrderLine("iPhone 9", "6735735"));
        testOrders.add(new OrderLine("iPhone 10", "24562546"));
        testOrders.add(new OrderLine("iPhone 6", "123456789"));
        testOrders.add(new OrderLine("iPhone 7", "235326"));
        testOrders.add(new OrderLine("iPhone 8", "5786585689"));
        testOrders.add(new OrderLine("iPhone 9", "6735735"));
        testOrders.add(new OrderLine("iPhone 10", "24562546"));

        orderLineAdapter = new OrderLineAdapter(this, getLayoutInflater());
        orderLineAdapter.updateData(testOrders);

        mainListView.setAdapter(orderLineAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_pick, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
