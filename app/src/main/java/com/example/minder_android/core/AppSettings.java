package com.example.minder_android.core;

import android.content.Context;
import android.provider.Settings;

import com.example.minder_android.core.utils.TinyDB;

import java.util.ArrayList;

import static com.example.minder_android.core.Const.KEY_COOKIE_OUT;
import static com.example.minder_android.core.Const.KEY_FALED_SYNC_LIST;
import static com.example.minder_android.core.Const.KEY_LOGGED_IN;
import static com.example.minder_android.core.Const.KEY_MINDER_ID;
import static com.example.minder_android.core.Const.KEY_SYNC_LIST;

/**
 * Created by Max on 17.07.15.
 */
public class AppSettings {
    private static String mDeviceID;
    private static String mDeviceName;
    private static TinyDB mSharedPrefDB;



    public static void init(Context _context) {
        mDeviceID = Settings.Secure.getString(_context.getContentResolver(),Settings.Secure.ANDROID_ID);
        mDeviceName = android.os.Build.MODEL;
        mSharedPrefDB = new TinyDB(_context);
    }

    public static String getCookie() {
        return mSharedPrefDB.getString(KEY_COOKIE_OUT);
    }

    public static void storeCookie(String _cookie) {
        mSharedPrefDB.putString(KEY_COOKIE_OUT, _cookie);
    }

    public static void clearCookie() {
        mSharedPrefDB.remove(KEY_COOKIE_OUT);
    }

    public static String getMinderId() {
        return mSharedPrefDB.getString(KEY_MINDER_ID);
    }

    public static void setMinderId(String _minderId) {
        mSharedPrefDB.putString(KEY_MINDER_ID, _minderId);
    }

    public static String getDeviceId() {
        return mDeviceID;
    }


    public static String getDeviceName() {
        return mDeviceName;
    }

    public static boolean isAppLoggedIn() {
        return mSharedPrefDB.getBoolean(KEY_LOGGED_IN, false);
    }

    public static void setLoggedIn(boolean _loggedIn) {
        mSharedPrefDB.putBoolean(KEY_LOGGED_IN, _loggedIn);
    }


    public synchronized static void addToSyncList(ArrayList<String> _list) {
        ArrayList<String> list = getSyncList();
        for (String item : _list) {
            if (!list.contains(item)) {
                list.add(item);
            }
        }
        mSharedPrefDB.putListString(KEY_SYNC_LIST, list);
    }

    public synchronized static ArrayList<String> getSyncList() {
        return mSharedPrefDB.getListString(KEY_SYNC_LIST);
    }

    public synchronized static void setSyncList(ArrayList<String> _list) {
        mSharedPrefDB.putListString(KEY_SYNC_LIST, _list);
    }

    public synchronized static void addToFailedSyncList(ArrayList<String> _list) {
        ArrayList<String> list = getFaledSyncList();
        for (String item : _list) {
            if (!list.contains(item)) {
                list.add(item);
            }
        }
        mSharedPrefDB.putListString(KEY_FALED_SYNC_LIST, list);
    }

    public synchronized static ArrayList<String> getFaledSyncList() {
        return mSharedPrefDB.getListString(KEY_FALED_SYNC_LIST);
    }

    public synchronized static void setFailedSyncList(ArrayList<String> _list) {
        mSharedPrefDB.putListString(KEY_FALED_SYNC_LIST, _list);
    }

    public synchronized static void moveToFailedList(String _item){
        ArrayList<String> syncList = getSyncList();
        syncList.remove(_item);
        setSyncList(syncList);
        ArrayList<String> failedList = getFaledSyncList();
        if (!failedList.contains(_item)) {
            failedList.add(_item);
        }
        setFailedSyncList(failedList);
    };

    public synchronized static void deleteFromSyncList(String _item){
        ArrayList<String> syncList = getSyncList();
        syncList.remove(_item);
        setSyncList(syncList);
    };
}
