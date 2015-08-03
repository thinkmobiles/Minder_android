package com.example.minder_android.rest.response_models.current_device_response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Max on 29.07.15.
 */
public class LastLocation {

    @Expose
    private String dateTime;
    @Expose
    private String lat;
    @SerializedName("long")
    @Expose
    private String _long;

    /**
     *
     * @return
     * The dateTime
     */
    public String getDateTime() {
        return dateTime;
    }

    /**
     *
     * @param dateTime
     * The dateTime
     */
    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    /**
     *
     * @return
     * The lat
     */
    public Object getLat() {
        return lat;
    }

    /**
     *
     * @param lat
     * The lat
     */
    public void setLat(String lat) {
        this.lat = lat;
    }

    /**
     *
     * @return
     * The _long
     */
    public Object getLong() {
        return _long;
    }

    /**
     *
     * @param _long
     * The long
     */
    public void setLong(String _long) {
        this._long = _long;
    }

}
