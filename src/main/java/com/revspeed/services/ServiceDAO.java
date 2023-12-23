package com.revspeed.services;

import com.revspeed.user.User;

public interface ServiceDAO {
    void register(User userObject);
    void login();
}
