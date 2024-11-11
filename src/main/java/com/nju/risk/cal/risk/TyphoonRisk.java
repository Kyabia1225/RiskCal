package com.nju.risk.cal.risk;

import com.nju.risk.model.CompanyInfo;
import com.nju.risk.model.StorageTank;
import com.nju.risk.util.DoubleFormatter;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;
@Service
public class TyphoonRisk implements DeviceBrokenRisk {
    @Override
    public Map<String, Double> calculate(CompanyInfo companyInfo, StorageTank storageTank) {
        Map<String, Double> res = new LinkedHashMap<>();
        res.put("高风载荷情境下", 0.32);
        res.put("极高风载荷情境下", 0.40);
        return res;
    }

    public Map<String, Double> adapt(Map<String, Double> input, double gao, double jigao) {
        Map<String, Double> res = new LinkedHashMap<>();
        res.put("高风载荷情境下", DoubleFormatter.format(input.get("高风载荷情境下") * gao));
        res.put("极高风载荷情境下", DoubleFormatter.format(input.get("极高风载荷情境下") * jigao));
        return res;
    }
}
