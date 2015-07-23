package com.sky.bootcamp.pickerpacker.models;

/**
 * Created by bca23 on 23/07/15.
 */
public class OrderLine {
    private String productName;
    private String productBarcode;

    public OrderLine(String name, String barcode) {
        this.productName = name;
        this.productBarcode = barcode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductBarcode() {
        return productBarcode;
    }

    public void setProductBarcode(String productBarcode) {
        this.productBarcode = productBarcode;
    }
}
