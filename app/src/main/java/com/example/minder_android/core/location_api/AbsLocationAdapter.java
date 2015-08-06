package com.example.minder_android.core.location_api;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import static com.example.minder_android.core.Const.ACTION_STORE_LOCATION;

/**
 * Created by Max on 22.07.15.
 * Abstract class, encapsulates location providers. Gives an interface to subscribe
 * given Broadcast receiver to Location updates
 * Describes interface ISubscriptionResult to use for error handling
 */
abstract class AbsLocationAdapter {
    protected Context mContext;
    protected ISubscriptionResult mSubscriptionResultListener;
    protected Class mSubscriber;


    public AbsLocationAdapter(Context _context) {
        this.mContext = _context;
    }

    public  void subscribeLocationUpdates(Class<? extends BroadcastReceiver> _subscriber){
        mSubscriber = _subscriber;
    };
    public  void unsubscribeLocationUpdates(Class<? extends BroadcastReceiver> _subscriber){
        mSubscriber = _subscriber;
    };


    // dirty hack ?
    // this method uses side effect of PendingIntent.FLAG_NO_CREATE , so it may not work in SDK ver. > 20
//    public boolean isSubscribedLocationUpdates(Class<? extends BroadcastReceiver> _subscriber){
//        return PendingIntent.getBroadcast(mContext, 0, createSubscriberIntent(_subscriber), PendingIntent.FLAG_NO_CREATE) != null;
//    };

    protected PendingIntent createPendingIntent(Class<? extends BroadcastReceiver> _subscriber) {
        return PendingIntent.getBroadcast(mContext, 0, createSubscriberIntent(_subscriber), 0);
    }

    protected Intent createSubscriberIntent(Class<? extends BroadcastReceiver> _subscriber) {
        Intent i=new Intent(mContext, _subscriber);
        i.setAction(ACTION_STORE_LOCATION);
        return i;
    }

    protected void setConnectionListener(ISubscriptionResult _listener) {
        this.mSubscriptionResultListener = _listener;
    }
    protected void removeConnectionListener() {
        this.mSubscriptionResultListener = null;
    }

    interface ISubscriptionResult {
        void onSuccess(String _message);
        void onFailure();
    }
}
