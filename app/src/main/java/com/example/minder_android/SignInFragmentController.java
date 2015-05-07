package com.example.minder_android;

import android.view.View;

/**
 * Created by ���� on 06.05.2015.
 */
public final class SignInFragmentController {
    private BaseFragment mFragment;
    private EmulateServerConnect mConnect;

    public SignInFragmentController(final BaseFragment _fragment) {
        mFragment = _fragment;
        mConnect = new EmulateServerConnect(mFragment.getBaseActivity());
    }


    public final boolean validID(final String _id) {
        if (_id.isEmpty() || _id == null) return false;
        if (EmulateServerConnect.getMinderId().equals(_id)) return true;
        return false;
    }

    public final String getMinderId() {
        return mConnect.getMinderId();
    }


}
