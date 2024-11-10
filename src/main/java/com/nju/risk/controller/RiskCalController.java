package com.nju.risk.controller;


import com.nju.risk.cal.probability.EarthquakeProbability;
import com.nju.risk.cal.probability.FloodProbability;
import com.nju.risk.cal.probability.RainstormProbability;
import com.nju.risk.cal.probability.TyphoonProbability;
import com.nju.risk.cal.risk.EarthquakeRisk;
import com.nju.risk.cal.risk.FloodRisk;
import com.nju.risk.cal.risk.RainstormRisk;
import com.nju.risk.cal.risk.TyphoonRisk;
import com.nju.risk.model.RiskCalculationRequest;
import com.nju.risk.util.MapModifier;
import com.nju.risk.util.UtilCollections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/risk")
public class RiskCalController {
    private final EarthquakeRisk earthquakeRisk;
    private final FloodRisk floodRisk;
    private final RainstormRisk rainstormRisk;
    private final TyphoonRisk typhoonRisk;

    private final EarthquakeProbability earthquakeProbability;
    private final FloodProbability floodProbability;
    ;
    private final RainstormProbability rainstormProbability;
    private final TyphoonProbability typhoonProbability;

    @Autowired
    public RiskCalController(EarthquakeRisk earthquakeRisk, FloodRisk floodRisk, RainstormRisk rainstormRisk, TyphoonRisk typhoonRisk,
                             EarthquakeProbability earthquakeProbability, FloodProbability floodProbability, RainstormProbability rainstormProbability, TyphoonProbability typhoonProbability) {
        this.earthquakeRisk = earthquakeRisk;
        this.floodRisk = floodRisk;
        this.rainstormRisk = rainstormRisk;
        this.typhoonRisk = typhoonRisk;
        this.earthquakeProbability = earthquakeProbability;
        this.floodProbability = floodProbability;
        this.rainstormProbability = rainstormProbability;
        this.typhoonProbability = typhoonProbability;
    }

    @PostMapping("/calculate")
    public Map<String, Map<String, Double>> calculateRisk(@RequestBody RiskCalculationRequest request) {
        if (!UtilCollections.checkIfRequireCalculationGlobally(request.getStorageTank())) {
            throw new RuntimeException("该类型储罐无需计算");
        } else {
            double earthquake = (double) earthquakeProbability
                    .getProbability(request.getCompanyInfo().getLongitude(), request.getCompanyInfo().getLatitude())
                    .getAttribute(EarthquakeProbability.EARTHQUAKE);
            double flood = (double) floodProbability
                    .getProbability(request.getCompanyInfo().getLongitude(), request.getCompanyInfo().getLongitude())
                    .getAttribute(FloodProbability.GAILV1KM);
            double rainstorm = (double) rainstormProbability
                    .getProbability(request.getCompanyInfo().getLongitude(), request.getCompanyInfo().getLongitude())
                    .getAttribute(RainstormProbability.RAINSTORM);
            double typhoon = (double) typhoonProbability
                    .getProbability(request.getCompanyInfo().getLongitude(), request.getCompanyInfo().getLongitude())
                    .getAttribute(TyphoonProbability.TP_GAO);
            UtilCollections.prepareTankDefaultData(request.getStorageTank(), request.getCompanyInfo());
            return Map.of("地震风险", MapModifier.applyRateToMap(earthquakeRisk.calculate(request.getCompanyInfo(), request.getStorageTank()), earthquake),
                    "洪水风险", MapModifier.applyRateToMap(floodRisk.calculate(request.getCompanyInfo(), request.getStorageTank()), flood),
                    "暴雨风险", MapModifier.applyRateToMap(rainstormRisk.calculate(request.getCompanyInfo(), request.getStorageTank()), rainstorm),
                    "台风风险", MapModifier.applyRateToMap(typhoonRisk.calculate(request.getCompanyInfo(), request.getStorageTank()), typhoon));
        }
    }
}
