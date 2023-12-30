package com.revspeed.dao.daoImp;

import com.revspeed.dao.UserPlanServiceDAO;
import com.revspeed.domain.UserPlan;
import com.revspeed.jdbc.DBConnectionProperties;
import com.revspeed.jdbc.GettingDBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserPlanServiceDAOImpl implements UserPlanServiceDAO {
    private static Connection con = null;
    public UserPlanServiceDAOImpl() {
        con = GettingDBConnection.createInstance().getConnect();
//        con = DBConnectionProperties.getConnect();
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

    @Override
    public UserPlan save(UserPlan userPlan) {
        if(userPlan.getUserPlanId() > 0){
            userPlan = update(userPlan);
        } else {
            userPlan = insert(userPlan);
        }
        return userPlan;
    }

    private static UserPlan update(UserPlan userPlan) {
        try(CallableStatement callableStatement = con.prepareCall("call revspeed_0.updateUserPlan(?, ?, ?, ?, ?, ?, ?, ?)")) {
            callableStatement.setInt(1, userPlan.getUserPlanId());
            callableStatement.setInt(2, userPlan.getUserid());
            callableStatement.setInt(3, userPlan.getPlanId());
            callableStatement.setString(4, userPlan.getPlanStatus());
            callableStatement.setString(5, userPlan.getPaymentStatus());
            callableStatement.setDate(6, convertToSqlDate(userPlan.getStartDate()));
            callableStatement.setDate(7, convertToSqlDate(userPlan.getEndDate()));
            callableStatement.setString(8, userPlan.getRemarks());
            callableStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return userPlan;
    }

    private static UserPlan insert(UserPlan userPlan) {
        try(CallableStatement callableStatement = con.prepareCall("call revspeed_0.insertUserPlan(?, ?, ?, ?, ?, ?, ?)")) {
            callableStatement.setInt(1, userPlan.getUserid());
            callableStatement.setInt(2, userPlan.getPlanId());
            callableStatement.setString(3, userPlan.getPlanStatus());
            callableStatement.setString(4, userPlan.getPaymentStatus());
            callableStatement.setDate(5, convertToSqlDate(userPlan.getStartDate()));
            callableStatement.setDate(6, convertToSqlDate(userPlan.getEndDate()));
            callableStatement.setString(7, userPlan.getRemarks());
            callableStatement.execute();
            ResultSet resultSet = callableStatement.getResultSet();
            if (resultSet.next()) {
                int insertedId = resultSet.getInt("insertedId");
                userPlan.setUserPlanId(insertedId);
            } else {
                System.out.println("Error retrieving inserted ID.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return userPlan;
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

    private static java.sql.Date convertToSqlDate(java.util.Date utilDate) {
        // Convert java.util.Date to java.sql.Date
        return new java.sql.Date(utilDate.getTime());
    }
}
