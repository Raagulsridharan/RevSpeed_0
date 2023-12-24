package com.revspeed.services;

import com.revspeed.domain.UserPlan;

import java.util.List;

public interface UserPlanService {
    List<UserPlan> getUserPlans(int userId);
    void showUserPlans(List<UserPlan> userPlans);
}
