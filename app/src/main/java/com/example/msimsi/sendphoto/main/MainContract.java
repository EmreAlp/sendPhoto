package com.example.msimsi.sendphoto.main;

import android.app.Activity;

import com.example.msimsi.sendphoto.data.entity.PreSignedPostItem;
import com.example.msimsi.sendphoto.data.response.PreSignedUrlResponse;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Response;

/**
 * Created by MSIMSI on 2.10.2017.
 * All rights reserved.
 */
public interface MainContract {

    interface View {

        void sendResource();

        void onShowPermisson();

        void giveLocationInfo(Double latitude, Double longitude, String city);

        void sendPostParameters(Response<PreSignedUrlResponse> response);

        void showToast();
    }

    interface presenter {

        void onUploadPhotoClicked();

        void onTakePhotoClicked();

        void showWarning();

        void getPreSignedUrl(PreSignedPostItem preSignedPostItem);

        void fetchLocationInfo(Activity activity);

        void postPhoto(Map<String, String> map, RequestBody requestBody, MultipartBody.Part body);
    }
}
