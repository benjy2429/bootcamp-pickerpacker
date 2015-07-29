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
                queryString += " WHERE profiles_orderline.quantity_picked < profiles_orderline.quantity";
            } else if (filter.equals("Picked")) {
                queryString += " WHERE profiles_orderline.quantity_packed < profiles_orderline.quantity AND profiles_orderline.quantity_packed < profiles_orderline.quantity_picked";
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
                int pmodelID = rs.getInt("pmodel_id_id");
                String barcode = rs.getString("barcode");
                String productName = rs.getString("name");

                System.out.println(status);

                OrderLine orderline = new OrderLine(id, status, quantity, quantityPacked, quantityPicked, orderID, pmodelID ,productName, barcode);
                results.add(orderline);
            }
        } catch (SQLException e) {
            Log.e("OrderLine db connection", e.getMessage());
        }

        return results;
    }

    public static OrderLine getOrderLineByBarcode(String barcode) throws SQLException {
        OrderLine orderline = null;

        Connection con = Database.GetConnection();
        String queryString = "SELECT * FROM profiles_orderline INNER JOIN plans_pmodel ON profiles_orderline.pmodel_id_id = plans_pmodel.id INNER JOIN plans_product ON plans_pmodel.product_id_id = plans_product.id WHERE plans_pmodel.barcode = ? LIMIT 1";
        PreparedStatement ps = con.prepareStatement(queryString);
        ps.setString(1, barcode);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            int id = rs.getInt("id");
            String status = rs.getString("status");
            int quantity = rs.getInt("quantity");
            int quantityPacked = rs.getInt("quantity_packed");
            int quantityPicked = rs.getInt("quantity_picked");
            int orderID = rs.getInt("order_id_id");
            int pmodelID = rs.getInt("pmodel_id_id");
            String productName = rs.getString("name");

            orderline = new OrderLine(id, status, quantity, quantityPacked, quantityPicked, orderID, pmodelID, productName, barcode);
        }

        return orderline;
    }

    public static void updateOrderline(int quantity, int quantity_picked, int quantity_packed, int id){

        try {

            Connection c = Database.GetConnection();
            Statement stmt = c.createStatement();
            String queryString = "UPDATE profiles_orderline SET quantity=?, quantity_picked=?, quantity_packed=? WHERE id=?;";
            PreparedStatement ps = c.prepareStatement(queryString);
            ps.setInt(1, quantity);
            ps.setInt(2, quantity_picked);
            ps.setInt(3, quantity_packed);
            ps.setInt(4, id);

            ps.execute();

        } catch (SQLException e) {
            System.err.print(e);
        }

    }

    public static void updateOrder(String status, int order_id){

        try {
            Connection c = Database.GetConnection();
            Statement stmt = c.createStatement();
            String queryString = "UPDATE profiles_order SET status=? WHERE id=?;";
            PreparedStatement ps = c.prepareStatement(queryString);
            ps.setString(1, status);
            ps.setInt(2, order_id);
            ps.execute();

        } catch (SQLException e) {
            System.err.print(e);
        }

    }

    public static void updatePModel(int quantity, int pmodel_id){

        try {
            Connection c = Database.GetConnection();
            Statement stmt = c.createStatement();
            String queryString = "UPDATE plans_pmodel SET quantity=? WHERE id=?;";
            PreparedStatement ps = c.prepareStatement(queryString);
            ps.setInt(1, quantity);
            ps.setInt(2, pmodel_id);
            ps.execute();

        } catch (SQLException e) {
            System.err.print(e);
        }

    }
}
