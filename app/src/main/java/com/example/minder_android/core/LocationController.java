package com.example.minder_android.core;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;

import static com.example.minder_android.core.Const.ACTION_STORE_LOCATION;
import static com.example.minder_android.core.Const.LOCATION_UPDATE_INTERVAL;

/**
 * Created by Max on 17.07.15.
 */
public class LocationController {
    private static String DEBUG_TAG = "minder_android : LocationController";

    public static void subscribeLocationUpdates(final Context _context) {
        LocationManager locationManager =  (LocationManager) _context.getSystemService(Context.LOCATION_SERVICE);

        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, LOCATION_UPDATE_INTERVAL, 0,
                    createPendingIntent(_context));
        }
        if (locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, LOCATION_UPDATE_INTERVAL, 0,
                    createPendingIntent(_context));
        }
    }

    private static PendingIntent createPendingIntent(Context _context) {
        return PendingIntent.getBroadcast(_context, 0, createStoreLocationReceiverIntent(_context), 0);
    }

    public static void unsubscribeLocationUpdates(Context _context) {
        LocationManager locationManager =  (LocationManager) _context.getSystemService(Context.LOCATION_SERVICE);
        locationManager.removeUpdates(PendingIntent.getBroadcast(_context, 0, createStoreLocationReceiverIntent(_context), 0));
    }

    private static Intent createStoreLocationReceiverIntent(Context _context) {
        Intent i=new Intent(_context, StoreLocationReceiver.class);
        i.setAction(ACTION_STORE_LOCATION);
        return i;
    }

}
