package com.example.minder_android.rest;

import com.example.minder_android.core.Const;

import retrofit.RestAdapter;
import retrofit.client.OkClient;

/**
 * Created by Max on 16.07.15.
 */
public final class ServiceBuilder {



    public static RestApiService getApiService() {
        return new RestAdapter.Builder()
                .setEndpoint(Const.SERVER_URL)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setClient(new OkClient())
                .setRequestInterceptor(new RestApiHeaders())
                .build()
                .create(RestApiService.class);
    }
}
