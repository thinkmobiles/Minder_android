package com.example.minder_android.core.location_api;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import static com.example.minder_android.core.Const.FUSED_LOCATION_RESOLUTION_REQUEST_ID;

/**
 * Created by Max on 23.07.15.
 */
public class LocationUIHelper {
    private Context mContext;

    public LocationUIHelper(Context _context) {
        this.mContext = _context;
    }

    public  void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == FUSED_LOCATION_RESOLUTION_REQUEST_ID && resultCode == Activity.RESULT_OK) {
            LocationAPIController apiController = new LocationAPIController(mContext);
            ((FusedLocationAdapter) apiController.getAdapter()).reconnect();
        }
    }
}
