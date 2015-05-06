package com.example.minder_android;

import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by ёзер on 05.05.2015.
 */
public abstract class BaseFragment extends Fragment {
    private BaseActivity mActivity;
    protected ActionBar mActionBar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity   = (BaseActivity) getActivity();
        mActionBar  = mActivity.getActionBar();

    }

    @Override
    public void onResume() {
        super.onResume();
        if (mActionBar != null) {
            setAbTitle();
        }
    }

    protected abstract void setAbTitle();


    public final BaseActivity getBaseActivity() {
        return mActivity;
    }

}
