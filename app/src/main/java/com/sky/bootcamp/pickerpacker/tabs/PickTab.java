package com.sky.bootcamp.pickerpacker.tabs;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.app.ListFragment;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.sky.bootcamp.pickerpacker.adapters.OrderLineAdapter;
import com.sky.bootcamp.pickerpacker.database.OrderLineDao;
import com.sky.bootcamp.pickerpacker.models.OrderLine;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bca23 on 25/07/15.
 */
public class PickTab extends MasterTab {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        orderLineAdapter = new OrderLineAdapter(inflater);

        // Get some orderlines
        // TODO FIX BROKEN DAO
        //orderLineAdapter.updateData(OrderLineDao.getOrderLines());

        setListAdapter(orderLineAdapter);

        return super.onCreateView(inflater, container, savedInstanceState);
    }


}
