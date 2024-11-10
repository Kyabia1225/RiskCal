package com.nju.risk.cal.probability;

import com.nju.risk.cal.config.ShapeFilePath;
import org.geotools.api.data.SimpleFeatureSource;
import org.geotools.api.feature.simple.SimpleFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EarthquakeAndRainstormProbability implements Probability {
    public static final String EARTHQUAKE = "earthquake";
    public static final String RAINSTORM = "rainstorm";

    @Autowired
    private ShapeFilePath shapeFilePath;

    @Override
    public SimpleFeature getProbability(double longitude, double latitude) {
        SimpleFeatureSource featureSource = ShapeFileReader.readShapeFile(shapeFilePath.getFilePath() + "eqlianjie_point.shp");
        return ShapeFileReader.getNearestPointFeature(longitude, latitude, featureSource);
    }
}
