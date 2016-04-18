package com.mapzen.android;

import com.mapzen.tangram.MapController;

import org.junit.Test;

import static com.mapzen.android.TestHelper.getMockContext;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class MapViewTest {
    private MapView mapView = new MapView(getMockContext());

    @Test public void shouldNotBeNull() throws Exception {
        assertThat(mapView).isNotNull();
    }

    @Test public void getMapAsync_shouldInjectMapInitializer() throws Exception {
        mapView.getMapAsync(new TestCallback() {
            @Override public void onMapReady(MapController mapController) {
            }
        });

        assertThat(mapView.mapInitializer).isNotNull();
    }

    @Test public void getMapAsync_shouldInvokeMapInitializer() throws Exception {
        final MapInitializer mapInitializer = mock(MapInitializer.class);
        final MapView.OnMapReadyCallback callback = new TestCallback();
        mapView.mapInitializer = mapInitializer;
        mapView.getMapAsync(callback);
        verify(mapInitializer, times(1)).init(mapView, callback);
    }

    @Test public void getMapAsync_shouldInvokeMapInitializerWithApiKey() throws Exception {
        final MapInitializer mapInitializer = mock(MapInitializer.class);
        final MapView.OnMapReadyCallback callback = new TestCallback();
        final String key = "vector-tiles-test-key";
        mapView.mapInitializer = mapInitializer;
        mapView.getMapAsync(callback, key);
        verify(mapInitializer, times(1)).init(mapView, callback, key);
    }
}
