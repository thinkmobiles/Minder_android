package com.example.minder_android.core;

/**
 * Created by ���� on 06.05.2015.
 */
public abstract class Const {
    public static final int SPLASH_SLEEP_TIME = 3000;
    public static final int LOCATION_UPDATE_INTERVAL = 30 * 60 * 1000; //30 min
//    public static final int LOCATION_UPDATE_INTERVAL = 30000; //test
    public static final int MIN_PASSWORD_LENGTH = 6;

    public static final int FUSED_LOCATION_RESOLUTION_REQUEST_ID    = 789;
    public static final int FUSED_LOCATION_SETTINGS_REQUEST_ID      = 790;

    public final static String ACTION_STORE_LOCATION        = "com.example.minder_android.core.StoreLocationService.STORE_LOCATION";

    public static final String SERVER_URL = "http://134.249.164.53:8877"; //production
//    public static final String SERVER_URL = "http://134.249.164.53:8829/"; //debug
//    public static final String SERVER_URL = "http://127.0.0.1:8877";  // For connection failure testing



    // JSON and SharedPref KEYS
    public static final String KEY_EMAIL            = "email";
    public static final String KEY_PASS             = "pass";
    public static final String KEY_FIRSTNAME        = "firstName";
    public static final String KEY_LASTNAME         = "lastName";
    public static final String KEY_SUCCESS          = "success";
    public static final String KEY_MINDER_ID        = "minderId";
    public static final String KEY_DEVICE_ID        = "deviceId";
    public static final String KEY_NAME             = "name";
    public static final String KEY_LOCATION         = "location";
    public static final String KEY_LONG             = "long";
    public static final String KEY_LAT              = "lat";
    public static final String KEY_COOKIE_IN        = "set-cookie";
    public static final String KEY_COOKIE_OUT       = "Cookie";
    public static final String KEY_LOGGED_IN       = "LoggedIn";

}
