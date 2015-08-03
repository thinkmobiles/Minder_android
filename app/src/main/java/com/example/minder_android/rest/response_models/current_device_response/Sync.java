package com.example.minder_android.rest.response_models.current_device_response;

import com.google.gson.annotations.Expose;

/**
 * Created by Max on 29.07.15.
 */
public class Sync {

        @Expose
        private Boolean enabled;
        @Expose
        private String lastSyncDateTime;

        /**
         *
         * @return
         * The enabled
         */
        public Boolean isEnabled() {
            return enabled;
        }

        /**
         *
         * @param enabled
         * The enabled
         */
        public void setEnabled(Boolean enabled) {
            this.enabled = enabled;
        }

        /**
         *
         * @return
         * The lastSyncDateTime
         */
        public String getLastSyncDateTime() {
            return lastSyncDateTime;
        }

        /**
         *
         * @param lastSyncDateTime
         * The lastSyncDateTime
         */
        public void setLastSyncDateTime(String lastSyncDateTime) {
            this.lastSyncDateTime = lastSyncDateTime;
        }

}
