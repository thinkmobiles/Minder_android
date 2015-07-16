package com.example.minder_android.main;

import android.provider.Settings;
import android.widget.Toast;

import com.example.minder_android.core.Const;
import com.example.minder_android.core.utils.TinyDB;
import com.example.minder_android.rest.RequestManager;
import com.google.gson.JsonObject;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by ���� on 06.05.2015.
 */
public final class SignInFragmentController {
    private SignInFragment mFragment;
    private TinyDB mSharedPrefDB;

    public SignInFragmentController(final SignInFragment _fragment) {
        mFragment = _fragment;
        mSharedPrefDB = new TinyDB(mFragment.getActivity());
    }


    public final boolean validID(final String _id) {
        if (_id.isEmpty() || _id == null) return false;
        return true;
    }


    public void signInUser(final String _mindId) {
        RequestManager.signInUser(getRequestModelJson(_mindId), new Callback<JsonObject>() {
            @Override
            public void success(JsonObject _jsonObject, Response _response) {
                onUserSignInSuccess(_jsonObject);
            }

            @Override
            public void failure(RetrofitError _error) {
                Toast.makeText(mFragment.getActivity(), _error.getMessage(), Toast.LENGTH_LONG).show();
                mFragment.onUserSignInFailure();
            }
        });
    }

    private void onUserSignInSuccess(JsonObject _jsonObject) {
        mFragment.onUserSignInSuccess();
    }

    private JsonObject getRequestModelJson(String _mindId) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty(Const.KEY_MINDER_ID, _mindId);
        jsonObject.addProperty(Const.KEY_DEVICE_ID, getDeviceId());
        jsonObject.addProperty(Const.KEY_NAME, getDeviceName());
        return jsonObject;
    }


    private String getDeviceId() {
        return Settings.Secure.getString(mFragment.getActivity().getContentResolver(),
                Settings.Secure.ANDROID_ID);
    }

    public String getMinderId() {
        return mSharedPrefDB.getString(Const.KEY_MINDER_ID);
    }

    public String getDeviceName() {
        return android.os.Build.MODEL;
    }
}
