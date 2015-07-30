package com.example.minder_android.rest.response_models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Max on 29.07.15.
 */
public class DeviceConfig {

    @SerializedName("_id")
    @Expose
    private String Id;
    @Expose
    private String user;
    @Expose
    private String deviceId;
    @Expose
    private String deviceType;
    @Expose
    private String updatedAt;
    @Expose
    private String createdAt;
    @Expose
    private Sync sync;
    @Expose
    private GeoFence geoFence;

    @Expose
    private LastLocation lastLocation;
    @Expose
    private Integer status;
    @Expose
    private String name;

    /**
     *
     * @return
     * The Id
     */
    public String getId() {
        return Id;
    }

    /**
     *
     * @param Id
     * The _id
     */
    public void setId(String Id) {
        this.Id = Id;
    }

    /**
     *
     * @return
     * The user
     */
    public String getUser() {
        return user;
    }

    /**
     *
     * @param user
     * The user
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     *
     * @return
     * The deviceId
     */
    public String getDeviceId() {
        return deviceId;
    }

    /**
     *
     * @param deviceId
     * The deviceId
     */
    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    /**
     *
     * @return
     * The deviceType
     */
    public String getDeviceType() {
        return deviceType;
    }

    /**
     *
     * @param deviceType
     * The deviceType
     */
    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    /**
     *
     * @return
     * The updatedAt
     */
    public String getUpdatedAt() {
        return updatedAt;
    }

    /**
     *
     * @param updatedAt
     * The updatedAt
     */
    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    /**
     *
     * @return
     * The createdAt
     */
    public String getCreatedAt() {
        return createdAt;
    }

    /**
     *
     * @param createdAt
     * The createdAt
     */
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    /**
     *
     * @return
     * The sync
     */
    public Sync getSync() {
        return sync;
    }

    /**
     *
     * @param sync
     * The sync
     */
    public void setSync(Sync sync) {
        this.sync = sync;
    }

    /**
     *
     * @return
     * The geoFence
     */
    public GeoFence getGeoFence() {
        return geoFence;
    }

    /**
     *
     * @param geoFence
     * The geoFence
     */
    public void setGeoFence(GeoFence geoFence) {
        this.geoFence = geoFence;
    }

    /**
     *
     * @return
     * The lastLocation
     */
    public LastLocation getLastLocation() {
        return lastLocation;
    }

    /**
     *
     * @param lastLocation
     * The lastLocation
     */
    public void setLastLocation(LastLocation lastLocation) {
        this.lastLocation = lastLocation;
    }

    /**
     *
     * @return
     * The status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     *
     * @param status
     * The status
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     *
     * @return
     * The name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     * The name
     */
    public void setName(String name) {
        this.name = name;
    }

}