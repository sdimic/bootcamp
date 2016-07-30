package com.stefandimic.dbapplication.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Stefan on 30.7.2016..
 */
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
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
