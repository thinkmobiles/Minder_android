package com.example.minder_android.core;

import android.content.Intent;
import android.location.Location;
import android.util.Log;

import com.commonsware.cwac.wakeful.WakefulIntentService;
import com.example.minder_android.R;
import com.example.minder_android.core.events.StoreLocationEvent;
import com.example.minder_android.rest.RequestJsonFactory;
import com.example.minder_android.rest.RequestManager;
import com.example.minder_android.rest.RestApiHeaders;
import com.google.gson.JsonObject;

import de.greenrobot.event.EventBus;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

import static com.example.minder_android.core.Const.KEY_LOCATION;


/**
 * Created by Max on 20.07.15.
 */
public class StoreLocationService  extends WakefulIntentService  {
    private static String DEBUG_TAG = "minder_android : StoreLocationService";

    public StoreLocationService() {
        super("StoreLocationService");
    }


    @Override
    protected void doWakefulWork(Intent _intent) {
        Location location = _intent.getExtras().getParcelable(KEY_LOCATION);
        storeLocationToServer(location);
    }

    private void storeLocationToServer(Location _location) {
        AppSettings.init(getApplicationContext());
        if (_location == null || !AppSettings.isAppLoggedIn()) { return ;}
        RestApiHeaders.setCookie(AppSettings.getCookie());
        RequestManager.storeLocationToServer(RequestJsonFactory.createLocateRequestJson(_location), mCallback);
    }

    private Callback<JsonObject> mCallback = new Callback<JsonObject>() {
        @Override
        public void success(JsonObject _jsonObject, Response _response) {
            EventBus.getDefault().postSticky(new StoreLocationEvent(getString(R.string.location_stored)));
            Log.d(DEBUG_TAG, "LOCATION STORED");
        }

        @Override
        public void failure(RetrofitError _error) {
            EventBus.getDefault().postSticky(new StoreLocationEvent( getString(R.string.error_storing_location) + _error.getMessage()));
            Log.d(DEBUG_TAG, "ERROR STORING LOCATION: " + _error.getMessage());
        }
    };


}
