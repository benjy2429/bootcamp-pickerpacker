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
import java.util.concurrent.ExecutionException;

/**
 * Created by mgh01 on 28/07/2015.
 */
public class Tabbed {

    public static ArrayList<OrderLine> getOrderline(String filter) throws SQLException, NullPointerException {
        return DatabaseAccessLayer.getOrderLines(filter);
    }

    public static ArrayList<OrderLine> getOrderline(final ArrayList<OrderLine> orders, final String filter) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                try {
                    orders.addAll(getOrderline(filter));
                } catch (SQLException | NullPointerException e) {
                    Log.e("Database Connection", e.getMessage());
                }
                return null;
            }
        }.execute();

        return orders;
    }

    public static OrderLine getOrderLineByBarcode(final String barcode) {


        OrderLine mOrderline;

        AsyncTask<Void, Void, OrderLine> task = new AsyncTask<Void, Void, OrderLine>() {

            @Override
            protected OrderLine doInBackground(Void... params) {
                String errMsg = "";
                try {
                    OrderLine orderline = DatabaseAccessLayer.getOrderLineByBarcode(barcode);
                    return orderline;
                } catch (SQLException | NullPointerException e) {
                    Log.e("Database Connection", e.getMessage());
                }
                return null;
            }
        }.execute();

        try {
            mOrderline = task.get();
            return mOrderline;
        } catch (InterruptedException | ExecutionException e) {
            Log.e("Database Connection", e.getMessage());
        }

        return null;
    }

    public static void updateOrderline(final int quantity_picked, final int quantity_packed, final int id) {

        AsyncTask<Void, Void, Void> task = new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... params) {
                try {
                    DatabaseAccessLayer.updateOrderline(quantity_picked, quantity_packed, id);
                } catch (SQLException | NullPointerException e) {
                    Log.e("Database Connection", e.getMessage());
                }
                return null;
            }
        }.execute();
    }

    public static void updateOrder(final String status, final int order_id){
        AsyncTask<Void, Void, Void> task = new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... params) {
                try {
                    DatabaseAccessLayer.updateOrder(status, order_id);
                } catch (SQLException | NullPointerException e) {
                    Log.e("Database Connection", e.getMessage());
                }
                return null;
            }
        }.execute();
    }

    public static int getStockLevel(final int id) {
        AsyncTask<Void, Void, Integer> task = new AsyncTask<Void, Void, Integer>() {
            @Override
            protected Integer doInBackground(Void... params) {
                int quantity = 0;
                try {
                    quantity = DatabaseAccessLayer.getPModelQuantity(id);
                } catch (SQLException | NullPointerException e) {
                    Log.e("Database Connection", e.getMessage());
                }
                return quantity;
            }
        }.execute();

        try {
            System.out.println(task.get());
            int mQuantity = task.get();
            return mQuantity;
        } catch (InterruptedException | ExecutionException e) {
            Log.e("Database Connection", e.getMessage());
        }

        return 0;
    }

    public static void updatePModel(final int quantity, final int pmodel_id){
        AsyncTask<Void, Void, Void> task = new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... params) {
                try {
                    DatabaseAccessLayer.updatePModel(quantity, pmodel_id);
                } catch (SQLException | NullPointerException e) {
                    Log.e("Database Connection", e.getMessage());
                }
                return null;
            }
        }.execute();

    }
}
