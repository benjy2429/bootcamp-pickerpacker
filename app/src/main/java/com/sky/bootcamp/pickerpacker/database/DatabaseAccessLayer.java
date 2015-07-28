package com.sky.bootcamp.pickerpacker.database;

import com.sky.bootcamp.pickerpacker.models.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by mgh01 on 27/07/2015.
 */
public class DatabaseAccessLayer {

    private static Connection conn = Database.GetConnection();

    public static User getUsers(String email){

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT email FROM auth_user WHERE email="+email);
        }catch(SQLException e){
            System.err.print(e);
        }
        return null;
    }

}
