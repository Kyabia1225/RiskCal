package com.nju.risk.cal.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ShapeFilePath {
    @Value("${shapefile.path}")
    private String filePath;

    public String getFilePath() {
        return filePath;
    }
}
