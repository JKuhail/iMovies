package com.jkuhail.imovies.controller;

import androidx.core.util.Consumer;

import android.os.Bundle;
import android.widget.TextView;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.jkuhail.imovies.R;
import com.jkuhail.imovies.model.Constants;
import com.jkuhail.imovies.model.Credits;
import com.jkuhail.imovies.model.ItemDetails;
import com.jkuhail.imovies.model.MediaType;
import com.jkuhail.imovies.network.Operations;

import java.util.ArrayList;

public class DetailsActivity extends YouTubeBaseActivity {
    int element_id;
    TextView element_name, overview, credits_character , credits_name, trailer;
    YouTubePlayerView youtubePlayerView;
    Operations operations;
    String mediaType;
    boolean isTrending;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        element_id = getIntent().getIntExtra(Constants.ELEMENT_ID , 0);
        isTrending = getIntent().getBooleanExtra(Constants.IS_TRENDING , false);
        mediaType = getIntent().getStringExtra(Constants.ELEMENT_TYPE);


        element_name = findViewById(R.id.element_name);
        overview = findViewById(R.id.overview);
        credits_character = findViewById(R.id.credits_character);
        credits_name = findViewById(R.id.credits_name);
        trailer = findViewById(R.id.trailer);
        youtubePlayerView = findViewById(R.id.youtubePlayerView);

        operations = new Operations();


        operations.fetchDetails(mediaType, element_id, new Consumer<ItemDetails>() {
            @Override
            public void accept(ItemDetails itemDetails) {
                element_name.setText(itemDetails.getName());
                overview.setText(itemDetails.getOverview());
            }
        });
        operations.fetchCredits(mediaType, element_id, new Consumer<ArrayList<Credits>>() {
            @Override
            public void accept(ArrayList<Credits> credits) {
                StringBuilder character = new StringBuilder();
                StringBuilder name = new StringBuilder();
                for(int i = 0; i < credits.size(); i++){
                    character.append(credits.get(i).getCharacter()).append("\n");
                    credits_character.setText(character);
                    name.append(credits.get(i).getName()).append("\n");
                    credits_name.setText(name);
                    if(credits_character.getLineCount() != credits_name.getLineCount()){
                        name.append("\n") ;
                    }
                }
            }
        });

        operations.fetchVideo(mediaType, element_id, new Consumer<String>() {
            @Override
            public void accept(String s) {
                playVideo(s, youtubePlayerView);
                }
            });


    }
    public void playVideo(final String videoId, YouTubePlayerView youTubePlayerView) {
        //initialize youtube player view
        youTubePlayerView.initialize(Constants.YOUTUBE_API_KEY,
                new YouTubePlayer.OnInitializedListener() {
                    @Override
                    public void onInitializationSuccess(YouTubePlayer.Provider provider,
                                                        YouTubePlayer youTubePlayer, boolean b) {
                        youTubePlayer.cueVideo(videoId);
                    }

                    @Override
                    public void onInitializationFailure(YouTubePlayer.Provider provider,
                                                        YouTubeInitializationResult youTubeInitializationResult) {

                    }
                });
    }
}