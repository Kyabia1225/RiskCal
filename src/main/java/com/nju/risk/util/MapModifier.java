package com.nju.risk.util;

import java.util.Map;
import java.util.stream.Collectors;

public class MapModifier {
    public static Map<String, Double> applyRateToMap(Map<String, Double> map, double rate) {
        return map.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> entry.getValue() * rate
                ));
    }
}
