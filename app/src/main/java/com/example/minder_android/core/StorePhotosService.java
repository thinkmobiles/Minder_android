package com.example.minder_android.core;

import android.content.Intent;
import android.util.Log;

import com.commonsware.cwac.wakeful.WakefulIntentService;
import com.example.minder_android.R;
import com.example.minder_android.core.events.FileUploadResultEvent;
import com.example.minder_android.core.events.PhotoSyncSuccessEvent;
import com.example.minder_android.core.utils.FileUtils;
import com.example.minder_android.core.utils.ServiceUtils;
import com.example.minder_android.rest.RequestManager;
import com.example.minder_android.rest.response_models.sync_response.SyncResponse;

import java.util.List;

import de.greenrobot.event.EventBus;
import retrofit.RetrofitError;
import retrofit.mime.TypedFile;

import static com.example.minder_android.core.Const.DEBUG_TAG;

/**
 * Created by Max on 28.07.15.
 */
public class StorePhotosService extends WakefulIntentService{

    private  List<String> mPhotoList;

    public StorePhotosService() {
        super("StorePhotosService");
    }


    @Override
    protected void doWakefulWork(Intent intent) {
        Log.d(DEBUG_TAG, "STORE SERVICE STARTED");
        AppSettings.init(getApplicationContext());
        mPhotoList = AppSettings.getSyncList();
        storeImagesToServer();
    }



    private void storeImagesToServer() {
        for (String image : mPhotoList) {
            if (ServiceUtils.isNetworkAvailable(getApplicationContext())) {
                uploadImage(image);
            }
        }
    }

    private void uploadImage(final String _image) {
        SyncResponse response = null;
        Log.d(DEBUG_TAG, "STORING " + _image);
                TypedFile file = FileUtils.getTypedFile(_image);
        try {
            response = RequestManager.sync(file.fileName(), FileUtils.getDate(file), file);

        } catch (RetrofitError error) {
            EventBus.getDefault().postSticky(new FileUploadResultEvent(getApplicationContext().getString(R.string.upload_failed, file.fileName(), error.getBody().toString())));
            return;
        }

        if (response.getSuccess().equalsIgnoreCase(getApplicationContext().getString(R.string.success_created))) {
            handleResponse(response);
        }
    }

    private void handleResponse(SyncResponse _response) {
        if (_response.getModel() != null ) {
            EventBus.getDefault().postSticky(
                    new PhotoSyncSuccessEvent(this.getString(R.string.file_saved, _response.getModel().getOriginalName())));
            AppSettings.deleteFromSyncList(getItemFromList(_response.getModel().getOriginalName()));

        }
    }

    private String getItemFromList(String _fileName) {
        for (String item : mPhotoList) {
            if (item.toUpperCase().contains(_fileName.toUpperCase())) {
                return item;
            }
        }
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(DEBUG_TAG, "DESTROING PHOTO STORE SERVICE");

    }
}
