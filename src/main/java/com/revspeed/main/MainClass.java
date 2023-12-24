package com.revspeed.main;

import com.revspeed.domain.Plan;
import com.revspeed.domain.UserPlan;
import com.revspeed.services.PlanService;
import com.revspeed.services.UserPlanService;
import com.revspeed.services.serviceImp.PlanServiceImpl;
import com.revspeed.services.serviceImp.UserPlanServiceImpl;
import com.revspeed.services.serviceImp.UserServiceImpl;
import com.revspeed.domain.User;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class MainClass {
    private static final Scanner sc = new Scanner(System.in);

    private static User user = null; // session
    private static UserServiceImpl userService = new UserServiceImpl();
    private static PlanServiceImpl planService = new PlanServiceImpl();
    private static UserPlanService userPlanService = new UserPlanServiceImpl();
    public static void main(String[] args) {

        System.out.println("------------------------");
        System.out.println("~ Welcome to RevSpeed_0 ~");
        System.out.println("------------------------");
        while (true){
            showMainMenu();
            while(user != null){
                showUserMenu();
            }
        }
    }

    private static void showMainMenu() {
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

    private static void showUserMenu() {
        System.out.print("Choose your option:\n\t1.View Profile\n\t2.Manage Subscriptions\n\t3.View Plans\n\t4.Logout\nOption : ");

        int option = sc.nextInt();
        switch (option){
            case 1:
                System.out.println("Profile");
                System.out.println("========");
                System.out.println(user.toString());
                break;
            case 2:
                System.out.println("Manage Subscriptions");
                System.out.println("========");
                userPlanService.showUserPlans(userPlanService.getUserPlans(user.getUserId()));
                showUserPlanMenu();
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
    private static void showUserPlanMenu() {
        System.out.println("Choose options:\n\t1.Add Subscription\n\t2.Change Subscription\n\t3.Cancel Subscription\n\t4.Back");
        int option = sc.nextInt();
        switch (option){
            case 1:
                addSubscriptionMenu();
                break;
            case 2:
                updateSubscriptionMenu();
                break;
            case 3:
                cancelSubscriptionMenu();
                break;
            case 4:
                showUserMenu();
                break;
            default:
                System.out.println("Please enter the correct option....");
                showUserPlanMenu();
        }
    }

    private static void cancelSubscriptionMenu() {
        System.out.println("Currently available user plans are...");
        List<UserPlan> userPlans = userPlanService.getUserPlans(user.getUserId());
        userPlanService.showUserPlans(userPlans);

        System.out.print("Please enter the existing plan Id to Cancel: ");
        int existingPlanId = sc.nextInt();

        Date currentDate = new Date();
        UserPlan existingUserPlan = userPlanService.findUserPlanById(userPlans,existingPlanId);
        if(existingUserPlan != null) {
            if(existingUserPlan.getPlanStatus().equalsIgnoreCase("CANCELLED")) {
                System.out.println("The selected plan is already cancelled.");
            } else if((existingUserPlan.getEndDate().compareTo(currentDate) > 0)){
                existingUserPlan.cancelPlan();
                userPlanService.saveUserPlan(existingUserPlan);
                System.out.println("Subscription cancelled successfully...");
                userPlanService.showUserPlans(userPlanService.getUserPlans(user.getUserId()));
                showUserPlanMenu();
            }
        }
        else {
            System.out.println("please select the valid plan to cancel");
            cancelSubscriptionMenu();
        }
    }

    private static void updateSubscriptionMenu() {
        System.out.println("Currently available user plans are...");
        List<UserPlan> userPlans = userPlanService.getUserPlans(user.getUserId());
        userPlanService.showUserPlans(userPlans);

        System.out.print("Please enter the existing plan Id : ");
        int existingPlanId = sc.nextInt();

        Date currentDate = new Date();
        UserPlan existingUserPlan = userPlanService.findUserPlanById(userPlans,existingPlanId);
        if(existingUserPlan != null && (existingUserPlan.getEndDate().compareTo(currentDate) > 0)){
            System.out.println("Currently available user plans are...");
            List<Plan> plans = planService.getAllPlans();
            planService.showPlans(plans);

            System.out.print("Please enter the new plan Id : ");
            int newPlanId = sc.nextInt();

            if(existingPlanId == newPlanId){
                System.out.print("selected plans are same. please select different plan. ");
                updateSubscriptionMenu();
            } else {
                Plan plan = planService.findPlanById(plans, newPlanId);
                existingUserPlan.changePlan(plan);
                userPlanService.saveUserPlan(existingUserPlan);
                System.out.println("Subscription updated successfully...");
                userPlanService.showUserPlans(userPlanService.getUserPlans(user.getUserId()));
                showUserPlanMenu();
            }
        }
        else {
            System.out.println("please select the Active plan to update");
            updateSubscriptionMenu();
        }
    }

    private static void addSubscriptionMenu() {
        System.out.println("Currently available plans are...");
        List<Plan> plans = planService.getAllPlans();
        planService.showPlans(plans);
        System.out.print("Please enter the plan Id : ");
        int planId = sc.nextInt();
        UserPlan existingUserPlan = userPlanService.findUserPlanById(userPlanService.getUserPlans(user.getUserId()),planId);
        Date currentDate = new Date();
        if(existingUserPlan != null && (existingUserPlan.getEndDate().compareTo(currentDate) > 0)){
            System.out.println("Subscription Already exists!! which ends only on " + existingUserPlan.getEndDate());
            userPlanService.showUserPlans(userPlanService.getUserPlans(user.getUserId()));
        }
        else {
            Plan plan = planService.findPlanById(plans, planId);
            if(plan==null){
                System.out.println("Invalid plan Selection...");
            }
            else{
                UserPlan userPlan = new UserPlan(user,plan);
                userPlanService.saveUserPlan(userPlan);
                System.out.println("Subscription added successfully...");
                userPlanService.showUserPlans(userPlanService.getUserPlans(user.getUserId()));
            }
        }

        showUserPlanMenu();
    }

}
