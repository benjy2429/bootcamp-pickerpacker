package com.sky.bootcamp.pickerpacker.models;

import com.sky.bootcamp.pickerpacker.database.Database;

import java.sql.Connection;

/**
 * Created by mgh01 on 27/07/2015.
 */
public class PModel {

    private int id;
    private double price;
    private String barcode;
    private int quantity;
    private Product product;

    public PModel(int id, double price, String barcode, int quantity, Product product){
        this.id = id;
        this.price = price;
        this.barcode = barcode;
        this.quantity = quantity;
        this.product = product;
    }

    public Product getProduct() {
        return this.product;
    }

    public String getBarcode() {
        return this.barcode;
    }
}
