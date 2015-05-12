package com.example.minder_android.base;

import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;

/**
 * Created by ���� on 05.05.2015.
 */
public abstract class BaseFragment extends Fragment {
    private BaseActivity mActivity;
    protected ActionBar mActionBar;

    @Override
    public void onCreate(Bundle _savedInstanceState) {
        super.onCreate(_savedInstanceState);
        mActivity   = (BaseActivity) getActivity();
        mActionBar  = mActivity.getActionBar();

    }

    @Override
    public final void onResume() {
        super.onResume();
        if (mActionBar != null) {
            prepareActionBar();

        }
    }

    protected abstract void prepareActionBar();

    public final BaseActivity getBaseActivity() {
        return mActivity;
    }

}
