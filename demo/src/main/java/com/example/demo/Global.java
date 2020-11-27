package com.example.demo;
import java.sql.*;
import  java.util.*;

public class Global {


    public static HashMap<String, ArrayList<Integer>> user_plus_api_rate_map;     // <user+api<maxcntr,currentcntr>> //alternative to a database
    public static HashMap<String, Long> timemap; // <user+api,starttime>
    public static final String JDBC_DRIVER = "org.h2.Driver";
    public static final String DB_URL = "jdbc:h2:mem:database";
    public static final String USER = "sa";
    public static final String PASS = "";
    public static Connection conn = null;
    public static PreparedStatement preparedStatement = null;
    public static final int fixed_window=60;
    public static final int default_rate = 5;
    Global() throws InterruptedException {


        try {

            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            preparedStatement = conn.prepareStatement("SELECT max_rate FROM RATE_DATABASE where userID=? and api = ?");


            user_plus_api_rate_map = new HashMap<String, ArrayList<Integer>>();
            timemap = new HashMap<String, Long>();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
