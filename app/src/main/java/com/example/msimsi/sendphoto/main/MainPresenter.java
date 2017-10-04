package com.example.msimsi.sendphoto.main;

import android.app.Activity;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.util.Log;

import com.example.msimsi.sendphoto.RetrofitSingleton;
import com.example.msimsi.sendphoto.SendPhotoApi;
import com.example.msimsi.sendphoto.data.entity.PreSignedPostItem;
import com.example.msimsi.sendphoto.data.response.PhotoResponse;
import com.example.msimsi.sendphoto.data.response.PreSignedUrlResponse;
import com.example.msimsi.sendphoto.utility.GPSTracker;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by MSIMSI on 2.10.2017.
 * All rights reserved.
 */


class MainPresenter implements MainContract.presenter {

    private final MainContract.View view;
    private SendPhotoApi api;

    MainPresenter(MainContract.View view) {

        this.view = view;
        api = RetrofitSingleton.getInstance();
    }

    @Override
    public void onUploadPhotoClicked() {
        view.sendResource();
    }

    @Override
    public void onTakePhotoClicked() {
        view.onShowPermisson();
    }

    @Override
    public void showWarning() {
        view.showToast();
    }

    @Override
    public void getPreSignedUrl(PreSignedPostItem preSignedPostItem) {

        Call<PreSignedUrlResponse> call = api.getPreSignedUrl(preSignedPostItem);

        call.enqueue(new Callback<PreSignedUrlResponse>() {
            @Override
            public void onResponse(Call<PreSignedUrlResponse> call, Response<PreSignedUrlResponse> response) {

                Log.v("Success", "Fetched Pre Signed Url Headers");
                view.sendPostParameters(response);
            }

            @Override
            public void onFailure(Call<PreSignedUrlResponse> call, Throwable t) {

                Log.e("Failure", t.toString());
            }
        });
    } // Get Necessary Headers for Post Photo

    @Override
    public void fetchLocationInfo(Activity activity) {

        GPSTracker gpsTracker = new GPSTracker(activity.getApplicationContext());
        Location l = gpsTracker.getLocation();

        Double userLatitude = 0.0;
        Double userLongitude = 0.0;

        if (l != null) {
            userLatitude = l.getLatitude();
            userLongitude = l.getLongitude();

            Log.v("User Location : ", userLatitude + " " + userLongitude);
        }

        String city;

        Geocoder gcd = new Geocoder(activity.getApplicationContext(), Locale.getDefault());
        List<Address> addresses = null;

        try {
            addresses = gcd.getFromLocation(userLatitude, userLongitude, 1);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (addresses != null && addresses.size() > 0) {
            city = addresses.get(0).getAdminArea();

            Log.v("User City : ", city);
        } else {
            city = "No City";
        }

        view.giveLocationInfo(userLatitude, userLongitude, city);
    }  // Location and City Provider

    @Override
    public void postPhoto(Map<String, String> map, RequestBody requestBody, MultipartBody.Part body) {

        Call<PhotoResponse> call = api.uploadImage(map, requestBody, body);

        call.enqueue(new Callback<PhotoResponse>() {
            @Override
            public void onResponse(Call<PhotoResponse> call,
                                   Response<PhotoResponse> response) {

                if (response.body() == null) {
                    Log.e("Error", "There is a response but the body is empty");
                }
            }

            @Override
            public void onFailure(Call<PhotoResponse> call, Throwable t) {

                Log.e("Upload error:", t.getMessage());
            }
        });
    }  // Call for Upload Image to Amazon S3 Server
}
