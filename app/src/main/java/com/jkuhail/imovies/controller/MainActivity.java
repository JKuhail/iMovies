package com.jkuhail.imovies.controller;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import com.jkuhail.imovies.R;



public class MainActivity extends AppCompatActivity {
    Button top_movies, top_tv_shows, trends;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        top_movies = findViewById(R.id.top_movies);
        top_tv_shows = findViewById(R.id.top_tv_shows);
        trends = findViewById(R.id.trends);

        getSupportFragmentManager().beginTransaction().replace(R.id.mainContainer , new ShowsFragment()).commit();

        top_movies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.mainContainer , new MoviesFragment()).commit();
            }
        });
        top_tv_shows.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.mainContainer , new ShowsFragment()).commit();
            }
        });
        trends.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.mainContainer , new TrendsFragment()).commit();
            }
        });


    }
}