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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Operations{


    public void fetch(final MediaType mediaType, final Consumer<ArrayList<Item>> callback , final Consumer<VolleyError> errorCallBack){

        JsonObjectRequest objectRequest  = new JsonObjectRequest(Request.Method.GET, API.TOP_RATED.topRatedUrl(mediaType), null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("Error", "something");
                        try {
                            String name = "";
                            if(mediaType.equals(MediaType.MOVIE)){
                                name = "title";
                            }else if(mediaType.equals(MediaType.TV)){
                                name = "name";
                            }
                            JSONArray movieArray = response.getJSONArray("results");
                            ArrayList<Item> result = new ArrayList<>();
                            for (int i = 0; i < movieArray.length(); i++) {
                                JSONObject movie_element = movieArray.getJSONObject(i);
                                String title = movie_element.getString(name);
                                int rate = movie_element.getInt("vote_average");
                                String image = Constants.IMAGE_INTRO + movie_element.getString( "poster_path");
                                String language = movie_element.getString("original_language");
                                Item item = new Item(title , image , language , String.valueOf(rate));
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
                errorCallBack.accept(error);
            }
        });
        Log.i("Object",objectRequest.toString());
        AppController.getInstance().addToRequestQueue(objectRequest);
    }

}
