package com.example.minder_android.main;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.commonsware.cwac.wakeful.WakefulIntentService;
import com.example.minder_android.R;
import com.example.minder_android.core.AppBroadcastsReceiver;
import com.example.minder_android.core.AppSettings;
import com.example.minder_android.core.StorePhotosService;
import com.example.minder_android.core.events.LocationApiConnectionEvent;
import com.example.minder_android.core.events.MessageEvent;
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

import static com.example.minder_android.core.Const.ACTION_SYNC;


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
//                Toast.makeText(mFragment.getActivity(), _error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        LocationAPIController.INSTANCE.unsubscribeLocationUpdates(AppBroadcastsReceiver.class);
        AppSettings.setLoggedIn(false);
        stopSyncService();
    }

    private void stopSyncService() {
        AlarmManager alarmManager  = (AlarmManager)mFragment.getActivity().getSystemService(Context.ALARM_SERVICE);
        PendingIntent alarmIntent = PendingIntent.getBroadcast(mFragment.getActivity().getApplication(),
                0,
                new Intent(mFragment.getActivity(), AppBroadcastsReceiver.class).setAction(ACTION_SYNC),
                0);
        alarmManager.cancel(alarmIntent);

    }

    public final void onDestroy() {
        EventBus.getDefault().unregister(this);
    }

    public void onEvent(LocationApiConnectionEvent _event){
        mFragment.setHomeScreenText(_event.isConnected ? _event.message : mFragment.getString(R.string.location_api_connection_error));

        Intent storePhotosIntent = new Intent(mFragment.getActivity().getApplication(), StorePhotosService.class);
        WakefulIntentService.sendWakefulWork(mFragment.getActivity().getApplication(), storePhotosIntent);
    };

    public void onEvent(MessageEvent _event){
        addEventMessageToLog(_event);
    }

    private void addEventMessageToLog(MessageEvent _event) {
        mFragment.setHomeScreenText(String.format("%s \n %s : %s ", mFragment.getHomeStringText(), DateTimeConverter.getCurrentDateTime(), _event.message));
    }

}
