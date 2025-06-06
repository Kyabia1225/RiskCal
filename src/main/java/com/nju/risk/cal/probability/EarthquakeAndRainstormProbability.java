package com.nju.risk.cal.probability;

import com.nju.risk.cal.config.ShapeFilePath;
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
        return ShapeFileReader.getNearestPointFeature(shapeFilePath.getFilePath() + "eqlianjie_point.shp", longitude, latitude);
    }
}
