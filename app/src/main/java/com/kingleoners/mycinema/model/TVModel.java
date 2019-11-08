package com.kingleoners.mycinema.model;

import android.util.Log;

import com.kingleoners.mycinema.BuildConfig;
import com.kingleoners.mycinema.item.TV;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import cz.msebera.android.httpclient.Header;


public class TVModel extends ViewModel {
    private MutableLiveData<ArrayList<TV>> tvList = new MutableLiveData<>();
    private MutableLiveData<ArrayList<TV>> tvListSearch = new MutableLiveData<>();

    public void pencarianTV(String nama){
        AsyncHttpClient client = new AsyncHttpClient();
        final ArrayList<TV> tvItemSearch = new ArrayList<>();
        String url = BuildConfig.API_URL_TV_SEARCH + nama;
        Log.i("Pencarian TV", url);
        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String strTV = new String(responseBody);
                    JSONObject object = new JSONObject(strTV);
                    JSONArray array = object.getJSONArray("results");
                    for(int i = 0; i < array.length(); i++ ){
                        JSONObject objectTV = array.getJSONObject(i);
                        TV tv = new TV(objectTV);
                        Log.i("Get Name",tv.getTVnama());
                        tvItemSearch.add(tv);
                    }
                    tvListSearch.postValue(tvItemSearch);
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


    public void listTV(){
        AsyncHttpClient client = new AsyncHttpClient();
        final ArrayList<TV> tvItem = new ArrayList<>();
        client.get(BuildConfig.API_URL_TV, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String strTV = new String(responseBody);
                    JSONObject object = new JSONObject(strTV);
                    JSONArray array = object.getJSONArray("results");
                    for(int i = 0; i < array.length(); i++ ){
                        JSONObject objectTV = array.getJSONObject(i);
                        TV tv = new TV(objectTV);
                        tvItem.add(tv);
                    }
                    tvList.postValue(tvItem);
                } catch (Exception e) {
                    Log.d("Exception", e.getMessage());
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.d("Failed", error.getMessage());
            }
        });
    }
    public LiveData<ArrayList<TV>> getTV(){
        return tvList;
    }

    public LiveData<ArrayList<TV>> getTVSearch(){
        return tvListSearch;
    }
}
