package com.example.routemarker;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.content.Context;
import android.location.Criteria;
import android.location.GpsStatus;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.PowerManager;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.CompoundButton.OnCheckedChangeListener;

import com.example.routemarker.BearingFrameLayout;
//import com.deitel.routetracker.R;						//TODO

//import com.deitel.routemarker.RouteOverlay;			//TODO	
import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;

public class RouteMarker extends MapActivity {

	private LocationManager locationManager; // gives location data
	private MapView mapView; // displays a Google map
	private MapController mapController; // manages map pan/zoom
	private Location previousLocation; // previous reported location
	//private RouteOverlay routeOverlay; // Overlay that shows route on map		//TODO
	private long distanceTraveled; // total distance the user traveled
	private BearingFrameLayout bearingFrameLayout; // rotates the MapView
	private boolean tracking; // whether app is currently tracking
	private long startTime; // time (in milliseconds) when tracking starts
	private PowerManager.WakeLock wakeLock; // used to prevent device sleep
	private boolean gpsFix; // whether we have a GPS fix for accurate data

	private static final double MILLISECONDS_PER_HOUR = 1000 * 60 * 60;
	private static final double MILES_PER_KILOMETER = 0.621371192;
	private static final int MAP_ZOOM = 18; // Google Maps supports 1-21

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_route_marker);
		
		 // create new MapView using your Google Maps API key
	      bearingFrameLayout = new BearingFrameLayout(this, 
	         getResources().getString(R.string.google_maps_api_key));
	      
	      // add bearingFrameLayout to mainLayout
	      FrameLayout mainLayout = 
	         (FrameLayout) findViewById(R.id.mainLayout);
	      mainLayout.addView(bearingFrameLayout, 0);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_route_marker, menu);
		return true;
	}

	// Google terms of use require this method to return
	// true if you're displaying route information like driving directions
	@Override
	protected boolean isRouteDisplayed() {
		return false; // we aren't displaying route information
	} // end method isRouteDisplayed

}
