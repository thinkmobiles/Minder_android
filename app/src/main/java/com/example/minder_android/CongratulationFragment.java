package com.example.minder_android;

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
 * Created by ёзер on 05.05.2015.
 */
public class CongratulationFragment extends BaseFragment {
    @InjectView(R.id.ok_button_FC)      Button mOk_Btn;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_congratulation, container,false);
        ButterKnife.inject(this, view);
        getBaseActivity().setActionBar(false);
        return view;
    }

    @Override
    protected void setAbTitle() {

    }

    @OnClick (R.id.ok_button_FC)
    final void onClick() {
        getBaseActivity().getFragmentManager().popBackStack();
        getBaseActivity().switchContent(new SignInFragment(), false);
    }

}
