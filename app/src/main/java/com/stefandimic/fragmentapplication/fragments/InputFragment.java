package com.stefandimic.fragmentapplication.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.stefandimic.fragmentapplication.R;

public class InputFragment extends Fragment {

    public InputFragment() {}

    public static InputFragment newInstance() {
        return new InputFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_input, container, false);
        Button submit = (Button) v.findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(getClass().getSimpleName(), "onClick: submit called");
            }
        });


        return v;
    }


}
