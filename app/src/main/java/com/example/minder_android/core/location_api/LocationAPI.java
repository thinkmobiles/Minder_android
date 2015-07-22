package com.example.minder_android.core.location_api;

import android.content.BroadcastReceiver;
import android.content.Context;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;

/**
 * Created by Max on 22.07.15.
 */
public class LocationAPI {
    private static AbsLocationAdapter mAdapter;

    public static void unsubscribeLocationUpdates(final Context _context, Class<? extends BroadcastReceiver> _subsriber) {
        getLocationAdapter(_context).unsubscribeLocationUpdates(_subsriber);
    }

    public static void subscribeLocationUpdates(final Context _context, Class<? extends BroadcastReceiver>  _subsriber) {
        getLocationAdapter(_context).subscribeLocationUpdates(_subsriber);
    }

    private static AbsLocationAdapter getLocationAdapter(Context _context) {
        if (mAdapter != null) return mAdapter;
        if (isFusedLocationAvailable(_context)) {
            mAdapter = new FusedLocationAdapter(_context);
        } else {
            mAdapter = new AOSPLocationAdapter(_context);
        }
        return  mAdapter;
    }

    private static boolean isFusedLocationAvailable(Context _context) {
        return GooglePlayServicesUtil.isGooglePlayServicesAvailable(_context) == ConnectionResult.SUCCESS;
    }

    public static boolean isSubscribedLocationUpdates(final Context _context, Class<? extends BroadcastReceiver>  _subsriber) {
        return getLocationAdapter(_context).isSubscribedLocationUpdates(_subsriber);
    }



}
