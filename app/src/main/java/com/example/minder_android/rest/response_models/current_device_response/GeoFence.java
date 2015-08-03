package com.example.minder_android.rest.response_models.current_device_response;

import com.google.gson.annotations.Expose;

/**
 * Created by Max on 29.07.15.
 */
public class GeoFence {

    @Expose
    private Boolean enabled;
    @Expose
    private Integer radius;
    @Expose
    private Location fixedLocation;

    /**
     * @return The enabled
     */
    public Boolean getEnabled() {
        return enabled;
    }

    /**
     * @param enabled The enabled
     */
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * @return The radius
     */
    public Integer getRadius() {
        return radius;
    }

    /**
     * @param radius The radius
     */
    public void setRadius(Integer radius) {
        this.radius = radius;
    }
}
