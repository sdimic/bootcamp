package com.stefandimic.contentresolverdemo;

import android.app.ListActivity;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends ListActivity {
    private static final Uri CONTENT_URI =
            Uri.parse("content://com.stefandimic.provider.texts/texts");

    private Cursor mCursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mCursor = getContentResolver().query(CONTENT_URI, null, null, null, null);
        startManagingCursor(mCursor);

        ListAdapter adapter = new SimpleCursorAdapter(
                this,
                android.R.layout.two_line_list_item,
                mCursor,
                new String[] {"_id", "text"},
                new int[] {android.R.id.text1, android.R.id.text2});

        setListAdapter(adapter);
    }
}
