package com.example.minder_android.core;

import android.content.Intent;
import android.database.Cursor;
import android.os.Environment;
import android.provider.MediaStore;

import com.commonsware.cwac.wakeful.WakefulIntentService;
import com.example.minder_android.R;
import com.example.minder_android.core.events.FunctionalityDisabledEvent;
import com.example.minder_android.core.events.PhotoSyncSuccessEvent;
import com.example.minder_android.core.utils.DateTimeConverter;
import com.example.minder_android.core.utils.FileUtils;
import com.example.minder_android.rest.RequestManager;
import com.example.minder_android.rest.response_models.DeviceConfig;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.File;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import de.greenrobot.event.EventBus;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.mime.TypedFile;

import static com.example.minder_android.core.Const.DATE_TIME_FORMAT_SERVER;
import static com.example.minder_android.core.Const.ERROR_MESSAGE_FUNCTIONALITY_DISABLED;
import static com.example.minder_android.core.Const.KEY_ERROR;
import static com.example.minder_android.core.Const.KEY_MODEL;
import static com.example.minder_android.core.Const.KEY_ORIGINAL_NAME;
import static com.example.minder_android.core.Const.KEY_SUCCESS;

/**
 * Created by Max on 28.07.15.
 */
public class StorePhotosService extends WakefulIntentService{
    public static final String CAMERA_IMAGE_BUCKET_NAME =
            Environment.getExternalStorageDirectory().toString()
                    + "/DCIM/Camera";
    public static final String CAMERA_IMAGE_BUCKET_ID =
            getBucketId(CAMERA_IMAGE_BUCKET_NAME);

    private  List<String> mPhotoList;

    public StorePhotosService() {
        super("StorePhotosService");
    }


    @Override
    protected void doWakefulWork(Intent intent) {
//        android.os.Debug.waitForDebugger();
        AppSettings.init(getApplicationContext());
        DeviceConfig config = getCurrentDeviceConfigFromServer();
        String lastSync = config.getSync().getLastSyncDateTime();
        if (config.getSync().isEnabled()) {
            mPhotoList = getCameraImages(lastSync);
            storeImagesToServer();
        }

    }

    private DeviceConfig getCurrentDeviceConfigFromServer() {
        Gson gson = new Gson();
        JsonObject deviceConfig = RequestManager.getCurrentDeviceConfig();
        return gson.fromJson(deviceConfig, DeviceConfig.class);
    }

    private void storeImagesToServer() {
        for (String image : mPhotoList){
            uploadImage(image);
        }
    }

    private void uploadImage(String _image) {
        TypedFile file = getFile(_image);
        RequestManager.sync(file.fileName(), FileUtils.getDate(file), file, new Callback<JsonObject>() {
            @Override
            public void success(JsonObject _jsonObject, Response _response) {
                handleResponse(_jsonObject);
            }

            @Override
            public void failure(RetrofitError _error) {
                handleError(_error);
            }
        });
    }

    private void handleError(RetrofitError _error) {
        JsonObject error = (JsonObject) _error.getBodyAs(JsonObject.class);
        if (error.get(KEY_ERROR).getAsString().equals(ERROR_MESSAGE_FUNCTIONALITY_DISABLED)) {
            EventBus.getDefault().postSticky(
                    new FunctionalityDisabledEvent(this.getString(R.string.func_disabled, ERROR_MESSAGE_FUNCTIONALITY_DISABLED)));
        }

    }

    private void handleResponse(JsonObject _response) {
        if (_response.has(KEY_SUCCESS) && _response.has(KEY_MODEL) ) {
            EventBus.getDefault().postSticky(
                    new PhotoSyncSuccessEvent(this.getString(R.string.file_saved, getFileNameFromResponse(_response))));
        }
    }

    private String getFileNameFromResponse(JsonObject _response) {
        return _response.get(KEY_MODEL).getAsJsonObject().get(KEY_ORIGINAL_NAME).getAsString();
    }

    private TypedFile getFile(String _image) {
        String type = FileUtils.getMimeType(_image);
        return new TypedFile(type, new File(_image));
    }

    public List<String> getCameraImages(String _lastSync) {
        final String[] projection = { MediaStore.Images.Media.DATA, MediaStore.Images.Media.DATE_ADDED };
        final String selection = MediaStore.Images.Media.BUCKET_ID + " = ?";
        final String[] selectionArgs = { CAMERA_IMAGE_BUCKET_ID };
        final Cursor cursor = getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                projection,
                selection,
                selectionArgs,
                null);
        ArrayList<String> result = new ArrayList<String>(cursor.getCount());
        if (cursor.moveToFirst()) {
            final int dataColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            final int dateAddedColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATE_ADDED);
            do {
                if (isNeededFileSync(cursor.getString(dateAddedColumn), _lastSync)) {
                    final String data = cursor.getString(dataColumn);
                    result.add(data);
                }
            } while (cursor.moveToNext());
        }
        cursor.close();
        return result;
    }

    private boolean isNeededFileSync(String _fileDate, String _lastSync) {
        boolean result = false;
        if ("".equals(_lastSync) || _lastSync == null) {
            return true;
        }
        try {
            result = new Date(Long.valueOf(_fileDate) * 1000)
                    .after(DateTimeConverter.convertFromUTC(DateTimeConverter.getDateFromString(_lastSync, DATE_TIME_FORMAT_SERVER)));
        } catch (ParseException e) {
            e.printStackTrace();
        };
        return result;
    }

    /**
     * Matches code in MediaProvider.computeBucketValues. Should be a common
     * function.
     */
    public static String getBucketId(String path) {
        return String.valueOf(path.toLowerCase().hashCode());
    }
}
