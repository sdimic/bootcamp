package com.stefandimic.fragmentapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.stefandimic.fragmentapplication.fragments.InputFragment;
import com.stefandimic.fragmentapplication.fragments.TextFragment;

public class MainActivity extends AppCompatActivity implements InputFragment.Callback{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onSubmitPressed(String text) {
        TextFragment fragment =
                (TextFragment) getSupportFragmentManager().findFragmentById(R.id.textFragment);
        fragment.setText(text);
    }
}
