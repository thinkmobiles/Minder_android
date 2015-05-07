package com.example.minder_android;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by ���� on 05.05.2015.
 */
public class CreateAccountFragment extends BaseFragment {
    @InjectView(R.id.first_name_FCA)        EditText mFirstNameET;
    @InjectView(R.id.last_name_FCA)         EditText mLastNameET;
    @InjectView(R.id.email_FCA)             EditText mEmailET;
    @InjectView(R.id.password_FCA)          EditText mPasswordET;
    @InjectView(R.id.confirm_password_FCA)  EditText mConfirmPasswordET;
    @InjectView(R.id.button_signup_FCA)     Button mSignUpBtn;
    @InjectView(R.id.accept_CB_FCA)         CheckBox mAcceptCB;

    private CreateAccountFragmentController mController;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mController = new CreateAccountFragmentController(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create_account, container, false);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    protected void setAbTitle() {
        mActionBar.setTitle(getResources().getString(R.string.Sing_up));
    }


    @OnClick (R.id.button_signup_FCA)
    final void onSignUpClicked() {
        String check = mController.checkInput(mFirstNameET.getText().toString(), mLastNameET.getText().toString(),
                                        mEmailET.getText().toString(), mPasswordET.getText().toString(),
                                        mConfirmPasswordET.getText().toString(), mAcceptCB.isChecked() );
        if (check.equals(Const.SUCESSFUL)) {
            getBaseActivity().getFragmentManager().popBackStack();
            getBaseActivity().switchContent(new CongratulationFragment());
        } else {
            getBaseActivity().showAlertDialog("Input error", check, null, "", "OK");
        }

    }

}
