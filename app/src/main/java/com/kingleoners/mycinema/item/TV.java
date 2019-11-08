package com.kingleoners.mycinema.item;

import android.os.Parcel;
import android.os.Parcelable;

import com.kingleoners.mycinema.BuildConfig;

import org.json.JSONObject;

public class TV implements Parcelable {
    private String TVnama, TVnilai, TVdeskripsi, TVtanggal, TVbahasa, TVfoto;
    private int TVid;

    public TV() {

    }

    public TV(String TVnama, String TVnilai, String TVdeskripsi, String TVtanggal, String TVbahasa, String TVfoto, int TVid) {
        this.TVnama = TVnama;
        this.TVnilai = TVnilai;
        this.TVdeskripsi = TVdeskripsi;
        this.TVtanggal = TVtanggal;
        this.TVbahasa = TVbahasa;
        this.TVfoto = TVfoto;
        this.TVid = TVid;
    }

    public String getTVnama() {
        return TVnama;
    }

    public void setTVnama(String TVnama) {
        this.TVnama = TVnama;
    }

    public String getTVnilai() {
        return TVnilai;
    }

    public void setTVnilai(String TVnilai) {
        this.TVnilai = TVnilai;
    }

    public String getTVdeskripsi() {
        return TVdeskripsi;
    }

    public void setTVdeskripsi(String TVdeskripsi) {
        this.TVdeskripsi = TVdeskripsi;
    }

    public String getTVtanggal() {
        return TVtanggal;
    }

    public void setTVtanggal(String TVtanggal) {
        this.TVtanggal = TVtanggal;
    }

    public String getTVbahasa() {
        return TVbahasa;
    }

    public void setTVbahasa(String TVbahasa) {
        this.TVbahasa = TVbahasa;
    }

    public String getTVfoto() {
        return TVfoto;
    }

    public void setTVfoto(String TVfoto) {
        this.TVfoto = TVfoto;
    }

    public int getTVid() {
        return TVid;
    }

    public void setTVid(int TVid) {
        this.TVid = TVid;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.TVnama);
        dest.writeString(this.TVnilai);
        dest.writeString(this.TVdeskripsi);
        dest.writeString(this.TVtanggal);
        dest.writeString(this.TVbahasa);
        dest.writeString(this.TVfoto);
        dest.writeInt(this.TVid);
    }

    protected TV(Parcel in) {
        this.TVnama = in.readString();
        this.TVnilai = in.readString();
        this.TVdeskripsi = in.readString();
        this.TVtanggal = in.readString();
        this.TVbahasa = in.readString();
        this.TVfoto = in.readString();
        this.TVid = in.readInt();
    }

    public static final Creator<TV> CREATOR = new Creator<TV>() {
        @Override
        public TV createFromParcel(Parcel source) {
            return new TV(source);
        }

        @Override
        public TV[] newArray(int size) {
            return new TV[size];
        }
    };

    public TV(JSONObject object) {
        try {
            int TVid = object.getInt("id");
            String TVnama = object.getString("name");
            String TVnilai = object.getString("vote_average");
            String TVdeskripsi = object.getString("overview");
            String TVtanggal = object.getString("first_air_date");
            String TVbahasa = object.getString("original_language");
            String TVfoto = BuildConfig.API_URL_PHOTO + object.getString("poster_path");

            this.TVid = TVid;
            this.TVnama = TVnama;
            this.TVnilai = TVnilai;
            this.TVdeskripsi = TVdeskripsi;
            this.TVtanggal = TVtanggal;
            this.TVbahasa = TVbahasa;
            this.TVfoto = TVfoto;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
