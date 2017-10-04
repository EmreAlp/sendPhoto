package com.example.msimsi.sendphoto.data.entity;

import com.google.gson.annotations.SerializedName;

public class PreSignedPostItem {

    /**
     * Created by MSIMSI on 1.10.2017.
     * All rights reserved.
     */

    @SerializedName("resource")
    private ResourceItem resource;

    public PreSignedPostItem(ResourceItem resource) {
        this.resource = resource;
    }

    public ResourceItem getResource() {
        return resource;
    }

    public void setResource(ResourceItem resource) {
        this.resource = resource;
    }
}