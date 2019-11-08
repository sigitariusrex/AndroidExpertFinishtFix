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
import com.kingleoners.mycinema.item.TV;

import androidx.appcompat.app.AppCompatActivity;

public class TVDetailActivity extends AppCompatActivity {
    public static final String EXTRA_TV = "EXTRA_TV";
    private TextView tvnama, tvnilai, tvdeskripsi, tvtanggal, tvbahasa;
    private ImageView tvfoto;
    private TV tv;
    private FavoriteHelper favoriteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv_detail);

        tv = getIntent().getParcelableExtra(EXTRA_TV);

        String tvNama = tv.getTVnama();
        String tvNilai = tv.getTVnilai();
        String tvDeskripsi = tv.getTVdeskripsi();
        String tvTanggal = tv.getTVtanggal();
        String tvBahasa = tv.getTVbahasa();

        tvnama = findViewById(R.id.tv_nama);
        tvnilai = findViewById(R.id.tv_nilai);
        tvdeskripsi = findViewById(R.id.tv_deskripsi);
        tvtanggal = findViewById(R.id.tv_tanggal);
        tvbahasa = findViewById(R.id.tv_bahasa);
        tvfoto = findViewById(R.id.tv_foto);

        tvnama.setText(tvNama);
        tvnilai.setText(tvNilai);
        tvdeskripsi.setText(tvDeskripsi);
        tvtanggal.setText(tvTanggal);
        tvbahasa.setText(tvBahasa);
        String cover = tv.getTVfoto();
        Glide.with(TVDetailActivity.this)
                .load(cover)
                .placeholder(R.drawable.loading)
                .into(tvfoto);

        favoriteHelper = FavoriteHelper.getInstance(getApplicationContext());
        favoriteHelper.open();

        getSupportActionBar().setTitle(tv.getTVnama());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (favoriteHelper.isExistTv(tv)){
            getMenuInflater().inflate(R.menu.menu_delete_favorite, menu);
        }else{
            getMenuInflater().inflate(R.menu.menu_add_favorite, menu);
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_add_favorite){
            if(!favoriteHelper.isExistTv(tv)){
                long result = favoriteHelper.insertFavTv(tv);
                if(result > 0){
                    item.setIcon(R.drawable.ic_favorite_white);
                    Toast.makeText(TVDetailActivity.this, getString(R.string.success_favorite),Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(TVDetailActivity.this, getString(R.string.failed_favorite), Toast.LENGTH_LONG).show();
                }
            }else{
                Toast.makeText(TVDetailActivity.this,getString(R.string.done_favorite),Toast.LENGTH_SHORT).show();
            }
        } else{
            if(item.getItemId() == R.id.action_delete_favorite){
                int result = favoriteHelper.deleteFavTv(tv.getTVid());
                if(result > 0){
                    item.setIcon(R.drawable.ic_favorite_border_white);
                    Toast.makeText(TVDetailActivity.this,getString(R.string.success_delete),Toast.LENGTH_SHORT).show();
                } else{
                    Toast.makeText(TVDetailActivity.this,getString(R.string.failed_delete), Toast.LENGTH_SHORT).show();
                }
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
