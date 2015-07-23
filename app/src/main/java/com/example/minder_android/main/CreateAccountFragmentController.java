package com.example.minder_android.main;

import android.widget.Toast;

import com.example.minder_android.R;
import com.example.minder_android.core.AppSettings;
import com.example.minder_android.core.Const;
import com.example.minder_android.core.utils.EmailValidator;
import com.example.minder_android.core.utils.PBarController;
import com.example.minder_android.rest.RequestJsonFactory;
import com.example.minder_android.rest.RequestManager;
import com.google.gson.JsonObject;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by ���� on 06.05.2015.
 */
public final class CreateAccountFragmentController {
    private CreateAccountFragment mFragment;



    public CreateAccountFragmentController(CreateAccountFragment _fragment) {
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
        return mFragment.getString(R.string.successful);
    }


    private final boolean validEmail(final String _email) {
        EmailValidator validator = new EmailValidator();
        return validator.validate(_email);
    }


    public void signUpUser(final String _firstName, final String _lastName, final String _email, final String _password) {
        PBarController.showProgressDialog(mFragment.getActivity());
        RequestManager.signUpUser(RequestJsonFactory.createSignUpRequestJson(_firstName, _lastName, _email, _password), new Callback<JsonObject>() {
            @Override
            public void success(JsonObject _jsonObject, Response _response) {
                PBarController.hideProgressDialog();
                onUserCreateSuccess(_jsonObject);
            }

            @Override
            public void failure(RetrofitError _error) {
                PBarController.hideProgressDialog();
                Toast.makeText(mFragment.getActivity(), _error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }


    private void onUserCreateSuccess(JsonObject _jsonObject) {
        if (_jsonObject.has(Const.KEY_SUCCESS)) {
            AppSettings.setMinderId(_jsonObject.get(Const.KEY_MINDER_ID).getAsString());
        }
        mFragment.onUserCreateSuccess();
    }

}
