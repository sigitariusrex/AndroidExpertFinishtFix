package com.kingleoners.mycinema.widget;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.os.Binder;
import android.os.Bundle;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.bumptech.glide.Glide;
import com.kingleoners.mycinema.R;
import com.kingleoners.mycinema.database.DatabaseContract;
import com.kingleoners.mycinema.helper.MappingHelper;
import com.kingleoners.mycinema.item.Movie;

import java.util.ArrayList;
import java.util.List;

class StackRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {

    private final List<Bitmap> mWidgetItems = new ArrayList<>();
    private ArrayList<Movie> movies;
    private final Context context;
    Cursor cursor;

    StackRemoteViewsFactory(Context context) {
        this.context = context;
    }


    @Override
    public void onCreate() {
    }

    @Override
    public void onDataSetChanged() {
        if(cursor != null){
            cursor.close();
        }
        final long identityToken = Binder.clearCallingIdentity();
        cursor = context.getContentResolver().query(DatabaseContract.FilmColumns.CONTENT_URI_MOVIE,
                null,
                null,
                null,
                null);
        movies = MappingHelper.mapCursorToArrayList(cursor);
        Binder.restoreCallingIdentity(identityToken);
        for (Movie iMovie: movies) {
            try {

                Bitmap bitmap = Glide.with(context)
                        .asBitmap()
                        .load(iMovie.getMVfoto())
                        .submit(512, 512)
                        .get();
                mWidgetItems.add(bitmap);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onDestroy() {
        if (cursor!=null){
            cursor.close();
        }
    }

    @Override
    public int getCount() {
        return cursor.getCount();
    }

    @Override
    public RemoteViews getViewAt(int i) {
        RemoteViews rv = new RemoteViews(context.getPackageName(), R.layout.widget_item);
        rv.setImageViewBitmap(R.id.imageView, mWidgetItems.get(i));
        Bundle extras = new Bundle();
        extras.putInt(FavMovieWidget.EXTRA_ITEM, i);
        Intent fillInIntent = new Intent();
        fillInIntent.putExtras(extras);
        rv.setOnClickFillInIntent(R.id.imageView, fillInIntent);
        return rv;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }
}
