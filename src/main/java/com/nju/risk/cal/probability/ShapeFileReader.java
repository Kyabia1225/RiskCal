package com.nju.risk.cal.probability;

import lombok.extern.slf4j.Slf4j;
import org.geotools.api.feature.simple.SimpleFeature;
import org.geotools.api.filter.Filter;
import org.geotools.api.filter.FilterFactory;
import org.geotools.data.shapefile.ShapefileDataStore;
import org.geotools.data.simple.SimpleFeatureIterator;
import org.geotools.factory.CommonFactoryFinder;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

@Slf4j
public class ShapeFileReader {
    private static final String GEOM = "the_geom";
    private static final String DEGREE = "degree";

    public static SimpleFeature getNearestPointFeature(String path, double longitude, double latitude) {
        ShapefileDataStore dataStore;
        try {
            dataStore = new ShapefileDataStore(new File(path).toURI().toURL());
            dataStore.setMemoryMapped(false);
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }

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

        try (SimpleFeatureIterator features = Objects.requireNonNull(dataStore.getFeatureSource())
                .getFeatures(filter)
                .features()) {

            while (features.hasNext()) {
                SimpleFeature feature = features.next();
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
        } finally {
            dataStore.dispose();
        }

        if (nearestPoint == null) {
            throw new RuntimeException("Nearest point not found");
        }
        return nearestPoint;
    }

}
