package com.example.minder_android.base;

import android.app.ActionBar;
import android.app.Activity;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.example.minder_android.R;
import com.example.minder_android.core.CustomDialog;
import com.example.minder_android.core.OnCustomClickListener;


/**
 * Created by ���� on 05.05.2015.
 */
public abstract class BaseActivity extends Activity {

    public final void switchContent(final Fragment _fragment) {
        switchContent(_fragment, true);
    }

    public final void switchContent(final Fragment _fragment, boolean addToBackStack) {
        hideSoftKeyBoard();
        final FragmentTransaction transaction = getFragmentManager().beginTransaction();
        if (addToBackStack) {
            transaction.addToBackStack(_fragment.getTag());
        }
        transaction.replace(R.id.fl_content, _fragment, _fragment.getTag()).commit();
    }

    public final void hideSoftKeyBoard() {
        final View focusedView = getCurrentFocus();
        if (focusedView!= null) {
            InputMethodManager inputMethodManager = (InputMethodManager)  getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(focusedView.getWindowToken(), 0);
        }
    }

    public final void setActionBar(boolean _visible) {
        final ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            if (_visible) actionBar.show();
            else actionBar.hide();
        }
    }

    public final void showDialog(final String _title, final String _message, final OnCustomClickListener _listener, final String _positiveTitle, final String _negativeTitle) {
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

}
