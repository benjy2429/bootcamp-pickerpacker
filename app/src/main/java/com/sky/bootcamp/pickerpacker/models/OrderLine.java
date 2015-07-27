package com.sky.bootcamp.pickerpacker.models;

import com.sky.bootcamp.pickerpacker.database.Database;

import java.sql.Connection;


/**
 * Created by bca23 on 23/07/15.
 */
public class OrderLine {

    private int id;
    private String status;
    private int quantity;
    private int quantityPacked;
    private int quantityPicked;
    private int orderID;
    private int pmodelID;

    public OrderLine(int id, String status, int quantity, int quantityPacked, int quantityPicked, int orderID, int pmodelID) {

        this.status = status;
        this.quantity = quantity;
        this.quantityPacked = quantityPacked;
        this.quantityPicked = quantityPicked;
        this.orderID = orderID;
        this.pmodelID = pmodelID;
    }
}