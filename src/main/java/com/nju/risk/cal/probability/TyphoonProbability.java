package com.nju.risk.cal.probability;

import lombok.extern.slf4j.Slf4j;
import org.geotools.api.data.SimpleFeatureSource;
import org.geotools.api.feature.Feature;
import org.springframework.stereotype.Service;
@Service
@Slf4j
public class TyphoonProbability implements Probability {
    public static final String TP_GAO = "tp_gao";
    public static final String TP_JIGAO = "tp_jigao";
    @Override
    public Feature getProbability(double longitude, double latitude) {
        SimpleFeatureSource featureSource = ShapeFileReader.readShapeFile("dbFiles/typhoon.shp");
        return ShapeFileReader.getNearestPointFeature(longitude, latitude, featureSource);
    }
}
