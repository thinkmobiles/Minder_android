package com.example.minder_android.core;

import android.content.Context;
import android.provider.Settings;

import com.example.minder_android.core.utils.TinyDB;

/**
 * Created by Max on 17.07.15.
 */
public class   DeviceParams {
    private static String mDeviceID;
    private static String mDeviceName;
    private static String mMinderId;

    private static TinyDB mSharedPrefDB;



    public static String getMinderId() {
        mMinderId = mSharedPrefDB.getString(Const.KEY_MINDER_ID);
        return mMinderId;
    }

    public static void setMinderId(String _minderId) {
        DeviceParams.mMinderId = _minderId;
        mSharedPrefDB.putString(Const.KEY_MINDER_ID, mMinderId);
    }

    public static void init(Context _context) {
        mDeviceID = Settings.Secure.getString(_context.getContentResolver(),Settings.Secure.ANDROID_ID);
        mDeviceName = android.os.Build.MODEL;
        mSharedPrefDB = new TinyDB(_context);
    }

    public static String getDeviceId() {
        return mDeviceID;
    }

    public static String getDeviceName() {
        return mDeviceName;
    }

}
