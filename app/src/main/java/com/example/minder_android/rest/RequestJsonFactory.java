package com.example.minder_android.rest;

import com.example.minder_android.core.DeviceParams;
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

    public static JsonObject createLocateRequestJson(){
        JsonObject location = new JsonObject();
        location.addProperty(KEY_LAT, LocationController.getCurrentLocation().getLatitude());
        location.addProperty(KEY_LONG, LocationController.getCurrentLocation().getLongitude());
        JsonObject model = new JsonObject();
        model.addProperty(KEY_MINDER_ID, DeviceParams.getMinderId());
        model.addProperty(KEY_DEVICE_ID, DeviceParams.getDeviceId());
        model.add(KEY_LOCATION, location);
        return model;
    }

    public static JsonObject createSignInRequestJson(String _mindId) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty(KEY_MINDER_ID, _mindId);
        jsonObject.addProperty(KEY_DEVICE_ID, DeviceParams.getDeviceId());
        jsonObject.addProperty(KEY_NAME, DeviceParams.getDeviceName());
        return jsonObject;
    }

    public static JsonObject createSignUpRequestJson(final String _firstName, final String _lastName, final String _email, final String _password) {
        JsonObject model = new JsonObject();
        model.addProperty(KEY_FIRSTNAME, _firstName);
        model.addProperty(KEY_LASTNAME, _lastName);
        model.addProperty(KEY_EMAIL, _email);
        model.addProperty(KEY_PASS, _password);
        return model;
    }
}
