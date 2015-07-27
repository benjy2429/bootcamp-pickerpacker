package com.sky.bootcamp.pickerpacker.models;

import com.sky.bootcamp.pickerpacker.database.Database;

import java.sql.Connection;

/**
 * Created by mgh01 on 27/07/2015.
 */
public class Product {

    private int id;
    private double price;
    private String barcode;
    private int quantity;
    private int productID;

    public Product(int id,double price, String barcode, int quantity, int productID){
        this.id = id;
        this.price = price;
        this.barcode = barcode;
        this.quantity = quantity;
        this.productID = productID;
    }
}
