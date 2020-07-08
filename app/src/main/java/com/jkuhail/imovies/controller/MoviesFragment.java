package com.jkuhail.imovies.controller;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.core.util.Consumer;
import androidx.fragment.app.Fragment;

import com.android.volley.VolleyError;
import com.jkuhail.imovies.R;
import com.jkuhail.imovies.controller.adapter.MainAdapter;
import com.jkuhail.imovies.model.Constants;
import com.jkuhail.imovies.model.Item;
import com.jkuhail.imovies.model.MediaType;
import com.jkuhail.imovies.network.Operations;

import java.util.ArrayList;


public class MoviesFragment extends Fragment {
    MainAdapter mainAdapter;
    ListView mainListView;
    Operations operations = new Operations();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View root = inflater.inflate(R.layout.fragment_movies, container, false);

        mainListView = root.findViewById(R.id.movies_list);

        operations.fetchTopRated(MediaType.MOVIE.getValue(), new Consumer<ArrayList<Item>>() {
            @Override
            public void accept(ArrayList<Item> items) {
                mainAdapter = new MainAdapter(getActivity(), items);
                mainListView.setAdapter(mainAdapter);
            }
        });
        mainListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int element_id = MainAdapter.data.get(position).getId();
                Intent intent = new Intent(root.getContext(), DetailsActivity.class);
                intent.putExtra(Constants.ELEMENT_ID, element_id);
                intent.putExtra(Constants.ELEMENT_TYPE, MediaType.MOVIE.getValue());
                startActivity(intent);
            }
        });

        return root;
    }

}