package com.example.minder_android.core;

/**
 * Created by ���� on 06.05.2015.
 */
public abstract class Const {
    public static final int SLEEP_TIME = 3000;
    public static final int MIN_PASSWORD_LENGTH = 6;
    public static final int MINDER_ID_LENGTH = 8;

    public final static String BROADCAST_ACTION = "Minder_ID.HomeService";
    public final static String BROADCAST_PARAM_RESULT = "home_string";

//    public static final String SERVER_URL = "http://127.0.0.1:8877";  // For connection failure testing
    public static final String SERVER_URL = "http://134.249.164.53:8877";


    // JSON and SharedPref KEYS
    public static final String KEY_EMAIL            = "email";
    public static final String KEY_PASS             = "pass";
    public static final String KEY_FIRSTNAME        = "firstName";
    public static final String KEY_LASTNAME         = "lastName";
    public static final String KEY_SUCCESS          = "success";
    public static final String KEY_MESSAGE          = "message";
    public static final String KEY_MINDER_ID        = "minderId";
    public static final String KEY_DEVICE_ID        = "deviceId";
    public static final String KEY_NAME             = "name";
}
