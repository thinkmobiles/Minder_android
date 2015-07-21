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
    private static LocationManager mLocationManager;
    private static String DEBUG_TAG = "minder_android : LocationController";

    public static void init(final Context _context) {
        mLocationManager = (LocationManager) _context.getSystemService(Context.LOCATION_SERVICE);

        if (mLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, LOCATION_UPDATE_INTERVAL, 0,
                    createPendingIntent(_context));
        }
        if (mLocationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
            mLocationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, LOCATION_UPDATE_INTERVAL, 0,
                    createPendingIntent(_context));
        }
    }

    private static PendingIntent createPendingIntent(Context _context) {
        return PendingIntent.getBroadcast(_context, 0, createStoreLocationReceiverIntent(_context), 0);
    }

    public static void removeLocationUpdates(Context _context) {
        if (mLocationManager == null) {
            mLocationManager = (LocationManager) _context.getSystemService(Context.LOCATION_SERVICE);
        }
        mLocationManager.removeUpdates(PendingIntent.getBroadcast(_context, 0, createStoreLocationReceiverIntent(_context), 0));
    }

    private static Intent createStoreLocationReceiverIntent(Context _context) {
        Intent i=new Intent(_context, StoreLocationReceiver.class);
        i.setAction(ACTION_STORE_LOCATION);
        return i;
    }

}
