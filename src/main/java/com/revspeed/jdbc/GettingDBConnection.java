package com.revspeed.jdbc;

import com.revspeed.services.serviceImp.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLOutput;

public class GettingDBConnection {
    private static GettingDBConnection connect = null;
    private Connection con = null;
    private final String url  = "jdbc:mysql://localhost:3306/revSpeed_0";
    private final String user = "root";
    private final String password = "root";
    private static final Logger logger = LoggerFactory.getLogger(GettingDBConnection.class);

    private GettingDBConnection(){
        try {
            con = DriverManager.getConnection(url,user,password);
        } catch (SQLException e) {
            logger.error("Exception in getting DB connection",e);
            System.out.println("Here some Connection Exception....!!!");
            //throw new RuntimeException("Here some Connection Exception....!!! "+e);
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
                logger.error("Exception in closing DB connection",e);
                System.out.println("Something happen in close connection...!!!");
                //throw new RuntimeException("Something happen in close connection...!!!");
            }
        }
        return connect;
    }
}
