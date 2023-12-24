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
        String insertQuery = "insert into user_profile(userName,firstName,lastName,mobileNumber,emailId,address,password) values(?,?,?,?,?,?,?)";
        try(PreparedStatement preparedStatement = con.prepareStatement(insertQuery)) {
            preparedStatement.setString(1, userObject.getUserName());
            preparedStatement.setString(2, userObject.getFirstName());
            preparedStatement.setString(3, userObject.getLastname());
            preparedStatement.setLong(4, userObject.getMobileNumber());
            preparedStatement.setString(5, userObject.getEmailId());
            preparedStatement.setString(6, userObject.getAddress());
            preparedStatement.setString(7, userObject.getPassword());
            preparedStatement.execute();

            return getUser(userObject.getUserName(),userObject.getPassword());
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
