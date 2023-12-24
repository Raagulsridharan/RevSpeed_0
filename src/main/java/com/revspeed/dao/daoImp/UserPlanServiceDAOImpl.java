package com.revspeed.dao.daoImp;

import com.revspeed.dao.UserPlanServiceDAO;
import com.revspeed.domain.Plan;
import com.revspeed.domain.UserPlan;
import com.revspeed.jdbc.GettingDBConnection;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserPlanServiceDAOImpl implements UserPlanServiceDAO {
    private static Connection con = null;
    public UserPlanServiceDAOImpl() {
        con = GettingDBConnection.createInstance().getConnect();
    }
    @Override
    public List<UserPlan> getUserPlans(int userId) {
        List<UserPlan> userPlans = new ArrayList<>();
        try(CallableStatement callableStatement = con.prepareCall("call revspeed_0.getUserPlans(?)")) {
            callableStatement.setInt(1,userId);
            ResultSet resultSet = callableStatement.executeQuery();
            while(resultSet.next()) {
                userPlans.add(BuildUserPlan(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return userPlans;
    }

    private UserPlan BuildUserPlan(ResultSet resultSet) throws SQLException {
        UserPlan userPlan = new UserPlan();
        userPlan.setUserPlanId(resultSet.getInt("userPlanId"));
        userPlan.setUserid(resultSet.getInt("userId"));
        userPlan.setPlanId(resultSet.getInt("planId"));
        userPlan.setPlanName(resultSet.getString("planName"));
        userPlan.setPlanStatus(resultSet.getString("planStatus"));
        userPlan.setPaymentStatus(resultSet.getString("paymentStatus"));
        userPlan.setStartDate(resultSet.getDate("startDate"));
        userPlan.setEndDate(resultSet.getDate("endDate"));
        userPlan.setRemarks(resultSet.getString("remarks"));
        return  userPlan;
    }
}
