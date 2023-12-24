package com.revspeed.domain;

import java.util.Date;

public class UserPlan {
    private int userPlanId;
    private int userid;
    private int planId;
    private String planStatus;
    private String paymentStatus;
    private Date startDate;
    private Date endDate;
    private String remarks;
    private String planName;
    public int getUserPlanId() {
        return userPlanId;
    }
    public void setUserPlanId(int userPlanId) {
        this.userPlanId = userPlanId;
    }
    public int getUserid() {
        return userid;
    }
    public void setUserid(int userid) {
        this.userid = userid;
    }
    public int getPlanId() {
        return planId;
    }
    public void setPlanId(int planId) {
        this.planId = planId;
    }
    public String getPlanStatus() {
        return planStatus;
    }
    public void setPlanStatus(String planStatus) {
        this.planStatus = planStatus;
    }
    public String getPaymentStatus() {
        return paymentStatus;
    }
    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
    public Date getStartDate() {
        return startDate;
    }
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    public Date getEndDate() {
        return endDate;
    }
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    public String getRemarks() {
        return remarks;
    }
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
    public String getPlanName() {
        return planName;
    }
    public void setPlanName(String planName) {
        this.planName = planName;
    }

    @Override
    public String toString() {
        return "\t"+ planName + "\t" + planStatus + "\t" + paymentStatus + "\t\t" + startDate + "\t\t\t" + endDate;
    }
}