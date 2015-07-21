package com.example.minder_android.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.minder_android.R;
import com.example.minder_android.base.BaseFragment;
import com.example.minder_android.core.AppSettings;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by ���� on 05.05.2015.
 */
public final class SignInFragment extends BaseFragment {
    @InjectView(R.id.minder_id_edit_text_fl)        EditText etMinderId;

    private SignInFragmentController mController;


    @Override
    public final void onCreate(Bundle _savedInstanceState) {
        super.onCreate(_savedInstanceState);
        mController = new SignInFragmentController(this);

    }

    @Override
    protected void prepareToolBar() {
        setToolbarVisibility(true);
        getBaseActivity().getToolbar().setTitle(getResources().getString(R.string.Sign_in));
        getBaseActivity().getToolbar().setNavigationIcon(null);
    }

    @Nullable
    @Override
    public final View onCreateView(final LayoutInflater _inflater, final ViewGroup _container, final Bundle _savedInstanceState) {
        View view = _inflater.inflate(R.layout.fragment_login, _container, false);
        ButterKnife.inject(this, view);
        etMinderId.setText(AppSettings.getMinderId());
        return view;
    }

    @OnClick (R.id.login_button_FL)
    final void onLoginClicked(){
        if (mController.validID(etMinderId.getText().toString())) {
            mController.signInUser(etMinderId.getText().toString());
        }
    }

    @OnClick (R.id.create_account_button_FL)
    final void onCreateAccountClicked() {
        getBaseActivity().switchContent(new CreateAccountFragment(), true);
    }

    public void onUserSignInFailure() {
        getBaseActivity().showDialog(getString(R.string.id_is_incorrect),getString(android.R.string.ok), null, null);

    }

    public void onUserSignInSuccess() {
        getBaseActivity().switchContent(new HomeFragment(), false);
    }
}
