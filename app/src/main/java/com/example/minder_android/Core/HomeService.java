package com.example.minder_android.core;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.example.minder_android.R;

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
                    i++;
                    try {
                        sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        return;
                    }
                }
            }
        };
        mThread.start();
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
