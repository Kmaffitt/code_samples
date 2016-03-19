package com.flysystems.catchit_stl;

import java.util.List;

import com.flysystems.catchit_stl.model.MetroSystem;
import com.flysystems.catchit_stl.model.MetroSystemOnDiskModel;
import com.flysystems.catchit_stl.model.MetroSystemTest;
import com.flysystems.catchit_stl.model.Station;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

public class StationPager extends FragmentActivity{
	private ViewPager mViewPager;
	private MetroSystem sys;
	private List<Station> mStations;
	
@Override
public void onCreate(Bundle savedInstanceState){
	super.onCreate(savedInstanceState);
	mViewPager = new ViewPager(this);
	mViewPager.setId(R.id.viewPager);
	setContentView(mViewPager);
	sys = new MetroSystemOnDiskModel( this.getResources());
//	sys = new MetroSystemTest();
	
	mStations = sys.getStationsByDistance(38.649379, -90.301000);
	
	FragmentManager fm = getSupportFragmentManager();
	mViewPager.setAdapter(new FragmentStatePagerAdapter(fm){

		@Override
		public Fragment getItem(int pos) {
			Station station = mStations.get(pos);
			return StationFragment.newInstance(sys, mStations, pos);
		}

		@Override
		public int getCount() {
			return mStations.size();
		}
		
	});
	
}
}
