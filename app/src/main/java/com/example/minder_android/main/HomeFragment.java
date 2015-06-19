package com.example.minder_android.main;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.minder_android.base.BaseFragment;
import com.example.minder_android.R;
import com.example.minder_android.core.OnCustomClickListener;
import com.example.minder_android.core.HomeServiceCallBack;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by ���� on 06.05.2015.
 */
public final class HomeFragment extends BaseFragment {
    @InjectView(R.id.home_TV_FH)                TextView tvHomeScreen;

    private HomeFragmentController mController;

    @Override
    public void onCreate(Bundle _savedInstanceState) {
        super.onCreate(_savedInstanceState);
        mController = new HomeFragmentController(this);
   }

    @Override
    protected void prepareToolBar() {

    }

    @Nullable
    @Override
    public final View onCreateView(final LayoutInflater _inflater, final ViewGroup _container, final Bundle _savedInstanceState) {
        View view = _inflater.inflate(R.layout.fragment_home, _container, false);
        //getBaseActivity().setActionBar(true);
        ButterKnife.inject(this, view);
        mController.onCreateView();

        return view;
    }

    @Override
    public void onDestroy() {
        mController.onDestroy();
        mController = null;
        super.onDestroy();
    }

    /*@Override
    protected final void prepareActionBar() {
        mActionBar.setTitle(getString(R.string.home));
        mActionBar.setDisplayHomeAsUpEnabled(false);
    }
*/
    @OnClick (R.id.disconnect_btn_FH)
    final void onDisconnectClicked() {
        getBaseActivity().showDialog("Exit", "Are you sure?", "Yes", "No", new OnCustomClickListener() {
            @Override
            public void onPositive() {
                mController.onDisconnect();
                getBaseActivity().switchContent(new SignInFragment(), false);
            }

            @Override
            public void onNegative() {

            }
        });
    }

    public void setHomeScreenText(final String _message) {
        tvHomeScreen.setText(_message);
    }

}
