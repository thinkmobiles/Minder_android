package com.example.minder_android;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Юзер on 06.05.2015.
 */
public final class EmailValidator{
    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private Pattern mPattern;
    private Matcher mMatcher;

    public EmailValidator(){
        mPattern = Pattern.compile(EMAIL_PATTERN);
    }

    public final boolean validate(final String _hex){
        mMatcher = mPattern.matcher(_hex);
        return mMatcher.matches();
    }
}
