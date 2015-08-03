package com.example.minder_android.rest.response_models.sync_response;

import com.google.gson.annotations.Expose;

/**
 * Created by Max on 31.07.15.
 */
public class SyncResponse {

    @Expose
    private String success;
    @Expose
    private Model model;
    @Expose
    private String url;

    /**
     *
     * @return
     *     The success
     */
    public String getSuccess() {
        return success;
    }

    /**
     *
     * @param success
     *     The success
     */
    public void setSuccess(String success) {
        this.success = success;
    }

    /**
     *
     * @return
     *     The model
     */
    public Model getModel() {
        return model;
    }

    /**
     *
     * @param model
     *     The model
     */
    public void setModel(Model model) {
        this.model = model;
    }

    /**
     *
     * @return
     *     The url
     */
    public String getUrl() {
        return url;
    }

    /**
     *
     * @param url
     *     The url
     */
    public void setUrl(String url) {
        this.url = url;
    }

}
