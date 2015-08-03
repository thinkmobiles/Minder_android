package com.example.minder_android.rest;

import com.example.minder_android.rest.response_models.current_device_response.DeviceConfig;
import com.example.minder_android.rest.response_models.sync_response.SyncResponse;
import com.google.gson.JsonObject;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.Multipart;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Part;
import retrofit.mime.TypedFile;

import static com.example.minder_android.core.Const.KEY_FILE;
import static com.example.minder_android.core.Const.KEY_FILE_CREATED_AT;
import static com.example.minder_android.core.Const.KEY_ORIGINAL_NAME;

/**
 * Created by Max on 16.07.15.
 */
public interface RestApiService {


    @Headers("Accept: application/json")
    @POST("/signIn")
    void signInUser(@Body final JsonObject _requestJson,
                   final Callback<JsonObject> _callback);

    @Headers("Accept: application/json")
    @GET("/devices/currentDevice")
    DeviceConfig getCurrentDeviceConfig();

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


    @Headers("Accept: application/json")
    @Multipart
    @POST("/sync")
    SyncResponse sync(@Part(KEY_ORIGINAL_NAME) String _originalName,
              @Part(KEY_FILE_CREATED_AT) String _fileCreatedAt,
              @Part(KEY_FILE) TypedFile _file);
}
