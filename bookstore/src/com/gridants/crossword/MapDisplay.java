package com.gridants.crossword;

import java.util.List;

import org.osmdroid.DefaultResourceProxyImpl;
import org.osmdroid.ResourceProxy;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Overlay;
import org.osmdroid.views.overlay.OverlayItem;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;

public class MapDisplay extends Activity {

	Overlay currentPositionOverlay;
	Drawable drawableCurrent;

	Overlay offerOverlay;
	Drawable drawableOffer;

	ResourceProxy resourceProxy;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.map_display);
		
		
		try
		{


		resourceProxy = new DefaultResourceProxyImpl(getApplicationContext());

		MapView mapView = (MapView) findViewById(R.id.mapview);
		mapView.setTileSource(TileSourceFactory.MAPNIK);
		mapView.setUseDataConnection(false);
		mapView.setClickable(true);
		mapView.setBackgroundColor(0);
		mapView.setMultiTouchControls(true);
		mapView.getController().setZoom(6);

		drawableCurrent = this.getResources().getDrawable(R.drawable.circlegw);

		drawableOffer = this.getResources().getDrawable(R.drawable.offer50);

		List<Overlay> mapOverlays = mapView.getOverlays();
		currentPositionOverlay = addPoint(drawableCurrent, 0, 0);
		mapOverlays.add(currentPositionOverlay);

		offerOverlay = addPoint(drawableOffer, 1000000, 1000000);
		mapOverlays.add(offerOverlay);
		// Invalidate map
		mapView.postInvalidate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public HelloItemizedOverlay addPoint(Drawable drawable, int x, int y) {
		HelloItemizedOverlay mMyOverlay = new HelloItemizedOverlay(drawable,
				resourceProxy, this);
		GeoPoint point = new GeoPoint(y, x);
		OverlayItem overlayitem = new OverlayItem("I'm in Mexico City!",
				"Hola, Mundo!", point);
		mMyOverlay.addOverlay(overlayitem);
		return mMyOverlay;
	}

	public HelloItemizedOverlay addPoint(Drawable drawable, int[] x, int[] y) {
		HelloItemizedOverlay mMyOverlay = new HelloItemizedOverlay(drawable,
				resourceProxy, this);
		for (int i = 0; i < x.length; i++) {
			GeoPoint point = new GeoPoint(y[i], x[i]);
			OverlayItem overlayitem = new OverlayItem("I'm in Mexico City!",
					"Hola, Mundo!", point);
			mMyOverlay.addOverlay(overlayitem);
		}
		return mMyOverlay;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.map_display, menu);
		return true;
	}

}
