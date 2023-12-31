package com.revspeed.domain;

import java.util.Calendar;
import java.util.Date;

import static com.revspeed.utilities.DateUtilities.convertToSqlDate;

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

    public UserPlan(){

    }
    public UserPlan(User user, Plan plan) {
        userid = user.getUserId();
        planId = plan.getPlanId();
        planName = plan.getPlanName();
        planStatus = "Active";
        paymentStatus = "due";
        startDate = new Date();
        endDate = incrementDate(new Date(),plan.getValidityInDays());
        remarks = "new subscription added";
    }

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
        //return convertToSqlDate(startDate);
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
        return String.format("%-8d %-20s %-15s %-15s %-12s %-12s",
                planId, planName, planStatus, paymentStatus, startDate, endDate);
        // return "\t"+ planId+"\t"+ planName + "\t" + planStatus + "\t" + paymentStatus + "\t\t" + startDate + "\t\t\t" + endDate;
    }
    private static Date incrementDate(Date date, int daysToAdd) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, daysToAdd);
        return calendar.getTime();
    }

    public void changePlan(Plan plan) {
        this.planId = plan.getPlanId();
        this.planName = plan.getPlanName();
        this.startDate = new Date();
        this.endDate = incrementDate(new Date(),plan.getValidityInDays());
        this.planStatus = "Active";
        this.paymentStatus = "due";
        this.remarks = "subscription updated";
    }

    public void cancelPlan() {
        this.planStatus = "Cancelled";
        this.endDate = new Date();
        this.remarks = "subscription cancelled";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        UserPlan userPlan = (UserPlan) obj;
        return userPlanId == userPlan.userPlanId
                && userid == userPlan.userid
                && planId == userPlan.planId
                && paymentStatus.equals(userPlan.paymentStatus)
                && planStatus.equals(userPlan.planStatus)
                && startDate.equals(userPlan.startDate)
                && endDate.equals(userPlan.endDate);
    }
}
