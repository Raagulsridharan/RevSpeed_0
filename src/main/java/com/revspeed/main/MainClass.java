package com.revspeed.main;

import com.revspeed.user.User;
import com.revspeed.services.daoImp.ServiceManagement;

import java.sql.SQLException;
import java.util.Scanner;

public class MainClass {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        User userObject = new User();
        ServiceManagement serviceManagementObject = new ServiceManagement();

        System.out.println("Welcome to RevSpeed_0");
        System.out.print("Choose your option:\n\t1.Login\n\t2.Register\n\t3.Forget Password\noption : ");

        int option = sc.nextInt();
        switch (option){
            case 1:
                System.out.println("Login is progressing...");
                serviceManagementObject.login();
                break;
            case 2:
                System.out.println("Register is progressing...");
                serviceManagementObject.register(userObject);
                break;
            case 3:
                System.out.println("Forget password is loading...");
                break;
            default:
                System.out.println("Please choose correct option...");
        }
    }
}
