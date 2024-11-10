package com.nju.risk.cal.probability;

import org.geotools.api.data.SimpleFeatureSource;
import org.geotools.api.feature.simple.SimpleFeature;
import org.springframework.stereotype.Service;

@Service
public class EarthquakeProbability implements Probability {
    public static final String EARTHQUAKE = "earthquake";

    @Override
    public SimpleFeature getProbability(double longitude, double latitude) {
        SimpleFeatureSource featureSource = ShapeFileReader.readShapeFile("dbFiles/eqlianjie_point.shp");
        return ShapeFileReader.getNearestPointFeature(latitude, longitude, featureSource);
    }
}
