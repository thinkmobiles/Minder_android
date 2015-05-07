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
    @InjectView(R.id.message_FD)        TextView tvMessage;
    @InjectView(R.id.positive_btn_FD)   Button btnPositive;
    @InjectView(R.id.negative_btn_FD)   Button btnNegative;

    private View view;
    private OnCustomClickListener mListener = null;
    private String mTitle;
    private String mMessage;
    private String mPositiveTitle;
    private String mNegativeTitle;


    @Nullable
    @Override
    public final View onCreateView(final LayoutInflater _inflater, final ViewGroup _container, final Bundle _savedInstanceState) {
        view = _inflater.inflate(R.layout.fragment_dialog, _container, false);
        ButterKnife.inject(this, view);
        getDialog().setTitle(mTitle);

        tvMessage.setText(mMessage);

        if (!(mPositiveTitle.isEmpty() || mPositiveTitle == null)) {
            btnPositive.setVisibility(View.VISIBLE);
            btnPositive.setText(mPositiveTitle);
            btnPositive.setOnClickListener(this);
        }

        if (!(mNegativeTitle.isEmpty() || mNegativeTitle == null)) {
            btnNegative.setVisibility(View.VISIBLE);
            btnNegative.setText(mNegativeTitle);
            btnNegative.setOnClickListener(this);
        }
        return view;
    }


    public final void setTitle(final String _title) {
        mTitle = _title;
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
            case R.id.positive_btn_FD:
                if (mListener != null) mListener.onPositive();
                dismiss();
                break;

            case R.id.negative_btn_FD:
                if (mListener != null) mListener.onNegative();
                dismiss();
                break;
        }
    }
}
