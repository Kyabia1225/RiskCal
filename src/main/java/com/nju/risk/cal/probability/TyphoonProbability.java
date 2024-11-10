package com.nju.risk.cal.probability;

import org.geotools.api.data.SimpleFeatureSource;
import org.geotools.api.feature.simple.SimpleFeature;
import org.springframework.stereotype.Service;

@Service
public class TyphoonProbability implements Probability {
    public static final String TP_GAO = "tp_gao";
    public static final String TP_JIGAO = "tp_jigao";
    @Override
    public SimpleFeature getProbability(double longitude, double latitude) {
        SimpleFeatureSource featureSource = ShapeFileReader.readShapeFile("dbFiles/typhoon.shp");
        return ShapeFileReader.getNearestPointFeature(longitude, latitude, featureSource);
    }
}
