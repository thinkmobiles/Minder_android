package com.example.minder_android.core.location_api;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentSender;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.minder_android.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import static com.example.minder_android.core.Const.FUSED_LOCATION_RESOLUTION_REQUEST_ID;
import static com.example.minder_android.core.Const.LOCATION_UPDATE_INTERVAL;


/**
 * Created by Max on 22.07.15.
 */
class FusedLocationAdapter extends AbsLocationAdapter implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private enum  ACTION {UNDEFINED, SUBSCRIBE, UNSUBSCRIBE};

    private GoogleApiClient mClient;
    private ACTION mAction = ACTION.UNDEFINED;

    public FusedLocationAdapter(Context _context) {
        super(_context);
        mClient = new GoogleApiClient.Builder(mContext)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();
    }

    @Override
    public void subscribeLocationUpdates(Class<? extends BroadcastReceiver> _subscriber) {
        super.subscribeLocationUpdates(_subscriber);
        mAction = ACTION.SUBSCRIBE;
        if (!mClient.isConnected()) {
            mClient.connect();
        } else {
            subscribe();
        }

    }

    @Override
    public void unsubscribeLocationUpdates(Class<? extends BroadcastReceiver> _subscriber) {
        super.unsubscribeLocationUpdates(_subscriber);
        mAction = ACTION.UNSUBSCRIBE;
        if (!mClient.isConnected()) {
            mClient.connect();
        } else {
            unsubscribe();
        }
    }

    @Override
    public void onConnected(Bundle bundle) {
        switch (mAction){
            case SUBSCRIBE:
                subscribe();
                mConnectionListener.onConnected();
                break;
            case UNSUBSCRIBE:
                unsubscribe();
                break;
            case UNDEFINED:
                mConnectionListener.onConnectionFailed();
        }
    }

    private void unsubscribe() {
        LocationServices.FusedLocationApi
                .removeLocationUpdates(mClient, createPendingIntent(mSubscriber));
    }

    private void subscribe() {
        LocationRequest request=new LocationRequest()
                .setExpirationDuration(LOCATION_UPDATE_INTERVAL * 10)
                .setInterval(LOCATION_UPDATE_INTERVAL)
                .setFastestInterval(LOCATION_UPDATE_INTERVAL / 2);
        LocationServices.FusedLocationApi.requestLocationUpdates(mClient, request, createPendingIntent(mSubscriber));
    }

    @Override
    public void onConnectionSuspended(int i) {
        mConnectionListener.onConnectionFailed();
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        boolean isResolved = false;
        if (connectionResult.hasResolution()) {
            try {
                connectionResult.startResolutionForResult((Activity) mContext, FUSED_LOCATION_RESOLUTION_REQUEST_ID);
                isResolved = true;
            }
            catch (IntentSender.SendIntentException e) {
                Log.e(((Object) this).getClass().getSimpleName(),
                        "Exception trying to startResolutionForResult()", e);
            }
        }
        if (!isResolved) {
            Toast.makeText(mContext, R.string.no_fused, Toast.LENGTH_LONG).show();
            mConnectionListener.onConnectionFailed();
        }
    }

    void reconnect() {
        if (!mClient.isConnected()) {
            mClient.connect();
        }
    }
}
