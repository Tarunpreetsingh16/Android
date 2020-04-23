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

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class LandingScreen extends AppCompatActivity {
    Button btnContactSelection ;
    PermissionHandler permissionHandler;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.landing_screen);

        //intialize variables and setup button functionality
        initializeData();
        setupSelectContact();
    }
    public void initializeData(){
        btnContactSelection = findViewById(R.id.btnSelectContact);
        permissionHandler = new PermissionHandler(getWindow().getDecorView().getRootView(),getApplicationContext(),this);
    }
    private void setupSelectContact() {
        //when select contact button is selected
        btnContactSelection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //initialize contact selection process
                //check if permission is granted
                if(ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED){
                    pickContact();
                }
                else
                {//if not granted then request for permission
                    permissionHandler.requestPersmission();
                }
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //check the result code
        if(requestCode == 1 && resultCode == RESULT_OK){
            ContactsHandler handler = new ContactsHandler(this);
            String contactData[] = handler.getContactDetails(data.getData());

//            for(int i=0;i<contactData.length;i++){
//                Log.i("Data",contactData[i]);
//            }
        }
    }

    public void pickContact(){
        Intent contactPicker = new Intent(Intent.ACTION_PICK, ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
        startActivityForResult(contactPicker,1);
    }
}
