package com.nju.risk.cal;

import com.nju.risk.model.CompanyInfo;
import com.nju.risk.model.StorageTank;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class EarthquakeRisk implements DeviceBrokenRisk {
    @Override
    public Map<String, Double> calculate(CompanyInfo companyInfo, StorageTank storageTank) {
        return Map.of("情景1", 0.0, "情景2", 0.0);
    }
}
