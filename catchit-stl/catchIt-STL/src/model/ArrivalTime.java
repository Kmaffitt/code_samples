package com.flysystems.catchit_stl.model;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ArrivalTime extends WeekDay implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = -5662691663363752894L;

    @Override
    public String toString() {
        return super.toString();
//        DateFormat df = new SimpleDateFormat("hh:mm a");
//        try {
//        String s = df.format(this);
//        return s;
//        } catch( IllegalArgumentException e) {
//            e.printStackTrace();
//            return null;
//        }

    }

    public ArrivalTime(Date d) {
        super(d);
    }
    
    public ArrivalTime() {
        super( new Date());
    }
    
    public ArrivalTime( String s) {
    	String[] tokens = s.split(":");
    	int hours = Integer.parseInt(tokens[0]);
    	int minutes = Integer.parseInt( tokens[1]);
    	this.setHour(hours);
    	this.setMinutes(minutes);
    }
    
}
