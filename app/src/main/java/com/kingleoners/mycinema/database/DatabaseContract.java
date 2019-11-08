package com.kingleoners.mycinema.database;

import android.net.Uri;
import android.provider.BaseColumns;

public class DatabaseContract {
    public static String TABLE_FAVORITE_MOVIE = "favorite_movie";
    public static String TABLE_FAVORITE_TV = "favorite_tv";
    public static final String AUTHORITY = "com.kingleoners.mycinema";
    private static final String SCHEME = "content";
    public static final class FilmColumns implements BaseColumns{
        public static String NAMA = "nama";
        public static String TANGGAL = "tanggal";
        public static String NILAI = "nilai";
        public static String DESKRIPSI = "deskripsi";
        public static String FOTO = "foto";
        public static String BAHASA = "bahasa";

        public static final Uri CONTENT_URI_MOVIE= new Uri.Builder()
                .scheme(SCHEME)
                .authority(AUTHORITY)
                .appendPath(TABLE_FAVORITE_MOVIE)
                .build();
    }
}
