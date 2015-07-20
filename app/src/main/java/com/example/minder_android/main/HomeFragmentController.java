package com.example.minder_android.main;

import android.content.Intent;
import android.content.IntentFilter;
import android.widget.Toast;

import com.example.minder_android.core.Const;
import com.example.minder_android.core.HomeReceiver;
import com.example.minder_android.core.HomeService;
import com.example.minder_android.core.HomeServiceCallBack;
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
public final class HomeFragmentController implements HomeServiceCallBack{
    private HomeFragment mFragment;
    private HomeReceiver mReceiver;
    private Intent mServiceIntent;
    private static String mMessage;


    public HomeFragmentController(final HomeFragment _fragment) {
        mFragment = _fragment;
    }

    public final void onCreateView(){
        mReceiver = new HomeReceiver();
        mReceiver.setCallBack(this);

        mServiceIntent = new Intent(mFragment.getBaseActivity(), HomeService.class);
        if (!HomeService.isServiceRunning())
            mFragment.getBaseActivity().startService(mServiceIntent);

        IntentFilter intentFilter = new IntentFilter(Const.BROADCAST_ACTION);
        mFragment.getBaseActivity().registerReceiver(mReceiver, intentFilter);
        mFragment.setHomeScreenText(mMessage);
    }

    public final void onDisconnect() {
        PBarController.showProgressDialog(mFragment.getActivity());
        mFragment.getBaseActivity().stopService(mServiceIntent);
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
    }

    public final void onDestroy() {
        mFragment.getBaseActivity().unregisterReceiver(mReceiver);
    }

    @Override
    public void onReceive(final String _message) {
        mMessage = _message;
        mFragment.setHomeScreenText(mMessage);
    }

}
