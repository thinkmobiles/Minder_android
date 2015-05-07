package com.example.minder_android;

import android.view.View;

/**
 * Created by ���� on 06.05.2015.
 */
public final class CreateAccountFragmentController {
    private BaseFragment mFragment;


    public CreateAccountFragmentController(BaseFragment _fragment) {
        mFragment = _fragment;
    }

    public final String checkInput(final String _firstName, final String _lastName, final String _email, final String _password, final String _confirmPassword, final boolean _checkTerms) {
        if (_firstName.isEmpty()) return Const.FIRSTNAME_ERROR;
        if (_lastName.isEmpty()) return Const.LASTNAME_ERROR;
        if (!validEmail(_email)) return Const.EMAIL_ERROR;
        if (_password.isEmpty()) return Const.PASSWORD_EMPTY_ERROR;
        if (_password.length() < Const.MIN_PASSWORD_LENGTH) return Const.PASSWORD_LENGTH_ERROR;
        if (!_password.equals(_confirmPassword)) return Const.CONFIRM_ERROR;
        if (!_checkTerms) return Const.ACCEPT_TERMS_ERROR;
        EmulateServerConnect.postMinderId();

        return Const.SUCESSFUL;
    }


    private final boolean validEmail(final String _email) {
        EmailValidator validator = new EmailValidator();

        return validator.validate(_email);
    }
}
