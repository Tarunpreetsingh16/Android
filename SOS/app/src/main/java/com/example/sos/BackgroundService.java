package com.example.sos;

import android.Manifest;
import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.IBinder;
import android.util.Log;

import androidx.core.content.ContextCompat;


/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class BackgroundService extends Service {
    //create location manager to fetch location of the user
    private LocationManager locationManager = null;

    //set location listeners for gps and network both
    LocationLookout[] locations = new LocationLookout[]{
            new LocationLookout(LocationManager.NETWORK_PROVIDER),
            new LocationLookout(LocationManager.GPS_PROVIDER)
    };
    private void initializeLocationManager() {
        if (locationManager == null) {
            locationManager = (LocationManager) getApplicationContext().getSystemService(Context.LOCATION_SERVICE);
        }
    }

    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        return START_STICKY;
    }

    @Override
    public void onCreate() {
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
                        LocationManager.NETWORK_PROVIDER, 5000, 20f,
                        locations[1]);
            }
        } catch (Exception e){
           e.printStackTrace();
        }
        try {
            //fetch from network
            locationManager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER, 5000, 20f,
                    locations[0]);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //on destroy of the service remove any listeners as GC won't be able to remove them
        if (locationManager != null) {
            for (int i = 0; i < locations.length; i++) {
                try {
                    //try removing all the location updates
                    locationManager.removeUpdates(locations[i]);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
