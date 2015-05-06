package com.example.minder_android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by ёзер on 05.05.2015.
 */
public final class SplashActivity extends Activity {
    private final int SLEEP_TIME = 3000;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_activity);



        Thread splashTread = new Thread() {
            @Override
            public void run() {

                try {
                    sleep(SLEEP_TIME);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {

                    startActivity(new Intent(SplashActivity.this,
                            MainActivity.class));
                    finish();
                }
            }

            ;
        };
        splashTread.start();


    }

}
