package com.jkuhail.imovies.network;


import android.util.Log;


import androidx.core.util.Consumer;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import com.jkuhail.imovies.model.API;
import com.jkuhail.imovies.model.Constants;
import com.jkuhail.imovies.model.Item;
import com.jkuhail.imovies.model.MediaType;
import com.jkuhail.imovies.model.Time;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Operations{


    public void fetchTopRated(final MediaType mediaType, final Consumer<ArrayList<Item>> callback){

        JsonObjectRequest objectRequest  = new JsonObjectRequest(Request.Method.GET, API.TOP_RATED.topRatedUrl(mediaType), null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("Error", "something");
                        try {
                            String name = "title";
                            if(mediaType.equals(MediaType.TV)){
                                name = "name";
                            }
                            JSONArray movieArray = response.getJSONArray("results");
                            ArrayList<Item> result = new ArrayList<>();
                            for (int i = 0; i < movieArray.length(); i++) {
                                JSONObject element = movieArray.getJSONObject(i);
                                int id = element.getInt("id");
                                String title = element.getString(name);
                                int rate = element.getInt("vote_average");
                                String image = Constants.IMAGE_INTRO + element.getString( "poster_path");
                                String language = element.getString("original_language");
                                Item item = new Item(id , title , image , language , String.valueOf(rate));
                                result.add(item);
                            }
                            callback.accept(result);
                        } catch (JSONException e) {
                            Log.e("Error", "Problem parsing the movie JSON results", e);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Error", "Problem parsing the movie JSON results");
            }
        });
        Log.i("Object",objectRequest.toString());
        AppController.getInstance().addToRequestQueue(objectRequest);
    }

    public void fetchTrending(final MediaType mediaType, final Time time, final Consumer<ArrayList<Item>> callback){

        JsonObjectRequest objectRequest  = new JsonObjectRequest(Request.Method.GET, API.TRENDING.trendingUrl(mediaType , time), null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("Error", "something");
                        try {
                            JSONArray movieArray = response.getJSONArray("results");
                            ArrayList<Item> result = new ArrayList<>();
                            for (int i = 0; i < movieArray.length(); i++) {
                                JSONObject element = movieArray.getJSONObject(i);
                                int id = element.getInt("id");
                                String title = element.getString("title");
                                int rate = element.getInt("vote_average");
                                String image = Constants.IMAGE_INTRO + element.getString( "poster_path");
                                String language = element.getString("original_language");
                                Item item = new Item(id, title , image , language , String.valueOf(rate));
                                result.add(item);
                            }
                            callback.accept(result);
                        } catch (JSONException e) {
                            Log.e("Error", "Problem parsing the movie JSON results", e);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Error", "Problem parsing the movie JSON results");
            }
        });
        Log.i("Object",objectRequest.toString());
        AppController.getInstance().addToRequestQueue(objectRequest);
    }

}
