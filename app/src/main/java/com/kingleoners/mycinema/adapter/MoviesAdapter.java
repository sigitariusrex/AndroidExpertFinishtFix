package com.kingleoners.mycinema.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.kingleoners.mycinema.R;
import com.kingleoners.mycinema.activity.MovieDetailActivity;
import com.kingleoners.mycinema.item.Movie;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.ListViewHolder>{
    private Activity activity;
    private Context context;
    private ArrayList<Movie> arrayList;
    private List<Movie> movieList = new ArrayList<>();

    public MoviesAdapter(Activity activity){
        this.activity = activity;
    }

    public MoviesAdapter(Context context){
        this.context = context;
    }

    public ArrayList<Movie> getFilms() {
        return arrayList;
    }

    public void setMovie(List<Movie> data){
        this.movieList.clear();
        this.movieList.addAll(data);
        notifyDataSetChanged();
    }

    class ListViewHolder extends RecyclerView.ViewHolder{
        TextView mvnama, mvnilai, mvdeskripsi, mvtanggal, mvbahasa;
        ImageView mvfoto;

        ListViewHolder(@NonNull View v){
            super(v);
            mvnama = v.findViewById(R.id.mv_nama);
            mvnilai = v.findViewById(R.id.mv_nilai);
            mvdeskripsi = v.findViewById(R.id.mv_deskripsi);
            mvtanggal = v.findViewById(R.id.mv_tanggal);
            mvbahasa = v.findViewById(R.id.mv_bahasa);
            mvfoto = v.findViewById(R.id.mv_foto);
        }
    }
    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item,parent,false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ListViewHolder holder, final int position) {
        final Movie movie = movieList.get(position);

        Glide.with(holder.itemView.getContext())
                .load(movieList.get(position).getMVfoto())
                .placeholder(R.drawable.loading)
                .apply(new RequestOptions().override(350, 550))
                .into(holder.mvfoto);
        holder.mvnama.setText(movie.getMVnama());
        holder.mvnilai.setText(movie.getMVnilai());
        holder.mvdeskripsi.setText(movie.getMVdeskripsi());
        holder.mvtanggal.setText(movie.getMVtanggal());
        holder.mvbahasa.setText(movie.getMVbahasa());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent moveObjectIntent = new Intent(holder.itemView.getContext(), MovieDetailActivity.class);

                Movie movieset = new Movie();
                movieset.setMVid(movieList.get(position).getMVid());
                movieset.setMVnama(movieList.get(position).getMVnama());
                movieset.setMVnilai(movieList.get(position).getMVnilai());
                movieset.setMVdeskripsi(movieList.get(position).getMVdeskripsi());
                movieset.setMVtanggal(movieList.get(position).getMVtanggal());
                movieset.setMVbahasa(movieList.get(position).getMVbahasa());
                moveObjectIntent.putExtra(MovieDetailActivity.EXTRA_MOVIE, movie);
                holder.itemView.getContext().startActivity(moveObjectIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }
}
