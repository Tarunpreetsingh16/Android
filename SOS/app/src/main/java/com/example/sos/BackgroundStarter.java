package com.example.sos;

import android.app.Application;
import android.content.Intent;

public class BackgroundStarter extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        //start background process to fetch data
        startService(new Intent(this,BackgroundService.class));
    }
}
