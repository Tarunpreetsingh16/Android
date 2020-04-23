package com.example.sos;

import android.location.Location;
import android.os.Bundle;
import android.util.Log;

class LocationLookout implements android.location.LocationListener {
    //value to store the location of the user
    Location location;
    //constructor
    public LocationLookout(String provider) {
        //initialize location
        location = new Location(provider);
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
        //get the location of the user when changed
        this.location.set(location);
        //testing
        Log.i("Testing",String.valueOf(this.location.getLatitude()));
    }
    @Override
    public void onProviderEnabled(String provider) {
        //code when provider enabled
    }


}
