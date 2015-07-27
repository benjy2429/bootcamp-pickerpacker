package com.sky.bootcamp.pickerpacker.models;

/**
 * Created by bca23 on 27/07/15.
 */
public class Product {

    private int id;
    private String name;

    public Product(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
