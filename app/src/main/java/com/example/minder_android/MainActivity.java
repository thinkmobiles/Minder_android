package com.example.minder_android;

import android.app.Fragment;
import android.os.Bundle;

import com.example.minder_android.base.BaseActivity;
import com.example.minder_android.core.AppSettings;
import com.example.minder_android.core.StoreLocationReceiver;
import com.example.minder_android.main.CongratulationFragment;
import com.example.minder_android.main.HomeFragment;
import com.example.minder_android.main.SignInFragment;
import com.example.minder_android.rest.RequestManager;

public final class MainActivity extends BaseActivity {


    @Override
    protected void onCreate(final Bundle _savedInstanceState) {
        super.onCreate(_savedInstanceState);

        RequestManager.registerClient(getApplicationContext());
        AppSettings.init(getApplicationContext());
        if (_savedInstanceState == null) {
            if (StoreLocationReceiver.isAlarmsScheduled(this)) {
                switchContent(new HomeFragment(), false);
            } else {
                switchContent(new SignInFragment(), false);
            }
        } else {
            switchContent(getFragmentManager().findFragmentByTag(_savedInstanceState.getString("tag")), false);
        }

    }

    @Override
    protected void onSaveInstanceState(Bundle _outState) {
        _outState.putString("tag", getCurrentFragment().getTag().toString());
        super.onSaveInstanceState(_outState);
    }

    @Override
    public final void onBackPressed() {

        if (!(getCurrentFragment() instanceof CongratulationFragment)) {
            super.onBackPressed();
        }
    }


    private final Fragment getCurrentFragment() {
        return getFragmentManager().findFragmentById(R.id.fl_content);
    }
}
