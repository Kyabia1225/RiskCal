package com.nju.risk.cal.probability;

import lombok.extern.slf4j.Slf4j;
import org.geotools.api.data.SimpleFeatureSource;
import org.geotools.api.feature.Feature;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class FloodProbability implements Probability {
    public static final String GAILV1KM = "gailv1km";

    @Override
    public Feature getProbability(double longitude, double latitude) {
        SimpleFeatureSource featureSource = ShapeFileReader.readShapeFile("dbFiles/flood.shp");
        return ShapeFileReader.getNearestPointFeature(latitude, longitude, featureSource);
    }
}
