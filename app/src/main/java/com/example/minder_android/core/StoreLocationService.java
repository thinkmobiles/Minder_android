package com.example.minder_android.core;

import android.content.Intent;
import android.location.Location;
import android.util.Log;

import com.commonsware.cwac.wakeful.WakefulIntentService;
import com.example.minder_android.rest.RequestJsonFactory;
import com.example.minder_android.rest.RequestManager;
import com.example.minder_android.rest.RestApiHeaders;
import com.google.gson.JsonObject;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Max on 20.07.15.
 */
public class StoreLocationService  extends WakefulIntentService implements LocationController.ILocation {
    private static String DEBUG_TAG = "minder_android : StoreLocationService";

    private Callback<JsonObject> mCallback = new Callback<JsonObject>() {
        @Override
        public void success(JsonObject _jsonObject, Response _response) {
            Log.d(DEBUG_TAG, "LOCATION STORED");
        }

        @Override
        public void failure(RetrofitError _error) {
            Log.d(DEBUG_TAG, "ERROR STORING LOCATION: " + _error.getMessage());
        }
    };

    public StoreLocationService() {
        super("StoreLocationService");
    }


    @Override
    protected void doWakefulWork(Intent _intent) {
        LocationController.setListener(this);
        Log.d(DEBUG_TAG, "LISTENER SET");
        storeLocationToServer();
    }



    private void storeLocationToServer() {
        LocationController.init(getApplicationContext());
        AppSettings.init(getApplicationContext());
    }

    @Override
    public void locationReceived(Location _location) {
        storeLocationToServer(_location);
    }

    private void storeLocationToServer(Location _location) {
        AppSettings.init(getApplicationContext());
        RestApiHeaders.setCookie(AppSettings.getCookie());
        RequestManager.storeLocationToServer(RequestJsonFactory.createLocateRequestJson(_location), mCallback);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LocationController.setListener(null);
        Log.d(DEBUG_TAG, "LISTENER UNSET");

    }
}
