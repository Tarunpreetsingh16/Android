package com.example.sos;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.List;
import java.util.Locale;

class LocationLookout implements android.location.LocationListener {
    //value to store the location of the user
    Location location;
    Context context;
    //constructor
    public LocationLookout(String provider,Context context) {
        //initialize location
        location = new Location(provider);
        this.context = context;
    }

    @Override
    public void onProviderDisabled(String provider) {
        //code when provider disabled
    }
    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        //code when status is changed
    }
    @Override
    public void onLocationChanged(Location location) {

        try {
            //get the location of the user when changed

            this.location.set(location);
             //testing
            Log.i("Testing",String.valueOf(this.location.getLatitude()));
            Geocoder geocoder = new Geocoder(context, Locale.getDefault());
            DatabaseHandler dbs = new DatabaseHandler(context);

            List<Address> addresses = geocoder.getFromLocation(this.location.getLatitude(), this.location.getLongitude(), 1);

            LocationDetails locationDetails = new LocationDetails();
            locationDetails.setLatitude(String.valueOf(this.location.getLatitude()));
            locationDetails.setLongitude(String.valueOf(this.location.getLongitude()));
            locationDetails.setCountry(String.valueOf(addresses.get(0).getCountryName()));
            locationDetails.setLocality(String.valueOf(addresses.get(0).getLocality()));
            locationDetails.setAddress(String.valueOf(addresses.get(0).getAddressLine(0)));

            if(dbs.insertLocation(locationDetails)){
                Toast.makeText(context,"Inserted",Toast.LENGTH_LONG).show();
            }
            else{
                Toast.makeText(context,"Insertion failed!",Toast.LENGTH_LONG).show();
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    @Override
    public void onProviderEnabled(String provider) {
        //code when provider enabled
    }
}
