package com.revspeed.services.daoImp;

import com.revspeed.jdbc.GettingDBConnection;
import com.revspeed.services.ServiceDAO;
import com.revspeed.user.User;

import java.sql.*;
import java.util.Scanner;

public class ServiceManagement implements ServiceDAO {
    private static final Scanner sc = new Scanner(System.in);
    private static Connection con = null;
    public ServiceManagement() {
        con = GettingDBConnection.createInstance().getConnect();
    }
    public void register(User userObject) {
        String insertQuery = "insert into user_profile(userName,firstName,lastName,mobileNumber,emailId,address,password) values(?,?,?,?,?,?,?)";
        try(PreparedStatement pstm = con.prepareStatement(insertQuery)) {
            System.out.print("Enter your Username : ");
            String userName = sc.next();
            System.out.print("Enter your Firstname : ");String firstName = sc.next();
            System.out.print("Enter your Lastname : ");
            String lastName = sc.next();
            System.out.print("Enter your Mobile number : ");
            long mobileNumber = sc.nextLong();
            sc.nextLine();
            System.out.print("Enter your Email Id : ");
            String emailId = sc.nextLine();
            System.out.print("Enter your Address : ");
            String address = sc.nextLine();
            System.out.print("Enter your Password : ");
            String password = sc.nextLine();

            userObject = new User(userName,firstName,lastName,mobileNumber,emailId,address,password);

            pstm.setString(1, userObject.getUserName());
            pstm.setString(2, userObject.getFirstName());
            pstm.setString(3, userObject.getLastname());
            pstm.setLong(4, userObject.getMobileNumber());
            pstm.setString(5, userObject.getEmailId());
            pstm.setString(6, userObject.getAddress());
            pstm.setString(7, userObject.getPassword());
            pstm.execute();
            System.out.println("Registered successfully...");

            login();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void login(){
        System.out.println("For login....");
        System.out.print("Enter Your UserName : ");
        String UserName = sc.next();
        System.out.print("Enter Your Password : ");
        String Password = sc.next();
        try(CallableStatement clstm = con.prepareCall("call revspeed_0.getUserNamePswd(?,?)")) {
            clstm.setString(1,UserName);
            clstm.setString(2,Password);
            ResultSet rs = clstm.executeQuery();
            if (rs.next()){
                System.out.println("Login Successfully...\nYour profile is loading...");

            }
            else{
                System.out.println("Invalid username or password...");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
