package com.example.sos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.ViewParent;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //check if app was already launched
        SharedPreferences preferences = getSharedPreferences(getString(R.string.app_pref),MODE_PRIVATE);
        boolean firstLaunchCheck = preferences.getBoolean(getString(R.string.first_launch_check),false);
        //if true then already launched
        if(firstLaunchCheck){
            Intent landingScreen = new Intent(this,LandingScreen.class);
            startActivity(landingScreen);
            finish();
        }

        getSupportActionBar().hide();

        //show info screen for first launch
        InfoCardHandler handler = new InfoCardHandler(getWindow().getDecorView().getRootView(),getApplicationContext());
        handler.showInfoScreen();
        handler.setInfoCardsHandler();

        //setup other functionality of info screen
        handler.setupSupplementary();
    }

}
