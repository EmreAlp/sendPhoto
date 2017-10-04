package com.example.msimsi.sendphoto.utility;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

/**
 * Created by MSIMSI on 3.10.2017.
 * All rights reserved.
 */

public class CheckPermission {

    //  CHECK FOR LOCATION PERMISSION
    public static boolean checkPermission(Activity activity) {
        int result = ContextCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION);
        if (result == PackageManager.PERMISSION_GRANTED) {

            return true;

        } else {

            return false;

        }
    }

    //REQUEST FOR PERMISSSION
    public static void requestPermission(Activity activity, final int code) {

            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, code);
    }

}