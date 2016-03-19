package com.flysystems.catchit_stl.model;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

import com.flysystems.catchit_stl.R;
import android.content.res.Resources;
import android.util.Log;


public class MetroSystemOnDiskModel implements MetroSystem {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8380581638287920938L;
	Resources resources;
	
	public MetroSystemOnDiskModel( Resources resources) {
		this.resources = resources;
	}

	@Override
	public List<Station> getStations() {
		List<Station> stations;
		Log.d("getStations", "starting");
		try {
            InputStream is = resources.openRawResource(R.raw.stations);
            stations = readStations( is);
		}
		catch( IOException e) {
			stations = new ArrayList<Station>();
			e.printStackTrace();
		}
		Log.d("getStations","returning " + stations.size() + "stations, " + stations.get(0) + " thru " + stations.get(stations.size()-1));
        return stations;
	}

    @Override
    public List<Station> getStationsByDistance(double lat, double lng) {
		Log.d("getStationsByDistance", "starting");
        List<Station> stations = getStations();
        
        Collections.sort(stations, new ComparatorByDistance( lat, lng));
		Log.d("getStationsByDistance","returning " + stations.size() + "stations, " + stations.get(0) + " thru " + stations.get(stations.size()-1));
        
        return stations;
    }

    @Override
    public List<Route> getRoutes(Station s) {
		Log.d("getRoutes", "starting for station " + s.getName());
        List<Route> routes = null;
        try {
	        InputStream is = resources.openRawResource(R.raw.routes);
	        routes = readRoutes(is, s);
        }
        catch( IOException e) {
        	routes = new ArrayList<Route>();
        	e.printStackTrace();
        }
		Log.d("getRoutes","returning " + routes.size() + "routes, " + routes.get(0).getName() + " thru " + routes.get(routes.size()-1).getName());
        return routes;
    }

    @Override
    public List<Date> getTimesOnStation(Station s, Route r, Date now) {
		Log.d("getTimesOnStation", "starting for station " + s.getName() + " route " + r.getName());
        List<Date> arrivalTimes;
        try {
            arrivalTimes = getArrivalTimes( s, r, now);
        }
        catch( IOException e) {
        	e.printStackTrace();
        	arrivalTimes = new ArrayList<Date>();
        }
		Log.d("getTimesOnStation","returning " );
        return  arrivalTimes;
    }

    @Override
    public List<Date> getTimesOnStation(Station s, Route r, Date t, int count) {
		Log.d("getTimesOnStation", "starting for station " + s.getName() + " route " + r.getName() + ", time=" + t + ", count=" + count);
        try {
            List<Date> arrivalTimes = getArrivalTimes( s, r, t);
            List<Date> times = new ArrayList<Date>(count);
            
            for( int i = 0; i < arrivalTimes.size(); i++) {
            	Date time = arrivalTimes.get(i);
            	if( time.after(t)) {
            		times.add(time);
            		if( times.size() == count) 
            			break;
            	}
            }
            if( times.size() < count) {
                times.addAll( getFirstArrivalTimes(s,r, count - times.size(), t));
            }

    		Log.d("getTimesOnStation","returning " + times.size() + "times, " + times.get(0) + " thru " + times.get(times.size()-1));
            return times;
        }
        catch( IOException e) {
        	e.printStackTrace();
        	return new ArrayList<Date>();
        }
    }

    private ArrayList<Station> readStations(InputStream is) throws IOException {
        ArrayList<Station> s = new ArrayList<Station>();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(is));
            // skip header row.
            String line = reader.readLine();
            while( (line = reader.readLine()) != null) {
                String[] tokens = line.split("\\|");
                String name = tokens[0];
                float lat;
                float lng;
                try {
                    lat = Float.valueOf(tokens[1]);
                    lng = Float.valueOf(tokens[2]);
                }
                catch( NumberFormatException e) {
                    throw new IOException("Invalid (lat,lng): (" + tokens[1] + "," + tokens[2] + ")");
                }
                s.add( new Station( name, lat, lng));
            }
            
