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
    public static final int ALL_TEXTS = 1;
    public static final int SINGLE_TEXT = 2;
    public static final String AUTHORITY = "com.stefandimic.provider";
    public static final UriMatcher sUriMatcher = getUriMatcher();

    private TextDbHelper mDbHelper;

    @Override
    public boolean onCreate() {
        mDbHelper = new TextDbHelper(getContext());
        return true;
    }

    private static UriMatcher getUriMatcher() {
        UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        matcher.addURI(AUTHORITY, InputContract.Texts.TABLE_NAME, ALL_TEXTS);
        matcher.addURI(AUTHORITY, InputContract.Texts.TABLE_NAME + "/#", SINGLE_TEXT);
        return matcher;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        switch (sUriMatcher.match(uri)) {
            case ALL_TEXTS:
                return "vnd.android.cursor.dir/vnd." + AUTHORITY + "."
                        + InputContract.Texts.TABLE_NAME;
            case SINGLE_TEXT:
                return "vnd.android.cursor.item/vnd." + AUTHORITY + "."
                        + InputContract.Texts.TABLE_NAME;
            default:
                throw new IllegalArgumentException("Unsupported Uri: " + uri);
        }
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection,
                        String selection, String[] selectionArgs, String sortOrder) {
        String id = null;
        switch (sUriMatcher.match(uri)) {
            case ALL_TEXTS:
                //do nothing
                break;
            case SINGLE_TEXT:
                id = uri.getLastPathSegment();
                break;
        }

        return mDbHelper.getTexts(id, projection, selection, selectionArgs, sortOrder);
    }


    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        long id = mDbHelper.addText(contentValues);
        return ContentUris.withAppendedId(InputContract.Texts.CONTENT_URI, id);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        String id = null;
        switch (sUriMatcher.match(uri)) {
            case ALL_TEXTS:
                //do nothing
                break;
            case SINGLE_TEXT:
                id = uri.getLastPathSegment();
                break;

        }
        return mDbHelper.deleteTexts(id);
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String s, String[] strings) {
        String id = null;
        switch (sUriMatcher.match(uri)) {
            case ALL_TEXTS:
                //do nothing
                break;
            case SINGLE_TEXT:
                id = uri.getLastPathSegment();
                break;
        }
        return mDbHelper.updateTexts(id, contentValues);
    }
}