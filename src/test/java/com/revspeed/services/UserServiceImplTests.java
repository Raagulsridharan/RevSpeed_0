package com.revspeed.services;

import com.revspeed.dao.UserServiceDAO;
import com.revspeed.dao.daoImp.UserServiceDAOImpl;
import com.revspeed.domain.User;
import com.revspeed.services.serviceImp.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class UserServiceImplTests {
    @Test
    public void login_should_return_user(){
        //Arrange
        User user = new User("ravi","ravi","s",9876543210l,"ravi@gmail.com","7,Car street,Salem","password");
        user.setUserId(1);
        String simulatedInput = "ravi password";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        Scanner mockScanner = new Scanner(System.in);

        UserServiceDAO mockUserServiceDAOImpl = mock(UserServiceDAOImpl.class);
        when(mockUserServiceDAOImpl.getUser(anyString(),anyString())).thenReturn(user);

        //Act
        UserServiceImpl userService = new UserServiceImpl(mockScanner,mockUserServiceDAOImpl);
        User actualUser = userService.login();

        //Assert
        Assertions.assertEquals(user.getUserName(),actualUser.getUserName());
        System.setIn(System.in);
    }
    @Test
    public void login_should_not_return_user(){
        //Arrange
        User user = new User("ravi","ravi","s",9876543210l,"ravi@gmail.com","7,Car street,Salem","password");
        user.setUserId(1);
        String simulatedInput = "ravi password";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        Scanner mockScanner = new Scanner(System.in);

        UserServiceDAO mockUserServiceDAOImpl = mock(UserServiceDAOImpl.class);
        when(mockUserServiceDAOImpl.getUser(anyString(),anyString())).thenReturn(null);

        //Act
        UserServiceImpl userService = new UserServiceImpl(mockScanner,mockUserServiceDAOImpl);
        User actualUser = userService.login();

        //Assert
        assertThat(actualUser).isNull();
        System.setIn(System.in);
    }
}
