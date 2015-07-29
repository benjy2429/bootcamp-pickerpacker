package com.sky.bootcamp.pickerpacker.controllers;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.sky.bootcamp.pickerpacker.database.DatabaseAccessLayer;
import com.sky.bootcamp.pickerpacker.models.OrderLine;
import com.sky.bootcamp.pickerpacker.models.User;

import java.lang.reflect.Array;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by mgh01 on 28/07/2015.
 */
public class Tabbed {

    public static ArrayList<OrderLine> getOrderline(String filter){
        return DatabaseAccessLayer.getOrderLines(filter);
    }

    public static ArrayList<OrderLine> getOrderline(final ArrayList<OrderLine> orders, final String filter){
        new AsyncTask<Void, Void, Void>(){
            @Override
            protected Void doInBackground(Void... params) {
                orders.addAll(getOrderline(filter));
                return null;
            }
        }.execute();

        return orders;
    }

}
