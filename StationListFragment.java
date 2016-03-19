package com.android.metrotrackr;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.example.catchit_stl.model.MetroSystem;
import com.example.catchit_stl.model.Route;
import com.example.catchit_stl.model.Station;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class StationListFragment extends ListFragment {
	private ArrayList<com.example.catchit_stl.model.Station> mStations;
	private ArrayList<Route> mRoutes;
	private static final String TAG = "StationListFragment";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		InputStream iss = getResources().openRawResource(R.raw.stations);
		InputStream stream;
		HashMap <String, InputStream> map = new HashMap();
		
		
		Log.d(TAG, "Opening resource streams");
		stream = getResources().openRawResource(R.raw.metrolinkweekdayschedule_blue_east);
		map.put("Blue East", stream);
		stream = getResources().openRawResource(R.raw.metrolinkweekdayschedule_blue_west); 
		map.put("Blue West", stream);
		stream = getResources().openRawResource(R.raw.metrolinkweekdayschedule_red_west); 
		map.put("Red West", stream);
		stream = getResources().openRawResource(R.raw.metrolinkweekdayschedule_red_east); 
		map.put("Red East", stream);
		
		MetroSystem sys = null;
		try {
			sys = MetroSystem.getInstance(iss, map);
			//streams closed in readStations + readSchedule 
		} catch (IOException e) {
			e.printStackTrace();
		}
		mStations = sys.getAllStations();
		mRoutes = sys.getAllRoutes();

		StationAdapter adapter = new StationAdapter(mStations);
		setListAdapter(adapter);

	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		Station s = ((StationAdapter) getListAdapter()).getItem(position);
		Log.d(TAG, s.getName() + " was clicked");
	}
	
	

	private class StationAdapter extends ArrayAdapter<Station> {
		public StationAdapter(ArrayList<Station> stations) {
			super(getActivity(), 0, stations);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if (convertView == null) {
				convertView = getActivity().getLayoutInflater().inflate(
						R.layout.station_fragment, null);
			}
			Station s = getItem(position);
			
			List<Route> sroutes = s.getRoutes();
			
			TextView station = (TextView) convertView.findViewById(R.id.stationname);
			station.setText(s.getName());
			
			android.support.v4.app.FragmentManager fm = getChildFragmentManager();
			
			FragmentTransaction ft = fm.beginTransaction();
			
			for( int i=0; i < sroutes.size(); i++) {
			    ft.add(R.id.linecontainer, LineListFragment.newInstance(sroutes.get(i)));
			}
			
			ft.commit();
			
			return convertView;
			
//			Button line1 = (Button)convertView.findViewById(R.id.line1);
//			line1.setText(sroutes.get(0).getName());
//			
//			Button line2 = (Button)convertView.findViewById(R.id.line2);
//			line2.setText(sroutes.get(1).getName());
//
//			Spinner arrivaltime = (Spinner) convertView
//					.findViewById(R.id.arrivaltime1);
			// configure spinner here

			//TextView timer = (TextView) convertView.findViewById(R.id.timer1);
			// do timer voodoo here

			
		}
	}
}
