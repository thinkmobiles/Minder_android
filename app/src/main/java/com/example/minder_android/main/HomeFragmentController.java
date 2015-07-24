package com.example.minder_android.main;

import android.widget.Toast;

import com.example.minder_android.R;
import com.example.minder_android.core.AppSettings;
import com.example.minder_android.core.StoreLocationReceiver;
import com.example.minder_android.core.events.LocationApiConnectionEvent;
import com.example.minder_android.core.events.StoreLocationEvent;
import com.example.minder_android.core.location_api.LocationAPIController;
import com.example.minder_android.core.utils.DateTimeConverter;
import com.example.minder_android.core.utils.PBarController;
import com.example.minder_android.rest.RequestManager;
import com.example.minder_android.rest.RestApiHeaders;
import com.google.gson.JsonObject;

import de.greenrobot.event.EventBus;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


/**
 * Created by Юзер on 08.05.2015.
 */
public final class HomeFragmentController {
    private static String LOG_TAG = "HomeFragmentController";
    private HomeFragment mFragment;


    public HomeFragmentController(final HomeFragment _fragment) {
        mFragment = _fragment;
    }

    public final void onCreateView(){
        EventBus.getDefault().registerSticky(this);

    }

    public final void onDisconnect() {
        PBarController.showProgressDialog(mFragment.getActivity());
        RequestManager.signOutUser(new Callback<JsonObject>() {
            @Override
            public void success(JsonObject _jsonObject, Response _response) {
                RestApiHeaders.clearCookie();
                PBarController.hideProgressDialog();
            }

            @Override
            public void failure(RetrofitError _error) {
                PBarController.hideProgressDialog();
                RestApiHeaders.clearCookie();
                Toast.makeText(mFragment.getActivity(), _error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        LocationAPIController.INSTANCE.unsubscribeLocationUpdates(StoreLocationReceiver.class);
        AppSettings.setLoggedIn(false);
    }

    public final void onDestroy() {
        EventBus.getDefault().unregister(this);
    }

    public void onEvent(LocationApiConnectionEvent _event){
        mFragment.setHomeScreenText(mFragment.getString(_event.isConnected ? R.string.connected : R.string.location_api_connection_error));
    };
    public void onEvent(StoreLocationEvent _event){
        mFragment.setHomeScreenText(String.format("%s \n %s : %s ",mFragment.getHomeStringText(), DateTimeConverter.getCurrentDateTime(), _event.Message));
    };
}
