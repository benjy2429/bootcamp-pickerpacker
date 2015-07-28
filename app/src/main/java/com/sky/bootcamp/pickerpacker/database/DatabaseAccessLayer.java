package com.sky.bootcamp.pickerpacker.database;

import android.util.Log;

import com.sky.bootcamp.pickerpacker.models.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.sky.bootcamp.pickerpacker.models.OrderLine;

import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * Created by mgh01 on 27/07/2015.
 */
public class DatabaseAccessLayer {

    private static Connection conn = Database.GetConnection();

    public static User getUserFromEmail(String email) throws SQLException {
        User user = null;

        Connection con = Database.GetConnection();
        String queryString = "SELECT * FROM auth_user INNER JOIN profiles_person ON profiles_person.user_id = auth_user.id WHERE auth_user.email = ? LIMIT 1";
        PreparedStatement ps = con.prepareStatement(queryString);
        ps.setString(1, email);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            String password = rs.getString("password");
            Timestamp lastLogin = rs.getTimestamp("last_login");
            String role = rs.getString("role");
            user = new User(email, password, lastLogin, role);
        }

        return user;
    }

    public static ArrayList<OrderLine> getOrderLines(String filter) {
        ArrayList<OrderLine> results = new ArrayList<OrderLine>();

        try {
            Connection c = Database.GetConnection();
            String queryString = "SELECT * FROM profiles_orderline INNER JOIN plans_pmodel ON profiles_orderline.pmodel_id_id = plans_pmodel.id INNER JOIN plans_product ON plans_pmodel.product_id_id = plans_product.id";
            if (filter.equals("Pending")) {
                queryString += " WHERE profiles_orderline.quantity > 0";
            } else if (filter.equals("Picked")) {
                queryString += " WHERE profiles_orderline.quantity_picked > 0";
            }
            queryString += " LIMIT 10";
            PreparedStatement ps = c.prepareStatement(queryString);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String status = rs.getString("status");
                int quantity = rs.getInt("quantity");
                int quantityPacked = rs.getInt("quantity_packed");
                int quantityPicked = rs.getInt("quantity_picked");
                int orderID = rs.getInt("order_id_id");
                String barcode = rs.getString("barcode");
                String productName = rs.getString("name");

                OrderLine orderline = new OrderLine(id, status, quantity, quantityPacked, quantityPicked, orderID, productName, barcode);
                results.add(orderline);
            }
        } catch (SQLException e) {
            Log.e("OrderLine db connection", e.getMessage());
        }

        return results;
    }

}
