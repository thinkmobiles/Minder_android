package com.example.minder_android.core;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;

import com.commonsware.cwac.wakeful.WakefulIntentService;
import com.example.minder_android.core.location_api.LocationAPIController;
import com.google.android.gms.location.LocationResult;

import static com.example.minder_android.core.Const.ACTION_STORE_LOCATION;
import static com.example.minder_android.core.Const.ACTION_SYNC;
import static com.example.minder_android.core.Const.KEY_LOCATION;

/**
 * Created by Max on 21.07.15.
 */
public class AppBroadcastsReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context _context, Intent _intent) {
//        android.os.Debug.waitForDebugger();
        if (_intent.getAction() == ACTION_SYNC) {
            Intent serviceIntent = createSyncServiceIntent(_context);
            WakefulIntentService.sendWakefulWork(_context, serviceIntent);
        }
        if (_intent.getAction() == ACTION_STORE_LOCATION) {
            Intent serviceIntent = createStoreLocationServiceIntent(_context);
            Location location = (Location) (LocationResult.hasResult(_intent)
                                ? LocationResult.extractResult(_intent).getLastLocation()
                                : _intent.getExtras().getParcelable(LocationManager.KEY_LOCATION_CHANGED));
            serviceIntent.putExtra(KEY_LOCATION, location);
            if (location != null) {
                WakefulIntentService.sendWakefulWork(_context, serviceIntent);
            }
        }
        else {
            if (isSubscriptionOnRebootNeeded()) {
                LocationAPIController controller = LocationAPIController.INSTANCE.setContext(_context);
                controller.subscribeLocationUpdates(this.getClass());
            }
        }
    }

    private Intent createSyncServiceIntent(Context _context) {
        Intent i=new Intent(_context, PhotosSyncLocatorService.class);
        return i;
    }

    // method stub for future scheduling after device reboot
    private static boolean isSubscriptionOnRebootNeeded() { return false; }

    private static Intent createStoreLocationServiceIntent(Context _context) {
        Intent i=new Intent(_context, StoreLocationService.class);
        i.setAction(ACTION_STORE_LOCATION);
        return i;
    }

}