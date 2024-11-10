package com.nju.risk.cal.risk;

import com.nju.risk.model.CompanyInfo;
import com.nju.risk.model.StorageTank;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;
@Service
public class RainstormRisk implements DeviceBrokenRisk {
    private final Double H4case1 = 0.02;
    private final Double H4case2 = 0.05;
    @Override
    public Map<String, Double> calculate(CompanyInfo companyInfo, StorageTank storageTank) {
        if (!storageTank.getForm().equals("浮顶罐")) {
            return Map.of("非浮顶罐无需计算", 0.0);
        } else {
            Double res1 = (9.8 * storageTank.getFloatingRoofWeight() + 7.693 * storageTank.getFloatingRoofDensity() * Math.pow(storageTank.getDiameter(), 2)
                    * (storageTank.getFloatingRoofThickness() + H4case1) - 35.49168 * storageTank.getDrainagePipeDiameter() * storageTank.getDrainageSystemEfficiency())
                    / (7.693 * storageTank.getStoredSubstanceDensity() * Math.pow(storageTank.getDiameter(), 2) * storageTank.getHeight());
            Double res2 = (9.8 * storageTank.getFloatingRoofWeight() + 7.693 * storageTank.getFloatingRoofDensity() * Math.pow(storageTank.getDiameter(), 2)
                    * (storageTank.getFloatingRoofThickness() + H4case2) - 35.49168 * storageTank.getDrainagePipeDiameter() * storageTank.getDrainageSystemEfficiency())
                    / (7.693 * storageTank.getStoredSubstanceDensity() * Math.pow(storageTank.getDiameter(), 2) * storageTank.getHeight());

            Map<String, Double> res = new LinkedHashMap<>();
            res.put("情景1", res1);
            res.put("情景2", res2);
            return res;
        }
    }
}
