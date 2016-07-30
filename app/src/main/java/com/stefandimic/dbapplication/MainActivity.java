package com.stefandimic.dbapplication;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.stefandimic.dbapplication.database.InputContract;
import com.stefandimic.dbapplication.database.TextDbHelper;
import com.stefandimic.dbapplication.fragments.InputFragment;
import com.stefandimic.dbapplication.fragments.TextFragment;

public class MainActivity extends AppCompatActivity implements InputFragment.Callback{

    private static final String TAG = MainActivity.class.getSimpleName();

    private SQLiteDatabase mDatabase;
    private TextDbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDbHelper = new TextDbHelper(this);
        mDatabase = mDbHelper.getWritableDatabase();
        new QueryTextsTask().execute();
    }

    @Override
    public void onOkPressed(String text) {
        ContentValues cv = new ContentValues();
        cv.put(InputContract.Texts.TEXT, text);
        mDatabase.insert(InputContract.Texts.TABLE_NAME, null, cv);
        new QueryTextsTask().execute();
    }

    private class QueryTextsTask extends AsyncTask<Void, Void, Cursor> {

        public QueryTextsTask() {}

        @Override
        protected Cursor doInBackground(Void... voids) {
            return mDatabase.query(InputContract.Texts.TABLE_NAME,
                    null, null, null, null, null, null);
        }

        @Override
        protected void onPostExecute(Cursor cursor) {
            if(cursor.getCount() < 1) {
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
    }

    private void updateListBox(String texts) {
        TextFragment fragment =
                (TextFragment) getSupportFragmentManager().findFragmentById(R.id.textFragment);
        fragment.setText(texts);
    }
}
