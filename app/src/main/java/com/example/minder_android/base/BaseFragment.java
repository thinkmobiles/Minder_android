package com.example.minder_android.base;

import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.minder_android.R;

/**
 * Created by ���� on 05.05.2015.
 */
public abstract class BaseFragment extends Fragment {
    private BaseActivity mActivity;

    @Override
    public void onCreate(Bundle _savedInstanceState) {
        super.onCreate(_savedInstanceState);
        mActivity   = (BaseActivity) getActivity();
    }

    @Override
    public final void onResume() {
        super.onResume();
        if (mActivity.getToolbar() != null) {
            prepareToolBar();
        }
    }

    protected abstract void prepareToolBar();

    public final void setToolbarVisibility(final boolean _visible) {
        if (mActivity.getToolbar() != null) {
            mActivity.getToolbar().setVisibility(_visible ? View.VISIBLE : View.GONE);
        }
    }

    public final BaseActivity getBaseActivity() {
        return mActivity;
    }

}
