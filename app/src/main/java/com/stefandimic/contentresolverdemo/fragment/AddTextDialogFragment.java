package com.stefandimic.contentresolverdemo.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.stefandimic.contentresolverdemo.R;

public class AddTextDialogFragment extends DialogFragment implements View.OnClickListener {
    public static final String TAG = AddTextDialogFragment.class.getName();

    private EditText mText;
    private Callback mCallback;

    public static AddTextDialogFragment newInstance() {
        return new AddTextDialogFragment();
    }

    public interface Callback {
        void insertText(String text);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if(context instanceof Callback) {
            mCallback = (Callback) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement Callback.");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallback = null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.add_text_fragment, container, false);
        mText = (EditText) v.findViewById(R.id.editText_title);
        Button ok = (Button) v.findViewById(R.id.button_ok);
        Button cancel = (Button) v.findViewById(R.id.button_cancel);
        ok.setOnClickListener(this);
        cancel.setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_cancel:
                dismiss();
                break;
            case R.id.button_ok:
                if (TextUtils.isEmpty(mText.getText())) {
                    Toast.makeText(getActivity(),
                            "Please enter some text", Toast.LENGTH_SHORT).show();
                } else if (mCallback != null) {
                    mCallback.insertText(mText.getText().toString());
                    dismiss();
                }
                break;
        }
    }
}
