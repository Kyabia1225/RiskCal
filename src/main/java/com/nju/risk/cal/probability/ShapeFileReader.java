package com.nju.risk.cal.probability;

import lombok.extern.slf4j.Slf4j;
import org.geotools.api.data.FileDataStore;
import org.geotools.api.data.FileDataStoreFinder;
import org.geotools.api.data.SimpleFeatureSource;
import org.geotools.api.feature.simple.SimpleFeature;
import org.geotools.api.filter.Filter;
import org.geotools.api.filter.FilterFactory;
import org.geotools.data.simple.SimpleFeatureIterator;
import org.geotools.factory.CommonFactoryFinder;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;

import java.net.URL;
import java.util.Objects;

@Slf4j
public class ShapeFileReader {
    private static final String GEOM = "the_geom";
    private static final String DEGREE = "degree";

    public static SimpleFeatureSource readShapeFile(String fileName) {
        FileDataStore dataStore = null;
        try {
            URL shapeFileURL = ShapeFileReader.class.getClassLoader().getResource(fileName);
             dataStore = FileDataStoreFinder.getDataStore(shapeFileURL);
             return dataStore.getFeatureSource();
        } catch (Exception e) {
            log.error(e.getMessage());
        } finally {
            if (dataStore != null) {
                dataStore.dispose();
            }
        }
        return null;
    }

    public static SimpleFeature getNearestPointFeature(double longitude, double latitude, SimpleFeatureSource featureSource) {
        GeometryFactory geometryFactory = new GeometryFactory();
        Coordinate coordinate = new Coordinate(longitude, latitude);
        Point inputPoint = geometryFactory.createPoint(coordinate);
        double kmToDegree = 1.0 / (111.0 * Math.cos(Math.toRadians(latitude)));
        FilterFactory filterFactory = CommonFactoryFinder.getFilterFactory();
        Filter filter = filterFactory.dwithin(
                filterFactory.property(GEOM),
                filterFactory.literal(inputPoint),
                kmToDegree,
                DEGREE
        );
        SimpleFeature nearestPoint = null;
        double minDistance = Double.MAX_VALUE;
        try {
            SimpleFeatureIterator features = Objects.requireNonNull(featureSource).getFeatures(filter).features();
            while (features.hasNext()) {
                SimpleFeature feature = features.next();
                log.info(feature.getAttribute(GEOM).toString());
                if (feature.getAttribute(GEOM) instanceof Point point) {
                    double distance = inputPoint.distance(point);
                    if (distance < minDistance) {
                        minDistance = distance;
                        nearestPoint = feature;
                    }
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        if (nearestPoint == null) {
            throw new RuntimeException("Nearest point not found");
        }
        return nearestPoint;
    }
}
