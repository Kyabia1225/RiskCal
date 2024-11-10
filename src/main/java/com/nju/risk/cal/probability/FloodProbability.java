package com.nju.risk.cal.probability;

import org.geotools.api.data.SimpleFeatureSource;
import org.geotools.api.feature.simple.SimpleFeature;
import org.springframework.stereotype.Service;

@Service
public class FloodProbability implements Probability {
    public static final String GAILV1KM = "gailv1km";

    @Override
    public SimpleFeature getProbability(double longitude, double latitude) {
        SimpleFeatureSource featureSource = ShapeFileReader.readShapeFile("dbFiles/flood.shp");
        return ShapeFileReader.getNearestPointFeature(longitude, latitude, featureSource);
    }
}
