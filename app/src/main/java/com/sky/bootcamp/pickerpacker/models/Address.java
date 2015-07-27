package com.sky.bootcamp.pickerpacker.models;

/**
 * Created by mgh01 on 27/07/2015.
 */
public class Address {

    private int id;
    private String addressLine1;
    private String addressLine2;
    private String addressLine3;
    private String city;
    private String county;
    private String country;
    private String postcode;
    private int userID;

    public Address(int id, String addressLine1,String addressLine2, String addressLine3, String city, String county, String country, String postcode, int userID){
        this.id = id;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.addressLine3 = addressLine3;
        this.city = city;
        this.county = county;
        this.country = country;
        this.postcode = postcode;
        this.userID = userID;
    }
}
