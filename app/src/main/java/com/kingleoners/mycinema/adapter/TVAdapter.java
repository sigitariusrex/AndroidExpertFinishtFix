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
import com.kingleoners.mycinema.activity.TVDetailActivity;
import com.kingleoners.mycinema.item.TV;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TVAdapter extends RecyclerView.Adapter<TVAdapter.ListViewHolder>{
    private Activity activity;
    private Context context;
    private ArrayList<TV> arrayList;
    private List<TV> tvList = new ArrayList<>();

    public TVAdapter(Activity activity){
        this.activity = activity;
    }

    public ArrayList<TV> getTv(){
        return arrayList;
    }

    public TVAdapter(Context context){
        this.context = context;
    }

    public void setTV(List<TV> data){
        this.tvList.clear();
        this.tvList.addAll(data);
        notifyDataSetChanged();
    }

    class ListViewHolder extends RecyclerView.ViewHolder {
        TextView tvnama, tvnilai, tvdeskripsi, tvtanggal, tvbahasa;
        ImageView tvfoto;

        ListViewHolder(@NonNull View v) {
            super(v);
            tvnama = v.findViewById(R.id.tv_nama);
            tvnilai= v.findViewById(R.id.tv_nilai);
            tvdeskripsi = v.findViewById(R.id.tv_deskripsi);
            tvtanggal = v.findViewById(R.id.tv_tanggal);
            tvbahasa = v.findViewById(R.id.tv_bahasa);
            tvfoto = v.findViewById(R.id.tv_foto);
        }
    }
    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.tv_item, viewGroup, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ListViewHolder holder, final int position) {
        final TV tv = tvList.get(position);
        Glide.with(holder.itemView.getContext())
                .load(tvList.get(position).getTVfoto())
                .placeholder(R.drawable.loading)
                .apply(new RequestOptions().override(350, 550))
                .into(holder.tvfoto);
        holder.tvnama.setText(tv.getTVnama());
        holder.tvnilai.setText(tv.getTVnilai());
        holder.tvdeskripsi.setText(tv.getTVdeskripsi());
        holder.tvtanggal.setText(tv.getTVtanggal());
        holder.tvbahasa.setText(tv.getTVbahasa());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent moveObjectIntent = new Intent(holder.itemView.getContext(), TVDetailActivity.class);

                TV tvs = new TV();
                tvs.setTVid(tvList.get(position).getTVid());
                tvs.setTVnama(tvList.get(position).getTVnama());
                tvs.setTVnilai(tvList.get(position).getTVnilai());
                tvs.setTVdeskripsi(tvList.get(position).getTVdeskripsi());
                tvs.setTVtanggal(tvList.get(position).getTVtanggal());
                tvs.setTVbahasa(tvList.get(position).getTVbahasa());
                moveObjectIntent.putExtra(TVDetailActivity.EXTRA_TV, tv);
                holder.itemView.getContext().startActivity(moveObjectIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return tvList.size();
    }
}