package com.nju.risk.cal.probability;

import org.geotools.api.feature.Feature;

public interface Probability {
    Feature getProbability(double longitude, double latitude);
}
