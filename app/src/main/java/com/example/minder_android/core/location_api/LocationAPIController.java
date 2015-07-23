package com.example.minder_android.core.location_api;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.widget.Toast;

import com.example.minder_android.R;
import com.example.minder_android.core.events.LocationApiConnectionEvent;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;

import de.greenrobot.event.EventBus;

/**
 * Created by Max on 22.07.15.
 */
public class LocationAPIController implements AbsLocationAdapter.IConnection {
    private AbsLocationAdapter mAdapter;
    private Context mContext;
    private LocationUIHelper mUIHelper;
    private Class mSubsriber;

    public LocationAPIController(Context _context) {
        this.mContext = _context;
    }

    public  LocationUIHelper getUIHelper() {
        return mUIHelper == null ? new LocationUIHelper(mContext) : mUIHelper;
    }

    public void unsubscribeLocationUpdates( Class<? extends BroadcastReceiver> _subsriber) {
        mSubsriber = _subsriber;
        getLocationAdapter().unsubscribeLocationUpdates(_subsriber);
    }

    public void subscribeLocationUpdates(Class<? extends BroadcastReceiver>  _subsriber) {
        mSubsriber = _subsriber;
        getLocationAdapter().subscribeLocationUpdates(_subsriber);
    }

    private AbsLocationAdapter getLocationAdapter() {
        if (mAdapter != null) return mAdapter;
        if (isFusedLocationAvailable(mContext)) {
            mAdapter = new FusedLocationAdapter(mContext);
        } else {
            mAdapter = new AOSPLocationAdapter(mContext);
        }
        mAdapter.setConnectionListener(this);
        return  mAdapter;
    }

    private boolean isFusedLocationAvailable(Context _context) {
        return GooglePlayServicesUtil.isGooglePlayServicesAvailable(_context) == ConnectionResult.SUCCESS;
    }

    public boolean isSubscribedLocationUpdates(final Context _context, Class<? extends BroadcastReceiver>  _subsriber) {
        return getLocationAdapter().isSubscribedLocationUpdates(_subsriber);
    }

    public AbsLocationAdapter getAdapter() {
        return mAdapter;
    }

    @Override
    public void onConnected() {
        EventBus.getDefault().postSticky(new LocationApiConnectionEvent(true));
    }

    @Override
    public void onConnectionFailed() {
        if (mAdapter instanceof FusedLocationAdapter) {
            mAdapter.removeConnectionListener();
            mAdapter = new AOSPLocationAdapter(mContext);
            mAdapter.setConnectionListener(this);
            mAdapter.subscribeLocationUpdates(mSubsriber);
        } else {
            Toast.makeText(mContext, R.string.no_location_profider_available, Toast.LENGTH_LONG).show();
            EventBus.getDefault().postSticky(new LocationApiConnectionEvent(false));
        }
    }
}
