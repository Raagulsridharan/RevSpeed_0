package com.revspeed.services.serviceImp;

import com.revspeed.dao.PlanDAO;
import com.revspeed.dao.daoImp.PlanDAOImpl;
import com.revspeed.domain.Plan;
import com.revspeed.services.PlanService;

import java.util.List;

public class PlanServiceImpl implements PlanService {
    private final PlanDAO planDAO;
    public PlanServiceImpl(PlanDAO planDAO) {
        this.planDAO = planDAO;
    }

    @Override
    public List<Plan> getAllPlans() {
        return planDAO.getAllPlans();
    }

    @Override
    public void showPlans(List<Plan> plans) {
        //System.out.printf("%-8d %-20s %-10.2f %-15s %-15d %-30s","PlanId", "PlanName", "Cost", "PlanType", "ValidityInDays", "PlanDescription ");
//        System.out.printf("%-8s %-20s %-10s %-15s %-15s %-80s%n",
//                "PlanId", "PlanName", "Cost", "PlanType", "ValidityInDays", "PlanDescription");
//        System.out.println("PlanId \t PlanName \t\t Cost \t PlanType \t ValidityInDays \t PlanDescription ");
        System.out.println("|-------|-------------------|-------|-----------|----------------|----------------------------------------------------------------|");
        System.out.println("|PlanId | PlanName          | Cost  | PlanType  | ValidityInDays | PlanDescription");
        System.out.println("|-------|-------------------|-------|-----------|----------------|----------------------------------------------------------------|");
        for (Plan plan : plans) {
            System.out.println(plan.toString());
        }
    }

    public Plan findPlanById(List<Plan> plans, int planId) {
        for (Plan plan : plans) {
            if (plan.getPlanId() == planId) {
                return plan;
            }
        }
        return null;
    }
}
