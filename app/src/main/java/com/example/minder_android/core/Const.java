package com.example.minder_android.core;

/**
 * Created by ���� on 06.05.2015.
 */
public abstract class Const {
    public static final int SPLASH_SLEEP_TIME        = 3000;
    public static final int LOCATION_UPDATE_INTERVAL = 30 * 60 * 1000; //30 min
    //    public static final int LOCATION_UPDATE_INTERVAL = 30000; //test
//        public static final int SYNC_INTERVAL            = 30 * 60 * 1000; //30 min
    public static final int SYNC_INTERVAL            = 20000; // test
    public static final int MIN_PASSWORD_LENGTH        = 6;

    public static final int FUSED_LOCATION_RESOLUTION_REQUEST_ID    = 789;
    public static final int FUSED_LOCATION_SETTINGS_REQUEST_ID      = 790;

    public final static String ACTION_STORE_LOCATION                = "com.example.minder_android.core.StoreLocationService.STORE_LOCATION";
    public final static String ACTION_SYNC                          = "com.example.minder_android.core.StorePhotosService.SYNC";

    public final static String DATE_TIME_FORMAT                     = "yyyy/MM/dd HH:mm:ss";
    public final static String DATE_TIME_FORMAT_SERVER              = "yyyy-MM-dd'T'HH:mm:ss.SSS";

    public static final String SERVER_URL = "http://134.249.164.53:8877"; //production
    public static final String DEBUG_TAG = "MINDER_ANDROID_LOG"; //production
    public static final String START_OF_TIMES = "1970-01-01T00:00:00.000Z";
//    public static final String SERVER_URL = "http://134.249.164.53:8829/"; //debug
//    public static final String SERVER_URL = "http://127.0.0.1:8877";  // For connection failure testing



    // JSON and SharedPref KEYS
    public static final String KEY_EMAIL            = "email";
    public static final String KEY_PASS             = "pass";
    public static final String KEY_FIRSTNAME        = "firstName";
    public static final String KEY_LASTNAME         = "lastName";
    public static final String KEY_SUCCESS          = "success";
    public static final String KEY_ERROR            = "error";
    public static final String KEY_MINDER_ID        = "minderId";
    public static final String KEY_DEVICE_ID        = "deviceId";
    public static final String KEY_NAME             = "name";
    public static final String KEY_LOCATION         = "location";
    public static final String KEY_LONG             = "long";
    public static final String KEY_LAT              = "lat";
    public static final String KEY_COOKIE_IN        = "set-cookie";
    public static final String KEY_COOKIE_OUT       = "Cookie";
    public static final String KEY_LOGGED_IN        = "LoggedIn";
    public static final String KEY_ORIGINAL_NAME    = "originalName";
    public static final String KEY_FILE_CREATED_AT  = "fileCreatedAt";
    public static final String KEY_FILE             = "file";
    public static final String KEY_MODEL            = "model";
    public static final String KEY_SYNC_LIST        = "SYNC_LIST";
    public static final String KEY_FALED_SYNC_LIST  = "FALED_SYNC_LIST";


    public static final String ERROR_MESSAGE_FUNCTIONALITY_DISABLED             = "This functionality is disabled.";

}
