package com.example.msimsi.sendphoto.main;

import android.app.Activity;

import com.example.msimsi.sendphoto.data.entity.UploadPhotoItem;

import okhttp3.MultipartBody;

/**
 * Created by MSIMSI on 2.10.2017.
 * All rights reserved.
 */
public interface MainContract {

    interface View {

        void onShowPermisson();

        void giveLocationInfo(Double latitude, Double longitude, String city);

        void sendRequestBody(String url);

        void showToast();

        void sendFile();

    }

    interface presenter {

        void onUploadPhotoClicked();

        void onTakePhotoClicked();

        void showWarning();

        void fetchLocationInfo(Activity activity);

        void postPhoto(UploadPhotoItem uploadPhotoItem);

        void postPhotoToUploadsIm(MultipartBody.Part body);
    }
}
