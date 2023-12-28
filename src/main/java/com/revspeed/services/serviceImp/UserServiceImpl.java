package com.revspeed.services.serviceImp;

import com.revspeed.dao.UserServiceDAO;
import com.revspeed.dao.daoImp.UserServiceDAOImpl;
import com.revspeed.main.MainClass;
import com.revspeed.services.UserService;
import com.revspeed.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.ls.LSOutput;

import java.util.Scanner;
import java.io.Console;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class UserServiceImpl implements UserService {
    private static final Scanner sc = new Scanner(System.in);
    Console console = System.console();
    UserServiceDAO userServiceDAO = new UserServiceDAOImpl();
    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
                    "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    private static final Pattern pattern = Pattern.compile(EMAIL_PATTERN);
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    public static boolean isValidEmail(String email) {
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    @Override
    public User register() {
        try {
            logger.info("Registration Started {}", UserServiceImpl.class.getSimpleName());

            User userObject = new User();

            userObject.setUserName(checkUserName());
            System.out.print("Enter your Firstname : ");
            userObject.setFirstName(sc.next());
            System.out.print("Enter your Lastname : ");
            userObject.setLastname(sc.next());
            userObject.setMobileNumber(checkMobileNumber());
            userObject.setEmailId(checkEmailId());
            sc.nextLine();
            System.out.print("Enter your Address : ");
            userObject.setAddress(sc.nextLine());
            userObject.setPassword(checkPassword(userObject));

            UserServiceDAOImpl dao = new UserServiceDAOImpl();
            userObject = dao.save(userObject);

            if(userObject != null){
                System.out.println("Registered successfully...");
            } else {
                System.out.println("Registration failed!!!");
            }
            return userObject;
        } catch (Exception e) {
            logger.error("Error in registration",e);
            return null;
        }
    }

    private String checkPassword(User userObject) {
        String password;
        boolean isSame;
        do {
            password = promptPassword("Enter your password : ");
            String retypePassword = promptPassword("Retype your password : ");
            isSame = userObject.isSame(password, retypePassword);
            if(!isSame){
                System.out.println("Retype password mismatched!!!");
            }
        } while (!isSame);
        return password;
    }
    private String checkUserName() {
        String userName;
        boolean isDuplicate;
        do {
            System.out.print("Enter your UserName : ");
            userName = sc.next();
            isDuplicate = userServiceDAO.isUserNameExist(userName);
            if(isDuplicate){
                System.out.println("UserName already exist!!!");
            }
        } while (isDuplicate);
        return userName;
    }
    private long checkMobileNumber() {
        long mobileNumber;
        boolean isValid = true;
        do {
            System.out.print("Enter your MobileNumber : ");
            mobileNumber = sc.nextLong();
            if(String.valueOf(mobileNumber).length()!=10){
                System.out.println("Invalid Mobile Number!!! Mobile number should be 10 digits...");
                isValid = false;
                continue;
            }
            boolean isDuplicate = userServiceDAO.isMobileNumberExist(mobileNumber);
            if(isDuplicate){
                System.out.println("MobileNumber already exist!!!");
                isValid = false;
                continue;
            }
            isValid = true;
        } while (!isValid);
        return mobileNumber;
    }
    private String checkEmailId() {
        String emailId;
        boolean isValid = true;
        do {
            System.out.print("Enter your EmailId : ");
            emailId = sc.next();
            if (emailId.isEmpty()) {
                System.out.println("EmailId is mandatory!!!");
                isValid = false;
                continue;
            }
            if (!isValidEmail(emailId)) {
                System.out.println("EmailId is invalid!!!");
                isValid = false;
                continue;
            }
            boolean isDuplicate = userServiceDAO.isEmailExist(emailId);
            if(isDuplicate){
                System.out.println("EmailId already exist!!!");
                isValid = false;
                continue;
            }
            isValid = true;
        } while (!isValid);
        return emailId;
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
        logger.info("Login Started {}", UserServiceImpl.class.getSimpleName());

        System.out.println("For login.....");
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
    public User updateMobileNumber(User user) {
        System.out.print("Please enter the New Mobile Number : ");
        long mobileNumber = sc.nextLong();
        user.setMobileNumber(mobileNumber);
        UserServiceDAO userServiceDAO = new UserServiceDAOImpl();
        System.out.println("Mobile number updated successfully...");
        return userServiceDAO.save(user);
    }
    public User updateEmail(User user) {
        System.out.print("Please enter the Email Id : ");
        String emailId = sc.next();
        user.setEmailId(emailId);
        UserServiceDAO userServiceDAO = new UserServiceDAOImpl();
        System.out.println("Email Id updated successfully...");
        return userServiceDAO.save(user);
    }
    public User updatePassword(User user) {
        String password = checkPassword(user);
        user.setPassword(password);
        UserServiceDAO userServiceDAO = new UserServiceDAOImpl();
        System.out.println("Password updated successfully...");
        return userServiceDAO.save(user);
    }
}
