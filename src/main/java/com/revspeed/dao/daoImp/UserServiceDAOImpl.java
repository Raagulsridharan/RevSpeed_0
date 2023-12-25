package com.revspeed.dao.daoImp;

import com.revspeed.jdbc.GettingDBConnection;
import com.revspeed.dao.UserServiceDAO;
import com.revspeed.domain.User;

import java.sql.*;

public class UserServiceDAOImpl implements UserServiceDAO {
    private static Connection con = null;
    public UserServiceDAOImpl() {
        con = GettingDBConnection.createInstance().getConnect();
    }
    public User save(User userObject) {
        if(userObject.getUserId()>0){
            return update(userObject);
        }
        return insert(userObject);
    }
    private User insert(User userObject) {
        try(CallableStatement callableStatement = con.prepareCall("call revspeed_0.insertUserProfile(?,?,?,?,?,?,?)")) {
            callableStatement.setString(1, userObject.getUserName());
            callableStatement.setString(2, userObject.getFirstName());
            callableStatement.setString(3, userObject.getLastname());
            callableStatement.setLong(4, userObject.getMobileNumber());
            callableStatement.setString(5, userObject.getEmailId());
            callableStatement.setString(6, userObject.getAddress());
            callableStatement.setString(7, userObject.getPassword());
            callableStatement.execute();
            ResultSet resultSet = callableStatement.getResultSet();
            if (resultSet.next()) {
                int insertedId = resultSet.getInt("insertedId");
                userObject.setUserId(insertedId);
            } else {
                System.out.println("Error retrieving inserted ID.");
            }

            return userObject;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private User update(User userObject) {
        try(CallableStatement callableStatement = con.prepareCall("call revspeed_0.updateUserProfile(?,?,?,?)")) {
            callableStatement.setInt(1, userObject.getUserId());
            callableStatement.setLong(2, userObject.getMobileNumber());
            callableStatement.setString(3, userObject.getEmailId());
            callableStatement.setString(4, userObject.getPassword());
            callableStatement.execute();

            return userObject;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public User getUser(String userName, String password){
        try(CallableStatement callableStatement = con.prepareCall("call revspeed_0.getUserNamePswd(?,?)")) {
            callableStatement.setString(1,userName);
            callableStatement.setString(2,password);
            ResultSet resultSet = callableStatement.executeQuery();
            if (resultSet.next()){
                return BuildUser(resultSet);
            }
            else{
                return  null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private User BuildUser(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setUserId(resultSet.getInt("userId"));
        user.setUserName(resultSet.getString("userName"));
        user.setFirstName(resultSet.getString("firstName"));
        user.setLastname(resultSet.getString("lastName"));
        user.setEmailId(resultSet.getString("emailId"));
        user.setMobileNumber(resultSet.getLong("mobileNumber"));
        user.setPassword(resultSet.getString("password"));
        user.setAddress(resultSet.getString("address"));
        user.setRemarks(resultSet.getString("remarks"));
        return  user;
    }
}
