package com.stefandimic.contentresolverdemo;

import android.app.LoaderManager;
import android.content.AsyncQueryHandler;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.stefandimic.contentresolverdemo.fragment.AddTextDialogFragment;

public class DetailActivity extends AppCompatActivity
        implements LoaderManager.LoaderCallbacks<Cursor>, AddTextDialogFragment.Callback {
    private static final String TAG = DetailActivity.class.getSimpleName();

    private TextView mId;
    private TextView mText;
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mId = (TextView) findViewById(R.id.textView);
        mText = (TextView) findViewById(R.id.textView2);
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);

        long id = getIntent().getLongExtra(MainActivity.EXTRA_ID, -1);

        Log.d(TAG, "onCreate: id = " + id);

        Uri uri = ContentUris.withAppendedId(ContentUtil.CONTENT_URI_SPECIFIC, id);
        Log.d(TAG, "onCreate: uri = " + uri.toString());
        Bundle bundle = new Bundle();
        bundle.putParcelable("uri", uri);

        getLoaderManager().initLoader(ContentUtil.URL_LOADER_SPECIFIC, bundle, this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        Uri uri = bundle.getParcelable("uri");
        return new CursorLoader(this, uri, null, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        cursor.moveToFirst();
        mProgressBar.setVisibility(View.GONE);
        mId.setText(cursor.getString(cursor.getColumnIndex("_id")));
        mText.setText(cursor.getString(cursor.getColumnIndex("text")));
        mId.setVisibility(View.VISIBLE);
        mText.setVisibility(View.VISIBLE);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_items, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                super.onBackPressed();
                return true;
            case R.id.delete:
                Log.d(TAG, "onOptionsItemSelected: id to delete = " + mId.getText());
                Uri uri = prepareUri();
                Log.d(TAG, "onOptionsItemSelected: uri = " + uri);
                AsyncQueryHandler handler = new AsyncQueryHandler(getContentResolver()) {};
                handler.startDelete(0, null, uri, null, null);
                finish();
                return true;
            case R.id.edit:
                displayNewTextFragment();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private Uri prepareUri() {
        return ContentUris.withAppendedId(ContentUtil.CONTENT_URI_SPECIFIC,
                Long.valueOf(mId.getText().toString()));
    }

    private void displayNewTextFragment() {
        AddTextDialogFragment.newInstance(mText.getText().toString())
                .show(getSupportFragmentManager(), AddTextDialogFragment.TAG);
    }

    @Override
    public void insertText(String text) {
        Log.d(TAG, "insertText: " + text);
        AsyncQueryHandler handler = new AsyncQueryHandler(getContentResolver()) {
            @Override
            protected void onUpdateComplete(int token, Object cookie, int result) {
                Toast.makeText(DetailActivity.this, R.string.saved, Toast.LENGTH_SHORT).show();
            }
        };
        Uri uri = prepareUri();
        ContentValues cv = new ContentValues();
        cv.put("text", text);
        handler.startUpdate(0, null, uri, cv, null, null);
        mText.setText(text);
    }
}
