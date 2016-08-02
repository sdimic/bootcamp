package com.stefandimic.contentresolverdemo;

import android.content.Context;
import android.database.Cursor;
import android.provider.BaseColumns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

public class MyAdapter extends CursorAdapter {

    public MyAdapter(Context context, Cursor cursor) {
        super(context, cursor, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View v = LayoutInflater.from(context)
                .inflate(android.R.layout.two_line_list_item, parent, false);
        v.setTag(cursor.getInt(cursor.getColumnIndex(BaseColumns._ID)));
        return v;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView id = (TextView) view.findViewById(android.R.id.text1);
        TextView text = (TextView) view.findViewById(android.R.id.text2);
        id.setText(cursor.getString(cursor.getColumnIndex("_id")));
        text.setText(cursor.getString(cursor.getColumnIndex("text")));
    }
}
