package com.revspeed.dao.daoImp;

import com.revspeed.dao.PlanDAO;
import com.revspeed.domain.Plan;
import com.revspeed.domain.User;
import com.revspeed.jdbc.GettingDBConnection;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlanDAOImpl implements PlanDAO {
    private static Connection con = null;
    public PlanDAOImpl() {
        con = GettingDBConnection.createInstance().getConnect();
    }
    @Override
    public List<Plan> getAllPlans() {
        List<Plan> plans = new ArrayList<>();
        try(CallableStatement callableStatement = con.prepareCall("call revspeed_0.getAllPlans()")) {
            ResultSet resultSet = callableStatement.executeQuery();
            while(resultSet.next()) {
                plans.add(BuildPlan(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return plans;
    }
    private Plan BuildPlan(ResultSet resultSet) throws SQLException {
        Plan plan = new Plan();
        plan.setPlanId(resultSet.getInt("planId"));
        plan.setPlanName(resultSet.getString("planName"));
        plan.setCost(resultSet.getDouble("cost"));
        plan.setPlanType(resultSet.getString("planType"));
        plan.setPlanDescription(resultSet.getString("planDescription"));
        plan.setValidityInDays(resultSet.getInt("validityInDays"));
        return  plan;
    }
}
