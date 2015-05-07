package com.example.minder_android;

import android.app.Fragment;
import android.os.Bundle;

public final class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(final Bundle _savedInstanceState) {
        super.onCreate(_savedInstanceState);
        setContentView(R.layout.activity_main);
        getActionBar().setDisplayShowHomeEnabled(false);
        switchContent(new SignInFragment(), false);

    }

    @Override
    public final void onBackPressed() {
        final Fragment currentFragment = getFragmentManager().findFragmentById(R.id.fl_content);
        if(!(currentFragment instanceof CongratulationFragment)) {
            super.onBackPressed();
        }
    }
}
