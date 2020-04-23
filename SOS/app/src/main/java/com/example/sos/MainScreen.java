package com.example.sos;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainScreen extends AppCompatActivity {
    private SensorManager sensorManager;
    private float acceleration;
    private float currentAcceleration;
    private float lastAcceleration;
    ImageButton btnFirstAdd;
    ImageButton btnSecondAdd;
    ImageButton btnThirdAdd;
    ImageButton btnFirstRemove;
    ImageButton btnSecondRemove;
    ImageButton btnThirdRemove;
    TextView txtFirstName;
    TextView txtSecondName;
    TextView txtThirdName;
    TextView txtFirstNumber;
    TextView txtSecondNumber;
    TextView txtThirdNumber;
    LinearLayout contact1;
    LinearLayout contact2;
    LinearLayout contact3;
    int check = 1;
    String contactData[];
    List<Contact> contacts;
    SharedPreferences.Editor editor;
    Gson gson;
    //    //create location manager to fetch location of the user
    private LocationManager locationManager = null;
    //set location listeners for gps and network both
    LocationLookout[] locations = new LocationLookout[]{
            new LocationLookout(LocationManager.NETWORK_PROVIDER,this),
            new LocationLookout(LocationManager.GPS_PROVIDER,this)};

    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_screen);
        startLocationServices();
        getSupportActionBar().hide();
        initializeData();
        //setup select contact functionality for all 3 contacts
        setupSelectContact();
        setupShakeFeature();
    }

    private void setupShakeFeature() {
        //setup  shake feature to send the message
        sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        //get accelerometer sensor of the mobile
        Objects.requireNonNull(sensorManager).registerListener(sensorListener,sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL);
        acceleration = 10f;
        //store previous acceleration to match if phone is really shaking or not
        currentAcceleration = SensorManager.GRAVITY_EARTH;
        lastAcceleration = SensorManager.GRAVITY_EARTH;
    }
