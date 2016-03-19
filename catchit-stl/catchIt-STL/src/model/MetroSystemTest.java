package com.flysystems.catchit_stl.model;

import java.io.IOException;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MetroSystemTest implements MetroSystem, Serializable {

	@Override
	public List<Station> getStations() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Station> getStationsByDistance(double lat, double lng) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Route> getRoutes(Station s) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Date> getTimesOnStation(Station s, Route r, Date now) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Date> getTimesOnStation(Station s, Route r, Date d, int count) {
		// TODO Auto-generated method stub
		return null;
	}
    
//    private static final long serialVersionUID = 5684445492607455520L;
//    private final Route redEastRoute;
//    private final Route redWestRoute;
//    private final Route blueEastRoute;
//    private final Route blueWestRoute;
//    
//    public MetroSystemTest() {
//        redEastRoute = new Route( "Red East");
//        redWestRoute = new Route( "Red West");
//        blueEastRoute = new Route( "Blue East");
//        blueWestRoute = new Route( "Blue West");
//    }
//
//    public List<Station> getStations() {
//        List<Station> stations = new ArrayList<Station>();
//        
//        stations.add( new Station( "UC-BB", 0.0f, 0.0f));
//        stations.add( new Station( "Delmar", 0.0f, 0.0f));
//        stations.add( new Station( "Forest Park", 0.0f, 0.0f));
//        
//        return stations;
//    }
//
//    @Override
//    public List<Station> getStationsByDistance( double lat, double lng) {
//        List<Station> stations = new ArrayList<Station>();
//        
//        stations.add( new Station( "UC-BB", 0.0f, 0.0f));
//        stations.add( new Station( "Delmar", 0.0f, 0.0f));
//        stations.add( new Station( "Forest Park", 0.0f, 0.0f));
//        
//        return stations;
//    }
//
//    @Override
//    public List<Route> getRoutes(Station s) {
//        List<Route> routes = new ArrayList<Route>();
//        
//        if( "UC-BB".equals(s.getName())) {
//            routes.add( blueEastRoute);
//            routes.add( blueEastRoute);
//        }
//        else if( "Delmar".equals(s.getName())) {
//            routes.add( redEastRoute);
//            routes.add( redWestRoute);
//        }
//        else if( "Forest Park".equals(s.getName())) {
//            routes.add( redEastRoute);
//            routes.add( redWestRoute);
//            routes.add( blueWestRoute);
//            routes.add( blueEastRoute);
//        }
//        
//        return routes;
//    }
//
//    @Override
//
//    public TimesOnStation getTimesOnStation(Station s, Route r) {
//
//        List<ArrivalTime> times = new ArrayList<ArrivalTime>();
//        DateFormat df = new SimpleDateFormat("HH:mm");
//
//        try {
//            if( "UC-BB".equals(s.getName())) {
//                if( "Blue East".equals( r.getName())) {
//                    times.add( new ArrivalTime( df.parse("11:11")));
//                    times.add( new ArrivalTime( df.parse("11:12")));
//                    times.add( new ArrivalTime( df.parse("11:13")));
//                    times.add( new ArrivalTime( df.parse("11:14")));
//                    times.add( new ArrivalTime( df.parse("11:15")));
//                }
//                else if( "Blue West".equals( r.getName())) {
//                    times.add( new ArrivalTime( df.parse("12:11")));
//                    times.add( new ArrivalTime( df.parse("12:12")));
//                    times.add( new ArrivalTime( df.parse("12:13")));
//                    times.add( new ArrivalTime( df.parse("12:14")));
//                    times.add( new ArrivalTime( df.parse("12:15")));
//                }
//            }
//            else if( "Delmar".equals(s.getName())) {
//                if( "Red East".equals( r.getName())) {
//                    times.add( new ArrivalTime( df.parse("13:11")));
//                    times.add( new ArrivalTime( df.parse("13:12")));
//                    times.add( new ArrivalTime( df.parse("13:13")));
//                    times.add( new ArrivalTime( df.parse("13:14")));
//                    times.add( new ArrivalTime( df.parse("13:15")));
//                }
//                else if( "Red West".equals( r.getName())) {
//                    times.add( new ArrivalTime( df.parse("14:11")));
//                    times.add( new ArrivalTime( df.parse("14:12")));
//                    times.add( new ArrivalTime( df.parse("14:13")));
//                    times.add( new ArrivalTime( df.parse("14:14")));
//                    times.add( new ArrivalTime( df.parse("14:15")));
//                }
//            }
//            else if( "Forest Park".equals(s.getName())) {
//                if( "Red East".equals( r.getName())) {
//                    times.add( new ArrivalTime( df.parse("15:11")));
//                    times.add( new ArrivalTime( df.parse("15:12")));
//                    times.add( new ArrivalTime( df.parse("15:13")));
//                    times.add( new ArrivalTime( df.parse("15:14")));
//                    times.add( new ArrivalTime( df.parse("15:15")));
//                }
//                else if( "Red West".equals( r.getName())) {
//                    times.add( new ArrivalTime( df.parse("16:11")));
//                    times.add( new ArrivalTime( df.parse("16:12")));
//                    times.add( new ArrivalTime( df.parse("16:13")));
//                    times.add( new ArrivalTime( df.parse("16:14")));
//                    times.add( new ArrivalTime( df.parse("16:15")));
//                }
//                else if( "Blue East".equals( r.getName())) {
//                    times.add( new ArrivalTime( df.parse("17:11")));
//                    times.add( new ArrivalTime( df.parse("17:12")));
//                    times.add( new ArrivalTime( df.parse("17:13")));
//                    times.add( new ArrivalTime( df.parse("17:14")));
//                    times.add( new ArrivalTime( df.parse("17:15")));
//                }
//                else if( "Blue West".equals( r.getName())) {
//                    times.add( new ArrivalTime( df.parse("18:11")));
//                    times.add( new ArrivalTime( df.parse("18:12")));
//                    times.add( new ArrivalTime( df.parse("18:13")));
//                    times.add( new ArrivalTime( df.parse("18:14")));
//                    times.add( new ArrivalTime( df.parse("18:15")));
//                }
//            }
//        }
//        catch( ParseException e) {
//        	e.printStackTrace();
//        }
//        return new TimesOnStation(times);
//    }
//
//    @Override
//
//    public TimesOnStation getTimesOnStation(Station s, Route r, ArrivalTime t, int count) {
//        return getTimesOnStation( s, r);
//    }
//    
//    public static void main(String[] args) {
//
//    	MetroSystem metroSystem = new MetroSystemTest();
//    	
//        List<Station> allStations = metroSystem.getStations();
//        
//        for( Station s: allStations) {
//        	System.out.println( s);
//        }
//    }

}
