package com.revspeed.domain;

import java.sql.Struct;

public class Plan {
    private int planId;
    private String planName;
    private double cost;
    private String planType;
    private String planDescription;
    private int validityInDays;
    public Plan() {
    }

    public Plan(int planId, String planName, double cost, String planType, String planDescription, int validityInDays) {
        this.planId = planId;
        this.planName = planName;
        this.cost = cost;
        this.planType = planType;
        this.planDescription = planDescription;
        this.validityInDays = validityInDays;
    }

    public int getPlanId() {
        return planId;
    }
    public void setPlanId(int planId) {
        this.planId = planId;
    }
    public String getPlanName() {
        return planName;
    }
    public void setPlanName(String planName) {
        this.planName = planName;
    }
    public double getCost() {
        return cost;
    }
    public void setCost(double cost) {
        this.cost = cost;
    }
    public String getPlanType() {
        return planType;
    }
    public void setPlanType(String planType) {
        this.planType = planType;
    }
    public String getPlanDescription() {
        return planDescription;
    }
    public void setPlanDescription(String planDescription) {
        this.planDescription = planDescription;
    }
    public int getValidityInDays() {
        return validityInDays;
    }
    public void setValidityInDays(int validityInDays) {
        this.validityInDays = validityInDays;
    }

    @Override
    public String toString() {
        return String.format("|%-7d| %-19s| %-5.2f| %-10s| %-15d| %-70s|",
                planId, planName, cost, planType, validityInDays, planDescription);
//        return String.format("%-8d %-20s %-10.2f %-15s %-15d %-80s",
//                planId, planName, cost, planType, validityInDays, planDescription);
        //return "\t"+ planId + "\t" + planName + "\t" + cost + "\t" + planType + "\t\t" + validityInDays + "\t\t\t" + planDescription;

    }
}
