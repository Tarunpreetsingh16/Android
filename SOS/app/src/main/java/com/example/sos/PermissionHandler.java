package com.example.sos;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

public class PermissionHandler extends  Activity{
    Activity activity;
    public PermissionHandler( Activity activity){
        this.activity = activity;
    }
    //ask for contact permission
    public void requestContactPermission() {
        ActivityCompat.requestPermissions(activity,new String[]{Manifest.permission.READ_CONTACTS},1);
    }
    //ask for location permission
    public void requestLocationPermission() {
        ActivityCompat.requestPermissions(activity,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},2);
    }
}
