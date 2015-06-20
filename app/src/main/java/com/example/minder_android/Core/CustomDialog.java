package com.example.minder_android.core;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.minder_android.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Юзер on 07.05.2015.
 */
public final class CustomDialog extends DialogFragment implements View.OnClickListener {
    @InjectView(R.id.dialog_title_CAD)      TextView tvMessage;
    @InjectView(R.id.positive_btn_CAD)      Button btnPositive;
    @InjectView(R.id.negative_btn_CAD)      Button btnNegative;

    private View view;
    private OnCustomClickListener mListener = null;
    private String mMessage;
    private String mPositiveTitle;
    private String mNegativeTitle;


    @Nullable
    @Override
    public final View onCreateView(final LayoutInflater _inflater, final ViewGroup _container, final Bundle _savedInstanceState) {
        view = _inflater.inflate(R.layout.custom_alert_dialog, _container, false);
        ButterKnife.inject(this, view);

        tvMessage.setText(mMessage);

        if (mPositiveTitle != null && !mPositiveTitle.isEmpty()) {
            btnPositive.setVisibility(View.VISIBLE);
            btnPositive.setText(mPositiveTitle);
            btnPositive.setOnClickListener(this);
        } else {
            btnPositive.setVisibility(View.GONE);
        }

        if (mNegativeTitle != null && !mNegativeTitle.isEmpty()) {
            btnNegative.setVisibility(View.VISIBLE);
            btnNegative.setText(mNegativeTitle);
            btnNegative.setOnClickListener(this);
        } else {
            btnNegative.setVisibility(View.GONE);
        }
        return view;
    }


    public final void setMessage(final String _message) {
        mMessage = _message;
    }

    public final void setOnCustomClickListener(final OnCustomClickListener _listener) {
        mListener = _listener;
    }

    public final void setPositiveButton(final String _title) {
        mPositiveTitle = _title;
    }

    public final void setNegativeButton(final String _title) {
        mNegativeTitle = _title;
    }


    @Override
    public final void onClick(final View _v) {
        switch (_v.getId()) {
            case R.id.positive_btn_CAD:
                if (mListener != null) mListener.onPositive();
                dismiss();
                break;

            case R.id.negative_btn_CAD:
                if (mListener != null) mListener.onNegative();
                dismiss();
                break;
        }
    }
}
