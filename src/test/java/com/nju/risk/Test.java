package com.nju.risk;

import com.nju.risk.cal.probability.FloodProbability;
import com.nju.risk.cal.probability.Probability;
import com.nju.risk.cal.probability.TyphoonProbability;
import org.geotools.api.feature.Feature;

public class Test {
    @org.junit.jupiter.api.Test
    public void test() {
        Probability floodProbability = new TyphoonProbability();
        Feature probability = floodProbability.getProbability(120d, 30d);
        System.out.println(probability);
    }
}
