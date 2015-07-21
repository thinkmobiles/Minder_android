package com.example.minder_android.rest;

import android.content.Context;
import android.location.Location;

import com.example.minder_android.core.AppSettings;
import com.example.minder_android.core.LocationController;
import com.google.gson.JsonObject;

import static com.example.minder_android.core.Const.KEY_DEVICE_ID;
import static com.example.minder_android.core.Const.KEY_EMAIL;
import static com.example.minder_android.core.Const.KEY_FIRSTNAME;
import static com.example.minder_android.core.Const.KEY_LASTNAME;
import static com.example.minder_android.core.Const.KEY_LAT;
import static com.example.minder_android.core.Const.KEY_LOCATION;
import static com.example.minder_android.core.Const.KEY_LONG;
import static com.example.minder_android.core.Const.KEY_MINDER_ID;
import static com.example.minder_android.core.Const.KEY_NAME;
import static com.example.minder_android.core.Const.KEY_PASS;

/**
 * Created by Max on 20.07.15.
 */
public class RequestJsonFactory {

    public static JsonObject createLocateRequestJson(Context _context){
        LocationController.init(_context);
        if (LocationController.getCurrentLocation() == null) {
            return null;
        }
        JsonObject location = new JsonObject();
        location.addProperty(KEY_LAT, LocationController.getCurrentLocation().getLatitude());
        location.addProperty(KEY_LONG, LocationController.getCurrentLocation().getLongitude());
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty(KEY_MINDER_ID, AppSettings.getMinderId());
        jsonObject.addProperty(KEY_DEVICE_ID, AppSettings.getDeviceId());
        jsonObject.add(KEY_LOCATION, location);
        return jsonObject;
    }

    public static JsonObject createLocateRequestJson(Location _location){
        JsonObject location = new JsonObject();
        location.addProperty(KEY_LAT, _location.getLatitude());
        location.addProperty(KEY_LONG, _location.getLongitude());
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty(KEY_MINDER_ID, AppSettings.getMinderId());
        jsonObject.addProperty(KEY_DEVICE_ID, AppSettings.getDeviceId());
        jsonObject.add(KEY_LOCATION, location);
        return jsonObject;
    }

    public static JsonObject createSignInRequestJson(String _mindId) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty(KEY_MINDER_ID, _mindId);
        jsonObject.addProperty(KEY_DEVICE_ID, AppSettings.getDeviceId());
        jsonObject.addProperty(KEY_NAME, AppSettings.getDeviceName());
        return jsonObject;
    }

    public static JsonObject createSignUpRequestJson(final String _firstName, final String _lastName, final String _email, final String _password) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty(KEY_FIRSTNAME, _firstName);
        jsonObject.addProperty(KEY_LASTNAME, _lastName);
        jsonObject.addProperty(KEY_EMAIL, _email);
        jsonObject.addProperty(KEY_PASS, _password);
        return jsonObject;
    }
}
