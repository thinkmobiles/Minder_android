package com.example.minder_android.base;

import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.minder_android.R;

/**
 * Created by ���� on 05.05.2015.
 */
public abstract class BaseFragment extends Fragment {
    private BaseActivity mActivity;
    protected ActionBar mActionBar;
    protected Toolbar mToolbar;

    @Override
    public void onCreate(Bundle _savedInstanceState) {
        super.onCreate(_savedInstanceState);
        mActivity   = (BaseActivity) getActivity();
        //mActionBar  = mActivity.getActionBar();
        mToolbar = (Toolbar) mActivity.findViewById(R.id.toolbar);
    }

    @Override
    public final void onResume() {
        super.onResume();
        if (mToolbar != null) {
            prepareToolBar();
        }
        //if (mActionBar != null) {
        //    prepareActionBar();

        //}
    }

    //protected abstract void prepareActionBar();
    protected abstract void prepareToolBar();

    public final BaseActivity getBaseActivity() {
        return mActivity;
    }

}
