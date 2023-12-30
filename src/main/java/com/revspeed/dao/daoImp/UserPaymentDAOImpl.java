package com.revspeed.dao.daoImp;

import com.revspeed.dao.UserPaymentDAO;
import com.revspeed.domain.UserPayment;
import com.revspeed.domain.UserPlan;
import com.revspeed.jdbc.DBConnectionProperties;
import com.revspeed.jdbc.GettingDBConnection;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class UserPaymentDAOImpl implements UserPaymentDAO {
    private static Connection con = null;
    public UserPaymentDAOImpl() {
        con = GettingDBConnection.createInstance().getConnect();
//        con = DBConnectionProperties.getConnect();
    }
    public UserPayment save(UserPayment userPayment) {
        if(userPayment.getPaymentId() == 0){
            insert(userPayment);
        }
        return userPayment;
    }
    private static void insert(UserPayment userPayment) {
        try(CallableStatement callableStatement = con.prepareCall("call revspeed_0.insertUserPayment( ?, ?, ?, ?, ?, ?,?,?)")) {
            callableStatement.setInt(1, userPayment.getUserId());
            callableStatement.setInt(2, userPayment.getPlanId());
            callableStatement.setInt(3, userPayment.getUserPlanId());
            callableStatement.setString(4, userPayment.getTransactionStatus());
            callableStatement.setDouble(5, userPayment.getChargeAmount());
            callableStatement.setString(6,userPayment.getPaymethod());
            callableStatement.setString(7,userPayment.getBankName());
            callableStatement.setLong(8, userPayment.getCustomerId());
            callableStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
