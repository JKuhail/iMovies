package com.jkuhail.imovies.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.jkuhail.imovies.R;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

public class MainActivity extends AppCompatActivity {
    BottomBar bottomBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction().replace(R.id.mainContainer , new MoviesFragment()).commit();

        bottomBar = findViewById(R.id.bottomBar);
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(int tabId) {
                switch (tabId){
                    case R.id.tab_movies:
                        getSupportFragmentManager().beginTransaction().replace(R.id.mainContainer , new MoviesFragment()).commit();
                        break;
                    case R.id.tab_tv_shows:
                        getSupportFragmentManager().beginTransaction().replace(R.id.mainContainer , new ShowsFragment()).commit();
                        break;
                    case R.id.tab_trends:
                        getSupportFragmentManager().beginTransaction().replace(R.id.mainContainer , new TrendsFragment()).commit();
                        break;
                }
            }
        });
    }
}