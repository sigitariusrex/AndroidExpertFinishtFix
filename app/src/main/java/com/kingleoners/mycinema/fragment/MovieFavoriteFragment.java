package com.kingleoners.mycinema.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.kingleoners.mycinema.R;
import com.kingleoners.mycinema.adapter.MoviesAdapter;
import com.kingleoners.mycinema.helper.FavoriteHelper;
import com.kingleoners.mycinema.item.Movie;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


/**
 * A simple {@link Fragment} subclass.
 */
public class MovieFavoriteFragment extends Fragment {
    public static final String EXTRA_MOVIE = "movie";
    public ArrayList<Movie> listMovies = new ArrayList<>();
    public RecyclerView rvMovie;
    public ProgressBar progressBar;
    private MoviesAdapter listMovieAdapter;
    private FavoriteHelper favoriteHelper;
    private Bundle saveState;

    public MovieFavoriteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_favorite, container, false);
    }

    @Override
    public void onStart() {
        rvMovie.setLayoutManager(new LinearLayoutManager(getContext()));
        rvMovie.setHasFixedSize(true);
        progressBar.setVisibility(View.VISIBLE);
        favoriteHelper = FavoriteHelper.getInstance(getContext());
        favoriteHelper.open();

        listMovieAdapter = new MoviesAdapter(getContext());
        rvMovie.setAdapter(listMovieAdapter);

        if (saveState == null) {
            listMovies.clear();
            listMovies.addAll(favoriteHelper.getAllFav());
            if (listMovies != null) {
                listMovieAdapter.setMovie(listMovies);
            } else {
                Toast.makeText(getContext(), getResources().getString(R.string.empty_data), Toast.LENGTH_SHORT).show();
            }
        } else {
            ArrayList<Movie> list = saveState.getParcelableArrayList(EXTRA_MOVIE);
            if (list != null) {
                listMovieAdapter.setMovie(list);
            }
        }
        super.onStart();
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvMovie = view.findViewById(R.id.rv_movie);
        progressBar = view.findViewById(R.id.progressBar);
        if (savedInstanceState != null) {
            saveState = savedInstanceState;
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(EXTRA_MOVIE, listMovieAdapter.getFilms());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        favoriteHelper.close();
    }
}