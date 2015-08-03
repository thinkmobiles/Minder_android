package com.example.minder_android.rest.response_models.sync_response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Max on 31.07.15.
 */
public class Model {

        @SerializedName("__v")
        @Expose
        private Integer V;
        @Expose
        private String device;
        @SerializedName("_id")
        @Expose
        private String Id;
        @Expose
        private String updatedAt;
        @Expose
        private String createdAt;
        @Expose
        private String fileCreatedAt;
        @Expose
        private String path;
        @Expose
        private String key;
        @Expose
        private String name;
        @Expose
        private String originalName;
        @Expose
        private String url;
        @Expose
        private String id;

        /**
         *
         * @return
         *     The V
         */
        public Integer getV() {
            return V;
        }

        /**
         *
         * @param V
         *     The __v
         */
        public void setV(Integer V) {
            this.V = V;
        }

        /**
         *
         * @return
         *     The device
         */
        public String getDevice() {
            return device;
        }

        /**
         *
         * @param device
         *     The device
         */
        public void setDevice(String device) {
            this.device = device;
        }

        /**
         *
         * @return
         *     The Id
         */
        public String getId() {
            return Id;
        }

        /**
         *
         * @param Id
         *     The _id
         */
        public void setId(String Id) {
            this.Id = Id;
        }

        /**
         *
         * @return
         *     The updatedAt
         */
        public String getUpdatedAt() {
            return updatedAt;
        }

        /**
         *
         * @param updatedAt
         *     The updatedAt
         */
        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }

        /**
         *
         * @return
         *     The createdAt
         */
        public String getCreatedAt() {
            return createdAt;
        }

        /**
         *
         * @param createdAt
         *     The createdAt
         */
        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        /**
         *
         * @return
         *     The fileCreatedAt
         */
        public String getFileCreatedAt() {
            return fileCreatedAt;
        }

        /**
         *
         * @param fileCreatedAt
         *     The fileCreatedAt
         */
        public void setFileCreatedAt(String fileCreatedAt) {
            this.fileCreatedAt = fileCreatedAt;
        }

        /**
         *
         * @return
         *     The path
         */
        public String getPath() {
            return path;
        }

        /**
         *
         * @param path
         *     The path
         */
        public void setPath(String path) {
            this.path = path;
        }

        /**
         *
         * @return
         *     The key
         */
        public String getKey() {
            return key;
        }

        /**
         *
         * @param key
         *     The key
         */
        public void setKey(String key) {
            this.key = key;
        }

        /**
         *
         * @return
         *     The name
         */
        public String getName() {
            return name;
        }

        /**
         *
         * @param name
         *     The name
         */
        public void setName(String name) {
            this.name = name;
        }

        /**
         *
         * @return
         *     The originalName
         */
        public String getOriginalName() {
            return originalName;
        }

        /**
         *
         * @param originalName
         *     The originalName
         */
        public void setOriginalName(String originalName) {
            this.originalName = originalName;
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

        /**
         *
         * @return
         *     The id
         */
        public String getid() {
            return id;
        }

        /**
         *
         * @param id
         *     The id
         */
        public void setid(String id) {
            this.id = id;
        }

}
