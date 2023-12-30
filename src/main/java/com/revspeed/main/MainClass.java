package com.revspeed.main;

import com.revspeed.domain.Plan;
import com.revspeed.domain.UserPayment;
import com.revspeed.domain.UserPlan;
import com.revspeed.services.UserPaymentService;
import com.revspeed.services.UserPlanService;
import com.revspeed.services.serviceImp.PlanServiceImpl;
import com.revspeed.services.serviceImp.UserPaymentServiceImpl;
import com.revspeed.services.serviceImp.UserPlanServiceImpl;
import com.revspeed.services.serviceImp.UserServiceImpl;
import com.revspeed.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class MainClass {
    private static final Scanner sc = new Scanner(System.in);
    private static User user = null; // session
    private static final UserServiceImpl userService = new UserServiceImpl();
    private static final PlanServiceImpl planService = new PlanServiceImpl();
    private static final UserPlanService userPlanService = new UserPlanServiceImpl();
    private static final UserPaymentService userPaymentService = new UserPaymentServiceImpl();
    private static final Logger logger = LoggerFactory.getLogger(MainClass.class);
    public static void main(String[] args) {
        logger.info("{} Application Started {}", "RevSpeed_0",MainClass.class.getSimpleName());
        System.out.println("|-------------------------|");
        System.out.println("|~ Welcome to RevSpeed_0 ~|");
        System.out.println("|-------------------------|");
        do {
            showMainMenu();
            while (user != null) {
                showUserMenu();
            }
        } while (true);
    }
    private static void showMainMenu() {
        System.out.print("Choose Your Option :\n\t1.Login\n\t2.Register\n\t3.Forget Password\n\t4.View Plans\n\t5.Exit\nOption : ");
        int option = sc.nextInt();
        switch (option){
            case 1:
                System.out.println("========================");
                System.out.println("Login is progressing...");
                System.out.println("========================");
                user = userService.login();
                break;
            case 2:
                System.out.println("==========================");
                System.out.println("Register is progressing...");
                System.out.println("==========================");
                user = userService.register();
                if(user==null){
                    System.out.println("Registration failed!!!\nTry again later...");
                    return;
                }
                user = userService.login();
                break;
            case 3:
                System.out.println("=================================");
                System.out.println("Forget password is progressing...");
                System.out.println("=================================");
                showMainMenu();
                break;
            case 4:
                System.out.println("==========");
                System.out.println("View Plans");
                System.out.println("==========");
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
                System.out.println("==========");
                System.out.println("My Profile");
                System.out.println("==========");
                System.out.println(user.toString());
                showUserProfileMenu();
                break;
            case 2:
                System.out.println("=======================");
                System.out.println("Manage My Subscriptions");
                System.out.println("=======================");
                userPlanService.showUserPlans(userPlanService.getUserPlans(user.getUserId()));
                showUserPlanMenu();
                break;
            case 3:
                System.out.println("==============");
                System.out.println("View All Plans");
                System.out.println("==============");
                planService.showPlans(planService.getAllPlans());
                break;
            case 4:
                System.out.println("Logging Out...");
                user = null;
                System.out.println("!!!Good bye!!!");
                break;
            default:
                System.out.println("Please choose correct option!!!");
        }
    }
    private static void showUserProfileMenu() {
        System.out.println("Choose Options :\n\t1.Update MobileNumber\n\t2.Change Password\n\t3.Update Email\n\t4.Back\nOption : ");
        int option = sc.nextInt();
        switch (option){
            case 1:
                user = userService.updateMobileNumber(user);
                break;
            case 2:
                user = userService.updatePassword(user);
                break;
            case 3:
                user = userService.updateEmail(user);
                break;
            case 4:
                showUserMenu();
                break;
            default:
                System.out.println("Please choose correct option!!!");
                showUserProfileMenu();
        }
    }
    private static void showUserPlanMenu() {
        System.out.println("Choose Options :\n\t1.Add Subscription\n\t2.Change Subscription\n\t3.Cancel Subscription\n\t4.Back\nOption : ");
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
                System.out.println("Please choose correct option!!!");
                showUserPlanMenu();
        }
    }
    private static void cancelSubscriptionMenu() {
        System.out.println("Currently available user plans are...");
        List<UserPlan> userPlans = userPlanService.getUserPlans(user.getUserId());
        userPlanService.showUserPlans(userPlans);

        System.out.print("Please enter the existing plan Id to Cancel : ");
        int existingPlanId = sc.nextInt();

        Date currentDate = new Date();
        UserPlan existingUserPlan = userPlanService.findUserPlanById(userPlans,existingPlanId);
        if(existingUserPlan != null) {
            if(existingUserPlan.getPlanStatus().equalsIgnoreCase("CANCELLED")) {
                System.out.println("The selected plan is already cancelled!!!");
            } else if((existingUserPlan.getEndDate().compareTo(currentDate) > 0)){
                existingUserPlan.cancelPlan();
                userPlanService.saveUserPlan(existingUserPlan);
                System.out.println("Subscription cancelled successfully...");
                userPlanService.showUserPlans(userPlanService.getUserPlans(user.getUserId()));
                showUserPlanMenu();
            }
        }
        else {
            System.out.println("Please select the valid plan to cancel!");
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
                System.out.print("Selected plans are same.! Please select different plan.!! ");
                updateSubscriptionMenu();
            } else {
                Plan plan = planService.findPlanById(plans, newPlanId);
                existingUserPlan.changePlan(plan);
                userPlanService.saveUserPlan(existingUserPlan);
                System.out.println("Subscription updated successfully...");
                addPayment(existingUserPlan, plan);
                userPlanService.showUserPlans(userPlanService.getUserPlans(user.getUserId()));
                showUserPlanMenu();
            }
        }
        else {
            System.out.println("Please select the Active plan to update");
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
                System.out.println("Invalid plan Selection!!!");
            }
            else{
                UserPlan userPlan = new UserPlan(user,plan);
                userPlanService.saveUserPlan(userPlan);
                System.out.println("Subscription added successfully...\nPlease continue to Payment...");
                addPayment(userPlan, plan);
                userPlanService.showUserPlans(userPlanService.getUserPlans(user.getUserId()));
            }
        }
        showUserPlanMenu();
    }
    private static void addPayment(UserPlan userPlan, Plan plan) {
        System.out.print("Please enter your BankName : ");
        UserPayment userPayment = new UserPayment(user.getUserId(), userPlan.getPlanId(), userPlan.getUserPlanId(),"due", plan.getCost(),"NetBanking");
        userPayment.setBankName(sc.next());
        System.out.print("Please enter the customer Id : ");
        userPayment.setCustomerId(sc.nextLong());
        userPayment.setTransactionStatus("Success");
        userPaymentService.save(userPayment);
        userPlan.setPaymentStatus("Paid");
        userPlanService.saveUserPlan(userPlan);
    }
}
