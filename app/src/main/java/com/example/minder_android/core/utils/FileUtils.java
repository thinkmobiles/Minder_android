package com.example.minder_android.core.utils;

import android.webkit.MimeTypeMap;

import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit.mime.TypedFile;

import static com.example.minder_android.core.Const.DATE_TIME_FORMAT;

/**
 * Created by Max on 29.07.15.
 */
public class FileUtils {

    // url = file path or whatever suitable URL you want.
    public static String getMimeType(String url) {
        String type = null;
        String extension = MimeTypeMap.getFileExtensionFromUrl(url);
        if (extension != null) {
            type = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension);
        }
        return type;
    }


    public static String getDate(TypedFile file) {
        return  new SimpleDateFormat(DATE_TIME_FORMAT).format(new Date(file.file().lastModified()));
    }


}
