package com.tspolice.sample.models;

public class ViolationsModel {

    private String vioOffenceCode, vioSection, violation, vioMinAmount, vioMaxAmount, vioAvgAmount, wheelerCode, vioFlag;

    public ViolationsModel() {
    }

    public String getVioOffenceCode() {
        return vioOffenceCode;
    }

    public void setVioOffenceCode(String vioOffenceCode) {
        this.vioOffenceCode = vioOffenceCode;
    }

    public String getVioSection() {
        return vioSection;
    }

    public void setVioSection(String vioSection) {
        this.vioSection = vioSection;
    }

    public String getViolation() {
        return violation;
    }

    public void setViolation(String violation) {
        this.violation = violation;
    }

    public String getVioMinAmount() {
        return vioMinAmount;
    }

    public void setVioMinAmount(String vioMinAmount) {
        this.vioMinAmount = vioMinAmount;
    }

    public String getVioMaxAmount() {
        return vioMaxAmount;
    }

    public void setVioMaxAmount(String vioMaxAmount) {
        this.vioMaxAmount = vioMaxAmount;
    }

    public String getVioAvgAmount() {
        return vioAvgAmount;
    }

    public void setVioAvgAmount(String vioAvgAmount) {
        this.vioAvgAmount = vioAvgAmount;
    }

    public String getWheelerCode() {
        return wheelerCode;
    }

    public void setWheelerCode(String wheelerCode) {
        this.wheelerCode = wheelerCode;
    }

    public String getVioFlag() {
        return vioFlag;
    }

    public void setVioFlag(String vioFlag) {
        this.vioFlag = vioFlag;
    }
}
