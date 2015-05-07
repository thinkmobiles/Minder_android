package com.example.minder_android;

import android.app.Fragment;
import android.os.Bundle;

import com.example.minder_android.base.BaseActivity;
import com.example.minder_android.main.CongratulationFragment;
import com.example.minder_android.main.SignInFragment;

public final class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(final Bundle _savedInstanceState) {
        super.onCreate(_savedInstanceState);
        setContentView(R.layout.activity_main);
        getActionBar().setDisplayShowHomeEnabled(false);
        if (_savedInstanceState == null)
            switchContent(new SignInFragment(), false);
        else {
            switchContent(getFragmentManager().findFragmentByTag(_savedInstanceState.getString("tag")),false);
        }

    }



    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString("tag", getCurrentFragment().getTag().toString());
        super.onSaveInstanceState(outState);
    }

    @Override
    public final void onBackPressed() {

        if(!(getCurrentFragment() instanceof CongratulationFragment)) {
            super.onBackPressed();
        }
    }


    private final Fragment getCurrentFragment() {
        return  getFragmentManager().findFragmentById(R.id.fl_content);
    }
}
