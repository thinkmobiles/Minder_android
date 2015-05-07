package com.example.minder_android;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by ���� on 05.05.2015.
 */
public final class SignInFragment extends BaseFragment {
    @InjectView(R.id.create_account_button_FL)      Button btnCreateAccount;
    @InjectView(R.id.login_button_FL)               Button btnLogin;
    @InjectView(R.id.minder_id_edit_text_fl)        EditText etMinderId;

    private SignInFragmentController mController;


    @Override
    public final void onCreate(Bundle _savedInstanceState) {
        super.onCreate(_savedInstanceState);
        mController = new SignInFragmentController(this);
    }

    @Nullable
    @Override
    public final View onCreateView(final LayoutInflater _inflater, final ViewGroup _container, final Bundle _savedInstanceState) {
        View view = _inflater.inflate(R.layout.fragment_login, _container, false);
        ButterKnife.inject(this, view);
        getBaseActivity().setActionBar(true);
        etMinderId.setText(mController.getMinderId());
        return view;
    }

    @Override
    protected final void setAbTitle() {
        mActionBar.setTitle(getResources().getString(R.string.Sign_in));
    }

    @OnClick (R.id.login_button_FL)
    final void onLoginClicked(){
        if (mController.validID(etMinderId.getText().toString())) {
            getBaseActivity().switchContent(new HomeFragment(), false);
        } else {
            getBaseActivity().showDialog("Error", "Id is incorrect", null, "OK", "");
        }
    }

    @OnClick (R.id.create_account_button_FL)
    final void onCreateAccountClicked() {
        getBaseActivity().switchContent(new CreateAccountFragment());
    }

}
