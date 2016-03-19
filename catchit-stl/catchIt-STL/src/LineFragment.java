package com.flysystems.catchit_stl;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;

import com.flysystems.catchit_stl.model.ArrivalTime;
import com.flysystems.catchit_stl.model.MetroSystem;
import com.flysystems.catchit_stl.model.Route;
import com.flysystems.catchit_stl.model.Station;
import com.flysystems.catchit_stl.model.TimesOnStation;

public class LineFragment extends Fragment{

	private List<Date> timesOnStation;
	private Route route;
	private Station station;
    DateFormat df = new SimpleDateFormat("HH:mm:ss");
    private TextView tv;
    private Spinner timeSpinner;
    private CountDownTimer cdt;
    private MetroSystem sys;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		route = (Route) getArguments().getSerializable("LINE");
		station = (Station) getArguments().getSerializable("STATION");
		sys = (MetroSystem) getArguments().getSerializable("SYSTEM");
		super.onCreate(savedInstanceState);
	} 
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

        Log.d("LineFragment.onCreateView", "start");
		View v = inflater.inflate(R.layout.linefragment_layout, container,
				false);

		Button lineButton = (Button) v.findViewById(R.id.linebutton);
		lineButton.setText(route.getName());
//		lineButton.setBackgroundColor(Color.RED);
		
		Resources res = getResources();
		Drawable redline = null;
		Drawable blueline = null;
		try{
			redline = Drawable.createFromXml(res, res.getXml(R.drawable.redline_custom_button));
			blueline = Drawable.createFromXml(res, res.getXml(R.drawable.blueline_custom_button));
		}catch (Exception e){
			Log.e("Error", "Can't load custom line button drawable");
		}
	
		if("Red West".equals(route.getName()) || "Red East".equals(route.getName())){
			lineButton.setBackground(redline);
		}else{
			lineButton.setBackground(blueline);
		}

		timeSpinner = (Spinner) v.findViewById(R.id.arrival_time_spinner);
		//Map<Station, TimesOnStation> map = route.getTimesOnStation();

        tv = (TextView) v.findViewById(R.id.timer);
        Date now = new Date();

        timesOnStation = sys.getTimesOnStation(station, route, now, 5);
        Log.d("LineFragment.onCreateView", "stationName = " + station.getName());
        Log.d("LineFragment.onCreateView", "routeName = " + route.getName());
        Log.d("LineFragment.onCreateView", "now = " + now);
        
        final List<Date> timesInSpinner = timesOnStation;
		ArrayAdapter<Date> adapter = new ArrayAdapter<Date>(v.getContext(),
				android.R.layout.simple_spinner_dropdown_item, timesInSpinner);
		timeSpinner.setAdapter(adapter);
		timeSpinner.setOnItemSelectedListener(new OnItemSelectedListener(){

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int pos, long id) {
			    
//			    Date selectedDate = (Date) parent.getItemAtPosition(pos);
				
	            Log.d("LineFragment.OnItemSelected", "pos = " + pos);
//                ArrivalTime selectedTime = timesOnStation.get(pos);
                Date selectedTime = (Date) parent.getItemAtPosition(pos);
		        Date now = new Date();

		        final long delta = (selectedTime.getTime() - now.getTime());
	            Log.d("LineFragment.OnItemSelected", "selectedTime = " + selectedTime.getTime() + " now = " + now.getTime() + " delta = " + delta);
		        
		        cdt.cancel();
		        cdt = new CustomCountDownTimer( LineFragment.this, delta, 1000);
		        cdt.start();
		        
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
			}
			
		});
		
		timeSpinner.setSelection(0);
		long delta = getDeltaTime(now, timesOnStation);
        Log.d("LineFragment.onCreateView", "delta = " + delta);
        cdt = new CustomCountDownTimer(LineFragment.this, delta, 1000).start();
        
        Log.d("LineFragment.onCreateView", "return");
		return v;
	}
	
	public TextView getTextView() {
	    return tv;
	}
	
	protected void init() {
        Log.d("LineFragment.init", "timesOnStation = " + timesOnStation);
        Date now = new Date();
        
//        final List<ArrivalTime> timesInSpinner = timesOnStation.getNextTimes(now, 5);
        final List<Date> timesInSpinner = sys.getTimesOnStation(station, route, now, 5);
        Log.d("LineFragment.init", "timesInSpinner = " + timesInSpinner);
        Log.d("LineFragment.init", "activity = " + getActivity() + " station= " + station.getName() + " route= " + route.getName());
        Log.d("LineFragment.init", "resource id = " + android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter<Date> adapter = new ArrayAdapter<Date>(getActivity(),
                android.R.layout.simple_spinner_dropdown_item, timesInSpinner);
        timeSpinner.setAdapter(adapter);
        
        timeSpinner.setSelection(0);
                
        final long delta = getDeltaTime(now, timesOnStation );
        
        cdt = new CustomCountDownTimer(LineFragment.this, delta, 1000).start();
    }
	
	@Override
	public void onDetach() {
		super.onDetach();
        Log.d("LineFragment.onDetach", "detaching line fragment!" + " station= " + station.getName() + " route= " + route.getName());
        cdt.cancel();
	}
	
	public static Fragment newInstance(MetroSystem test, Route route, Station station) {
		Fragment fragment = new LineFragment();
		Bundle bundle = new Bundle(1);
		bundle.putSerializable("LINE", route);
		bundle.putSerializable("STATION", station);
		bundle.putSerializable("SYSTEM", test);

		fragment.setArguments(bundle);
		return fragment;
	}
	
	public Route getRoute() {
		return route;
	}
	
	public Station getStation() {
		return station;
	}
	 public long getDeltaTime( Date t, List<Date> times) {
	        long delta = 0;
	        
	        for( Date at: times) {
	            if( at.after(t)) {
	                delta = at.getTime() - t.getTime();
	                Log.d("LineFragment.getDeltaTime", "at = " + at.getTime() + " t = " + t.getTime() + " delta = " + delta );
	                break;
	            }
	        }
	        return delta;
	    }
}
