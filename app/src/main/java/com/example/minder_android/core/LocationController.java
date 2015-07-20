package com.example.minder_android.core;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import static com.example.minder_android.core.Const.LOCATION_UPDATE_INTERVAL;

/**
 * Created by Max on 17.07.15.
 */
public class LocationController {
    private static LocationManager mLocationManager;
    private static Location mCurrentLocation;

    public static Location getCurrentLocation() {
        return mCurrentLocation;
    }

    public static void setCurrentLocation(Location _location) {
        mCurrentLocation = _location;
    }

    public static void init(Context _context) {
        mLocationManager = (LocationManager) _context.getSystemService(Context.LOCATION_SERVICE);
        // Define a listener that responds to location updates
        LocationListener locationListener = new LocationListener() {
            public void onLocationChanged(Location location) {
                // Called when a new location is found by the network location provider.
                setCurrentLocation(location);
            }

            public void onStatusChanged(String provider, int status, Bundle extras) {}

            public void onProviderEnabled(String provider) {
                if (provider.equals(LocationManager.GPS_PROVIDER)) {
                    mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, LOCATION_UPDATE_INTERVAL, 0, this);
                } else if (provider.equals(LocationManager.NETWORK_PROVIDER)) {
                    mLocationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, LOCATION_UPDATE_INTERVAL, 0, this);
                }
            }

            public void onProviderDisabled(String provider) {}
        };

        // Register the listener with the Location Manager to receive location updates
        if (mLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, LOCATION_UPDATE_INTERVAL, 0, locationListener);
        }
        if (mLocationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
            mLocationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, LOCATION_UPDATE_INTERVAL, 0, locationListener);
        }
    }
}
