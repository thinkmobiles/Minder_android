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
 * Created by ёзер on 05.05.2015.
 */
public final class SignInFragment extends BaseFragment {
    @InjectView(R.id.button_create_account_FL)      Button mButtonCreateAccout;
    @InjectView(R.id.button_login_FL)               Button mButtonLogin;
    @InjectView(R.id.minder_id_edit_text_fl)        EditText mMinderIdText;

    private String minderId;
    private SignInFragmentController mController;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mController = new SignInFragmentController(this);


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.inject(this, view);

        return view;
    }

    @Override
    protected void setAbTitle() {
        mActionBar.setTitle(getResources().getString(R.string.Sign_in));
    }

    @OnClick (R.id.button_login_FL)
    final void onLoginClicked(){
        if (mController.validID()) {
            getBaseActivity().switchContent(new HomeFragment(), false);
        }
    }

    @OnClick (R.id.button_create_account_FL)
    final void onCreateAccountClicked() {
        getBaseActivity().switchContent(new CreateAccountFragment());
    }


}
