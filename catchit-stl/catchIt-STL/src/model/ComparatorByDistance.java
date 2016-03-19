package com.flysystems.catchit_stl.model;

import java.util.Comparator;

public class ComparatorByDistance implements Comparator<Station> {
    private final double lat;
    private final double lng;

    public ComparatorByDistance( double lat, double lng) {
        this.lat = lat;
        this.lng = lng;
    }

    @Override
    public int compare( Station s1,  Station s2) {
        double d1 = Util.distance(lat, lng, s1.getLattitude(), s1.getLongitude());
        double d2 = Util.distance(lat, lng, s2.getLattitude(), s2.getLongitude());
        if( d1 < d2)       return -1;
        else if( d1 > d2)  return 1;
        else               return 0;
    }
}
