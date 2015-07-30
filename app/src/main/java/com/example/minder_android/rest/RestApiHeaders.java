package com.example.minder_android.rest;

import com.example.minder_android.core.AppSettings;

import retrofit.RequestInterceptor;

import static com.example.minder_android.core.Const.KEY_COOKIE_OUT;

/**
 * Created by Max on 20.07.15.
 */
public class RestApiHeaders implements RequestInterceptor {
    private static String mCookie;

    public RestApiHeaders() {
        mCookie = AppSettings.getCookie();
    }

    public static void setCookie(String _cookie) {
        mCookie = _cookie;
        AppSettings.storeCookie(mCookie);

    }

    public static void clearCookie() {
        mCookie = null;
        AppSettings.clearCookie();
    }

    @Override public void intercept(RequestInterceptor.RequestFacade request) {
        if (mCookie != null) {
            request.addHeader(KEY_COOKIE_OUT, mCookie);
        }
    }
}
