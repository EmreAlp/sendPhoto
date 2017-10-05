package com.example.msimsi.sendphoto.main;

import android.app.Activity;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.util.Log;

import com.example.msimsi.sendphoto.RetrofitSingleton;
import com.example.msimsi.sendphoto.SendPhotoApi;
import com.example.msimsi.sendphoto.data.entity.UploadPhotoItem;
import com.example.msimsi.sendphoto.data.response.PhotoResponse;
import com.example.msimsi.sendphoto.data.response.UploadResponse;
import com.example.msimsi.sendphoto.utility.GPSTracker;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import okhttp3.MultipartBody;
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
    private SendPhotoApi api2;

    MainPresenter(MainContract.View view) {

        this.view = view;

        api = RetrofitSingleton.getInstance(RetrofitSingleton.BASE_URL);
        api2 = RetrofitSingleton.getInstance(RetrofitSingleton.BASE_URL2);
    }

    @Override
    public void onUploadPhotoClicked() {
        view.sendFile();
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
    public void postPhoto(UploadPhotoItem uploadPhotoItem) {

        Call<PhotoResponse> call = api.uploadImage(uploadPhotoItem);

        call.enqueue(new Callback<PhotoResponse>() {
            @Override
            public void onResponse(Call<PhotoResponse> call,
                                   Response<PhotoResponse> response) {

                Log.e("id", String.valueOf(response.body().getId()));
                Log.e("name", response.body().getName());
                Log.e("location", response.body().getLocation());
                Log.e("url", response.body().getUrl());
                Log.e("cratedAt", response.body().getCreatedAt());
                Log.e("updatedAt", response.body().getUpdatedAt());
            }

            @Override
            public void onFailure(Call<PhotoResponse> call, Throwable t) {

                Log.e("Upload error:", t.getMessage());
            }
        });
    }  // Call for Upload Image to Amazon S3 Server

    @Override
    public void postPhotoToUploadsIm(MultipartBody.Part body) {

        Call<UploadResponse> call = api2.getFileUrlFromServer(body);

        call.enqueue(new Callback<UploadResponse>() {
            @Override
            public void onResponse(Call<UploadResponse> call, Response<UploadResponse> response) {

                if (response.body() != null && response.body().getStatusCode() == 200) {
                    view.sendRequestBody(response.body().getData().getImgUrl());
                }
            }

            @Override
            public void onFailure(Call<UploadResponse> call, Throwable t) {

            }
        });

    }

    public void onUploadImageClicked() {
        view.sendFile();
    }
}
