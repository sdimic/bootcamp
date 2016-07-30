package com.stefandimic.dbapplication.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.stefandimic.dbapplication.R;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class TextFragment extends Fragment {

    public TextFragment() {}

    private TextView mListBox;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_text, container, false);

        mListBox = (TextView) v.findViewById(R.id.listBox);

        return v;
    }

    public void setText(String data) {
        mListBox.setText(data);
    }
}
