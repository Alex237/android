package com.mapzen.android.sample;

import com.mapzen.android.MapFragment;
import com.mapzen.android.MapManager;
import com.mapzen.android.model.LatLng;
import com.mapzen.android.model.Polyline;
import com.mapzen.tangram.MapController;
import com.mapzen.tangram.MapData;
import com.mapzen.tangram.MapView;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Polyline SDK demo.
 */
public class PolylineMapzenActivity extends AppCompatActivity {

    MapFragment mapFragment;
    MapController mapController;
    MapManager overlayManager;
    MapData polylineData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample_mapzen);

        mapFragment = (MapFragment) getSupportFragmentManager().findFragmentById(R.id.fragment);
        mapFragment.getMapAsync(new com.mapzen.android.MapView.OnMapReadyCallback() {
            @Override public void onMapReady(MapController mapController) {
                PolylineMapzenActivity.this.mapController = mapController;
                configureMap();
            }
        });
    }

    private void configureMap() {
        overlayManager = mapFragment.getMapManager();
        Polyline polyline = new Polyline.Builder()
                .add(new LatLng(40.74433, -73.9903))
                .add(new LatLng(40.734807, -73.984770))
                .add(new LatLng(40.732172, -73.998674))
                .add(new LatLng(40.741050, -73.996142))
                .build();
        polylineData = overlayManager.addPolyline(polyline);

        //mapController.setMapZoom(17f);
        overlayManager.setMyLocationEnabled(true);
    }

    @Override protected void onDestroy() {
        super.onDestroy();
        cleanupMap();
    }

    private void cleanupMap() {
        polylineData.remove();
    }
}
