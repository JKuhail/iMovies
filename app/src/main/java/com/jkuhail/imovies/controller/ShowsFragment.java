package com.jkuhail.imovies.controller;

import android.os.Bundle;

import androidx.core.util.Consumer;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.android.volley.VolleyError;
import com.jkuhail.imovies.R;
import com.jkuhail.imovies.controller.adapter.MainAdapter;
import com.jkuhail.imovies.model.Item;
import com.jkuhail.imovies.model.MediaType;
import com.jkuhail.imovies.network.Operations;

import java.util.ArrayList;


public class ShowsFragment extends Fragment {
    MainAdapter mainAdapter;
    ListView mainListView;
    Operations operations = new Operations();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View root =  inflater.inflate(R.layout.fragment_shows, container, false);

        mainListView = root.findViewById(R.id.shows_list);
        operations.fetchTopRated(MediaType.TV, new Consumer<ArrayList<Item>>() {
            @Override
            public void accept(ArrayList<Item> items) {
                mainAdapter = new MainAdapter(getActivity(), items);
                mainListView.setAdapter(mainAdapter);
            }
        });
        return root;
    }
}