package com.revspeed.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class GettingDBConnection {
    private static GettingDBConnection connect = null;
    private Connection con = null;
    private final String url  = "jdbc:mysql://localhost:3306/revSpeed_0";
    private final String user = "root";
    private final String password = "root";
    private GettingDBConnection(){
        try {
            con = DriverManager.getConnection(url,user,password);
        } catch (SQLException e) {
            throw new RuntimeException("Here some Connection Exception....!!! "+e);
        }
    }
    public Connection getConnect(){
        return con;
    }
    public static GettingDBConnection createInstance(){
        if(connect == null){
            connect = new GettingDBConnection();
        }
        else{
            try {
                connect.getConnect().close();
                connect = new GettingDBConnection();
            } catch (SQLException e) {
                throw new RuntimeException("Something happen in close connection...!!!"+e);
            }
        }
        return connect;
    }
}
