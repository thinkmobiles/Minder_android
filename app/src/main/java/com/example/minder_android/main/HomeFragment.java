package com.example.minder_android.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.minder_android.base.BaseFragment;
import com.example.minder_android.R;
import com.example.minder_android.core.OnCustomClickListener;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by ���� on 06.05.2015.
 */
public final class HomeFragment extends BaseFragment implements OnCustomClickListener {
    @InjectView(R.id.disconnect_btn_FH)         Button btnDisconnect;


    @Nullable
    @Override
    public final View onCreateView(final LayoutInflater _inflater, final ViewGroup _container, final Bundle _savedInstanceState) {
        View view = _inflater.inflate(R.layout.fragment_home, _container, false);
        getBaseActivity().setActionBar(true);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    protected final void setActionBar() {
        mActionBar.setTitle(getString(R.string.home));
        mActionBar.setDisplayHomeAsUpEnabled(false);
    }

    @OnClick (R.id.disconnect_btn_FH)
    final void onDisconnectClicked() {
        getBaseActivity().showDialog("Exit", "Are you sure?", this, "Yes", "No");
    }

    @Override
    public final void onPositive() {
        getBaseActivity().switchContent(new SignInFragment(), false);
    }

    @Override
    public final void onNegative() {}
}
