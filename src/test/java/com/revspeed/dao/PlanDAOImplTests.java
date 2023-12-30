package com.revspeed.dao;

import com.revspeed.dao.daoImp.PlanDAOImpl;
import com.revspeed.domain.Plan;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PlanDAOImplTests {
    @Test
    public void getAllPlans_should_return_allPlans() throws SQLException {
        //Arrange
        Plan expectedPlanA = new Plan(1,"A",99.0,"Mobile","Free wifi",15);
        List<Plan> expectedPlans = new ArrayList<>();
        expectedPlans.add(expectedPlanA);

        Connection mockCon = mock(Connection.class);
        CallableStatement mockCallableStatement = mock(CallableStatement.class);
        ResultSet mockResultSet = mock(ResultSet.class);

        when(mockCon.prepareCall(anyString())).thenReturn(mockCallableStatement);
        when(mockCallableStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true,false);

        when(mockResultSet.getInt("planId")).thenReturn(expectedPlanA.getPlanId());
        when(mockResultSet.getString("planName")).thenReturn(expectedPlanA.getPlanName());
        when(mockResultSet.getDouble("cost")).thenReturn(expectedPlanA.getCost());
        when(mockResultSet.getString("planType")).thenReturn(expectedPlanA.getPlanType());
        when(mockResultSet.getString("planDescription")).thenReturn(expectedPlanA.getPlanDescription());
        when(mockResultSet.getInt("validityInDays")).thenReturn(expectedPlanA.getValidityInDays());

        //Act
        PlanDAOImpl planDAO = new PlanDAOImpl(mockCon);
        List<Plan> actualPlans = planDAO.getAllPlans();

        //Assert
        //Assertions.assertIterableEquals(expectedPlans,actualPlans);
        //assertThat(actualPlans).containsExactlyElementsOf(expectedPlans);
        Assertions.assertIterableEquals(actualPlans,expectedPlans);
    }
}
