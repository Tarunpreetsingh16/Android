package com.example.sos;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Handler;

public class SplashScreen extends AppCompatActivity {
    Handler handlerSplashScreen;
    Intent mainActivityIntent;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        getSupportActionBar().hide();
        //create handler to start a new thread for splash screen
        handlerSplashScreen = new Handler();
        //show splash screen for some time 2 or 3 secs and then launch main activity
        handlerSplashScreen.postDelayed(new Runnable() {
            @Override
            public void run() {
                //after 2.5 secs launch main acitivity
                mainActivityIntent = new Intent(SplashScreen.this,MainActivity.class);
                startActivity(mainActivityIntent); //launch the activity
                //finish the activity
                finish();
            }
        },1500); //wait for 2.5 secs till splash screen is being displayed
    }
}
