package com.example.msimsi.sendphoto.utility;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by MSIMSI on 12.9.2017.
 * Copyright (c) 2017 Kolektif Labs to present
 * All rights reserved.
 */

public class KLHelpers {

    public static boolean isConnected(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        return networkInfo != null &&
                networkInfo.isAvailable() &&
                networkInfo.isConnected();

    }
}
