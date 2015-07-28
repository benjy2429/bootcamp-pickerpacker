package com.sky.bootcamp.pickerpacker.tabs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sky.bootcamp.pickerpacker.adapters.OrderLineAdapter;
import com.sky.bootcamp.pickerpacker.database.DatabaseAccessLayer;

/**
 * Created by bca23 on 25/07/15.
 */
public class PickTab extends MasterTab {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        orderLineAdapter = new OrderLineAdapter(inflater);

        // Get some orderlines
        // TODO FIX BROKEN DAO
        //orderLineAdapter.updateData(DatabaseAccessLayer.getOrderLines());

        setListAdapter(orderLineAdapter);

        return super.onCreateView(inflater, container, savedInstanceState);
    }


}
