package com.sky.bootcamp.pickerpacker.tabs;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sky.bootcamp.pickerpacker.adapters.OrderToPickAdapter;
import com.sky.bootcamp.pickerpacker.controllers.Tabbed;
import com.sky.bootcamp.pickerpacker.models.OrderLine;

import java.util.ArrayList;

/**
 * Created by bca23 on 25/07/15.
 */
public class PickTab extends MasterTab {

    private OrderToPickAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        adapter = new OrderToPickAdapter(inflater);
        setListAdapter(adapter);

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    protected void initiateRefresh() {
        new RefreshListTask().execute();
    }

    private class RefreshListTask extends AsyncTask<Void, Void, ArrayList<OrderLine>> {

        @Override
        protected ArrayList<OrderLine> doInBackground(Void... params) {

            ArrayList<OrderLine> orders = Tabbed.getOrderline("Pending");
            return orders;
        }

        @Override
        protected void onPostExecute(ArrayList<OrderLine> orders) {
            super.onPostExecute(orders);

            if (orders == null || orders.isEmpty()) {
                Log.e("Refresh Data", "An error occurred getting the latest data");
            }

            adapter.updateData(orders);

            onRefreshComplete();
        }

    }


}
