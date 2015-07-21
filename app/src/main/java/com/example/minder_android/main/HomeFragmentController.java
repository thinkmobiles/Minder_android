package com.example.minder_android.main;

import android.widget.Toast;

import com.example.minder_android.core.StoreLocationReceiver;
import com.example.minder_android.core.utils.PBarController;
import com.example.minder_android.rest.RequestManager;
import com.example.minder_android.rest.RestApiHeaders;
import com.google.gson.JsonObject;

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
        StoreLocationReceiver.cancelAlarms(mFragment.getActivity());
    }

    public final void onDestroy() {
    }


}
