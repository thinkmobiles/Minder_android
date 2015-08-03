package com.example.minder_android.core.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

import static com.example.minder_android.core.Const.DEBUG_TAG;

/**
 * Created by Max on 31.07.15.
 */
public class ServiceUtils {

    public static boolean isServiceRunning(Context _context, Class _serviceClazz) {
        ActivityManager manager = (ActivityManager) _context.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (_serviceClazz.getName().equals(service.service.getClassName())) {
                Log.d(DEBUG_TAG, "PHOTO STORE SERVICE CHECK - RUNNING");
                return true;
            }
        }
        Log.d(DEBUG_TAG, "PHOTO STORE SERVICE CHECK - NOT RUNNING");
        return false;
    }

    public static boolean isNetworkAvailable(Context _context) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) _context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }



    public static boolean isServerReachable(String _ip, int _port) {
        boolean isReachable = false;

        try {
            SocketAddress sockaddr = new InetSocketAddress(_ip, _port);
            // Create an unbound socket
            Socket sock = new Socket();

            // This method will block no more than timeoutMs.
            // If the timeout occurs, SocketTimeoutException is thrown.
            int timeoutMs = 2000;   // 2 seconds
            sock.connect(sockaddr, timeoutMs);
            isReachable = true;
        } catch (Exception e) {
        }
        return isReachable;
    }
}
