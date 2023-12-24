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
        return "\t"+ planId + "\t" + planName + "\t" + cost + "\t" + planType + "\t\t" + validityInDays + "\t\t\t" + planDescription;
    }
}
