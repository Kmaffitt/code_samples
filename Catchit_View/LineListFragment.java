package com.android.metrotrackr;

import java.io.Serializable;
import java.util.List;

import com.example.catchit_stl.model.Route;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class LineListFragment extends Fragment{
	private Route route;
	private String TAG = "LineFragment";
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		route = (Route) getArguments().getSerializable("ROUTE");
				
	}
	
	/*
	 * we need an extra layer with linefragments added to this linelist fragment.
	 */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.line_fragment, container, false);

//        View convertView = getActivity().getLayoutInflater().inflate(
//                R.layout.line_fragment, null);

        Log.d(TAG, "drawing lines, route = " + route);
        Button line = (Button) v.findViewById(R.id.line);
        line.setText(route.getName());

        Spinner arrivaltime = (Spinner) v.findViewById(R.id.arrivaltime);
        // configure spinner here

        TextView timer = (TextView) v.findViewById(R.id.timer);
        // do timer voodoo here

        return v;
    }
    
	private class LineAdapter extends ArrayAdapter<Route> {
		private String TAG = "LineAdapter";
		public LineAdapter(List<Route> routes) {
			super(getActivity(), 0, routes);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if (convertView == null) {
				convertView = getActivity().getLayoutInflater().inflate(
						R.layout.line_fragment, null);
			}
			Log.d(TAG, "drawing lines, pos = " + position);
			Button line = (Button)convertView.findViewById(R.id.line);
			line.setText(getItem(position).getName());
			

			Spinner arrivaltime = (Spinner) convertView
					.findViewById(R.id.arrivaltime);
			// configure spinner here

			TextView timer = (TextView) convertView.findViewById(R.id.timer);
			// do timer voodoo here
			return convertView;
			
		}
	}

	public static Fragment newInstance(Route route) {
		Fragment llf = new LineListFragment();
		Bundle bundle = new Bundle(1);
		bundle.putSerializable("ROUTE", (Serializable) route);
		llf.setArguments(bundle);
		return llf;
	}
}
