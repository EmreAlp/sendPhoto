package com.example.msimsi.sendphoto;


import com.example.msimsi.sendphoto.data.entity.UploadPhotoItem;
import com.example.msimsi.sendphoto.data.response.PhotoResponse;
import com.example.msimsi.sendphoto.data.response.UploadResponse;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by MSIMSI on 2.10.2017.
 * All rights reserved.
 */
public interface SendPhotoApi {

//    @GET("/photos/")
//    Call<PhotoResponse> getPhotos(); // No need to use this Call !

    @POST("/photos")
    Call<PhotoResponse> uploadImage(@Body UploadPhotoItem uploadPhotoItem);

    @Multipart
    @POST("/api")
    Call<UploadResponse> getFileUrlFromServer(@Part MultipartBody.Part file);

}
