package com.sky.bootcamp.pickerpacker.models;

import com.sky.bootcamp.pickerpacker.database.Database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * Created by mgh01 on 27/07/2015.
 */
public class User {
    public static final String VALID_ROLE = "Warehouse Worker";

    private String password;
    private Timestamp lastLogin;
    private String email;
    private String role;

    private static Connection conn = Database.GetConnection();

    public User(String email, String password, Timestamp lastLogin, String role) {
        this.email = email;
        this.password = password;
        this.lastLogin = lastLogin;
        this.role = role;
    }

    public String getPassword() {
        return this.password;
    }

    public String getRole() {
        return this.role;
    }


    public static User[] ListAll() {

        try {

            Statement stmt = User.conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM auth_user");

            ArrayList<User> userList = new ArrayList<User>();
            while (rs.next()) {

                //userList.add(new User(rs.getInt(1),rs.getString(2), rs.getTimestamp(3), rs.getBoolean(4), rs.getString(5), rs.getString(6),rs.getString(7),rs.getString(8),rs.getBoolean(9), rs.getBoolean(10), rs.getTimestamp(11)));
            }

            System.out.println(userList.size());

            if (userList.size() != 0) {
                return userList
                        .toArray(new User[userList.size()]);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}


