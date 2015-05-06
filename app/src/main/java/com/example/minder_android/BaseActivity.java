package com.example.minder_android;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.view.View;
import android.view.inputmethod.InputMethodManager;


/**
 * Created by ёзер on 05.05.2015.
 */
public abstract class BaseActivity extends Activity {

    public void switchContent(Fragment _fragment) {
        switchContent(_fragment, true);
    }

    public void switchContent(Fragment _fragment, boolean addToBackStack) {
        hideSoftKeyBoard();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        if (addToBackStack) {
            transaction.addToBackStack(_fragment.getTag());
        }
        transaction.replace(R.id.fl_content, _fragment, _fragment.getTag()).commit();
    }

    public void hideSoftKeyBoard() {
        View focusedView = getCurrentFocus();
        if (focusedView!= null) {
            InputMethodManager inputMethodManager = (InputMethodManager)  getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(focusedView.getWindowToken(), 0);
        }
    }

    public void setActionBar(boolean visible) {
        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            if (visible) actionBar.show();
            else actionBar.hide();
        }
    }

    public final void showAlertDialog(final String _title, final String _text, final OnAlertDialogListener _listener, final String _positiveTitle, final String _negativeTitle) {
        AlertDialog.Builder ab = new AlertDialog.Builder(this, R.style.AlertDialogTheme);
        ab.setTitle(_title);
        ab.setMessage(_text);
        ab.setCancelable(false);
        if (_positiveTitle != null)
        ab.setPositiveButton(_positiveTitle, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                _listener.onPositive();
            }
        });
        if (_negativeTitle != null)
            ab.setNegativeButton(_negativeTitle, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    _listener.onNegative();
                }
            });
        Dialog dialog = ab.create();
        dialog.show();
    }


}
