package com.example.minder_android.core;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.Random;

/**
 * Created by Юзер on 06.05.2015.
 */
public final class EmulateServerConnect {
    private static final String KEY_ID = "Key_id";

    private static int mMinderId = -1;
    private static SharedPreferences preferences;

    public EmulateServerConnect(final Context _context) {
        preferences = PreferenceManager.getDefaultSharedPreferences(_context);
    }

    public static final String getMinderId() {
        return preferences.getString(KEY_ID, "");
    }

    public static final void postMinderId() {
        SharedPreferences.Editor ed = preferences.edit();
        ed.putString(KEY_ID, makeId());
        ed.commit();
    }

    private static final String makeId() {
        final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random rnd = new Random();

        StringBuilder sb = new StringBuilder(Const.MINDER_ID_LENGTH);
        for (int i = 0; i < Const.MINDER_ID_LENGTH; i++)
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
        return sb.toString();
    }

}
