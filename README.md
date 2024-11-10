# RiskCal

This project is built with Java 17.

Due to the existence of 'Geotools' dependency, please add the following maven repo to your settings.xml

```xml

<repository>
    <id>osgeo-releases</id>
    <url>https://repo.osgeo.org/repository/release/</url>
    <snapshots>
        <enabled>false</enabled>
    </snapshots>
    <releases>
        <enabled>true</enabled>
    </releases>
</repository>
```