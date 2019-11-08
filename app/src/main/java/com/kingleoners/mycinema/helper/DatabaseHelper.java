package com.kingleoners.mycinema.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.kingleoners.mycinema.database.DatabaseContract;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String CREATE_TABLE_MOVIE = String.format("CREATE TABLE %s" +
                    " (%s INTEGER PRIMARY KEY," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s REAL NOT NULL)",
            DatabaseContract.TABLE_FAVORITE_MOVIE,
            DatabaseContract.FilmColumns._ID,
            DatabaseContract.FilmColumns.NAMA,
            DatabaseContract.FilmColumns.TANGGAL,
            DatabaseContract.FilmColumns.NILAI,
            DatabaseContract.FilmColumns.DESKRIPSI,
            DatabaseContract.FilmColumns.FOTO,
            DatabaseContract.FilmColumns.BAHASA);

    private static final String CREATE_TABLE_TV = String.format("CREATE TABLE %s" +
                    " (%s INTEGER PRIMARY KEY," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s REAL NOT NULL)",
            DatabaseContract.TABLE_FAVORITE_TV,
            DatabaseContract.FilmColumns._ID,
            DatabaseContract.FilmColumns.NAMA,
            DatabaseContract.FilmColumns.TANGGAL,
            DatabaseContract.FilmColumns.NILAI,
            DatabaseContract.FilmColumns.DESKRIPSI,
            DatabaseContract.FilmColumns.FOTO,
            DatabaseContract.FilmColumns.BAHASA);

    private static String DATABASE_NAME = "favorite";

    DatabaseHelper(Context context){
        super(context, DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE_MOVIE);
        sqLiteDatabase.execSQL(CREATE_TABLE_TV);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DatabaseContract.TABLE_FAVORITE_MOVIE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DatabaseContract.TABLE_FAVORITE_TV);
        onCreate(sqLiteDatabase);
    }
}
