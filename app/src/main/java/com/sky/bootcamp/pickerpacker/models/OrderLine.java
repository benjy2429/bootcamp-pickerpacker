package com.sky.bootcamp.pickerpacker.models;

import com.sky.bootcamp.pickerpacker.database.Database;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;

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

    private static Connection conn = Database.GetConnection();

    public OrderLine(int id, String status, int quantity, int quantityPacked, int quantityPicked, int orderID, int pmodelID) {

        this.status = status;
        this.quantity = quantity;
        this.quantityPacked = quantityPacked;
        this.quantityPicked = quantityPicked;
        this.orderID = orderID;
        this.pmodelID = pmodelID;
    }

    public static OrderLine[] ListAll() {

        try {

            Statement stmt = OrderLine.conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM profiles_orderline");

            ArrayList<OrderLine> orderlineList = new ArrayList<OrderLine>();
            while (rs.next()) {
                orderlineList.add(new OrderLine(rs.getInt(1),rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getInt(5), rs.getInt(6), rs.getInt(7)));
            }

            if (orderlineList.size() != 0) {
                return orderlineList
                        .toArray(new OrderLine[orderlineList.size()]);
            }
        } catch (Exception e) {

        }
        return null;
    }

}