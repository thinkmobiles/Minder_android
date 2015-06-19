package com.example.minder_android.base;

import android.app.ActionBar;
import android.app.Activity;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.example.minder_android.R;
import com.example.minder_android.core.CustomDialog;
import com.example.minder_android.core.OnCustomClickListener;


/**
 * Created by ���� on 05.05.2015.
 */
public abstract class BaseActivity extends AppCompatActivity {
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }

    public final void switchContent(final Fragment _fragment) {
        switchContent(_fragment, true);
    }

    public final void switchContent(final Fragment _fragment, boolean addToBackStack) {
        hideSoftKeyBoard();
        final FragmentTransaction transaction = getFragmentManager().beginTransaction();

        transaction.replace(R.id.fl_content, _fragment, _fragment.getClass().getName().toString());
        if (addToBackStack) {
            transaction.addToBackStack(_fragment.getTag());
        }
        transaction.commit();
    }

    public final void hideSoftKeyBoard() {
        final View focusedView = getCurrentFocus();
        if (focusedView != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(focusedView.getWindowToken(), 0);
        }
    }

    public final void setToolbarVisibility(final boolean _visible) {
        if (toolbar != null) {
            toolbar.setVisibility(_visible ? View.VISIBLE : View.GONE);
        }
    }

    public final void setActionBar(boolean _visible) {
        final ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            if (_visible) actionBar.show();
            else actionBar.hide();
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            if (getFragmentManager().getBackStackEntryCount() > 0) {
                getFragmentManager().popBackStack();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    public final void showDialog(final String _title, final String _message, final String _positiveTitle, final String _negativeTitle, final OnCustomClickListener _listener) {
        CustomDialog dialog = new CustomDialog();
        dialog.setTitle(_title);
        dialog.setMessage(_message);
        dialog.setOnCustomClickListener(_listener);
        dialog.setCancelable(false);
        dialog.setStyle(DialogFragment.STYLE_NORMAL, R.style.AlertDialogTheme);
        if (_positiveTitle != null) dialog.setPositiveButton(_positiveTitle);
        if (_negativeTitle != null) dialog.setNegativeButton(_negativeTitle);

        dialog.show(getFragmentManager(), "");
    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() == 0) {
            finish();
        } else {
            getFragmentManager().popBackStack();
        }

    }
}
