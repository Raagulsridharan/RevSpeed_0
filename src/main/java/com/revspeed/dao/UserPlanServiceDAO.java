package com.revspeed.dao;

import com.revspeed.domain.Plan;
import com.revspeed.domain.UserPlan;

import java.util.List;

public interface UserPlanServiceDAO {
    List<UserPlan> getUserPlans(int userId);
}
