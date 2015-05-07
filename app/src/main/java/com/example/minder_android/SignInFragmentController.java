package com.example.minder_android;

import android.view.View;

/**
 * Created by ���� on 06.05.2015.
 */
public final class SignInFragmentController {
    private BaseFragment mFragment;
    private EmulateServerConnect connect;

    public SignInFragmentController(BaseFragment _fragment) {
        mFragment = _fragment;
        connect = new EmulateServerConnect(mFragment.getBaseActivity());
    }


    public final boolean validID(String _id) {
        if (EmulateServerConnect.getMinderId().equals(_id)) return true;
        return false;
    }

    public final String getMinderId() {
        return connect.getMinderId();
    }


}
