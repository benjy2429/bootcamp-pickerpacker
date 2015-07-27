package com.sky.bootcamp.pickerpacker.tabs;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sky.bootcamp.pickerpacker.adapters.OrderLineAdapter;
import com.sky.bootcamp.pickerpacker.models.OrderLine;

import java.util.ArrayList;

/**
 * Created by bca23 on 25/07/15.
 */
public class PackTab extends ListFragment {

    OrderLineAdapter orderLineAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        ArrayList<OrderLine> testOrders = new ArrayList<>();
        //testOrders.add(new OrderLine("Nexus 1", "123456789"));
        //testOrders.add(new OrderLine("Nexus 2", "123456789"));
        //testOrders.add(new OrderLine("Nexus 3", "123456789"));
        //testOrders.add(new OrderLine("Nexus 4", "123456789"));
        //testOrders.add(new OrderLine("Nexus 5", "123456789"));
        //testOrders.add(new OrderLine("Nexus 6", "123456789"));
        //testOrders.add(new OrderLine("Nexus 7", "123456789"));

        orderLineAdapter = new OrderLineAdapter(inflater);
        //orderLineAdapter.updateData(testOrders);

        setListAdapter(orderLineAdapter);

        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
