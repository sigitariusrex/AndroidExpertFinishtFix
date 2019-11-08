package com.kingleoners.mycinema.model;

import android.util.Log;

import com.kingleoners.mycinema.BuildConfig;
import com.kingleoners.mycinema.item.Movie;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import cz.msebera.android.httpclient.Header;


public class MovieModel extends ViewModel {
    private MutableLiveData<ArrayList<Movie>> mvList = new MutableLiveData<>();
    private MutableLiveData<ArrayList<Movie>> mvListSearch = new MutableLiveData<>();

    public void pencarianMovie(String nama){
        AsyncHttpClient client = new AsyncHttpClient();
        final ArrayList<Movie> mvItemSearch = new ArrayList<>();
        String url = BuildConfig.API_URL_MOVIE_SEARCH + nama;
        Log.i("Pencarian Movie",url);
        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String strMV = new String(responseBody);
                    JSONObject object = new JSONObject(strMV);
                    JSONArray array = object.getJSONArray("results");
                    for(int i = 0; i < array.length(); i++){
                        JSONObject objectMV = array.getJSONObject(i);
                        Movie mv = new Movie(objectMV);
                        Log.i("Get Name", mv.getMVnama());
                        mvItemSearch.add(mv);
                    }
                    mvListSearch.postValue(mvItemSearch);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.d("Failed", error.getMessage());
            }
        });
    }



    public void listMovie(){
        AsyncHttpClient client = new AsyncHttpClient();
        final ArrayList<Movie> mvItem = new ArrayList<>();
        client.get(BuildConfig.API_URL_MOVIE, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String strMV = new String(responseBody);
                    JSONObject object = new JSONObject(strMV);
                    JSONArray array = object.getJSONArray("results");
                    for(int i = 0; i < array.length(); i++){
                        JSONObject objectMV = array.getJSONObject(i);
                        Movie MV = new Movie(objectMV);
                        mvItem.add(MV);
                    }
                    mvList.postValue(mvItem);
                } catch (Exception e) {
                    Log.i("Exception", e.getMessage());
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.i("Failed", error.getMessage());
            }
        });
    }

    public LiveData<ArrayList<Movie>> getMovie(){
            return mvList;
    }

    public LiveData<ArrayList<Movie>> getMovieSearch() {
        return mvListSearch;
    }
}
