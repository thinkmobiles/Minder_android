package com.example.minder_android.core.location_api;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.location.LocationManager;

import com.example.minder_android.R;

import static com.example.minder_android.core.Const.LOCATION_UPDATE_INTERVAL;

/**
 * Created by Max on 17.07.15.
 *  Subscribes supplied Broadcast receiver to location updates using
 *  LocationManager GPS_PROVIDER or NETWORK_PROVIDER , if possible.
 * Performs basic error resolution
 */
class AOSPLocationAdapter extends AbsLocationAdapter {
    private final static String DEBUG_TAG = "minder_android : AOSPLocationAdapter";

    public AOSPLocationAdapter(Context _context) {
        super(_context);
    }

    @Override
    public void subscribeLocationUpdates(Class<? extends BroadcastReceiver> _subscriber) {
        super.subscribeLocationUpdates(_subscriber);

        LocationManager locationManager =  (LocationManager) mContext.getSystemService(Context.LOCATION_SERVICE);

        if (!(locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
                || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER))) {
            mSubscriptionResultListener.onFailure();
            return;
        }

        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, LOCATION_UPDATE_INTERVAL, 0,
                    createPendingIntent(_subscriber));
        }
        if (locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, LOCATION_UPDATE_INTERVAL, 0,
                    createPendingIntent(_subscriber));
        }
        mSubscriptionResultListener.onSuccess(mContext.getString(R.string.connected_te_service , this.getClass().getSimpleName().toString()));
    }

    @Override
    public void unsubscribeLocationUpdates(Class<? extends BroadcastReceiver> _subscriber) {
        super.subscribeLocationUpdates(_subscriber);
        LocationManager locationManager =  (LocationManager) mContext.getSystemService(Context.LOCATION_SERVICE);
        locationManager.removeUpdates(createPendingIntent(_subscriber));
    }


}
