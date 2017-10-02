package com.example.msimsi.sendphoto;


import com.example.msimsi.sendphoto.data.response.PhotoResponse;
import com.example.msimsi.sendphoto.data.entity.PreSignedPostItem;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by MSIMSI on 2.10.2017.
 * Copyright (c) 2017 Kolektif Labs to present
 * All rights reserved.
 */
public interface SendPhotoApi {

    @GET("/photos/")
    Call<PhotoResponse> getPhotos();

    @POST("/photos/")
    Call<PhotoResponse> uploadPhoto(@Body PhotoResponse photoModel);

    @POST("/pre_signed_posts")
    Call<PreSignedPostItem> getPreSignedUrl(@Body PreSignedPostItem preSignedPostModel);
}
