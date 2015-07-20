package com.example.minder_android.rest;

import android.content.Context;

import com.google.gson.JsonObject;

import retrofit.Callback;

/**
 * Created by Max on 16.07.15.
 */
public final class RequestManager {
    private Context mCtx;
    private final static RequestManager INSTANCE = new RequestManager();

    /**
     * Method register client and save context. Initialize this method in the first place.
     *
     * @param _ctx - context
     */
    public static void registerClient(final Context _ctx) {
        if (INSTANCE.mCtx == null)
            INSTANCE.mCtx = _ctx;
    }

    public static void signOutUser(final Callback<JsonObject> _callback) {
        ServiceBuilder.getApiService().signOutUser(new JsonObject(),  _callback);
    }

    public static void signUpUser(JsonObject _requestJson, final Callback<JsonObject> _callback) {
        ServiceBuilder.getApiService().signUpUser(_requestJson, _callback);
    }

    public static void signInUser(JsonObject _requestJson, final Callback<JsonObject> _callback) {
        ServiceBuilder.getApiService().signInUser(_requestJson, _callback);
    }

    public static void storeLocationToServer(JsonObject _requestJson, final Callback<JsonObject> _callback) {
        ServiceBuilder.getApiService().storeLocationToServer(_requestJson, _callback);
    }

}
