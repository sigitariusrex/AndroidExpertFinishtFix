package com.kingleoners.mycinema;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;

import com.kingleoners.mycinema.activity.NotificationActivity;
import com.kingleoners.mycinema.activity.SearchMovieActivity;
import com.kingleoners.mycinema.activity.SearchTVActivity;
import com.kingleoners.mycinema.fragment.FavoriteFragment;
import com.kingleoners.mycinema.fragment.MovieFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.kingleoners.mycinema.fragment.TVFragment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class MainActivity extends AppCompatActivity {
    private final String EXTRA_TITLE = "EXTRA_TITLE";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView mView = findViewById(R.id.navigation);
        mView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        if (savedInstanceState == null) {
            setActionBarTitle(title);
            mView.setSelectedItemId(R.id.navigation_movie);
        } else {
            title = savedInstanceState.getString(EXTRA_TITLE);
            setActionBarTitle(title);
        }
//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

    }
    public String title = "FilmKu";
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            Fragment mFrag;
            switch (menuItem.getItemId()) {
                case R.id.navigation_movie:
                    mFrag = new MovieFragment();
                    title = getString(R.string.movie);
                    setActionBarTitle(title);
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.container_layout, mFrag, mFrag.getClass().getSimpleName())
                            .commit();
                    return true;

                case R.id.navigation_tv:
                    mFrag = new TVFragment();
                    title = getString(R.string.tv);
                    setActionBarTitle(title);
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.container_layout, mFrag, mFrag.getClass().getSimpleName())
                            .commit();
                    return true;
                case R.id.navigation_favorite:
                    mFrag = new FavoriteFragment();
                    title = getString(R.string.favorite);
                    setActionBarTitle(title);
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.container_layout, mFrag, mFrag.getClass().getSimpleName())
                            .commit();
                    return true;
            }
            return false;
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.action_change_settings:
                Intent mIntent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
                startActivity(mIntent);
                break;
            case R.id.action_search_movie:
                Intent searchMvIntent = new Intent(this, SearchMovieActivity.class);
                startActivity(searchMvIntent);
                break;
            case R.id.action_search_tvshow:
                Intent searchTvIntent = new Intent(this, SearchTVActivity.class);
                startActivity(searchTvIntent);
                break;
            case R.id.action_reminder:
                Intent notifIntent = new Intent(this, NotificationActivity.class);
                startActivity(notifIntent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(EXTRA_TITLE, title);
    }

    private void setActionBarTitle(String title) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
        }
    }
}
