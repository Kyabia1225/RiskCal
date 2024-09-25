package com.nju.risk.model;


public class RiskCalculationRequest {
    private CompanyInfo companyInfo;
    private StorageTank storageTank;

    // Getters and Setters
    public CompanyInfo getCompanyInfo() {
        return companyInfo;
    }

    public void setCompanyInfo(CompanyInfo companyInfo) {
        this.companyInfo = companyInfo;
    }

    public StorageTank getStorageTank() {
        return storageTank;
    }

    public void setStorageTank(StorageTank storageTank) {
        this.storageTank = storageTank;
    }
}