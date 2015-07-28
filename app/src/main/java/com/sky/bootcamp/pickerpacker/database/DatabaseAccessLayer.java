package com.sky.bootcamp.pickerpacker.database;

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

/**
 * Created by mgh01 on 27/07/2015.
 */
public class DatabaseAccessLayer {

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
