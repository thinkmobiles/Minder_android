package com.example.minder_android.core.location_api;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.location.LocationManager;

import static com.example.minder_android.core.Const.LOCATION_UPDATE_INTERVAL;

/**
 * Created by Max on 17.07.15.
 */
class AOSPLocationAdapter extends AbsLocationAdapter {
    private final static String DEBUG_TAG = "minder_android : AOSPLocationAdapter";

    public AOSPLocationAdapter(Context _context) {
        super(_context);
    }

    @Override
    public void subscribeLocationUpdates(Class<? extends BroadcastReceiver> _subscriber) {
        LocationManager locationManager =  (LocationManager) mContext.getSystemService(Context.LOCATION_SERVICE);

        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, LOCATION_UPDATE_INTERVAL, 0,
                    createPendingIntent(_subscriber));
        }
        if (locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, LOCATION_UPDATE_INTERVAL, 0,
                    createPendingIntent(_subscriber));
        }
    }

    @Override
    public void unsubscribeLocationUpdates(Class<? extends BroadcastReceiver> _subscriber) {
        LocationManager locationManager =  (LocationManager) mContext.getSystemService(Context.LOCATION_SERVICE);
        locationManager.removeUpdates(createPendingIntent(_subscriber));
    }


}
