package com.revspeed.main;

import com.revspeed.services.serviceImp.UserServiceImpl;
import com.revspeed.user.User;

import java.util.Scanner;

public class MainClass {

    private static User user = null; // session
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        UserServiceImpl userService = new UserServiceImpl();
        System.out.println("------------------------");
        System.out.println("~ Welcome to RevSpeed_0 ~");
        System.out.println("------------------------");
        while (true){
            ShowMainMenu(sc, userService);
            while(user != null){
                ShowUserMenu(sc, userService);
            }
        }
    }

    private static void ShowMainMenu(Scanner sc, UserServiceImpl userService) {
        System.out.print("Choose your option:\n\t1.Login\n\t2.Register\n\t3.Forget Password\n\t4.Exit\noption : ");

        int option = sc.nextInt();
        switch (option){
            case 1:
                System.out.println("Login is progressing...");
                System.out.println("========================");
                user = userService.login();
                break;
            case 2:
                System.out.println("Register is progressing...");
                System.out.println("========================");
                user = userService.register();
                user = userService.login();
                break;
            case 3:
                System.out.println("Forget password is loading...");
                break;
            case 4:
                System.out.println("Exiting... Thanks!!");
                System.exit(0);
                break;
            default:
                System.out.println("Please choose correct option...");
        }
    }

    private static void ShowUserMenu(Scanner sc, UserServiceImpl userService) {
        System.out.print("Choose your option:\n\t1.View Profile\n\t2.View Plans\n\t3.View Internet and BroadBand services\n\t4.Logout\noption : ");

        int option = sc.nextInt();
        switch (option){
            case 1:
                System.out.println("Profile");
                System.out.println("========");
                break;
            case 2:
                System.out.println("Plans");
                System.out.println("========");
                break;
            case 3:
                System.out.println("Internet and BroadBand");
                System.out.println("========");
                break;
            case 4:
                System.out.println("Logging Out..");
                user = null;
                System.out.println("Good bye!");
                break;
            default:
                System.out.println("Please choose correct option...");
        }
    }
}
