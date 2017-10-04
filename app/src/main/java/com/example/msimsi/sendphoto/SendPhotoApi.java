package com.example.msimsi.sendphoto;


import com.example.msimsi.sendphoto.data.entity.PreSignedPostItem;
import com.example.msimsi.sendphoto.data.response.PhotoResponse;
import com.example.msimsi.sendphoto.data.response.PreSignedUrlResponse;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;

/**
 * Created by MSIMSI on 2.10.2017.
 * All rights reserved.
 */
public interface SendPhotoApi {

//    @GET("/photos/")
//    Call<PhotoResponse> getPhotos(); // No need to use this Call !

    @POST("/pre_signed_posts")
    Call<PreSignedUrlResponse> getPreSignedUrl(@Body PreSignedPostItem preSignedPostModel); // Get Necessary Headers for Photo Upload.

    @Multipart
    @POST("/photos/")
    Call<PhotoResponse> uploadImage(@PartMap Map<String, String> map, @Part("photo") RequestBody description, @Part MultipartBody.Part file); // Multi-part Upload Call (Not Sure !)

}
