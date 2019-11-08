package com.kingleoners.mycinema.activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.kingleoners.mycinema.R;
import com.kingleoners.mycinema.helper.FavoriteHelper;
import com.kingleoners.mycinema.item.Movie;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MovieDetailActivity extends AppCompatActivity {
    public static final String EXTRA_MOVIE = "EXTRA_MOVIE";
    private TextView mvnama, mvnilai, mvdeskripsi, mvtanggal, mvbahasa;
    private ImageView mvfoto;
    private Movie movie;
    private FavoriteHelper favoriteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        movie = getIntent().getParcelableExtra(EXTRA_MOVIE);

        String mvNama = movie.getMVnama();
        String mvNilai = movie.getMVnilai();
        String mvDeskripsi = movie.getMVdeskripsi();
        String mvTanggal = movie.getMVtanggal();
        String mvBahasa = movie.getMVbahasa();

        mvnama = findViewById(R.id.mv_nama);
        mvnilai = findViewById(R.id.mv_nilai);
        mvdeskripsi = findViewById(R.id.mv_deskripsi);
        mvtanggal = findViewById(R.id.mv_tanggal);
        mvbahasa = findViewById(R.id.mv_bahasa);
        mvfoto = findViewById(R.id.mv_foto);

        mvnama.setText(mvNama);
        mvnilai.setText(mvNilai);
        mvdeskripsi.setText(mvDeskripsi);
        mvtanggal.setText(mvTanggal);
        mvbahasa.setText(mvBahasa);
        String cover = movie.getMVfoto();
        Glide.with(MovieDetailActivity.this)
                .load(cover)
                .placeholder(R.drawable.loading)
                .into(mvfoto);

        favoriteHelper = FavoriteHelper.getInstance(getApplicationContext());
        favoriteHelper.open();

        getSupportActionBar().setTitle(movie.getMVnama());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (favoriteHelper.isExist(movie)){
            getMenuInflater().inflate(R.menu.menu_delete_favorite, menu);
        }else{
            getMenuInflater().inflate(R.menu.menu_add_favorite, menu);
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_add_favorite){
            if(!favoriteHelper.isExist(movie)){
                long result = favoriteHelper.insertFav(movie);
                if(result > 0){
                    item.setIcon(R.drawable.ic_favorite_white);
                    Toast.makeText(MovieDetailActivity.this, getString(R.string.success_favorite),Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(MovieDetailActivity.this, getString(R.string.failed_favorite), Toast.LENGTH_LONG).show();
                }
            }else{
                Toast.makeText(MovieDetailActivity.this,getString(R.string.done_favorite),Toast.LENGTH_SHORT).show();
            }
        } else{
            if(item.getItemId() == R.id.action_delete_favorite){
                int result = favoriteHelper.deleteFav(movie.getMVid());
                if(result > 0){
                    item.setIcon(R.drawable.ic_favorite_border_white);
                    Toast.makeText(MovieDetailActivity.this,getString(R.string.success_delete),Toast.LENGTH_SHORT).show();
                } else{
                    Toast.makeText(MovieDetailActivity.this,getString(R.string.failed_delete), Toast.LENGTH_SHORT).show();
                }
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
