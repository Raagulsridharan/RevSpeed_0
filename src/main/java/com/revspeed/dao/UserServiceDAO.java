package com.revspeed.dao;

import com.revspeed.domain.User;

public interface UserServiceDAO {
    User save(User userObject);
    User getUser(String userName, String password);
}
