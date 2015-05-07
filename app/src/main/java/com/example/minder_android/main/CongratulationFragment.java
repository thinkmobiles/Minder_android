package com.example.minder_android.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.minder_android.base.BaseFragment;
import com.example.minder_android.R;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by ���� on 05.05.2015.
 */
public final class CongratulationFragment extends BaseFragment {
    @InjectView(R.id.ok_button_FC)      Button btn_Ok;

    @Nullable
    @Override
    public final View onCreateView(final LayoutInflater _inflater, final ViewGroup _container, final Bundle _savedInstanceState) {
        View view = _inflater.inflate(R.layout.fragment_congratulation, _container,false);
        ButterKnife.inject(this, view);
        getBaseActivity().setActionBar(false);
        return view;
    }

    @Override
    protected final void setAbTitle() {

    }

    @OnClick (R.id.ok_button_FC)
    final void onClick() {
        getBaseActivity().getFragmentManager().popBackStack();
        getBaseActivity().switchContent(new SignInFragment(), false);
    }

}
