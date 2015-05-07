package com.example.minder_android.main;

import com.example.minder_android.base.BaseFragment;
import com.example.minder_android.R;
import com.example.minder_android.core.Const;
import com.example.minder_android.core.utils.EmailValidator;
import com.example.minder_android.core.EmulateServerConnect;

/**
 * Created by ���� on 06.05.2015.
 */
public final class CreateAccountFragmentController {
    private BaseFragment mFragment;


    public CreateAccountFragmentController(BaseFragment _fragment) {
        mFragment = _fragment;
    }

    public final String checkInput(final String _firstName, final String _lastName, final String _email, final String _password, final String _confirmPassword, final boolean _checkTerms) {
        if (_firstName.isEmpty()) return mFragment.getString(R.string.firstname_error);
        if (_lastName.isEmpty()) return mFragment.getString(R.string.lastname_error);
        if (!validEmail(_email)) return mFragment.getString(R.string.email_error);
        if (_password.isEmpty()) return mFragment.getString(R.string.password_error);
        if (_password.length() < Const.MIN_PASSWORD_LENGTH) return mFragment.getString(R.string.password_length_error) + " " + Const.MIN_PASSWORD_LENGTH;
        if (!_password.equals(_confirmPassword)) return mFragment.getString(R.string.confirm_error);
        if (!_checkTerms) return mFragment.getString(R.string.accept_terms_error);
        EmulateServerConnect.postMinderId();

        return mFragment.getString(R.string.successful);
    }


    private final boolean validEmail(final String _email) {
        EmailValidator validator = new EmailValidator();

        return validator.validate(_email);
    }
}
