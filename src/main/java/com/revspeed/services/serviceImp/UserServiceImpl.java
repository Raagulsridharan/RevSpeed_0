package com.revspeed.services.serviceImp;

import com.revspeed.dao.daoImp.UserServiceDAOImpl;
import com.revspeed.services.UserService;
import com.revspeed.user.User;
import java.util.Scanner;
import java.io.Console;

public class UserServiceImpl implements UserService {
    private static final Scanner sc = new Scanner(System.in);
    Console console = System.console();
    @Override
    public User register() {
        try {
            User userObject = new User();

            System.out.print("Enter your Username : ");
            userObject.setUserName(sc.next());
            System.out.print("Enter your Firstname : ");
            userObject.setFirstName(sc.next());
            System.out.print("Enter your Lastname : ");
            userObject.setLastname(sc.next());
            System.out.print("Enter your Mobile number : ");
            long mobileNumber = sc.nextLong();
            sc.nextLine();
            userObject.setMobileNumber(mobileNumber);
            System.out.print("Enter your Email Id : ");
            userObject.setEmailId(sc.nextLine());
            System.out.print("Enter your Address : ");
            userObject.setAddress(sc.nextLine());
            userObject.setPassword(checkPassword(userObject));

            UserServiceDAOImpl dao = new UserServiceDAOImpl();
            userObject = dao.save(userObject);
            if(userObject != null){
                System.out.println("Registered successfully...");
            } else {
                System.out.println("Registration failed!!");
            }
            return userObject;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private String checkPassword(User userObject) {
        String password;
        boolean isSame;
        do {
            password = promptPassword("Enter your password: ");
            String retypePassword = promptPassword("retype password: ");
            isSame = userObject.isSame(password, retypePassword);
            if(!isSame){
                System.out.println("retype password mismatched !!");
            }
        } while (!isSame);
        return password;
    }

    private String promptPassword(String prompt) {
        if (console != null) {
            char[] passwordArray = console.readPassword(prompt);
            return new String(passwordArray);
        } else {
            // Console is not available, fallback to Scanner
            System.out.print(prompt);
            return sc.next();
        }
    }

    @Override
    public User login() {
        System.out.println("For login....");
        System.out.print("Enter Your UserName : ");
        String userName = sc.next();
        String password = promptPassword("Enter your password:");
        UserServiceDAOImpl dao = new UserServiceDAOImpl();
        User user = dao.getUser(userName, password);
        if(user != null){
            System.out.println("Logged in successfully...");
        } else {
            System.out.println("Invalid username or password...");
        }
        return user;
    }
}
