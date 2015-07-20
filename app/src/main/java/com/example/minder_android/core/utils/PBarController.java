package com.example.minder_android.core.utils;

/**
 * Created by Max on 20.07.15.
 */
import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ProgressBar;

import com.example.minder_android.R;


public final class PBarController {

    private static Dialog mProgressDialog;
    private static Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case HIDE_PROGRESS_BAR:
                    hide();
                    break;
                case SHOW_PROGRESS_BAR:
                    show();
                    break;
            }
        }
    };

    private final static int SHOW_PROGRESS_BAR = 0;
    private final static int HIDE_PROGRESS_BAR = 1;

    public static void showProgressDialog(final Context _ctx) {
        if (mProgressDialog == null)
            createDialog(_ctx);
        if (!mProgressDialog.isShowing())
            show();
    }

    private static void show() {
        if(Thread.currentThread() == Looper.getMainLooper().getThread())
            mProgressDialog.show();
        else
            mHandler.sendEmptyMessage(HIDE_PROGRESS_BAR);
    }

    private static void createDialog(Context _ctx) {
        final View pBarView = LayoutInflater.from(_ctx).inflate(R.layout.dialog_progress_bar, null, false);
        ((ProgressBar) pBarView.findViewById(R.id.pbProgressDialog_DPB))
                .getIndeterminateDrawable();

        mProgressDialog = new Dialog(_ctx);
        mProgressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        mProgressDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mProgressDialog.setContentView(pBarView);
        mProgressDialog.setCancelable(false);
    }

    public static void hideProgressDialog() {
        if(Thread.currentThread() == Looper.getMainLooper().getThread())
            hide();
        else
            mHandler.sendEmptyMessage(HIDE_PROGRESS_BAR);
    }

    private static void hide() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
            mProgressDialog = null;
        }
    }
}
