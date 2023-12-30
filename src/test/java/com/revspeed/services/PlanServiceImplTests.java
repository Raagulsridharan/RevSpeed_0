package com.revspeed.services;

import com.revspeed.domain.Plan;
import com.revspeed.services.serviceImp.PlanServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PlanServiceImplTests {
    @Test
    public void getAllPlans_should_return_allPlans(){
        //Arrange
        PlanServiceImpl planService = new PlanServiceImpl();
        Plan expectedPlanA = new Plan(1,"A",99.0,"Mobile","Free wifi",15);
        Plan expectedPlanB = new Plan(2,"B",199.0,"Broadband","Free wifi",15);
        List<Plan> expectedPlans = new ArrayList<>();
        expectedPlans.add(expectedPlanA);
        expectedPlans.add(expectedPlanB);

        //Act
        List<Plan> actualPlans = planService.getAllPlans();

        //Assert
        Assertions.assertEquals(expectedPlans,actualPlans);
    }
}
