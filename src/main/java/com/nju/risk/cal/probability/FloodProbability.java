package com.nju.risk.cal.probability;

import com.nju.risk.cal.config.ShapeFilePath;
import org.geotools.api.feature.simple.SimpleFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FloodProbability implements Probability {
    public static final String GAILV1KM = "gailv1km";

    @Autowired
    private ShapeFilePath shapeFilePath;

    @Override
    public SimpleFeature getProbability(double longitude, double latitude) {
        return ShapeFileReader.getNearestPointFeature(shapeFilePath.getFilePath() + "flood.shp", longitude, latitude);
    }
}
