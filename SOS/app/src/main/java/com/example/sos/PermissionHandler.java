package com.example.sos;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;

public class PermissionHandler extends  Activity{
    private View view;
    private Context context;
    Activity activity;
    public PermissionHandler(View view, Context context, Activity activity){
        this.view = view;
        this.context = context;
        this.activity = activity;
    }

    public void requestPersmission() {
        if(ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.READ_CONTACTS)){
            Toast.makeText(context,"Permission not granted!",Toast.LENGTH_LONG).show();
        }
        else{
            ActivityCompat.requestPermissions(activity,new String[]{Manifest.permission.READ_CONTACTS},1);
        }
    }
}
