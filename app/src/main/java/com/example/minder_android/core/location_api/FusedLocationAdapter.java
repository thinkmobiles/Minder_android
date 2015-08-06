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
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStatusCodes;

import static com.example.minder_android.core.Const.FUSED_LOCATION_RESOLUTION_REQUEST_ID;
import static com.example.minder_android.core.Const.FUSED_LOCATION_SETTINGS_REQUEST_ID;
import static com.example.minder_android.core.Const.LOCATION_UPDATE_INTERVAL;


/**
 * Created by Max on 22.07.15.
 * Google Play Services Fused location provider interface adapter
 * Subscribes supplied Broadcast receiver to location updates, if possible
 * Performs basic error resolution and also uses Google LocationSettings API to turn on Location settings updates if possible
 */
class FusedLocationAdapter extends AbsLocationAdapter implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, ResultCallback<LocationSettingsResult> {


    private enum Action {UNDEFINED, SUBSCRIBE, UNSUBSCRIBE};

    private GoogleApiClient mClient;
    private Action mAction = Action.UNDEFINED;
    private boolean isLocationSettingsChecked = false;

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
        mAction = Action.SUBSCRIBE;
        if (!mClient.isConnected()) {
            mClient.connect();
        } else {
            subscribe();
        }

    }

    @Override
    public void unsubscribeLocationUpdates(Class<? extends BroadcastReceiver> _subscriber) {
        super.unsubscribeLocationUpdates(_subscriber);
        mAction = Action.UNSUBSCRIBE;
        if (!mClient.isConnected()) {
            mClient.connect();
        } else {
            unsubscribe();
        }
    }

    private void unsubscribe() {
        LocationServices.FusedLocationApi
                .removeLocationUpdates(mClient, createPendingIntent(mSubscriber));
    }

    private void subscribe() {
        LocationServices.FusedLocationApi.requestLocationUpdates(mClient, getLocationRequest(), createPendingIntent(mSubscriber));
        mSubscriptionResultListener.onSuccess(mContext.getString(R.string.connected_te_service, this.getClass().getSimpleName().toString()));
    }

    void reconnect() {
        if (!(mClient.isConnected() || mClient.isConnecting())) {
            mClient.connect();
        } else {
            mClient.disconnect();
            mClient.connect();
        }
    }
    void connectionResolutionError(){
        mSubscriptionResultListener.onFailure();
    }
    void settingsResolutionError(){
        mSubscriptionResultListener.onFailure();
    }

    private void checkLocationSettings() {
        LocationSettingsRequest.Builder b =
                new LocationSettingsRequest.Builder()
                        .addLocationRequest(getLocationRequest());
        PendingResult<LocationSettingsResult> result =
                LocationServices.SettingsApi.checkLocationSettings(mClient, b.build());
        result.setResultCallback(this);
    }

    private LocationRequest getLocationRequest(){
        return new LocationRequest()
                .setExpirationDuration(LOCATION_UPDATE_INTERVAL * 10)
                .setInterval(LOCATION_UPDATE_INTERVAL)
                .setFastestInterval(LOCATION_UPDATE_INTERVAL / 2);
    }



    //Callback from LocationSettings API
    @Override
    public void onResult(LocationSettingsResult _result) {
            boolean isSomthingWrong=true;
            switch(_result.getStatus().getStatusCode()) {
                case LocationSettingsStatusCodes.SUCCESS:
                    subscribe();
                    isSomthingWrong=false;
                break;
                case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                    try {
                        _result
                                .getStatus()
                                .startResolutionForResult((Activity) mContext, FUSED_LOCATION_SETTINGS_REQUEST_ID);
                        isSomthingWrong=false;
                    }
                    catch (IntentSender.SendIntentException e) {
        // oops
                    }
                break;
                case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
        // more oops
                break;
            }
            if (isSomthingWrong) {
                Toast.makeText(mContext, R.string.settings_resolution_fail_msg, Toast.LENGTH_LONG).show();
                mSubscriptionResultListener.onFailure();
            }
    }

    //Callback from Play Services
    @Override
    public void onConnected(Bundle bundle) {
        switch (mAction){
            case SUBSCRIBE:
                if (isLocationSettingsChecked) {
                    subscribe();
                } else {
                    checkLocationSettings();
                }
                break;
            case UNSUBSCRIBE:
                unsubscribe();
                break;
            case UNDEFINED:
                mSubscriptionResultListener.onFailure();
        }
    }

    //Callback from Play Services
    @Override
    public void onConnectionSuspended(int i) {
        mSubscriptionResultListener.onFailure();
    }

    //Callback from Play Services
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
            mSubscriptionResultListener.onFailure();
        }
    }
}
