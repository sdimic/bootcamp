package com.stefandimic.dbapplication.database;

import android.provider.BaseColumns;

/**
 * Created by Stefan on 30.7.2016..
 */
public class InputContract {
    public static abstract class Texts implements BaseColumns {
        public static final String TABLE_NAME = "texts";
        public static final String TEXT = "text";
    }
}
