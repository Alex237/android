package com.mapzen.android;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A map component in an app. This fragment is the simplest way to place a map in an application.
 * It's a wrapper around a view of a map to automatically handle the necessary life cycle needs.
 */
public class MapFragment extends Fragment {
    MapView mapView;

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        mapView = (MapView) inflater.inflate(R.layout.fragment_map, container, false);
        return mapView;
    }

    /**
     * Asynchronously creates the map and configures the vector tiles API key using the string
     * resource declared in the client application. Uses default stylesheet (bubble wrap).
     */
    public void getMapAsync(final OnMapReadyCallback callback) {
        mapView.getMapAsync(callback);
    }

    /**
     * Asynchronously creates the map and configures the vector tiles API key using the given
     * string parameter. Uses default stylesheet (bubble wrap).
     */
    public void getMapAsync(final OnMapReadyCallback callback, final String key) {
        mapView.getMapAsync(callback, key);
    }

    @Override public void onDestroy() {
        super.onDestroy();
        if (mapView != null) {
            mapView.onDestroy();
        }
    }
}
