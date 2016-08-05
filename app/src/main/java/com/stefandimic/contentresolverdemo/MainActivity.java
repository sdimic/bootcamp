package com.stefandimic.contentresolverdemo;

import android.app.LoaderManager;
import android.content.AsyncQueryHandler;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.stefandimic.contentresolverdemo.fragment.AddTextDialogFragment;

public class MainActivity extends AppCompatActivity
        implements LoaderManager.LoaderCallbacks<Cursor>,
        View.OnClickListener,
        AddTextDialogFragment.Callback {
    private static final String TAG = MainActivity.class.getSimpleName();

    public static final String EXTRA_ID = "id";

    private MyAdapter mAdapter;
    private ListView mList;
    private FloatingActionButton mFab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAdapter = new MyAdapter(this, null);
        mFab = (FloatingActionButton) findViewById(R.id.fab);
        mFab.setOnClickListener(this);
        mList = (ListView) findViewById(R.id.list);
        mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                int id = (Integer) view.getTag();
                Log.d(TAG, "onItemClick: item tag = " + id);
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                intent.putExtra(EXTRA_ID, id);
                startActivity(intent);
            }
        });

        mList.setAdapter(mAdapter);
        getLoaderManager().initLoader(ContentUtil.URL_LOADER_ALL, null, this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getLoaderManager().restartLoader(ContentUtil.URL_LOADER_ALL, null, this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return new CursorLoader(this, ContentUtil.CONTENT_URI_ALL, null, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        mAdapter.swapCursor(cursor);
    }

    @Override
    public void onLoaderReset(Loader loader) {
        mAdapter.swapCursor(null);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fab:
                displayNewTextFragment();
                break;
        }
    }

    private void displayNewTextFragment() {
        AddTextDialogFragment.newInstance().show(getSupportFragmentManager(),
                AddTextDialogFragment.TAG);
    }

    @Override
    public void insertText(String text) {
        Log.d(TAG, "insertText: " + text);
        ContentValues cv = new ContentValues();
        cv.put("text", text);
        new MyQueryHandler(getContentResolver()).startInsert(0, null,
                ContentUtil.CONTENT_URI_SPECIFIC, cv);
    }

    private class MyQueryHandler extends AsyncQueryHandler {
        public MyQueryHandler(ContentResolver cr) {
            super(cr);
        }

        @Override
        protected void onInsertComplete(int token, Object cookie, Uri uri) {
            Log.d(TAG, "onInsertComplete: uri = " + uri);
            MainActivity.this.getLoaderManager().restartLoader(ContentUtil.URL_LOADER_ALL,
                    null, MainActivity.this);
        }
    }
}