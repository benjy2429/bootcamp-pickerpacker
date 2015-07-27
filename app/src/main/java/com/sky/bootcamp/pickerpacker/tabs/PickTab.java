package com.sky.bootcamp.pickerpacker.tabs;

import android.support.v4.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sky.bootcamp.pickerpacker.adapters.OrderLineAdapter;
import com.sky.bootcamp.pickerpacker.models.OrderLine;

import java.util.ArrayList;

/**
 * Created by bca23 on 25/07/15.
 */
public class PickTab extends ListFragment {

    OrderLineAdapter orderLineAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        ArrayList<OrderLine> testOrders = new ArrayList<>();
        //testOrders.add(new OrderLine("iPhone 6", "123456789"));
        //testOrders.add(new OrderLine("iPhone 7", "235326"));
        //testOrders.add(new OrderLine("iPhone 8", "5786585689"));
        //testOrders.add(new OrderLine("iPhone 9", "6735735"));
        //testOrders.add(new OrderLine("iPhone 10", "24562546"));
        //testOrders.add(new OrderLine("iPhone 6", "123456789"));
        //testOrders.add(new OrderLine("iPhone 7", "235326"));
        //testOrders.add(new OrderLine("iPhone 8", "5786585689"));
        //testOrders.add(new OrderLine("iPhone 9", "6735735"));
        //testOrders.add(new OrderLine("iPhone 10", "24562546"));

        orderLineAdapter = new OrderLineAdapter(inflater);
        //orderLineAdapter.updateData(testOrders);

        setListAdapter(orderLineAdapter);

        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
