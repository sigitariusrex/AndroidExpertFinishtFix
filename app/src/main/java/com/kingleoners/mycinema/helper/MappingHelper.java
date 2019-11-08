package com.kingleoners.mycinema.helper;

import android.database.Cursor;

import com.kingleoners.mycinema.database.DatabaseContract;
import com.kingleoners.mycinema.item.Movie;

import java.util.ArrayList;

public class MappingHelper {
    public static ArrayList<Movie> mapCursorToArrayList(Cursor cursor){
        ArrayList<Movie> list = new ArrayList<>();
        while(cursor.moveToNext()){
            int ID = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseContract.FilmColumns._ID));
            String title = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.FilmColumns.NAMA));
            String ori_lang = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.FilmColumns.BAHASA));
            String date = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.FilmColumns.TANGGAL));
            String rate = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.FilmColumns.NILAI));
            String desc = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.FilmColumns.DESKRIPSI));
            String photo = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.FilmColumns.FOTO));

            Movie movie = new Movie();
            movie.setMVid(ID);
            movie.setMVnama(title);
            movie.setMVbahasa(ori_lang);
            movie.setMVtanggal(date);
            movie.setMVnilai(rate);
            movie.setMVdeskripsi(desc);
            movie.setMVfoto(photo);
            list.add(movie);
        }
        return list;
    }
}
