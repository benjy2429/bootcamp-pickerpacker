package com.sky.bootcamp.pickerpacker.database;

/**
 * Created by mgh01 on 22/07/2015.
 */
import java.sql.*;
import java.util.concurrent.*;

public class Database {
    /**
     * Connection to database.
     */
    private static Connection conn;
    /**
     * Connection string.
     */
    private static String url = "jdbc:postgresql://192.168.1.13/"; // DATABASE CONNECTION STRING
    /**
     * Username for database
     */
    private static String user = "postgres"; // DATABASE USERNAME
    /**
     * Password for database
     */
    private static String pass = "Welcome01"; // DATABASE PASSWORD
    /**
     * Static initializer
     */
    static{
        try{
            Class.forName("org.postgresql.Driver");
            Database.conn = DriverManager.getConnection(Database.url, Database.user, Database.pass);
        }
        catch (ClassNotFoundException ex) {System.err.println("ClassNotFoundException: " + ex.getMessage());}
        catch (SQLException ex)           {System.err.println("SQLException: " + ex.getMessage());}

    }

    /**
     * Get connection interface
     *
     * @return Connection instance
     */
    public static Connection GetConnection(){
        return Database.conn;
    }

    /**
     * Test function
     */
    public static void Test(){
        try{
            Connection c = Database.GetConnection();
            Statement stmt = c.createStatement();
            String queryString = "SELECT 1";
            ResultSet rs = stmt.executeQuery(queryString);
            rs.next();
            System.out.print("DATABASE CONNECTION: ");
            if(rs.getInt(1) == 1)
                System.out.println("PASS");
            else
                System.out.println("FAIL");
        }catch(Exception e){
            System.out.println("Fatal: " + e.getMessage());
            System.out.println(e.getCause());
        }
    }

    private Database(){
        /* NO INSTANCES ALLOWED*/
    }
}