package com.nju.risk.cal.probability;

import org.geotools.api.data.SimpleFeatureSource;
import org.geotools.api.feature.Feature;

public class RainstormProbability implements Probability {
    public static final String RAINSTORM = "rainstorm";

    @Override
    public Feature getProbability(double longitude, double latitude) {
        SimpleFeatureSource featureSource = ShapeFileReader.readShapeFile("dbFiles/eqlianjie_point.shp");
        return ShapeFileReader.getNearestPointFeature(latitude, longitude, featureSource);
    }
}
