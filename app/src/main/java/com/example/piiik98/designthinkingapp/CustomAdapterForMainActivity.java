package com.example.piiik98.designthinkingapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomAdapterForMainActivity extends BaseAdapter {
    Context context;
    int houseImage[];
    String bedlist[];
    String bathroomlist[];
    String pricelist[];
    LayoutInflater inflter;

    public CustomAdapterForMainActivity(Context applicationContext, int[] houseImage, String[] bedlist, String[] bathroomlist, String[] pricelist) {
        this.context = context;
        this.houseImage = houseImage;
        this.bedlist = bedlist;
        this.bathroomlist = bathroomlist;
        this.pricelist = pricelist;
        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return houseImage.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflter.inflate(R.layout.row_for_house, null);
        ImageView house = view.findViewById(R.id.houseImage);
        TextView bed = view.findViewById(R.id.bed);
        TextView bathroom = view.findViewById(R.id.bathroom);
        TextView price = view.findViewById(R.id.price);
        bed.setText(bedlist[i]);
        bathroom.setText(bathroomlist[i]);
        price.setText(pricelist[i]);
        house.setImageResource(houseImage[i]);
        return view;
    }
}
