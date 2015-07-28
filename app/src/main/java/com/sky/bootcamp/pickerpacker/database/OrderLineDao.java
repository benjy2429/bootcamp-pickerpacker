package com.sky.bootcamp.pickerpacker.database;

import com.sky.bootcamp.pickerpacker.models.OrderLine;
import com.sky.bootcamp.pickerpacker.models.PModel;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Created by bca23 on 27/07/15.
 */
public class OrderLineDao {

    public static ArrayList<OrderLine> getOrderLines() {
        ArrayList<OrderLine> results = new ArrayList<OrderLine>();

        Connection c = null;
        Statement stmt = null;
        try {
            c = Database.GetConnection();
            stmt = c.createStatement();
            String queryString = "SELECT * FROM profiles_orderline";
            ResultSet rs = stmt.executeQuery(queryString);
            while (rs.next()) {
                int id = rs.getInt("id");
                String status = rs.getString("status");
                int quantity = rs.getInt("quantity");
                int quantityPacked = rs.getInt("quantity_packed");
                int quantityPicked = rs.getInt("quantity_picked");
                int orderID = rs.getInt("order_id_id");
                PModel pmodel = null;

                OrderLine orderline = new OrderLine(id, status, quantity, quantityPacked, quantityPicked, orderID, pmodel);
                results.add(orderline);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (stmt != null) { stmt.close(); }
                if (c != null) { c.close(); }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

        return results;
    }
}
