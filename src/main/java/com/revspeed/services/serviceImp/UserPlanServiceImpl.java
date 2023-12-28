package com.revspeed.services.serviceImp;

import com.revspeed.dao.UserPlanServiceDAO;
import com.revspeed.dao.daoImp.UserPlanServiceDAOImpl;
import com.revspeed.domain.UserPlan;
import com.revspeed.services.UserPlanService;

import java.util.List;

public class UserPlanServiceImpl implements UserPlanService {

    @Override
    public List<UserPlan> getUserPlans(int userId) {
        UserPlanServiceDAO userPlanServiceDAO = new UserPlanServiceDAOImpl();
        return userPlanServiceDAO.getUserPlans(userId);
    }
    public void showUserPlans(List<UserPlan> userPlans) {
        //System.out.println("PlanId \tPlanName \t\t PlanStatus \t PaymentStatus \t StartDate \t Enddate ");
        System.out.printf("%-8s %-20s %-15s %-15s %-12s %-12s%n",
                "PlanId", "PlanName", "PlanStatus", "PaymentStatus", "StartDate", "EndDate");
        for (UserPlan userPlan : userPlans) {
            System.out.println(userPlan.toString());
        }
    }

    @Override
    public UserPlan saveUserPlan(UserPlan userPlan) {
       UserPlanServiceDAO userPlanServiceDAO = new UserPlanServiceDAOImpl();
       return userPlanServiceDAO.save(userPlan);
    }

    @Override
    public UserPlan findUserPlanById(List<UserPlan> userPlans, int planId) {
        for (UserPlan userPlan : userPlans) {
            if (userPlan.getPlanId() == planId) {
                return userPlan;
            }
        }
        return null;
    }

}
