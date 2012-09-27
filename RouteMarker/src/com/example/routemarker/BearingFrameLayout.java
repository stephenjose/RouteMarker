package com.example.routemarker;

import com.google.android.maps.MapView;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.view.Display;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;

public class BearingFrameLayout extends FrameLayout {

	private int scale = 0; // amount to scale layout
	private MapView mapView; // displays Google maps
	private float bearing = 0f; // compass bearing

	// returns layout parameters for MapView
	public LayoutParams getChildLayoutParams() 
	   {
	      Display display = 
	         ((Activity) getContext()).getWindowManager().getDefaultDisplay();    
	      
	      Point size = new Point();
	      display.getSize(size);
	      int w = size.x;
	      int h = size.y;
	      scale = (int) Math.sqrt((w * w) + (h * h));

	      return new LayoutParams(scale, scale);
	   } // end method getChildLayoutParams
	
	public BearingFrameLayout(Context context, String apiKey) {
		super(context);
		
		mapView = new MapView(context, apiKey); // create new MapView
		mapView.setClickable(true); // allow user interactions with the map
		mapView.setEnabled(true); // enables the MapView to generate events
		mapView.setSatellite(false); // display map image
		mapView.setBuiltInZoomControls(true); // enable zoom controls

		// set MapView's layout
		mapView.setLayoutParams(getChildLayoutParams());
		addView(mapView); // add MapView to this layout
	}

}
