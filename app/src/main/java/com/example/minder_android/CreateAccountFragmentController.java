package com.example.minder_android;

import android.view.View;

/**
 * Created by ёзер on 06.05.2015.
 */
public final class CreateAccountFragmentController {
    private BaseFragment mFragment;


    public CreateAccountFragmentController(BaseFragment _fragment) {
        mFragment = _fragment;
    }

    public final String checkInput(final String _firstName, final String _lastName, final String _email, final String _password, final String _confirmPassword) {
        if (_firstName.isEmpty()) return "First name is empty";
        if (_lastName.isEmpty()) return "Last name is empty";
        if (_email.isEmpty()) return "Email name is empty";
        if (!validEmail(_email)) return "Email adress is incorrect";
        if (_password.isEmpty()) return "Password is empty";
        if (_password.length() < Const.MIN_PASSWORD_LENGTH) return "Password length is less then " + Const.MIN_PASSWORD_LENGTH;
        if (!_password.equals(_confirmPassword)) return "Confirmed password doesn`t match to password";
        return "successful";
    }


    private final boolean validEmail(final String _email) {
        return _email.matches(Const.EMAIL_PATTERN);
    }
}
