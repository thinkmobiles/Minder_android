package com.example.minder_android.core.events;

/**
 * Created by Max on 23.07.15.
 */
public class LocationApiConnectionEvent {
    public boolean isConnected;
    public LocationApiConnectionEvent(boolean _isConnected) {
        this.isConnected = _isConnected;
    }
}
