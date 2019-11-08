package com.kingleoners.mycinema.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.kingleoners.mycinema.R;
import com.kingleoners.mycinema.adapter.TVAdapter;
import com.kingleoners.mycinema.helper.FavoriteHelper;
import com.kingleoners.mycinema.item.TV;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


/**
 * A simple {@link Fragment} subclass.
 */
public class TVFavoriteFragment extends Fragment {
    private static final String EXTRA_TVSHOW = "tv";
    private ArrayList<TV> listTV = new ArrayList<>();
    private RecyclerView rvTV;
    private ProgressBar progressBar;
    private TVAdapter listTVShowAdapter;
    private FavoriteHelper favoriteHelper;
    private Bundle State;


    public TVFavoriteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tv_favorite, container, false);
    }

    @Override
    public void onStart() {
        rvTV.setLayoutManager(new LinearLayoutManager(getContext()));
        rvTV.setHasFixedSize(true);
        progressBar.setVisibility(View.VISIBLE);
        favoriteHelper = FavoriteHelper.getInstance(getContext());
        favoriteHelper.open();

        listTVShowAdapter = new TVAdapter(getContext());
        rvTV.setAdapter(listTVShowAdapter);

        if (State == null) {
            listTV.clear();
            listTV.addAll(favoriteHelper.getAllFavTv());
            if (listTV != null) {
                listTVShowAdapter.setTV(listTV);
            } else {
                Toast.makeText(getContext(), getResources().getString(R.string.empty_data), Toast.LENGTH_SHORT).show();
            }
        } else {
            ArrayList<TV> list = State.getParcelableArrayList(EXTRA_TVSHOW);
            if (list != null) {
                listTVShowAdapter.setTV(list);
            }
        }
        super.onStart();
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvTV = view.findViewById(R.id.rv_fragment_tv_favorite);
        progressBar = view.findViewById(R.id.tv_progressBar);
        if (savedInstanceState != null) {
            State = savedInstanceState;
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(EXTRA_TVSHOW, listTVShowAdapter.getTv());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        favoriteHelper.close();
    }
}
