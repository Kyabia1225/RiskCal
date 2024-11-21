package com.nju.risk.cal.probability;

import com.nju.risk.cal.config.ShapeFilePath;
import org.geotools.api.feature.simple.SimpleFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TyphoonProbability implements Probability {
    public static final String TP_GAO = "tp_gao";
    public static final String TP_JIGAO = "tp_jigao";

    @Autowired
    private ShapeFilePath shapeFilePath;

    @Override
    public SimpleFeature getProbability(double longitude, double latitude) {
        return ShapeFileReader.getNearestPointFeature(shapeFilePath.getFilePath() + "typhoon.shp", longitude, latitude);
    }
}
