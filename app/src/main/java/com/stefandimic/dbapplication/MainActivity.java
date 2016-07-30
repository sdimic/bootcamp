package com.stefandimic.dbapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.stefandimic.dbapplication.fragments.InputFragment;

public class MainActivity extends AppCompatActivity implements InputFragment.Callback{

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onOkPressed(String text) {
        Log.d(TAG, "onOkPressed: " + text);
    }
}
