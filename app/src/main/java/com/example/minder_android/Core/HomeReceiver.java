package com.example.minder_android.core;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Юзер on 08.05.2015.
 */
public class HomeReceiver extends BroadcastReceiver {
    private HomeServiceCallBack mCallBack;

    public void setCallBack(final HomeServiceCallBack _callBack) {
        mCallBack = _callBack;
    }

    @Override
    public void onReceive(final Context _context, final Intent _intent) {
        if (mCallBack != null)
            mCallBack.onReceive(_intent.getStringExtra(Const.BROADCAST_PARAM_RESULT));
    }
}
