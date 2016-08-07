package com.bahadirakin.ml.dto;

import java.io.Serializable;

public class CompanyInfo implements Serializable {

    private RiskStatus industrialRisk;
    private RiskStatus managementRisk;
    private RiskStatus financialFlexibility;
    private RiskStatus credibility;
    private RiskStatus competitiveness;
    private RiskStatus operatingRisk;

    public RiskStatus getIndustrialRisk() {
        return industrialRisk;
    }

    public void setIndustrialRisk(RiskStatus industrialRisk) {
        this.industrialRisk = industrialRisk;
    }

    public RiskStatus getManagementRisk() {
        return managementRisk;
    }

    public void setManagementRisk(RiskStatus managementRisk) {
        this.managementRisk = managementRisk;
    }

    public RiskStatus getFinancialFlexibility() {
        return financialFlexibility;
    }

    public void setFinancialFlexibility(RiskStatus financialFlexibility) {
        this.financialFlexibility = financialFlexibility;
    }

    public RiskStatus getCredibility() {
        return credibility;
    }

    public void setCredibility(RiskStatus credibility) {
        this.credibility = credibility;
    }

    public RiskStatus getCompetitiveness() {
        return competitiveness;
    }

    public void setCompetitiveness(RiskStatus competitiveness) {
        this.competitiveness = competitiveness;
    }

    public RiskStatus getOperatingRisk() {
        return operatingRisk;
    }

    public void setOperatingRisk(RiskStatus operatingRisk) {
        this.operatingRisk = operatingRisk;
    }

    @Override
    public String toString() {
        return "CompanyInfo{" +
                "industrialRisk=" + industrialRisk +
                ", managementRisk=" + managementRisk +
                ", financialFlexibility=" + financialFlexibility +
                ", credibility=" + credibility +
                ", competitiveness=" + competitiveness +
                ", operatingRisk=" + operatingRisk +
                '}';
    }
}
