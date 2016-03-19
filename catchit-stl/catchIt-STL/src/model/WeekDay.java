package com.flysystems.catchit_stl.model;

import java.io.Serializable;
import java.util.Date;

/**
 * WeekDay.
 * 
 * Represent date and time at the precision of a week.
 * 
 * Monday = 0, Sunday = 6.
 * 12 AM = 0, 11PM = 23;
 * 
 * @author drm
 *
 */
public class WeekDay implements Serializable {

    private int day;
    private int hour;
    private int minute;
    private int second;
    
    private long time;
    
    public WeekDay(int day, int hour, int minutes, int seconds) {
        this.day = day;
        this.hour = hour;
        this.minute = minutes;
        this.second = seconds;
        computeTime();
    }
    
    /**
     * The default weekDay is Monday, 12:00:00 AM.
     * 
     * All other weekDay times are later (greater) than this time.
     */
    public WeekDay() {
        this(0,0,0,0);
    }
    
    public WeekDay( Date d) {
        day = d.getDay() - 1;
        day = (day == -1)? 6: day;
        hour = d.getHours();
        minute = d.getMinutes();
        second = d.getSeconds();
        computeTime();
    }
    
    public boolean isAfter( WeekDay d) {
        return time > d.getTime();
    }
    
    private void computeTime() {
        time = (((((day * 24) + hour) * 60) + minute) * 60) + second;
    }
    
    public long getTime() {
        return time;
    }
    
    public void setDayOfWeek( int dow) {
        day = dow;
        computeTime();
    }
    
    public void setHour( int h) {
    	hour = h;
    	computeTime();
    }
    
    public void setMinutes( int m) {
    	minute = m;
    	computeTime();
    }
    
    public String toString() {
        int h = hour % 12;
        h = (h == 0)? 12: h;
        String suffix = ( hour < 12)? "AM": "PM";
        
        return String.format("%d:%02d %s", h, minute, suffix);
    }
}
