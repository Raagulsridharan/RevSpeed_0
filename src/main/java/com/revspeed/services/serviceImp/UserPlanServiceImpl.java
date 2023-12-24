package com.revspeed.services.serviceImp;

import com.revspeed.dao.UserPlanServiceDAO;
import com.revspeed.dao.daoImp.UserPlanServiceDAOImpl;
import com.revspeed.domain.Plan;
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
        System.out.println("PlanName \t\t PlanStatus \t PaymentStatus \t StartDate \t Enddate ");
        for (UserPlan userPlan : userPlans) {
            System.out.println(userPlans.toString());
        }
    }

    @Override
    public UserPlan adduserPlan(UserPlan userPlan) {
       UserPlanServiceDAO userPlanServiceDAO = new UserPlanServiceDAOImpl();
       return userPlanServiceDAO.save(userPlan);
    }

}
