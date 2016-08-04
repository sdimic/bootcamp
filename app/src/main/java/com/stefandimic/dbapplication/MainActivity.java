package com.stefandimic.dbapplication;

import android.app.LoaderManager;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.stefandimic.dbapplication.database.InputContract;
import com.stefandimic.dbapplication.fragments.InputFragment;
import com.stefandimic.dbapplication.fragments.TextFragment;

public class MainActivity extends AppCompatActivity
        implements InputFragment.Callback, LoaderManager.LoaderCallbacks<Cursor>{

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getLoaderManager().initLoader(0, null, this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getLoaderManager().restartLoader(0, null, this);
    }

    @Override
    public void onOkPressed(String text) {
        ContentValues cv = new ContentValues();
        cv.put(InputContract.Texts.TEXT, text);
        getContentResolver().insert(InputContract.Texts.CONTENT_URI, cv);
        getLoaderManager().restartLoader(0, null, this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return new CursorLoader(this, InputContract.Texts.CONTENT_URI, null, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        parse(cursor);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    private void parse(Cursor cursor) {
        if(cursor == null || cursor.getCount() < 1) {
            return;
        }

        StringBuilder builder = new StringBuilder();

        while(cursor.moveToNext()) {
            String text = cursor.getString(cursor.getColumnIndex(InputContract.Texts.TEXT));
            builder.append(text);
            builder.append("\n\n");
        }

        cursor.close();
        updateListBox(builder.toString());
    }

    private void updateListBox(String texts) {
        TextFragment fragment =
                (TextFragment) getSupportFragmentManager().findFragmentById(R.id.textFragment);
        fragment.setText(texts);
    }
}