//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event){
//        if(keyCode == KeyEvent.KEYCODE_VOLUME_UP)
//    }
    //creating event of the accelerometer
    private final SensorEventListener sensorListener = new SensorEventListener(){

        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {
            List<LocationDetails> locationDetails = new ArrayList<LocationDetails>();
            //fetch x y z values of the accelerometer
            float x = sensorEvent.values[0];
            float y = sensorEvent.values[1];
            float z = sensorEvent.values[2];
            //store last acceleration
            lastAcceleration = currentAcceleration;
            //find interpolation of the acceleration
            currentAcceleration = (float) Math.sqrt((double)(x*x + y*y + z*z));
            //find the difference between last and current acceleration
            float delta = currentAcceleration - lastAcceleration;
            acceleration = acceleration * 0.9f +delta;
            if(acceleration > 30){
                try {
                    DatabaseHandler dbh = new DatabaseHandler(MainScreen.this);
                    Cursor crs = dbh.getData();
                    if (crs != null) {
                        crs.moveToFirst();
                        while (crs.moveToNext()) {
                            Log.i("counter", String.valueOf(crs.getCount()));
                            LocationDetails locationDetail = new LocationDetails();
                            locationDetail.setLongitude(crs.getString(crs.getColumnIndex("longitude")));
                            locationDetail.setLatitude(crs.getString(crs.getColumnIndex("latitude")));
                            locationDetail.setCountry(crs.getString(crs.getColumnIndex("country")));
                            locationDetail.setLocality(crs.getString(crs.getColumnIndex("locality")));
                            locationDetail.setAddress(crs.getString(crs.getColumnIndex("address")));
                            locationDetail.setDate(crs.getString(crs.getColumnIndex("date")));
                            //add it to the list
                            locationDetails.add(locationDetail);
                        }
                        MessageHandler messageHandler = new MessageHandler();
                        //no contacts on list
                        if (contacts.size() < 1) {
                            Toast.makeText(MainScreen.this,"Add contacts first!",Toast.LENGTH_LONG).show();
                            return;
                        }
                        messageHandler.sendMessages(locationDetails,contacts);
                        Toast.makeText(MainScreen.this,"Alert Sent!",Toast.LENGTH_LONG).show();
                    }
                }
                catch(Exception e){
                    Log.i("Message",e.getMessage());
                }
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {}
    };

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(sensorListener);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(sensorListener,sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL);
    }

    private void startLocationServices() {
        initializeLocationManager();
        try {
            //check if user granted the permission
            if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                //start requesting location update from the manager
                Log.i("Info","No permission granted!");
            }
            else {
                //if yes then fetch from GPS
                locationManager.requestLocationUpdates(
                        LocationManager.NETWORK_PROVIDER, 20000, 30f,
                        locations[1]);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        try {
            //fetch from network
            locationManager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER, 20000, 30f,
                    locations[0]);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private void initializeData() {
        btnFirstAdd = findViewById(R.id.btnAddFirst);
        btnSecondAdd= findViewById(R.id.btnAddSecond);
        btnThirdAdd = findViewById(R.id.btnAddThird);
        btnFirstRemove = findViewById(R.id.btnRemoveFirst);
        btnSecondRemove = findViewById(R.id.btnRemoveSecond);
        btnThirdRemove = findViewById(R.id.btnRemoveThird);
        txtFirstName = findViewById(R.id.txtFirstName);
        txtSecondName = findViewById(R.id.txtSecondName);
        txtThirdName = findViewById(R.id.txtThirdName);
        txtFirstNumber = findViewById(R.id.txtFirstNumber);
        txtSecondNumber = findViewById(R.id.txtSecondNumber);
        txtThirdNumber = findViewById(R.id.txtThirdNumber);
        contact1 = findViewById(R.id.firstContact);
        contact2 = findViewById(R.id.secondContact);
        contact3 = findViewById(R.id.thirdContact);
        sharedPreferences = getSharedPreferences(getString(R.string.app_pref),MODE_PRIVATE);
        contacts = new ArrayList<Contact>();
        editor = sharedPreferences.edit();
        gson = new Gson();
        setContactList();

    }
    private void initializeLocationManager() {
        try {
            if (locationManager == null) {
                locationManager = (LocationManager) getApplicationContext().getSystemService(Context.LOCATION_SERVICE);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private void setContactList() {
        //get already added contacts from the share pref
        String jsonData = sharedPreferences.getString(getString(R.string.contacts),"");
        //check if there are any contacts
        if(!jsonData.isEmpty())
        {
            Type type = new TypeToken<List<Contact>>(){}.getType();
            contacts = gson.fromJson(jsonData,type);
            Log.i("Contacts",String.valueOf(contacts.size()));
            contactData = new String[2];
            //set the data to the
            if(contacts.size() == 1){
                contactData[0] = contacts.get(0).getName();
                contactData[1] = contacts.get(0).getNumber();
                changeAttributesOnAdd(txtFirstName,txtFirstNumber,contact1,btnFirstAdd,btnFirstRemove);
            }
            else if(contacts.size() == 2){
                contactData[0] = contacts.get(0).getName();
                contactData[1] = contacts.get(0).getNumber();
                changeAttributesOnAdd(txtFirstName,txtFirstNumber,contact1,btnFirstAdd,btnFirstRemove);


                contactData[0] = contacts.get(1).getName();
                contactData[1] = contacts.get(1).getNumber();
                changeAttributesOnAdd(txtSecondName,txtSecondNumber,contact2,btnSecondAdd,btnSecondRemove);
            }
            else if(contacts.size() == 3){
                contactData[0] = contacts.get(0).getName();
                contactData[1] = contacts.get(0).getNumber();
                changeAttributesOnAdd(txtFirstName,txtFirstNumber,contact1,btnFirstAdd,btnFirstRemove);


                contactData[0] = contacts.get(1).getName();
                contactData[1] = contacts.get(1).getNumber();
                changeAttributesOnAdd(txtSecondName,txtSecondNumber,contact2,btnSecondAdd,btnSecondRemove);

                contactData[0] = contacts.get(2).getName();
                contactData[1] = contacts.get(2).getNumber();
                changeAttributesOnAdd(txtThirdName,txtThirdNumber,contact3,btnThirdAdd,btnThirdRemove);
            }
        }
    }

    private void setupSelectContact() {
       //sertup add and remove button functionality
       setupFirstContact();
       setupSecondContact();
       setupThirdContact();
   }

    private void setupFirstContact() {
        btnFirstAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pickContact();
                //set, the first contact add button was clicked
                check = 1;
            }
        });
        btnFirstRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //remove from shared preferences
                removeFromSharedPreferences(txtFirstNumber);
                //change attributes on removing a contact
                changeAttributesOnRemove(txtFirstName,txtFirstNumber,contact1,btnFirstAdd,btnFirstRemove);
            }
        });
    }

    private void setupSecondContact() {
        btnSecondAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pickContact();
                //set the first contact add button was clicked
                check = 2;
            }
        });
        btnSecondRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //remove from shared preferences
                removeFromSharedPreferences(txtSecondNumber);
                //change attributes on removing a contact
                changeAttributesOnRemove(txtSecondName,txtSecondNumber,contact2,btnSecondAdd,btnSecondRemove);
            }
        });
    }

    private void setupThirdContact() {
        btnThirdAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pickContact();
                //set the third contact add button was clicked
                check = 3;
            }
        });
        btnThirdRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //remove from shared preferences
                removeFromSharedPreferences(txtThirdNumber);
                //change attributes on removing a contact
                changeAttributesOnRemove(txtThirdName,txtThirdNumber,contact3,btnThirdAdd,btnThirdRemove);
            }
        });
    }

    @Override
   protected void onActivityResult(int requestCode, int resultCode, Intent data) {
       super.onActivityResult(requestCode, resultCode, data);

       //check the result code
       if(requestCode == 1 && resultCode == RESULT_OK){
           ContactsHandler handler = new ContactsHandler(this);
           contactData = handler.getContactDetails(data.getData());
           //check if  contact was already added to the list
           for(int i=0;i<contacts.size();i++){
               if(contacts.get(i).getNumber().equals( contactData[1])){
                   Log.i("Contacts","There");
                   Toast.makeText(this,"Contact already added!",Toast.LENGTH_LONG).show();
                   return;
               }
           }
           if(check == 1){
               //change attributes of UI elements
               changeAttributesOnAdd(txtFirstName,txtFirstNumber,contact1,btnFirstAdd,btnFirstRemove);
              //store in shared preference
               storeSharedPreferences();
           }
           else if(check == 2){
               //change attributes of UI elements
               changeAttributesOnAdd(txtSecondName,txtSecondNumber,contact2,btnSecondAdd,btnSecondRemove);
               //store in shared preference
               storeSharedPreferences();
           }
           else if(check == 3){
               //change attributes of UI elements
               changeAttributesOnAdd(txtThirdName,txtThirdNumber,contact3,btnThirdAdd,btnThirdRemove);
               //store in shared preference
               storeSharedPreferences();
           }
       }
   }

   public void storeSharedPreferences(){
       Contact contact =  new Contact();
       contact.setName(contactData[0]);
       contact.setNumber(contactData[1]);
       //add it to the list
       contacts.add(contact);
       String jsonData = gson.toJson(contacts);
       editor.putString(getString(R.string.contacts),jsonData);
       editor.commit();
   }

   public void pickContact(){
       Intent contactPicker = new Intent(Intent.ACTION_PICK, ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
       startActivityForResult(contactPicker,1);
   }

   public void changeAttributesOnAdd(TextView name, TextView number, LinearLayout contact, ImageButton add, ImageButton remove){
        //set data
       name.setText(contactData[0]);
       number.setText(contactData[1]);
       //change visibility
       contact.setVisibility(View.VISIBLE);
       add.setVisibility(View.GONE);
       remove.setVisibility(View.VISIBLE);
   }
   public void changeAttributesOnRemove(TextView name,TextView number, LinearLayout contact, ImageButton add, ImageButton remove){
       //set data
       name.setText("");
       number.setText("");
       //change visibility
       contact.setVisibility(View.GONE);
       add.setVisibility(View.VISIBLE);
       remove.setVisibility(View.GONE);
   }
    private void removeFromSharedPreferences(TextView txtNumber) {

        for(int i= 0;i<contacts.size();i++){
            if(txtNumber.getText().toString().equals(contacts.get(i).getNumber())){
                contacts.remove(i);
            }
        }

        //update shared pref
        String jsonData = gson.toJson(contacts);
        editor.putString(getString(R.string.contacts),jsonData);
        editor.commit();
    }


}
