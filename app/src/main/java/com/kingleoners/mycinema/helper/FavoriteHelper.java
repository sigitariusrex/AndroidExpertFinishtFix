package com.kingleoners.mycinema.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.kingleoners.mycinema.item.Movie;
import com.kingleoners.mycinema.item.TV;

import java.util.ArrayList;

import static android.provider.BaseColumns._ID;
import static com.kingleoners.mycinema.database.DatabaseContract.FilmColumns.TANGGAL;
import static com.kingleoners.mycinema.database.DatabaseContract.FilmColumns.DESKRIPSI;
import static com.kingleoners.mycinema.database.DatabaseContract.FilmColumns.BAHASA;
import static com.kingleoners.mycinema.database.DatabaseContract.FilmColumns.FOTO;
import static com.kingleoners.mycinema.database.DatabaseContract.FilmColumns.NILAI;
import static com.kingleoners.mycinema.database.DatabaseContract.FilmColumns.NAMA;
import static com.kingleoners.mycinema.database.DatabaseContract.TABLE_FAVORITE_MOVIE;
import static com.kingleoners.mycinema.database.DatabaseContract.TABLE_FAVORITE_TV;


public class FavoriteHelper {
    private static final String DATABASE_TABLE_MOVIE = TABLE_FAVORITE_MOVIE;
    private static final String DATABASE_TABLE_TV = TABLE_FAVORITE_TV;
    private static DatabaseHelper databaseHelper;
    private static FavoriteHelper INSTANCE;
    private static SQLiteDatabase db;

    public FavoriteHelper(Context context) {
        databaseHelper = new DatabaseHelper(context);
    }

    public static FavoriteHelper getInstance(Context context){
        if (INSTANCE == null){
            synchronized (SQLiteOpenHelper.class){
                if (INSTANCE == null){
                    INSTANCE = new FavoriteHelper(context);
                }
            }
        }
        return INSTANCE;
    }

    public void open() throws SQLException {
        db = databaseHelper.getWritableDatabase();
    }

    public void close(){
        databaseHelper.close();
        if(db.isOpen())
            db.close();
    }

    public ArrayList<Movie> getAllFav(){
        ArrayList<Movie> arrayList = new ArrayList<>();
        Cursor cursor = db.query(DATABASE_TABLE_MOVIE,
                null,
                null,
                null,
                null,
                null,
                _ID + " ASC",
                null);
        cursor.moveToFirst();
        Movie movie;
        if(cursor.getCount() > 0){
            do {
                movie = new Movie();
                movie.setMVid(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                movie.setMVnama(cursor.getString(cursor.getColumnIndexOrThrow(NAMA)));
                movie.setMVtanggal(cursor.getString(cursor.getColumnIndexOrThrow(TANGGAL)));
                movie.setMVnilai(cursor.getString(cursor.getColumnIndexOrThrow(NILAI)));
                movie.setMVdeskripsi(cursor.getString(cursor.getColumnIndexOrThrow(DESKRIPSI)));
                movie.setMVfoto(cursor.getString(cursor.getColumnIndexOrThrow(FOTO)));
                movie.setMVbahasa(cursor.getString(cursor.getColumnIndexOrThrow(BAHASA)));
                arrayList.add(movie);
                cursor.moveToNext();
            }while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }

    public boolean isExist(Movie movie){
        db = databaseHelper.getReadableDatabase();
        String QUERY = "SELECT * FROM " + TABLE_FAVORITE_MOVIE + " WHERE " + _ID + "=" + movie.getMVid();
        Cursor cursor = db.rawQuery(QUERY,null);
        if(cursor.getCount() <= 0){
            cursor.close();
            return false;
        }
        cursor.close();
        return true;
    }

    public long insertFav(Movie movie){
        ContentValues args = new ContentValues();
        args.put(_ID, movie.getMVid());
        args.put(NAMA, movie.getMVnama());
        args.put(TANGGAL, movie.getMVtanggal());
        args.put(NILAI, movie.getMVnilai());
        args.put(DESKRIPSI, movie.getMVdeskripsi());
        args.put(FOTO, movie.getMVfoto());
        args.put(BAHASA, movie.getMVbahasa());
        return db.insert(DATABASE_TABLE_MOVIE, null, args);
    }

    public int deleteFav(int id){
        return db.delete(TABLE_FAVORITE_MOVIE, _ID + " = '" + id + "'", null);
    }

    public Cursor queryByIdProvider(String id){
        return db.query(DATABASE_TABLE_MOVIE,
                null,
                _ID + " = ?",
                new String[]{id},
                null,
                null,
                null,
                null);
    }

    public Cursor queryProvider(){
        return db.query(DATABASE_TABLE_MOVIE,
                null,
                null,
                null,
                null,
                null,
                _ID + " ASC");
    }

    public ArrayList<TV> getAllFavTv(){
        ArrayList<TV> arrayList = new ArrayList<>();
        Cursor cursor = db.query(DATABASE_TABLE_TV,
                null,
                null,
                null,
                null,
                null,
                _ID + " ASC",
                null);
        cursor.moveToFirst();
        TV tv;
        if(cursor.getCount() > 0){
            do {
                tv = new TV();
                tv.setTVid(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                tv.setTVnama(cursor.getString(cursor.getColumnIndexOrThrow(NAMA)));
                tv.setTVtanggal(cursor.getString(cursor.getColumnIndexOrThrow(TANGGAL)));
                tv.setTVnilai(cursor.getString(cursor.getColumnIndexOrThrow(NILAI)));
                tv.setTVdeskripsi(cursor.getString(cursor.getColumnIndexOrThrow(DESKRIPSI)));
                tv.setTVfoto(cursor.getString(cursor.getColumnIndexOrThrow(FOTO)));
                tv.setTVbahasa(cursor.getString(cursor.getColumnIndexOrThrow(BAHASA)));
                arrayList.add(tv);
                cursor.moveToNext();
            }while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }

    public boolean isExistTv(TV tv){
        db = databaseHelper.getReadableDatabase();
        String QUERY = "SELECT * FROM " + TABLE_FAVORITE_TV + " WHERE " + _ID + "=" + tv.getTVid();

        Cursor cursor = db.rawQuery(QUERY,null);
        if(cursor.getCount() <= 0){
            cursor.close();
            return false;
        }
        cursor.close();
        return true;
    }

    public long insertFavTv(TV tv){
        ContentValues args = new ContentValues();
        args.put(_ID, tv.getTVid());
        args.put(NAMA, tv.getTVnama());
        args.put(TANGGAL, tv.getTVtanggal());
        args.put(NILAI, tv.getTVnilai());
        args.put(DESKRIPSI, tv.getTVdeskripsi());
        args.put(FOTO, tv.getTVfoto());
        args.put(BAHASA, tv.getTVbahasa());
        return db.insert(DATABASE_TABLE_TV, null, args);
    }

    public int deleteFavTv(int id){
        return db.delete(TABLE_FAVORITE_TV, _ID + " = '" + id + "'", null);
    }
}
