package com.nju.risk.cal.risk;

import com.nju.risk.model.CompanyInfo;
import com.nju.risk.model.StorageTank;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class FloodRisk implements DeviceBrokenRisk {

    private final Double v4case1 = 2.0;
    private final Double v4case2 = 0.5;
    private final Double v4case3 = 2.0;
    private final Double h4case1 = 2.0;
    private final Double h4case2 = 3.0;
    private final Double h4case3 = 0.5;

    @Override
    public Map<String, Double> calculate(CompanyInfo companyInfo, StorageTank storageTank) {
        if (storageTank.getShape().equals("立式圆筒罐")) {
            return calculateVerticalTank(storageTank);
        } else if (storageTank.getShape().equals("卧式圆筒罐")) {
            return calculateHorizontalTank(storageTank);
        } else {
            return Map.of("非立式或卧式圆筒罐，无需计算", 0.0);
        }
    }

    private Map<String, Double> calculateHorizontalTank(StorageTank storageTank) {
        // case 1
        Double res1 = ((1331.0 * Math.pow(storageTank.getDiameter(), -0.99) / (storageTank.getStoredSubstanceDensity() - storageTank.getSubstanceDensity())) * (h4case1 - storageTank.getSupportHeight())
                + ((-1.882) * Math.pow((9.8 * storageTank.getShellWeight() - 46.8), (-0.252)) - storageTank.getSubstanceDensity()) / (storageTank.getStoredSubstanceDensity() - storageTank.getSubstanceDensity()) - storageTank.getMinFillingLevel())
                / (storageTank.getMaxFillingLevel() - storageTank.getMinFillingLevel());

        // case 2
        Double res2 = ((1331.0 * Math.pow(storageTank.getDiameter(), -0.99) / (storageTank.getStoredSubstanceDensity() - storageTank.getSubstanceDensity())) * (h4case2 - storageTank.getSupportHeight())
                + ((-1.882) * Math.pow((9.8 * storageTank.getShellWeight() - 46.8), (-0.252)) - storageTank.getSubstanceDensity()) / (storageTank.getStoredSubstanceDensity() - storageTank.getSubstanceDensity()) - storageTank.getMinFillingLevel())
                / (storageTank.getMaxFillingLevel() - storageTank.getMinFillingLevel());

        // case 3
        Double res3 = ((1331.0 * Math.pow(storageTank.getDiameter(), -0.99) / (storageTank.getStoredSubstanceDensity() - storageTank.getSubstanceDensity())) * (h4case3 - storageTank.getSupportHeight())
                + ((-1.882) * Math.pow((9.8 * storageTank.getShellWeight() - 46.8), (-0.252)) - storageTank.getSubstanceDensity()) / (storageTank.getStoredSubstanceDensity() - storageTank.getSubstanceDensity()) - storageTank.getMinFillingLevel())
                / (storageTank.getMaxFillingLevel() - storageTank.getMinFillingLevel());
        Map<String, Double> res = new LinkedHashMap<>();
        res.put("情景1", res1);
        res.put("情景2", res2);
        res.put("情景3", res3);
        return res;
    }

    private Map<String, Double> calculateVerticalTank(StorageTank storageTank) {
        // case 1
        Double res1 = ((990.0 * v4case1 * v4case1 + 10780.0 * h4case1 - 0.199 * storageTank.getVolume() - 6950.0) /
                (9.8 * storageTank.getStoredSubstanceDensity() * storageTank.getHeight()) - storageTank.getMinFillingLevel())
                / (storageTank.getMaxFillingLevel() - storageTank.getMinFillingLevel());
        // case 2
        Double res2 = ((990.0 * v4case2 * v4case2 + 10780.0 * h4case2 - 0.199 * storageTank.getVolume() - 6950.0) /
                (9.8 * storageTank.getStoredSubstanceDensity() * storageTank.getHeight()) - storageTank.getMinFillingLevel())
                / (storageTank.getMaxFillingLevel() - storageTank.getMinFillingLevel());

        // case 3
        Double res3 = ((990.0 * v4case3 * v4case3 + 10780.0 * h4case3 - 0.199 * storageTank.getVolume() - 6950.0) /
                (9.8 * storageTank.getStoredSubstanceDensity() * storageTank.getHeight()) - storageTank.getMinFillingLevel())
                / (storageTank.getMaxFillingLevel() - storageTank.getMinFillingLevel());

        Map<String, Double> res = new LinkedHashMap<>();
        res.put("情景1", res1);
        res.put("情景2", res2);
        res.put("情景3", res3);
        return res;

    }
}
