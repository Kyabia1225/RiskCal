package com.nju.risk.controller;


import com.nju.risk.cal.EarthquakeRisk;
import com.nju.risk.cal.FloodRisk;
import com.nju.risk.cal.HeavyRainRisk;
import com.nju.risk.cal.TyphoonRisk;
import com.nju.risk.model.RiskCalculationRequest;
import com.nju.risk.util.UtilCollections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/risk")
public class RiskCalController {
    private final EarthquakeRisk earthquakeRisk;
    private final FloodRisk floodRisk;
    private final HeavyRainRisk heavyRainRisk;
    private final TyphoonRisk typhoonRisk;

    @Autowired
    public RiskCalController(EarthquakeRisk earthquakeRisk, FloodRisk floodRisk, HeavyRainRisk heavyRainRisk, TyphoonRisk typhoonRisk) {
        this.earthquakeRisk = earthquakeRisk;
        this.floodRisk = floodRisk;
        this.heavyRainRisk = heavyRainRisk;
        this.typhoonRisk = typhoonRisk;
    }

    @PostMapping("/calculate")
    public Map<String, Map<String, Double>> calculateRisk(@RequestBody RiskCalculationRequest request) {
        if (!UtilCollections.checkIfRequireCalculationGlobally(request.getStorageTank())) {
            throw new RuntimeException("未通过类型检查");
        } else {
            UtilCollections.prepareTankDefaultData(request.getStorageTank(), request.getCompanyInfo());
            return Map.of("地震风险", earthquakeRisk.calculate(request.getCompanyInfo(), request.getStorageTank()),
                    "洪水风险", floodRisk.calculate(request.getCompanyInfo(), request.getStorageTank()),
                    "暴雨风险", heavyRainRisk.calculate(request.getCompanyInfo(), request.getStorageTank()),
                    "台风风险", typhoonRisk.calculate(request.getCompanyInfo(), request.getStorageTank()));
        }
    }
}
