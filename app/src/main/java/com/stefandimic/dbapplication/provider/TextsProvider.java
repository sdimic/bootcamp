package com.stefandimic.dbapplication.provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.Nullable;

import com.stefandimic.dbapplication.database.InputContract;
import com.stefandimic.dbapplication.database.TextDbHelper;

public class TextsProvider extends ContentProvider{
    public static final int TEXTS = 1;
    public static final String AUTHORITY = "com.stefandimic.provider.texts";
    public static final UriMatcher sUriMatcher = getUriMatcher();

    private TextDbHelper mDbHelper;

    @Override
    public boolean onCreate() {
        mDbHelper = new TextDbHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection,
                        String selection, String[] selectionArgs, String sortOrder) {
        if(sUriMatcher.match(uri) == TEXTS) {
            return mDbHelper.getTexts(projection, selection, selectionArgs, sortOrder);
        }

        return null;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        long id = mDbHelper.addText(contentValues);
        return ContentUris.withAppendedId(InputContract.Texts.CONTENT_URI, id);
    }

    @Override
    public int delete(Uri uri, String s, String[] strings) {
        return mDbHelper.deleteTexts();
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String s, String[] strings) {
        return mDbHelper.updateTexts(contentValues);
    }

    private static UriMatcher getUriMatcher() {
        UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        matcher.addURI(AUTHORITY, InputContract.Texts.TABLE_NAME, TEXTS);
        return matcher;
    }
}
