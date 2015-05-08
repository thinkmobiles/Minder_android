package com.example.minder_android.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.minder_android.base.BaseFragment;
import com.example.minder_android.R;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by ���� on 05.05.2015.
 */
public final class CreateAccountFragment extends BaseFragment {
    @InjectView(R.id.first_name_FCA)        EditText etFisrtName;
    @InjectView(R.id.last_name_FCA)         EditText etLastName;
    @InjectView(R.id.email_FCA)             EditText etEmail;
    @InjectView(R.id.password_FCA)          EditText etPassword;
    @InjectView(R.id.confirm_password_FCA)  EditText etConfirmPassword;
    @InjectView(R.id.button_signup_FCA)     Button btnSignUp;
    @InjectView(R.id.accept_CB_FCA)         CheckBox cbAccept;

    private CreateAccountFragmentController mController;

    @Override
    public final void onCreate(Bundle _savedInstanceState) {
        super.onCreate(_savedInstanceState);
        mController = new CreateAccountFragmentController(this);

    }



    @Nullable
    @Override
    public final View onCreateView(final LayoutInflater _inflater, final ViewGroup _container, final Bundle _savedInstanceState) {
        View view = _inflater.inflate(R.layout.fragment_create_account, _container, false);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    protected final void setActionBar() {
        mActionBar.setTitle(getResources().getString(R.string.Sing_up));
        mActionBar.setDisplayHomeAsUpEnabled(true);
    }


    @OnClick (R.id.button_signup_FCA)
    final void onSignUpClicked() {
        final String check = mController.checkInput(etFisrtName.getText().toString(), etLastName.getText().toString(),
                etEmail.getText().toString(), etPassword.getText().toString(),
                etConfirmPassword.getText().toString(), cbAccept.isChecked());
        if (check.equals(getString(R.string.successful))) {
            getBaseActivity().getFragmentManager().popBackStack();
            getBaseActivity().switchContent(new CongratulationFragment());
        } else {
            getBaseActivity().showDialog("Input error", check, null, "", "OK");
        }

    }

}
