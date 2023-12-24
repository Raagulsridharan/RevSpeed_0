package com.revspeed.main;

import com.revspeed.services.PlanService;
import com.revspeed.services.UserPlanService;
import com.revspeed.services.serviceImp.PlanServiceImpl;
import com.revspeed.services.serviceImp.UserPlanServiceImpl;
import com.revspeed.services.serviceImp.UserServiceImpl;
import com.revspeed.domain.User;

import java.util.Scanner;

public class MainClass {

    private static User user = null; // session
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        UserServiceImpl userService = new UserServiceImpl();
        PlanServiceImpl planService = new PlanServiceImpl();
        UserPlanService userPlanService = new UserPlanServiceImpl();
        System.out.println("------------------------");
        System.out.println("~ Welcome to RevSpeed_0 ~");
        System.out.println("------------------------");
        while (true){
            ShowMainMenu(sc, userService, planService);
            while(user != null){
                ShowUserMenu(sc, userService,userPlanService);
            }
        }
    }

    private static void ShowMainMenu(Scanner sc, UserServiceImpl userService, PlanServiceImpl planService) {
        System.out.print("Choose your option:\n\t1.Login\n\t2.Register\n\t3.Forget Password\n\t4.View Plans\n\t5.Exit\noption : ");

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
                System.out.println("View Plans");
                planService.showPlans(planService.getAllPlans());
                break;
            case 5:
                System.out.println("Exiting... Thanks!!");
                System.exit(0);
                break;
            default:
                System.out.println("Please choose correct option...");
        }
    }

    private static void ShowUserMenu(Scanner sc, UserServiceImpl userService,UserPlanService userPlanService) {
        System.out.print("Choose your option:\n\t1.View Profile\n\t2.View MySubscription\n\t3.View Plans\n\t4.Logout\nOption : ");

        int option = sc.nextInt();
        switch (option){
            case 1:
                System.out.println("Profile");
                System.out.println("========");
                System.out.println(user.toString());
                break;
            case 2:
                System.out.println("My Plans");
                System.out.println("========");
                userPlanService.showUserPlans(userPlanService.getUserPlans(user.getUserId()));
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
