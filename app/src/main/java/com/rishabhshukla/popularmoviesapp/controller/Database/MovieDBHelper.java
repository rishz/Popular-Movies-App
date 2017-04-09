package com.rishabhshukla.popularmoviesapp.controller.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by rishabhshukla on 09/04/17.
 */

public class MovieDBHelper extends SQLiteOpenHelper {

    public static final String DB_NAME=  "Movies.db";
    public static final int DB_VER = 1;

    public MovieDBHelper(Context context) {
        super(context, DB_NAME, null, DB_VER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
