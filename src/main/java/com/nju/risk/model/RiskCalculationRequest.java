package com.nju.risk.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RiskCalculationRequest {
    private CompanyInfo companyInfo;
    private StorageTank storageTank;

}