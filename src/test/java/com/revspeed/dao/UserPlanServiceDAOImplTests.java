package com.revspeed.dao;

import com.revspeed.dao.daoImp.UserPlanServiceDAOImpl;
import com.revspeed.domain.Plan;
import com.revspeed.domain.User;
import com.revspeed.domain.UserPlan;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static com.revspeed.utilities.DateUtilities.convertToSqlDate;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserPlanServiceDAOImplTests {
    @Test
    public void getUserPlans_should_return_userPlans() throws SQLException {
        //Arrange
        User user = new User("ravi","ravi","s",9876543210l,"ravi@gmail.com","7,Car street,Salem","password");
        user.setUserId(1);
        Plan plan = new Plan(1,"A",99.0,"Mobile","Free wifi",15);
        
        UserPlan userPlan = new UserPlan(user,plan);
        userPlan.setUserPlanId(1);
       
        List<UserPlan> expectedUserPlan = new ArrayList<>();
        expectedUserPlan.add(userPlan);

        Connection mockCon = mock(Connection.class);
        CallableStatement mockCallableStatement = mock(CallableStatement.class);
        ResultSet mockResultSet = mock(ResultSet.class);

        when(mockCon.prepareCall(anyString())).thenReturn(mockCallableStatement);
        when(mockCallableStatement.executeQuery()).thenReturn(mockResultSet);
        //mockCallableStatement.setInt(1,anyInt());
        when(mockResultSet.next()).thenReturn(true,false);
        System.out.println(""+userPlan.getStartDate());
        when(mockResultSet.getInt("userPlanId")).thenReturn(userPlan.getUserPlanId());
        when(mockResultSet.getInt("userId")).thenReturn(userPlan.getUserPlanId());
        when(mockResultSet.getInt("planId")).thenReturn(userPlan.getPlanId());
        when(mockResultSet.getString("planName")).thenReturn(userPlan.getPlanName());
        when(mockResultSet.getString("planStatus")).thenReturn(userPlan.getPlanStatus());
        when(mockResultSet.getString("paymentStatus")).thenReturn(userPlan.getPaymentStatus());
        //when(mockResultSet.getDate("startDate")).thenReturn(userPlan.getStartDate());
        when(mockResultSet.getDate("startDate")).thenReturn(convertToSqlDate(userPlan.getStartDate()));
        when(mockResultSet.getDate("endDate")).thenReturn(convertToSqlDate(userPlan.getEndDate()));

        when(mockResultSet.getString("remarks")).thenReturn(userPlan.getRemarks());

        
        //Act
        UserPlanServiceDAOImpl userPlanServiceDAOTest = new UserPlanServiceDAOImpl(mockCon);
        List<UserPlan> actualUserPlan = userPlanServiceDAOTest.getUserPlans(user.getUserId());
        
        //Assert
        Assertions.assertIterableEquals(expectedUserPlan,actualUserPlan);
        
        
    }
    
}
