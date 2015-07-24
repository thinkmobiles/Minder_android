package com.example.minder_android.core.location_api;

import android.app.Activity;
import android.content.Intent;

import static com.example.minder_android.core.Const.FUSED_LOCATION_RESOLUTION_REQUEST_ID;
import static com.example.minder_android.core.Const.FUSED_LOCATION_SETTINGS_REQUEST_ID;

/**
 * Created by Max on 23.07.15.
 * Helper class to use in onActivityResult processing from Google Play Fused Location Provider
 * and Google LocationSettings API
 */

public class LocationUIHelper {

    public  static void onActivityResult(int _requestCode, int _resultCode, Intent _data) {
        switch (_requestCode){
            case FUSED_LOCATION_RESOLUTION_REQUEST_ID:
                FusedLocationAdapter adapter = (FusedLocationAdapter) LocationAPIController.INSTANCE.getAdapter();
                if (_resultCode == Activity.RESULT_OK) {
                    adapter.reconnect();
                } else {
                    adapter.connectionResolutionError();
                }
                break;
            case FUSED_LOCATION_SETTINGS_REQUEST_ID:
                FusedLocationAdapter fusedAdapter = (FusedLocationAdapter) LocationAPIController.INSTANCE.getAdapter();
                if (_resultCode == Activity.RESULT_OK) {
                    fusedAdapter.reconnect();
                } else {
                    fusedAdapter.settingsResolutionError();
                }
                break;
            default:
        }
    }
}
