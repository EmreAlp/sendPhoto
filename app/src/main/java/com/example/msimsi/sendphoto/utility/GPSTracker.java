package com.example.msimsi.sendphoto.utility;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Toast;

/**
 * Created by MSIMSI on 2.10.2017.
 * All rights reserved.
 */

public class GPSTracker implements LocationListener {

    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    Context context;

    public GPSTracker(Context context) {
        this.context = context;
    }

    public Location getLocation() {

        LocationManager lm = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        boolean isGPSEnabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
        Location l = null;
        if (isGPSEnabled) {
            try {
                lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 60000, 10, this);
                l = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            } catch (Exception e) {
                Toast.makeText(context, "Please enable GPS", Toast.LENGTH_LONG).show();
            }
            return l;
        } else {
            Toast.makeText(context, "Please enable GPS", Toast.LENGTH_LONG).show();
        }
        return null;
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
