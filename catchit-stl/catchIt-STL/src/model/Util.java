package com.flysystems.catchit_stl.model;

import java.util.Comparator;

public class Util {
    
    private final static double R = 3959.0;
    private final static double C = R * Math.PI / 180.0;
    private final static double HOME_LAT = 38.653710;
    private final static double HOME_LNG = -90.315843;
    
    public static double distance( double lat1, double lng1, double lat2, double lng2) {
        double dx = (lng2 - lng1) * Math.cos(lat1 * Math.PI / 180.0);
        double dy = (lat2 - lat1);
        double d = C * Math.sqrt( dx * dx + dy * dy);
        return d;
    }
    
}
