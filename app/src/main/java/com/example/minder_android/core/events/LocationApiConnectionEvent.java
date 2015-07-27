package com.example.minder_android.core.events;

/**
 * Created by Max on 23.07.15.
 */
public class LocationApiConnectionEvent {
    public boolean isConnected;
    public String message;

    public LocationApiConnectionEvent(boolean _isConnected) {
        this.isConnected = _isConnected;
        this.message = "";
    }

    public LocationApiConnectionEvent(boolean _isConnected, String _message) {
        this.isConnected = _isConnected;
        this.message = _message;
    }
}
