package com.example.piiik98.designthinkingapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomAdapterForCloseFriendActivity extends BaseAdapter {
    Context context;
    int[] profileImage;
    String[] profileName, profileLocation, profileNationality, profileMainLanguage, profileSecondaryLanguage;
    LayoutInflater layoutInflater;


    public CustomAdapterForCloseFriendActivity (Context applicationContext, int[] profileImage, String[] profileName, String[] location, String[] nationality, String[] mainLanguage, String[] secondaryLanguage) {
        this.context = context;
        this.profileImage = profileImage;
        this.profileName = profileName;
        this.profileLocation = location;
        this.profileNationality = nationality;
        this.profileMainLanguage = mainLanguage;
        this.profileSecondaryLanguage = secondaryLanguage;


    }
    @Override
    public int getCount() {
        return profileImage.length;
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
        friendProfile.setImageResource(profileImage[i]);
        name.setText(profileName[i]);
        location.setText(profileLocation[i]);
        nationality.setText(profileNationality[i]);
        mainLanguage.setText(profileMainLanguage[i]);
        secondaryLanguage.setText(profileSecondaryLanguage[i]);
        return view;
    }
}
