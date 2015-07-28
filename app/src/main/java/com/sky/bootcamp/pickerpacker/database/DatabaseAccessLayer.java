package com.sky.bootcamp.pickerpacker.database;

import android.util.Log;

import com.sky.bootcamp.pickerpacker.models.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.sky.bootcamp.pickerpacker.models.OrderLine;
import com.sky.bootcamp.pickerpacker.models.PModel;
import com.sky.bootcamp.pickerpacker.models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;


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

    public static ArrayList<OrderLine> getOrderLines() {
        ArrayList<OrderLine> results = new ArrayList<OrderLine>();

        try {
            Connection c = Database.GetConnection();
            Statement stmt = c.createStatement();
            String queryString = "SELECT * FROM profiles_orderline";
            ResultSet rs = stmt.executeQuery(queryString);
            while (rs.next()) {
                int id = rs.getInt("id");
                String status = rs.getString("status");
                int quantity = rs.getInt("quantity");
                int quantityPacked = rs.getInt("quantity_packed");
                int quantityPicked = rs.getInt("quantity_picked");
                int orderID = rs.getInt("order_id_id");


                OrderLine orderline = new OrderLine(id, status, quantity, quantityPacked, quantityPicked, orderID, status, "asdsrfwe");
                results.add(orderline);
            }
        } catch (SQLException e) {
            Log.e("OrderLine db connection", e.getMessage());
        }

        return results;
    }

}
