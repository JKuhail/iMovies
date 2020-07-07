package com.jkuhail.imovies.controller.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.jkuhail.imovies.R;
import com.jkuhail.imovies.network.AppController;
import com.jkuhail.imovies.model.Item;

import java.util.ArrayList;

public class MainAdapter extends BaseAdapter {
    private Activity activity;
    public static ArrayList<Item> data;
    ImageLoader imageLoader = AppController.getInstance().getImageLoader();

    public MainAdapter(Activity activity , ArrayList<Item> data) {
        this.activity = activity;
        this.data =data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View root = LayoutInflater.from(activity).inflate(R.layout.single_item , null , false);

        if (imageLoader == null)
            imageLoader = AppController.getInstance().getImageLoader();
        NetworkImageView thumbNail =  root
                .findViewById(R.id.thumbnail);

        final TextView title = root.findViewById(R.id.title);
        final TextView rate = root.findViewById(R.id.rate);
        final TextView language = root.findViewById(R.id.language);

        title.setText(data.get(position).getTitle());
        rate.setText( data.get(position).getRate());
        language.setText(data.get(position).getLanguage());
        thumbNail.setImageUrl(data.get(position).getImage(), imageLoader);
        return root;
    }
}
