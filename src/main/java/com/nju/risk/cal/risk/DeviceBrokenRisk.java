package com.nju.risk.cal.risk;

import com.nju.risk.model.CompanyInfo;
import com.nju.risk.model.StorageTank;

import java.util.Map;

public interface DeviceBrokenRisk {
    Map<String, Double> calculate(CompanyInfo companyInfo, StorageTank storageTank);
}
