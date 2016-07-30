package com.stefandimic.dbapplication.database;

import android.net.Uri;
import android.provider.BaseColumns;

import com.stefandimic.dbapplication.provider.TextsProvider;

public class InputContract {
    public static abstract class Texts implements BaseColumns {
        public static final Uri CONTENT_URI = Uri.parse("content://"
                + TextsProvider.AUTHORITY + "/"
                + InputContract.Texts.TABLE_NAME);
        public static final String TABLE_NAME = "texts";
        public static final String TEXT = "text";
    }
}
