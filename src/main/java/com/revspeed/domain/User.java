package com.revspeed.domain;

public class User {
    private  int userId;
    private String userName;
    private String firstName;
    private String lastname;
    private long mobileNumber;
    private String emailId;
    private String address;
    private String password;
    private String remarks;
    public User(){}
    public User(String userName, String firstName, String lastname, long mobileNumber, String emailId, String address, String password) {
        this.userName = userName;
        this.firstName = firstName;
        this.lastname = lastname;
        this.mobileNumber = mobileNumber;
        this.emailId = emailId;
        this.address = address;
        this.password = password;
    }
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastname() {
        return lastname;
    }
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    public long getMobileNumber() {
        return mobileNumber;
    }
    public void setMobileNumber(long mobileNumber) {
        this.mobileNumber = mobileNumber;
    }
    public String getEmailId() {
        return emailId;
    }
    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "My Profile\n" +"============"+
                "\n\tUserName\t : " + userName +
                "\n\tName\t\t : " + firstName +" "+ lastname +
                "\n\tMobileNumber : " + mobileNumber +
                "\n\tEmailId\t\t : " + emailId +
                "\n\tAddress\t\t : " + address +
                "\n\tRemarks\t\t : " + remarks ;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isSame(String password, String retypePassword) {
        return password.equals(retypePassword);
    }

    public String getRemarks() {
        return remarks;
    }
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
