package com.example.minder_android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.minder_android.core.Const;

/**
 * Created by ���� on 05.05.2015.
 */
public final class SplashActivity extends Activity {


    @Override
    public final void onCreate(final Bundle _savedInstanceState) {
        super.onCreate(_savedInstanceState);
        setContentView(R.layout.activity_splash);

        Thread splashTread = new Thread() {
            @Override
            public void run() {

                try {
                    sleep(Const.SLEEP_TIME);
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
