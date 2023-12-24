package com.revspeed.services.serviceImp;

import com.revspeed.dao.PlanDAO;
import com.revspeed.dao.daoImp.PlanDAOImpl;
import com.revspeed.domain.Plan;
import com.revspeed.services.PlanService;

import java.util.List;

public class PlanServiceImpl implements PlanService {
    @Override
    public List<Plan> getAllPlans() {
        PlanDAO planDAO = new PlanDAOImpl();
        return planDAO.getAllPlans();
    }

    @Override
    public void showPlans(List<Plan> plans) {
        System.out.println("planId \t planName \t\t cost \t planType \t validityInDays \t planDescription ");
        for (Plan plan : plans) {
            System.out.println(plan.toString());
        }
    }
}
