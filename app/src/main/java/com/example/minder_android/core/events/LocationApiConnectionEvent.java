package com.example.minder_android.core.events;

/**
 * Created by Max on 23.07.15.
 */
public class LocationApiConnectionEvent extends MessageEvent{
    public boolean isConnected;

    public LocationApiConnectionEvent(boolean _isConnected) {
        super("");
        this.isConnected = _isConnected;
    }

    public LocationApiConnectionEvent(boolean _isConnected, String _message) {
        super(_message);
        this.isConnected = _isConnected;
    }
}
