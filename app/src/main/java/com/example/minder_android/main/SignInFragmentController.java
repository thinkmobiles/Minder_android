package com.example.minder_android.main;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.example.minder_android.core.AppBroadcastsReceiver;
import com.example.minder_android.core.AppSettings;
import com.example.minder_android.core.location_api.LocationAPIController;
import com.example.minder_android.core.utils.PBarController;
import com.example.minder_android.rest.RequestJsonFactory;
import com.example.minder_android.rest.RequestManager;
import com.example.minder_android.rest.RestApiHeaders;
import com.google.gson.JsonObject;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Header;
import retrofit.client.Response;

import static com.example.minder_android.core.Const.ACTION_SYNC;
import static com.example.minder_android.core.Const.KEY_COOKIE_IN;

/**
 * Created by ���� on 06.05.2015.
 */
public final class SignInFragmentController {
    private SignInFragment mFragment;

    public SignInFragmentController(final SignInFragment _fragment) {
        mFragment = _fragment;
    }


    public final boolean validID(final String _id) {
        if (_id.isEmpty() || _id == null) return false;
        return true;
    }


    public void signInUser(final String _mindId) {
        PBarController.showProgressDialog(mFragment.getActivity());
        AppSettings.setMinderId(_mindId);
        RequestManager.signInUser(RequestJsonFactory.createSignInRequestJson(_mindId), new Callback<JsonObject>() {
            @Override
            public void success(JsonObject _jsonObject, Response _response) {
                PBarController.hideProgressDialog();
                onUserSignInSuccess(_jsonObject, _response);
            }

            @Override
            public void failure(RetrofitError _error) {
                PBarController.hideProgressDialog();
                Toast.makeText(mFragment.getActivity(), _error.getMessage(), Toast.LENGTH_LONG).show();
                mFragment.onUserSignInFailure();
            }
        });
    }

    private String getCookieIdFromResponse(Response _response) {
        for (Header header: _response.getHeaders()) {
            if (header.getName().equals(KEY_COOKIE_IN)) {
                return header.getValue();
            }
        }
        return "";
    }

    private void onUserSignInSuccess(JsonObject _jsonObject, Response _response) {
        String cookie = getCookieIdFromResponse(_response);
        RestApiHeaders.setCookie(cookie);
        LocationAPIController.INSTANCE.subscribeLocationUpdates(AppBroadcastsReceiver.class);
        AppSettings.setLoggedIn(true);
        startSyncService();
        mFragment.onUserSignInSuccess();
    }

    private void startSyncService() {
        AlarmManager alarmManager  = (AlarmManager)mFragment.getActivity().getSystemService(Context.ALARM_SERVICE);
        PendingIntent alarmIntent = PendingIntent.getBroadcast(mFragment.getActivity().getApplication(),
                0,
                new Intent(mFragment.getActivity(), AppBroadcastsReceiver.class).setAction(ACTION_SYNC),
                0);

        alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                10000,
                20000,
                alarmIntent);
    }
}
