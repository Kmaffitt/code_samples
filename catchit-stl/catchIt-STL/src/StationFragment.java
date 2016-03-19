package com.flysystems.catchit_stl;

import java.io.Serializable;
import java.util.List;

import com.flysystems.catchit_stl.model.MetroSystem;
import com.flysystems.catchit_stl.model.MetroSystemTest;
import com.flysystems.catchit_stl.model.Route;
import com.flysystems.catchit_stl.model.Station;



import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class StationFragment extends Fragment{
	private List<Station> stations;
	private MetroSystem sys;
	private int pos;
	

	@Override
	public void onCreate(Bundle savedInstanceState) {
		sys = (MetroSystem) getArguments().getSerializable("SYSTEM");
		stations = (List<Station>) getArguments().getSerializable("STATIONS");
		pos = (Integer) getArguments().getSerializable("POS");

		super.onCreate(savedInstanceState);
		
		
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
        Log.d("StationFragment.onCreateView", "start for pos = " + pos);
        Log.d("StationFragment.onCreateView", "stationName = " + stations.get(pos).getName());
		List<Route> routes = sys.getRoutes(stations.get(pos));
        Log.d("StationFragment.onCreateView", "nRoutes = " + routes.size());
        
		View v;
		if (routes.size() == 2) {
			v = inflater.inflate(R.layout.station2fragment_layout, container,
					false);
		} else {
			v = inflater.inflate(R.layout.station4fragment_layout, container,
					false);
		}
		
		TextView tv = (TextView) v.findViewById(R.id.stationName);
		tv.setText(stations.get(pos).getName());

		FragmentManager fm = getChildFragmentManager();
		FragmentTransaction ft = fm.beginTransaction();
		Fragment fragment = fm.findFragmentById(R.id.linefragment1);
		if (fragment == null) {
			fragment = LineFragment.newInstance(sys, routes.get(0),
					stations.get(pos));
			ft.add(R.id.linefragment1, fragment);
		}

		fragment = fm.findFragmentById(R.id.linefragment2);
		if (fragment == null) {
			fragment = LineFragment.newInstance(sys, routes.get(1),
					stations.get(pos));
			ft.add(R.id.linefragment2, fragment);
		}

		if (routes.size() == 4) {
			fragment = fm.findFragmentById(R.id.linefragment3);
			if (fragment == null) {
				fragment = LineFragment.newInstance(sys, routes.get(2),
						stations.get(pos));
				ft.add(R.id.linefragment3, fragment);
			}

			fragment = fm.findFragmentById(R.id.linefragment4);
			if (fragment == null) {
				fragment = LineFragment.newInstance(sys, routes.get(3),
						stations.get(pos));
				ft.add(R.id.linefragment4, fragment);
			}

		}

		ft.commit();
        Log.d("StationFragment.onCreateView", "exit for pos = " + pos);
		return v;
	}
	
	public static Fragment newInstance(MetroSystem test, List<Station> stations, int pos) {
		Fragment fragment = new StationFragment();
		Bundle bundle = new Bundle(1);
		bundle.putSerializable("SYSTEM", (Serializable) test);
		bundle.putSerializable("STATIONS", (Serializable)stations);
		bundle.putSerializable("POS", pos);
		fragment.setArguments(bundle);
		return fragment;
	}
}
