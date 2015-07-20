package com.example.minder_android.core;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

import com.example.minder_android.R;
import com.example.minder_android.rest.RequestJsonFactory;
import com.example.minder_android.rest.RequestManager;
import com.google.gson.JsonObject;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

import static com.example.minder_android.core.Const.LOCATION_UPDATE_INTERVAL;

/**
 * Created by Юзер on 08.05.2015.
 */
public final class HomeService extends Service {
    private static Thread mThread = null;
    private static HomeService mInstance;
    private String mHomeMessage;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        startProcess();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        mThread.interrupt();
        mInstance = null;
        super.onDestroy();
    }

    public final void startProcess() {
        final Intent intent = new Intent(Const.BROADCAST_ACTION);

        mThread = new Thread("HomeServiceThread") {
            int i = 0;
            @Override
            public void run() {
                while (!mThread.isInterrupted()) {

                    mHomeMessage = getString(R.string.home_text);
                    mHomeMessage = mHomeMessage + " #" + i;

                    intent.putExtra(Const.BROADCAST_PARAM_RESULT, mHomeMessage);
                    sendBroadcast(intent);
                    storeLocationToServer();
                    i++;
                    try {
                        sleep(LOCATION_UPDATE_INTERVAL);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        return;
                    }
                }
            }
        };
        mThread.start();
    }

    private void storeLocationToServer() {
        RequestManager.storeLocationToServer(RequestJsonFactory.createLocateRequestJson(), new Callback<JsonObject>() {
            @Override
            public void success(JsonObject _jsonObject, Response _response) {
                Toast.makeText(HomeService.this, R.string.location_stored, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void failure(RetrofitError _error) {
                Toast.makeText(HomeService.this, _error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public static final boolean isServiceRunning() {
        if (mInstance != null) return true;
        return false;
    }

}
