package com.example.minder_android.core;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.support.v4.content.WakefulBroadcastReceiver;

import com.commonsware.cwac.wakeful.WakefulIntentService;

import static com.example.minder_android.core.Const.ACTION_STORE_LOCATION;
import static com.example.minder_android.core.Const.LOCATION_UPDATE_INTERVAL;

/**
 * Created by Max on 21.07.15.
 */
public class StoreLocationReceiver extends WakefulBroadcastReceiver {
    private static final int INITIAL_DELAY=10000; // 10 seconds
    @Override
    public void onReceive(Context _context, Intent i) {
        if (i.getAction() == ACTION_STORE_LOCATION) {
            WakefulIntentService.sendWakefulWork(_context, StoreLocationService.class);
        }
        else {
            if (isScheduleAlarmNeeded()) {
                scheduleAlarms(_context);
            }
        }
    }

    // method stub for future scheduling after device reboot
    private static boolean isScheduleAlarmNeeded() { return false; }

    public static void scheduleAlarms(Context _context) {
        AlarmManager mgr=
                (AlarmManager)_context.getSystemService(Context.ALARM_SERVICE);
        Intent i = createStoreLocationIntent(_context);
        PendingIntent pi=PendingIntent.getBroadcast(_context, 0, i, 0);
        mgr.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                SystemClock.elapsedRealtime() + INITIAL_DELAY,
                LOCATION_UPDATE_INTERVAL, pi);
    }

    public static void cancelAlarms(Context _context) {
        AlarmManager mgr=
                (AlarmManager)_context.getSystemService(Context.ALARM_SERVICE);
        Intent i = createStoreLocationIntent(_context);
        PendingIntent pi=PendingIntent.getBroadcast(_context, 0, i, 0);
        mgr.cancel(pi);
    }

    // this method uses side effect of PendingIntent.FLAG_NO_CREATE , so it may not work in SDK ver. > 20
    public static boolean isAlarmsScheduled(Context _context) {
        Intent i = createStoreLocationIntent(_context);
        return PendingIntent.getBroadcast(_context, 0, i, PendingIntent.FLAG_NO_CREATE) != null;
    }

    private static Intent createStoreLocationIntent(Context _context) {
        Intent i=new Intent(_context, StoreLocationReceiver.class);
        i.setAction(ACTION_STORE_LOCATION);
        return i;
    }

}