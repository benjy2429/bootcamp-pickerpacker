package com.sky.bootcamp.pickerpacker.models;

import com.sky.bootcamp.pickerpacker.database.Database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Created by mgh01 on 27/07/2015.
 */
public class Product {

    private int id;
    private double price;
    private String barcode;
    private int quantity;
    private int productID;

    private static Connection conn = Database.GetConnection();

    public Product(int id,double price, String barcode, int quantity, int productID){
        this.id = id;
        this.price = price;
        this.barcode = barcode;
        this.quantity = quantity;
        this.productID = productID;
    }
}
