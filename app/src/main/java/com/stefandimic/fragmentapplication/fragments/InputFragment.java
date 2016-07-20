package com.stefandimic.fragmentapplication.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.stefandimic.fragmentapplication.R;

public class InputFragment extends Fragment {
    private Callback mListener;
    private TextView mInput;

    public interface Callback {
        void onSubmitPressed(String text);
    }

    public InputFragment() {}

    public static InputFragment newInstance() {
        return new InputFragment();
    }

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
        mInput = (TextView) v.findViewById(R.id.input);
        Button submit = (Button) v.findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mListener != null) {
                    String text = mInput.getText().toString();
                    mListener.onSubmitPressed(text);
                }
            }
        });
        return v;
    }
}