package com.stefandimic.dbapplication.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.stefandimic.dbapplication.R;

public class InputFragment extends Fragment {

    private static final String TAG = InputFragment.class.getSimpleName();

    public InputFragment() {}

    public interface Callback {
        void onOkPressed(String text);
    }

    private EditText mInput;
    private Callback mListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if(context instanceof Callback) {
            mListener = (Callback) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement Callback.");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_input, container, false);

        mInput = (EditText) v.findViewById(R.id.input);
        Button okBtn = (Button) v.findViewById(R.id.ok);

        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = mInput.getText().toString();

                if(TextUtils.isEmpty(text)) {
                    Toast.makeText(getActivity(), "Please enter some text", Toast.LENGTH_SHORT).show();
                } else if(mListener != null) {
                        mListener.onOkPressed(text);
                }
            }
        });

        return v;
    }
}
