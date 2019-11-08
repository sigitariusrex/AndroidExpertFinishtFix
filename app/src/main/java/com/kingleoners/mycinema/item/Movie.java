package com.kingleoners.mycinema.item;

import android.os.Parcel;
import android.os.Parcelable;

import com.kingleoners.mycinema.BuildConfig;

import org.json.JSONObject;

public class Movie implements Parcelable {

    private String MVnama, MVnilai, MVdeskripsi, MVtanggal, MVbahasa, MVfoto;
    private int MVid;

    public Movie() {

    }

    public Movie(String MVnama, String MVnilai, String MVdeskripsi, String MVtanggal, String MVbahasa, String MVfoto, int MVid) {
        this.MVnama = MVnama;
        this.MVnilai = MVnilai;
        this.MVdeskripsi = MVdeskripsi;
        this.MVtanggal = MVtanggal;
        this.MVbahasa = MVbahasa;
        this.MVfoto = MVfoto;
        this.MVid = MVid;
    }

    public String getMVnama() {
        return MVnama;
    }

    public void setMVnama(String MVnama) {
        this.MVnama = MVnama;
    }

    public String getMVnilai() {
        return MVnilai;
    }

    public void setMVnilai(String MVnilai) {
        this.MVnilai = MVnilai;
    }

    public String getMVdeskripsi() {
        return MVdeskripsi;
    }

    public void setMVdeskripsi(String MVdeskripsi) {
        this.MVdeskripsi = MVdeskripsi;
    }

    public String getMVtanggal() {
        return MVtanggal;
    }

    public void setMVtanggal(String MVtanggal) {
        this.MVtanggal = MVtanggal;
    }

    public String getMVbahasa() {
        return MVbahasa;
    }

    public void setMVbahasa(String MVbahasa) {
        this.MVbahasa = MVbahasa;
    }

    public String getMVfoto() {
        return MVfoto;
    }

    public void setMVfoto(String MVfoto) {
        this.MVfoto = MVfoto;
    }

    public int getMVid() {
        return MVid;
    }

    public void setMVid(int MVid) {
        this.MVid = MVid;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.MVnama);
        dest.writeString(this.MVnilai);
        dest.writeString(this.MVdeskripsi);
        dest.writeString(this.MVtanggal);
        dest.writeString(this.MVbahasa);
        dest.writeString(this.MVfoto);
        dest.writeInt(this.MVid);
    }

    protected Movie(Parcel in) {
        this.MVnama = in.readString();
        this.MVnilai = in.readString();
        this.MVdeskripsi = in.readString();
        this.MVtanggal = in.readString();
        this.MVbahasa = in.readString();
        this.MVfoto = in.readString();
        this.MVid = in.readInt();
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel source) {
            return new Movie(source);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    public Movie(JSONObject object){
        try {
            int MVid = object.getInt("id");
            String MVnama = object.getString("title");
            String MVbahasa = object.getString("original_language");
            String MVtanggal = object.getString("release_date");
            String MVnilai = object.getString("vote_average");
            String MVdeskripsi = object.getString("overview");
            String MVfoto = BuildConfig.API_URL_PHOTO + object.getString("poster_path");

            this.MVid = MVid;
            this.MVnama = MVnama;
            this.MVbahasa = MVbahasa;
            this.MVtanggal = MVtanggal;
            this.MVnilai = MVnilai;
            this.MVdeskripsi = MVdeskripsi;
            this.MVfoto = MVfoto;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
