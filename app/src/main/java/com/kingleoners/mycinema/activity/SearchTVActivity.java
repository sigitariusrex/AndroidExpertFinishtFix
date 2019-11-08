package com.kingleoners.mycinema.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

import com.kingleoners.mycinema.R;
import com.kingleoners.mycinema.adapter.SearchTVAdapter;
import com.kingleoners.mycinema.item.TV;
import com.kingleoners.mycinema.model.TVModel;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class SearchTVActivity extends AppCompatActivity {
    private TVModel model;
    private SearchTVAdapter adapter;
    private ArrayList<TV> arrayList = new ArrayList<>();
    private SearchView search;
    private final String QUERY = "QUERY";
    private String query;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_tv);

        model = ViewModelProviders.of(this).get(TVModel.class);
        model.getTVSearch().observe(this, getTV);

        RecyclerView rvTVShow = findViewById(R.id.rv_search_tv);
        rvTVShow.setHasFixedSize(true);
        rvTVShow.setLayoutManager(new LinearLayoutManager(this));

        adapter = new SearchTVAdapter(this);
        adapter.setFilm(arrayList);
        rvTVShow.setAdapter(adapter);

        this.setTitle(R.string.search_tv);
        if(savedInstanceState != null){
            query = savedInstanceState.getString(QUERY);
        }

    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i("Click TV", search.getQuery().toString());
        outState.putString(QUERY, search.getQuery().toString());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_tv_search, menu);
        MenuItem searchTVShowMenu = menu.findItem(R.id.action_search_tv);
        search = (SearchView) searchTVShowMenu.getActionView();
        search.onActionViewExpanded();
        if(query != null){
            search.setQuery(query,false);
        }
        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                Log.i("Input TV",s);
                model.pencarianTV(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    private Observer<ArrayList<TV>> getTV= new Observer<ArrayList<TV>>() {
        @Override
        public void onChanged(@Nullable ArrayList<TV> mov) {
            if (mov != null){
                arrayList = mov;
                adapter.setTVShow(mov);
            }
        }
    };
}
