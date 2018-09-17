package com.example.piiik98.designthinkingapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapterForCloseFriendActivity extends BaseAdapter {
    Context context;
    String choosenLocation;
    Boolean choosenNatioality;
    ArrayList<Integer> profileImage;
    ArrayList<String> profileName, profileLocation, profileNationality, profileMainLanguage, profileSecondaryLanguage;
    LayoutInflater layoutInflater;


    public CustomAdapterForCloseFriendActivity (Context applicationContext, ArrayList<Integer> profileImage, ArrayList<String> profileName, ArrayList<String> location, ArrayList<String> nationality, ArrayList<String> mainLanguage, ArrayList<String> secondaryLanguage, String choosenLocation, Boolean choosenNationality) {
        this.context = context;
        this.profileImage = profileImage;
        this.profileName = profileName;
        this.profileLocation = location;
        this.profileNationality = nationality;
        this.profileMainLanguage = mainLanguage;
        this.profileSecondaryLanguage = secondaryLanguage;

        layoutInflater = (LayoutInflater.from(applicationContext));

    }
    @Override
    public int getCount() {
        return profileName.size();
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
        view = layoutInflater.inflate(R.layout.row_for_friend, null);
        ImageView friendProfile = view.findViewById(R.id.profileImage);
        TextView name = view.findViewById(R.id.friendnameText);
        TextView location = view.findViewById(R.id.locationText);
        TextView nationality = view.findViewById(R.id.nationality);
        TextView mainLanguage = view.findViewById(R.id.languageText);
        TextView secondaryLanguage = view.findViewById(R.id.secondarylanguageText);

        friendProfile.setImageResource(R.drawable.com_facebook_profile_picture_blank_square);
        name.setText(profileName.get(i));
        location.setText(profileLocation.get(i));
        nationality.setText(profileNationality.get(i));
        mainLanguage.setText(profileMainLanguage.get(i));
        secondaryLanguage.setText(profileSecondaryLanguage.get(i));
        return view;
    }
}
