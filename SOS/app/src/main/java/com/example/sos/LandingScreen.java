package com.example.sos;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class LandingScreen extends AppCompatActivity {
    Button btnAccessLocation ;
    Button btnAccessContacts;
    boolean contactsAccessCheck = true;
    boolean locationAccessCheck = true;
    PermissionHandler permissionHandler;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.landing_screen);

        //initialize variables and setup button functionality
        initializeData();
        //check if required access is provided
        checkAccess();
        decisionMaker();
        //attach on click functionalities to the buttons
        setupButtons();
//        setupSelectContact();
    }

    private void setupButtons() {
        //ask for the contacts access
        btnAccessContacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                permissionHandler.requestContactPermission();
            }
        });
        //ask for the location access
        btnAccessLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                permissionHandler.requestLocationPermission();
            }
        });
    }

    private void checkAccess() {
        //check if permission is provided by the user for contacts
        String permission = Manifest.permission.READ_CONTACTS;
        int res = checkCallingOrSelfPermission(permission);
        if(res != PackageManager.PERMISSION_GRANTED){
            contactsAccessCheck = false;
            Toast.makeText(this,"Contact Access is required!",Toast.LENGTH_SHORT).show();
        }
//        //check if permission is provided by the user for location

        permission = Manifest.permission.ACCESS_FINE_LOCATION;
        res = checkCallingOrSelfPermission(permission);
        if(res != PackageManager.PERMISSION_GRANTED){
           locationAccessCheck = false;
           Toast.makeText(this,"Location Access is required!",Toast.LENGTH_SHORT).show();
       }
    }

    private void decisionMaker(){
        if(contactsAccessCheck && locationAccessCheck){
            //go to next screen of the app
            Intent mainScreenIntent = new Intent(this,MainScreen.class);
            startActivity(mainScreenIntent);
            finish();
        }
    }

    public void initializeData(){
        btnAccessContacts = findViewById(R.id.btnAccessContacts);
        btnAccessLocation = findViewById(R.id.btnAccessLocation);
        permissionHandler = new PermissionHandler(this);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch (requestCode){
            case 1:
                if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    contactsAccessCheck = true;
                    decisionMaker();
                }
                break;
            case 2:
                if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    locationAccessCheck = true;
                    decisionMaker();
                }
        }
    }
}
