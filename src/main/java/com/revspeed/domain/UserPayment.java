package com.revspeed.domain;

public class UserPayment {
    private int paymentId;
    private int userId;
    private int planId;
    private int userPlanId;
    private String transactionStatus;
    private double chargeAmount;
    private String paymethod;
    private String bankName;
    private long customerId;

    public UserPayment() {
    }
    public UserPayment(int userId, int planId, int userPlanId, String transactionStatus, double chargeAmount, String paymethod) {
        this.userId = userId;
        this.planId = planId;
        this.userPlanId = userPlanId;
        this.transactionStatus = transactionStatus;
        this.chargeAmount = chargeAmount;
        this.paymethod = paymethod;
    }
    public int getPaymentId() {
        return paymentId;
    }
    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public int getPlanId() {
        return planId;
    }
    public void setPlanId(int planId) {
        this.planId = planId;
    }
    public int getUserPlanId() {
        return userPlanId;
    }
    public void setUserPlanId(int userPlanId) {
        this.userPlanId = userPlanId;
    }
    public String getTransactionStatus() {
        return transactionStatus;
    }
    public void setTransactionStatus(String transactionStatus) {
        this.transactionStatus = transactionStatus;
    }
    public double getChargeAmount() {
        return chargeAmount;
    }
    public void setChargeAmount(double chargeAmount) {
        this.chargeAmount = chargeAmount;
    }
    public String getPaymethod() {
        return paymethod;
    }
    public void setPaymethod(String paymethod) {
        this.paymethod = paymethod;
    }
    public String getBankName() {
        return bankName;
    }
    public void setBankName(String bankName) {
        this.bankName = bankName;
    }
    public long getCustomerId() {
        return customerId;
    }
    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }
}
