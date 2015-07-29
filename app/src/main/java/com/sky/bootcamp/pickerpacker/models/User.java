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
}


