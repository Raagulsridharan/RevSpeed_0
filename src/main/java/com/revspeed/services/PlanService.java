package com.revspeed.services;

import com.revspeed.domain.Plan;

import java.util.List;

public interface PlanService {
    List<Plan> getAllPlans();
    void showPlans(List<Plan> plans);
}
