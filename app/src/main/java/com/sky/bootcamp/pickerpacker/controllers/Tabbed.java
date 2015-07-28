package com.sky.bootcamp.pickerpacker.controllers;

import android.os.AsyncTask;

import com.sky.bootcamp.pickerpacker.database.DatabaseAccessLayer;
import com.sky.bootcamp.pickerpacker.models.OrderLine;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by mgh01 on 28/07/2015.
 */
public class Tabbed {

    public static ArrayList<OrderLine> getOrderline(){
        return DatabaseAccessLayer.getOrderLines();
    }

    public static ArrayList<OrderLine> getOrderline(final ArrayList<OrderLine> orders){
        new AsyncTask<Void, Void, Void>(){
            @Override
            protected Void doInBackground(Void... params) {
                orders.addAll(getOrderline());
                return null;
            }
        }.execute();

        return orders;
    }



}
