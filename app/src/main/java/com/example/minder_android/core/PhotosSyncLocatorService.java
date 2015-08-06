package com.example.minder_android.core;

import android.content.Intent;
import android.database.Cursor;
import android.provider.MediaStore;
import android.util.Log;

import com.commonsware.cwac.wakeful.WakefulIntentService;
import com.example.minder_android.R;
import com.example.minder_android.core.events.GetDeviceConfigResultEvent;
import com.example.minder_android.core.events.MessageEvent;
import com.example.minder_android.core.utils.DateTimeConverter;
import com.example.minder_android.core.utils.ServiceUtils;
import com.example.minder_android.rest.RequestManager;
import com.example.minder_android.rest.response_models.current_device_response.DeviceConfig;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

import de.greenrobot.event.EventBus;
import retrofit.RetrofitError;

import static com.example.minder_android.core.Const.DATE_TIME_FORMAT_SERVER;
import static com.example.minder_android.core.Const.DEBUG_TAG;

/**
 * Created by Max on 31.07.15.
 * This service creates list of images taken since last sync and calls StorePhotosService to upload it
 */
public class PhotosSyncLocatorService extends WakefulIntentService {

    private static final CharSequence FILTER_PHOTO      = "DCIM";
    private static final CharSequence FILTER_SCREENSHOT = "SCREENSHOT";

    public PhotosSyncLocatorService() {
        super("PhotosSyncLocatorService");
    }
    private  ArrayList<String> mPhotoList;


    @Override
    protected void doWakefulWork(Intent intent) {
        DeviceConfig config = null;
        Log.d(DEBUG_TAG, "PHOTO LOCATOR SERVICE STARTED");

        AppSettings.init(getApplicationContext());
        if (!ServiceUtils.isNetworkAvailable(getApplicationContext())) {
            EventBus.getDefault().postSticky(new MessageEvent(
                    getApplicationContext().getString(R.string.no_connection)));
            return;
        }
        try{
            config = RequestManager.getCurrentDeviceConfig();
        } catch (RetrofitError e){
            EventBus.getDefault().postSticky(
                    new GetDeviceConfigResultEvent(getApplicationContext().getString(R.string.error_getting_device_config, e.toString())));
            return;
        }
        String lastSync = config.getSync().getLastSyncDateTime();
        if (config.getSync().isEnabled()) {
            mPhotoList = getCameraImages(lastSync);
            putImagesToSyncDB();
        }
        if (!AppSettings.getSyncList().isEmpty() &&
                !ServiceUtils.isServiceRunning(getApplicationContext(), StorePhotosService.class)) {
            startStoreService();
        }
    }


    private void startStoreService() {
        Log.d(DEBUG_TAG, "STARTING StoreService ");
        Intent intent = new Intent(getApplicationContext(), StorePhotosService.class);
        WakefulIntentService.sendWakefulWork(getApplicationContext(), intent);
    }

    private void putImagesToSyncDB() {
        AppSettings.addToSyncList(mPhotoList);
    }

    public ArrayList<String> getCameraImages(String _lastSync) {
        final String[] projection = { MediaStore.Images.Media.DATA, MediaStore.Images.Media.DATE_TAKEN };
        final Cursor cursor = getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                projection,
                null,null,null);
        ArrayList<String> result = new ArrayList<>(cursor.getCount());
        if (cursor.moveToFirst()) {
            final int dataColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            final int dateTakenColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATE_TAKEN);
            do {
                if (isNeededFileSync(cursor.getString(dateTakenColumn), _lastSync)
                        && isPhotoOrScreenshot(cursor.getString(dataColumn))) {
                    final String data = cursor.getString(dataColumn);
                    result.add(data);
                }
            } while (cursor.moveToNext());
        }
        cursor.close();
        return result;
    }

    private boolean isPhotoOrScreenshot(String _fileUri) {
        return _fileUri.toUpperCase().contains(FILTER_PHOTO) || _fileUri.toUpperCase().contains(FILTER_SCREENSHOT);
    }


    private boolean isNeededFileSync(String _fileDate, String _lastSync) {
        boolean result = false;
        if ("".equals(_lastSync) || _lastSync == null) {
            return true;
        }
        try {
            result = new Date(Long.valueOf(_fileDate))
                    .after(DateTimeConverter.convertFromUTC(DateTimeConverter.getDateFromString(_lastSync, DATE_TIME_FORMAT_SERVER)));
        } catch (ParseException e) {
            e.printStackTrace();
        };
        return result;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(DEBUG_TAG, "DESTROING LOCATOR ");

    }
}
