package com.stefandimic.fragmentapplication.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.stefandimic.fragmentapplication.R;

public class TextFragment extends Fragment {

    public TextFragment() {}

    private TextView mTextBox;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_text, container, false);
        mTextBox = (TextView) v.findViewById(R.id.textBox);
        return v;
    }

    public void setText(String text) {
        mTextBox.setText(text);
    }
}
