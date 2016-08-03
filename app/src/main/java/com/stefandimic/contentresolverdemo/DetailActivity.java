package com.stefandimic.contentresolverdemo;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.ContentUris;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class DetailActivity extends Activity implements LoaderManager.LoaderCallbacks<Cursor> {
    private static final String TAG = DetailActivity.class.getSimpleName();

    private TextView mId;
    private TextView mText;
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        mId = (TextView) findViewById(R.id.textView);
        mText = (TextView) findViewById(R.id.textView2);
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);

        int id = getIntent().getIntExtra(MainActivity.EXTRA_ID, -1);

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
}
