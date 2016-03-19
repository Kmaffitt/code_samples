package com.flysystems.catchit_stl.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public interface MetroSystem extends Serializable {
    
    public  List<Station> getStations();
    
    public  List<Station> getStationsByDistance( double lat, double lng);
    
    public List<Route> getRoutes( Station s);
     
    public List<Date> getTimesOnStation( Station s, Route r, Date now);

    public List<Date> getTimesOnStation( Station s, Route r, Date d, int count);
    
}
