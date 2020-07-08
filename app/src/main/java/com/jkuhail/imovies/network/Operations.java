package com.jkuhail.imovies.network;


import android.util.Log;


import androidx.core.util.Consumer;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import com.jkuhail.imovies.model.API;
import com.jkuhail.imovies.model.Constants;
import com.jkuhail.imovies.model.Credits;
import com.jkuhail.imovies.model.Item;
import com.jkuhail.imovies.model.ItemDetails;
import com.jkuhail.imovies.model.MediaType;
import com.jkuhail.imovies.model.Time;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Operations{


    public void fetchTopRated(final String mediaType, final Consumer<ArrayList<Item>> callback){

        JsonObjectRequest objectRequest  = new JsonObjectRequest(Request.Method.GET, API.TOP_RATED.topRatedUrl(mediaType), null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("Error", "something");
                        try {
                            String name = "title";
                            if(mediaType.equals(MediaType.TV.getValue())){
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
                                Item item = new Item(id, mediaType , title , image , language , String.valueOf(rate));
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

    public void fetchTrending(final String mediaType, final Time time, final Consumer<ArrayList<Item>> callback){

        JsonObjectRequest objectRequest  = new JsonObjectRequest(Request.Method.GET, API.TRENDING.trendingUrl(mediaType , time), null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("Error", "something");
                        try {
                            JSONArray movieArray = response.getJSONArray("results");
                            ArrayList<Item> result = new ArrayList<>();
                            String title;
                            for (int i = 0; i < movieArray.length(); i++) {
                                JSONObject element = movieArray.getJSONObject(i);
                                int id = element.getInt("id");
                                try{
                                    title = element.getString("title");
                                }catch (JSONException e){
                                    title = element.getString("name");
                                }
                                int rate = element.getInt("vote_average");
                                String image = Constants.IMAGE_INTRO + element.getString( "poster_path");
                                String language = element.getString("original_language");
                                String mediaType = element.getString("media_type");
                                Item item = new Item(id, mediaType, title , image , language , String.valueOf(rate));
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

    public void fetchDetails(final String mediaType, final int movieId , final Consumer<ItemDetails> callback){
        JsonObjectRequest objectRequest  = new JsonObjectRequest(Request.Method.GET, API.GET_ITEM.itemUrl(mediaType , movieId), null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("Error", "something");
                        try {
                            String name;
                            try{
                                name = response.getString("original_title");
                            }catch (JSONException e){
                                name = response.getString("name");
                            }
                            String overview = response.getString("overview");
                            ItemDetails itemDetails = new ItemDetails(name , overview);
                            callback.accept(itemDetails);
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

    public void fetchCredits(final String mediaType, final int itemId , final Consumer<ArrayList<Credits>> callback){
        JsonObjectRequest objectRequest  = new JsonObjectRequest(Request.Method.GET, API.GET_ITEM_CREDITS.itemUrl(mediaType , itemId), null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("Error", "something");
                        try {
                            JSONArray movieArray = response.getJSONArray("cast");
                            ArrayList<Credits> result = new ArrayList<>();
                            for (int i = 0; i < movieArray.length(); i++) {
                                JSONObject element = movieArray.getJSONObject(i);
                                String name = element.getString("name");

                                String character = element.getString("character");
                                Credits credits = new Credits(character , name);
                                result.add(credits);
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

    public void fetchVideo(final String mediaType, final int itemId , final Consumer<String> callback){
        JsonObjectRequest objectRequest  = new JsonObjectRequest(Request.Method.GET, API.GET_ITEM_VIDEO.itemUrl(mediaType, itemId), null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("Error", "something");
                        try {
                            JSONArray movieArray = response.getJSONArray("results");

                                JSONObject element = movieArray.getJSONObject(0);
                                String video_key = element.getString("key");

                            callback.accept(video_key);
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