            return s;
        }
        finally {
            if( reader != null) try { reader.close();} catch( IOException ig) {}
        }
    }
    
    /**
     * Map station names to the routes that go thru them.
     * 
     * @param is
     * @return
     * @throws IOException
     */
    private HashMap<String, List<String>> readRoutes(InputStream is) throws IOException {
    	HashMap<String, List<String>> routeMap = new HashMap<String, List<String>>();
    	
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(is));
            // skip header row.
            String line = reader.readLine();
            while( (line = reader.readLine()) != null) {
                String[] tokens = line.split("\\|");
                String stationName = tokens[0];
                List<String> routeNames = new ArrayList<String>();
                routeMap.put(stationName, routeNames);
                for( int i = 1; i < tokens.length; i++) {
                	routeNames.add( tokens[i]);
                }
            }
            
            return routeMap;
        }
        finally {
            if( reader != null) try { reader.close();} catch( IOException ig) {}
        }
    }
    
    /**
     * Read the routes for a single station.
     * 
     * @param is
     * @param s
     * @return
     * @throws IOException
     */
    private List<Route> readRoutes(InputStream is, Station s) throws IOException {
    	List<Route> routes = new ArrayList<Route>();
    	
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(is));
            // skip header row.
            String line = reader.readLine();
            while( (line = reader.readLine()) != null) {
                String[] tokens = line.split("\\|");
                String stationName = tokens[0];
                if( s.getName().equals( stationName)) {
                    for( int i = 1; i < tokens.length; i++) {
                    	routes.add( new Route( tokens[i]));
                    }
                    break;
                }
            }
            
            return routes;
        }
        finally {
            if( reader != null) try { reader.close();} catch( IOException ig) {}
        }
    }
    
    public HashMap<String, List<Date>> readArrivalTimes(InputStream is, Date now) throws IOException {
        ArrayList<String> stationNames = new ArrayList<String>();
        HashMap<String, List<Date>> timeMap = new HashMap<String, List<Date>>();
        
        BufferedReader reader = null;
        try {
            reader = new BufferedReader( new InputStreamReader(is));
            // read station names from header row.
            String line = reader.readLine();
            String[] tokens = line.split("\\|");
            for( int i = 0; i < tokens.length; i++) {
            	if( i % 2 == 0) {
            	    stationNames.add( tokens[i]);
            	    timeMap.put(tokens[i], new ArrayList<Date>());
            	}
            }
            
            // skip station number row.
            line = reader.readLine();
            
            while( (line = reader.readLine()) != null) {
                tokens = line.split("\\|", -1);
                for( int i = 0; i < tokens.length; i++) {
                	int inc = 0;
                	String time = null;
                	if( i % 2 == 0){
                		time = tokens[i];
                		if( ! tokens[i+1].equals("") && tokens[i+1] != null){
                			Log.d("MetroSystemOnDisk","time: " + tokens[i] + "token: " + (i+1) + "= " + (tokens[i+1]) + " Station: " + stationNames.get(i/2));
                			inc = Integer.parseInt(tokens[i+1]);
                		}
                		if( time != null && (! time.equals(""))) {
                    		String stationName = stationNames.get(i/2);
                    		List<Date> ns = timeMap.get(stationName);
                    		ns.add(createDate(now, time, inc));
                    	}

                	}
                	
                }
            }
            
            return timeMap;
        }
        finally {
            if( reader != null) try { reader.close();} catch( IOException ig) {}
        }
    }
    
    private List<Date> getArrivalTimes(Station s, Route r, Date now) throws IOException {
		//support for holidays coming soon!
    	
    	InputStream is = null;
    	
    	if(isWeekDay(now)){
	        if ( "Red East".equals(r.getName())) {
	    		is = resources.openRawResource(R.raw.metrolinkweekdayschedule_red_east);
	        }
	        else if ( "Blue East".equals(r.getName())) {
	    		is = resources.openRawResource(R.raw.metrolinkweekdayschedule_blue_east);
	        }
	        else if ( "Blue West".equals(r.getName())) {
	    		is = resources.openRawResource(R.raw.metrolinkweekdayschedule_blue_west);
	        }
	        else if ( "Red West".equals(r.getName())) {
	    		is = resources.openRawResource(R.raw.metrolinkweekdayschedule_red_west);
	        }
	        else {
	        	return new ArrayList<Date>();
	        }
    	}else{
    		if ( "Red East".equals(r.getName())) {
	    		is = resources.openRawResource(R.raw.metrolinkweekendschedule_red_east);
	        }
	        else if ( "Blue East".equals(r.getName())) {
	    		is = resources.openRawResource(R.raw.metrolinkweekdayschedule_blue_east);
	        }
	        else if ( "Blue West".equals(r.getName())) {
	    		is = resources.openRawResource(R.raw.metrolinkweekdayschedule_blue_west);
	        }
	        else if ( "Red West".equals(r.getName())) {
	    		is = resources.openRawResource(R.raw.metrolinkweekdayschedule_red_west);
	        }
	        else {
	        	return new ArrayList<Date>();
	        }
    	}
        HashMap<String, List<Date>> arrivalTimes = readArrivalTimes(is, now);
        return  arrivalTimes.get(s.getName());
    }
    
    private List<Date> getFirstArrivalTimes( Station s, Route r, int count, Date now) throws IOException {
    	List<Date> allTimes = getArrivalTimes(s, r, now);
    	List<Date> times = new ArrayList<Date>();
    	
    	for( int i = 0; i < count; i++) {
    		if( i < allTimes.size()) {
    			times.add(allTimes.get(i));
    		}
    	}
    	return times;
    }

    private List<Date> getLastArrivalTimes( Station s, Route r, int count, Date now) throws IOException {
    	List<Date> allTimes = getArrivalTimes(s, r, now);
    	List<Date> times = new ArrayList<Date>();
    	
    	int i = allTimes.size() - count;
    	if( i < 0) i = 0;
    	
    	for( ; i < allTimes.size(); i++) {
    		if( i < allTimes.size()) {
    			times.add(allTimes.get(i));
    		}
    	}
    	return times;
    }
    private Date createDate(Date now, String atstring, int dayincrement){
    	Date arrivaltimedate = new Date(now.getTime());
    	
    	String[] tokens = atstring.split(":");
    	int hours = Integer.parseInt(tokens[0]);
    	int minutes = Integer.parseInt( tokens[1]);
    	arrivaltimedate.setHours(hours);
    	arrivaltimedate.setMinutes(minutes);
    	if(dayincrement == 1){
    		arrivaltimedate.setDate(arrivaltimedate.getDate() + 1);
    	}
    	
    	return arrivaltimedate;
    	
    }
    private boolean isWeekDay(Date d){
    	Calendar c = new GregorianCalendar();
    	c.setTime(d);
    	if(c.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY ||c.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY){
    		return false;
    	}else{
    		return true;
    	}
    }
}
