package com.kingleoners.mycinema.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

import com.kingleoners.mycinema.R;
import com.kingleoners.mycinema.adapter.SearchMovieAdapter;
import com.kingleoners.mycinema.item.Movie;
import com.kingleoners.mycinema.model.MovieModel;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class SearchMovieActivity extends AppCompatActivity {
    private MovieModel model;
    private SearchMovieAdapter adapter;
    private ArrayList<Movie> arrayList = new ArrayList<>();
    private SearchView search;
    private final String QUERY = "QUERY";
    private String query;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_movie);

        model = ViewModelProviders.of(this).get(MovieModel.class);
        model.getMovieSearch().observe(this, getFilm);

        RecyclerView rvMovie = findViewById(R.id.rv_search_movie);
        rvMovie.setHasFixedSize(true);
        rvMovie.setLayoutManager(new LinearLayoutManager(this));

        adapter = new SearchMovieAdapter(this);
        adapter.setFilm(arrayList);
        rvMovie.setAdapter(adapter);

        this.setTitle(R.string.search_movie);
        if(savedInstanceState != null){
            query = savedInstanceState.getString(QUERY);
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i("Click Movie",search.getQuery().toString());
        outState.putString(QUERY, search.getQuery().toString());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_movie_search, menu);
        MenuItem searchMovieMenu = menu.findItem(R.id.action_search_movie);
        search = (SearchView) searchMovieMenu.getActionView();
        search.onActionViewExpanded();
        if(query != null){
            search.setQuery(query,false);
        }
        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                Log.i("Input Movie",s);
                model.pencarianMovie(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    private Observer<ArrayList<Movie>> getFilm = new Observer<ArrayList<Movie>>() {
        @Override
        public void onChanged(@Nullable ArrayList<Movie> mov) {
            if (mov != null){
                arrayList = mov;
                adapter.setMovie(mov);
            }
        }
    };
}
