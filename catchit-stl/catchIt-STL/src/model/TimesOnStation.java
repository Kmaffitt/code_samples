package com.flysystems.catchit_stl.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import android.util.Log;

public class TimesOnStation implements Serializable {
    private List<ArrivalTime> times;
    
    public TimesOnStation() {
        times = new ArrayList<ArrivalTime>();
    }
    public TimesOnStation(List<ArrivalTime> t){
    	times = t;
    }

    public ArrivalTime getNextTime( ArrivalTime d) {
        long dl = d.getTime();
        ArrivalTime nextTime= times.get(0);
        for( ArrivalTime t: times) {
            if( dl <= t.getTime() ) {
                nextTime = t;
                break;
            }
        }
        return nextTime;
    }
    
    public long getDeltaTime( ArrivalTime t) {
        long delta = 0;
        
        for( ArrivalTime at: times) {
            if( at.isAfter(t)) {
                delta = at.getTime() - t.getTime();
                Log.d("TimesOnStation.getDeltaTime", "at = " + at.getTime() + " t = " + t.getTime() + " delta = " + delta );
                break;
            }
        }
        return delta;
    }
    
    public List<ArrivalTime> getNextTimes( ArrivalTime d, int ntimes) {
        List<ArrivalTime> nextTimes = new ArrayList<ArrivalTime>(ntimes);
        
        ArrivalTime nextTime = getNextTime(d);
        int index = times.indexOf(nextTime);
        int size = times.size();
        
        for( int i = 0, j = index; i < ntimes; i++, j++) {
            nextTimes.add( times.get(j % size));
        }
        
        return nextTimes;
    }
    
    /**
     * Add arrival times.
     * 
     * Assumes that the times are added in increasing time order.
     * 
     * @param d
     */
    public void add( ArrivalTime d) {
        times.add(d);
    }
    
    public List<ArrivalTime> getArrivalTimes() {
    	return times;
    }
        
}
