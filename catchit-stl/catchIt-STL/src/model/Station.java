package com.flysystems.catchit_stl.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Station implements Serializable {
    private static final long serialVersionUID = 3479651768108829927L;
    private String name;
    private double lattitude;
    private double longitude;
    
    public Station( String name, float lat, float lng) {
        this.name = name;
        this.lattitude = lat;
        this.longitude = lng;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public double getLattitude() {
        return lattitude;
    }
    public void setLattitude(double lattitude) {
        this.lattitude = lattitude;
    }
    public double getLongitude() {
        return longitude;
    }
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
    
    public String toString() {
    	return name + ": lat = " + lattitude + ", lng = " + longitude;
    }
}
