package com.sky.bootcamp.pickerpacker.database;

<<<<<<< HEAD
import com.sky.bootcamp.pickerpacker.models.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
=======
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
>>>>>>> bc286c76ebd936a62c5bbaf5b9dbf36150eab021

/**
 * Created by mgh01 on 27/07/2015.
 */
public class DatabaseAccessLayer {

    private static Connection conn = Database.GetConnection();

    public static User getUserFromEmail(String email) throws SQLException {
        User user = null;
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = Database.GetConnection();
            String queryString = "SELECT * FROM auth_user LIMIT 1";
            ps = con.prepareStatement(queryString);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String password = rs.getString("password");
                Timestamp lastLogin = rs.getTimestamp("last_login");
                user = new User(email, password, lastLogin);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
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
