package com.example.minder_android.rest.response_models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Max on 29.07.15.
 */
public class Location {

    @SerializedName("long")
    @Expose
    private Integer _long;
    @Expose
    private Integer lat;

    /**
     *
     * @return
     * The _long
     */
    public Integer getLong() {
        return _long;
    }

    /**
     *
     * @param _long
     * The long
     */
    public void setLong(Integer _long) {
        this._long = _long;
    }

    /**
     *
     * @return
     * The lat
     */
    public Integer getLat() {
        return lat;
    }

    /**
     *
     * @param lat
     * The lat
     */
    public void setLat(Integer lat) {
        this.lat = lat;
    }

}