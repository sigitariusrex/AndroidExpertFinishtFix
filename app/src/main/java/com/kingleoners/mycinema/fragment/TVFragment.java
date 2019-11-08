package com.kingleoners.mycinema.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.kingleoners.mycinema.R;
import com.kingleoners.mycinema.adapter.TVAdapter;
import com.kingleoners.mycinema.item.TV;
import com.kingleoners.mycinema.model.TVModel;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


/**
 * A simple {@link Fragment} subclass.
 */
public class TVFragment extends Fragment {
    private ProgressBar progressBar;
    RecyclerView rvTV;
    TVAdapter adapter;
    TVModel model;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }


    public TVFragment() {
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
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tv, container, false);
    }

    @Override
    public void onViewCreated(View view,Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvTV = view.findViewById(R.id.rv_fragment_tv);
        progressBar = view.findViewById(R.id.tv_progressBar);

        model = ViewModelProviders.of(this).get(TVModel.class);
        model.getTV().observe(this, getTV);

        adapter = new TVAdapter(getActivity());
        adapter.notifyDataSetChanged();

        rvTV.setHasFixedSize(true);
        rvTV.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        rvTV.setAdapter(adapter);

        model.listTV();
        showLoading(false);
    }

    private Observer<ArrayList<TV>> getTV = new Observer<ArrayList<TV>>() {
        @Override
        public void onChanged(@Nullable ArrayList<TV> tv) {
            if (tv != null){
                adapter.setTV(tv);
                showLoading(true);
            }

        }
    };
}
