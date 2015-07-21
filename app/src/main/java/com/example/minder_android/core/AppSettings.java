package com.example.minder_android.core;

import android.content.Context;
import android.location.Location;
import android.provider.Settings;

import com.example.minder_android.core.utils.TinyDB;
import com.example.minder_android.rest.RequestJsonFactory;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import static com.example.minder_android.core.Const.KEY_COOKIE_OUT;
import static com.example.minder_android.core.Const.KEY_LOCATION;
import static com.example.minder_android.core.Const.KEY_MINDER_ID;

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

    public static void setCookie(String _cookie) {
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


//    public static Time getLastLocationTime(){}
//    public static void setLastLocationTime(Time _time) {    }

    public static void setLastLocationJson(Location _location) {
        mSharedPrefDB.putString(KEY_LOCATION, RequestJsonFactory.createLocateRequestJson(_location).toString());
//        setLastLocationTime();
    }
    public static JsonObject getLastLocationJson(){
        JsonParser parser = new JsonParser();
        return !mSharedPrefDB.getString(KEY_LOCATION).equals("") ? (JsonObject) parser.parse(mSharedPrefDB.getString(KEY_LOCATION)) : null;
    }


}