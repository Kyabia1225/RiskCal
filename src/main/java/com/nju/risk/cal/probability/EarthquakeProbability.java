package com.nju.risk.cal.probability;

import lombok.extern.slf4j.Slf4j;
import org.geotools.api.data.SimpleFeatureSource;
import org.geotools.api.feature.Feature;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EarthquakeProbability implements Probability {
    public static final String EARTHQUAKE = "earthquake";

    @Override
    public Feature getProbability(double longitude, double latitude) {
        SimpleFeatureSource featureSource = ShapeFileReader.readShapeFile("dbFiles/eqlianjie_point.shp");
        return ShapeFileReader.getNearestPointFeature(latitude, longitude, featureSource);
    }
}
