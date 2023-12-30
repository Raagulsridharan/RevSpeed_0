package com.revspeed.jdbc;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import static javax.management.remote.JMXConnectorFactory.connect;

public class DBConnectionProperties {
    private static Connection con = null;
    static{
        try (InputStream input = DBConnectionProperties.class.getClassLoader().getResourceAsStream("dbconnection.properties")) {
            Properties properties = new Properties();
            if (input != null) {
                properties.load(input);
                con = DriverManager.getConnection(  properties.getProperty("DB_URL"),
                                                    properties.getProperty("DB_USERNAME"),
                                                    properties.getProperty("DB_PASSWORD"));
                System.out.println("Connection established");
            }
            else {
                System.out.println("Unable to find dbconnection.properties");
            }

        }
        catch (IOException | SQLException e) {
            System.out.println("Connection not established"+e);
        }

    }
    public static Connection getConnect() {
        return con;
    }
}

