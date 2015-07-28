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
    private String productName;
    private String barcode;

    public OrderLine(int id, String status, int quantity, int quantityPacked, int quantityPicked, int orderID, String productName, String barcode) {

        this.status = status;
        this.quantity = quantity;
        this.quantityPacked = quantityPacked;
        this.quantityPicked = quantityPicked;
        this.orderID = orderID;
        this.productName = productName;
        this.barcode = barcode;
    }

    public String getName() {
        return productName;
    }

    public String getBarcode() {
        return barcode;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public int getQuantityPicked() {
        return this.quantityPicked;
    }
}