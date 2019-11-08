package com.kingleoners.mycinema.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.kingleoners.mycinema.R;
import com.kingleoners.mycinema.adapter.MoviesAdapter;
import com.kingleoners.mycinema.item.Movie;
import com.kingleoners.mycinema.model.MovieModel;

import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


/**
 * A simple {@link Fragment} subclass.
 */
public class MovieFragment extends Fragment {
    public static final String MOVIE = "Movie";
    private ProgressBar progressBar;
    MoviesAdapter adapter;
    RecyclerView rvMovie;
    MovieModel model;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public MovieFragment() {
        // Required empty public constructor
    }

    private void showLoading(Boolean state) {
        if (state) {
            progressBar.setVisibility(View.GONE);
        } else {
            progressBar.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvMovie = view.findViewById(R.id.rv_fragment_movie);
        progressBar = view.findViewById(R.id.movie_progressBar);

        model = ViewModelProviders.of(this).get(MovieModel.class);
        model.getMovie().observe(this, getMovie);

        adapter = new MoviesAdapter(getActivity());
        adapter.notifyDataSetChanged();

        rvMovie.setHasFixedSize(true);
        rvMovie.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        rvMovie.setAdapter(adapter);

        model.listMovie();
        showLoading(false);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie, container, false);
    }

//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//    }

    private Observer<ArrayList<Movie>> getMovie = new Observer<ArrayList<Movie>>() {
        @Override
        public void onChanged(@Nullable ArrayList<Movie> movies) {
            if (movies != null){
                adapter.setMovie(movies);
                showLoading(true);
            }

        }
    };

}
