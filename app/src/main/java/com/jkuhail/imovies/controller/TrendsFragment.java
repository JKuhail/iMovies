package com.jkuhail.imovies.controller;

import android.content.Intent;
import android.os.Bundle;

import androidx.core.util.Consumer;
import androidx.fragment.app.Fragment;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.jkuhail.imovies.R;
import com.jkuhail.imovies.controller.adapter.MainAdapter;
import com.jkuhail.imovies.model.Constants;
import com.jkuhail.imovies.model.Item;
import com.jkuhail.imovies.model.MediaType;
import com.jkuhail.imovies.model.Time;
import com.jkuhail.imovies.network.Operations;

import java.util.ArrayList;


public class TrendsFragment extends Fragment {

    MainAdapter mainAdapter;
    ListView mainListView;
    Operations operations = new Operations();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View root =  inflater.inflate(R.layout.fragment_trends, container, false);
        mainListView = root.findViewById(R.id.trends_list);

        operations.fetchTrending(MediaType.ALL.getValue(), Time.DAY,
                new Consumer<ArrayList<Item>>() {
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
                String mediaType = MainAdapter.data.get(position).getMediaType();

                Intent intent = new Intent(root.getContext(), DetailsActivity.class);
                intent.putExtra(Constants.ELEMENT_ID, element_id);
                intent.putExtra(Constants.ELEMENT_TYPE, mediaType);
                startActivity(intent);
            }
        });

        return root;
    }
}