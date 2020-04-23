package com.example.sos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;
import android.view.ViewParent;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<InfoAdapter> infoAdapters;
    private ViewPager infoViewPager;
    private InfoSlider infoSliderAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        //initialize variables
        initializeData();
        //show info screen for first launch
        showInfoScreen();
    }

    private void initializeData() {
        infoViewPager = findViewById(R.id.infoViewPager);
    }

    public void showInfoScreen(){
        //create a list of info cards
        createInfoList();
        infoSliderAdapter = new InfoSlider(this,infoAdapters);
        infoViewPager.setAdapter(infoSliderAdapter);
    }
    public void createInfoList(){
        infoAdapters = new ArrayList<InfoAdapter>();
        InfoAdapter infoAdapter = new InfoAdapter();
        //add items to the list that will be displayed on first launch for user info
        infoAdapter.setImage(R.drawable.sos_info_screen);
        infoAdapter.setTitle(getString(R.string.sos_info_title));
        infoAdapter.setSubtitle(getString(R.string.sos_info_subtitle));
        infoAdapters.add(infoAdapter);

        infoAdapter = new InfoAdapter();
        infoAdapter.setImage(R.drawable.contact_info_screen);
        infoAdapter.setTitle(getString(R.string.contact_info_title));
        infoAdapter.setSubtitle(getString(R.string.contact_info_subtitle));
        infoAdapters.add(infoAdapter);

        infoAdapter = new InfoAdapter();
        infoAdapter.setImage(R.drawable.shake_info_screen);
        infoAdapter.setTitle(getString(R.string.shake_info_title));
        infoAdapter.setSubtitle(getString(R.string.shake_info_subtitle));
        infoAdapters.add(infoAdapter);
    }
}
