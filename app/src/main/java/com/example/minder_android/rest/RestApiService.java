package com.example.minder_android.rest;

import com.google.gson.JsonObject;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.Headers;
import retrofit.http.POST;
import retrofit.http.PUT;

/**
 * Created by Max on 16.07.15.
 */
public interface RestApiService {


    @Headers("Accept: application/json")
    @POST("/signIn")
    void signInUser(@Body final JsonObject _requestJson,
                   final Callback<JsonObject> _callback);

    @Headers("Accept: application/json")
    @POST("/signUp")
    void signUpUser(@Body final JsonObject _requestJson,
                    final Callback<JsonObject> _callback);

    @Headers("Accept: application/json")
    @POST("/signOut")
    void signOutUser(@Body final JsonObject _requestJson,
                     final Callback<JsonObject> _callback);

    @Headers("Accept: application/json")
    @PUT("/devices/locate")
    void storeLocationToServer(@Body final JsonObject _requestJson,
                               final Callback<JsonObject> _callback);
}