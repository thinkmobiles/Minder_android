package com.example.minder_android.rest;

import retrofit.RequestInterceptor;

import static com.example.minder_android.core.Const.KEY_COOKIE_OUT;

/**
 * Created by Max on 20.07.15.
 */
public class RestApiHeaders implements RequestInterceptor {
    private static String mCookie;

    public static void setCookie(String _cookie) {
        mCookie = _cookie;
    }

    public static void clearCookie() {
        mCookie = null;
    }

    @Override public void intercept(RequestInterceptor.RequestFacade request) {
        if (mCookie != null) {
            request.addHeader(KEY_COOKIE_OUT, mCookie);
        }
    }
}
