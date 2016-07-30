package com.stefandimic.dbapplication.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;

public class TextDbHelper extends SQLiteOpenHelper{
    public static final String DATABASE_NAME = "bootcamp.db";
    public static final int DATABASE_VERSION = 1;

    public TextDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        createInputTable(sqLiteDatabase);
    }

    private void createInputTable(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + InputContract.Texts.TABLE_NAME + " ("
                + InputContract.Texts._ID + "INTEGER PRIMARY KEY, "
                + InputContract.Texts.TEXT + " TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {}

    public Cursor getTexts(String[] projection,
                           String selection,
                           String[] selectionArgs,
                           String sortOrder) {
        SQLiteQueryBuilder builder = new SQLiteQueryBuilder();
        builder.setTables(InputContract.Texts.TABLE_NAME);
        return builder.query(getReadableDatabase(), projection, selection, selectionArgs,
                null, null, sortOrder);
    }

    public long addText(ContentValues cv) throws SQLException{
        long id = getWritableDatabase().insert(InputContract.Texts.TABLE_NAME, "", cv);

        if(id <= 0) {
           throw new SQLException("Failed to add text");
        }

        return id;
    }

    public int deleteTexts() {
        return getWritableDatabase().delete(InputContract.Texts.TABLE_NAME, null, null);
    }

    public int updateTexts(ContentValues cv) {
        return getWritableDatabase().update(InputContract.Texts.TABLE_NAME, cv, null, null);
    }
}
