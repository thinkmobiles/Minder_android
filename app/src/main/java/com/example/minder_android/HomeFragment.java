package com.example.minder_android;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by ���� on 06.05.2015.
 */
public class HomeFragment extends BaseFragment implements OnCustomClickListener{
    @InjectView(R.id.disconnect_btn_FH)         Button disconnect_btn;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        getBaseActivity().setActionBar(true);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    protected void setAbTitle() {
        mActionBar.setTitle(getResources().getString(R.string.home));
    }

    @OnClick (R.id.disconnect_btn_FH)
    final void onDisconnectClicked() {

        getBaseActivity().showDialog("Exit", "Are you sure?", this, "Yes", "No");

    }

    @Override
    public void onPositive() {
        getBaseActivity().switchContent(new SignInFragment(), false);
    }

    @Override
    public void onNegative() {

    }
}
