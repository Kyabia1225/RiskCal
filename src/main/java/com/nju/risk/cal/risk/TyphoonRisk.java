package com.nju.risk.cal.risk;

import com.nju.risk.model.CompanyInfo;
import com.nju.risk.model.StorageTank;
import org.springframework.stereotype.Service;

import java.util.Map;
@Service
public class TyphoonRisk implements DeviceBrokenRisk {
    @Override
    public Map<String, Double> calculate(CompanyInfo companyInfo, StorageTank storageTank) {
        return Map.of("高风载荷情境下", 0.32, "极高风载荷情境下", 0.40);
    }
}
