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
    private int id;
    private String password;
    private Timestamp lastLogin;
    private boolean isSuperuser;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private boolean isStaff;
    private boolean isActive;
    private Timestamp dateJoined;

    private static Connection conn = Database.GetConnection();

    public User(int id, String password,
                Timestamp lastLogin,
                boolean isSuperuser,
                String username,
                String firstName,
                String lastName,
                String email,
                boolean isStaff,
                boolean isActive,
                Timestamp dateJoined) {

        this.id = id;
        this.password = password;
        this.lastLogin = lastLogin;
        this.isSuperuser = isSuperuser;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.isStaff = isStaff;
        this.isActive = isActive;
        this.dateJoined = dateJoined;

    }


    public static User[] ListAll() {

        try {

            Statement stmt = User.conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM auth_user");

            ArrayList<User> userList = new ArrayList<User>();
            while (rs.next()) {

                userList.add(new User(rs.getInt(1),rs.getString(2), rs.getTimestamp(3), rs.getBoolean(4), rs.getString(5), rs.getString(6),rs.getString(7),rs.getString(8),rs.getBoolean(9), rs.getBoolean(10), rs.getTimestamp(11)));
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


