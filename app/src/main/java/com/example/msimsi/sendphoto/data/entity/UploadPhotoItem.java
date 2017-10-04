package com.example.msimsi.sendphoto.data.entity;

import com.google.gson.annotations.SerializedName;

public class UploadPhotoItem {

    /**
     * Created by MSIMSI on 1.10.2017.
     * All rights reserved.
     */

    @SerializedName("photo")
    private PhotoItem photo;

    public void setPhoto(PhotoItem photo) {
        this.photo = photo;
    }

    public PhotoItem getPhoto() {
        return photo;
    }

    @Override
    public String toString() {
        return
                "UploadPhotoItem{" +
                        "photo = '" + photo + '\'' +
                        "}";
    }
}