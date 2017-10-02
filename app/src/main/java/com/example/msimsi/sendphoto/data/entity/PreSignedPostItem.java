package com.example.msimsi.sendphoto.data.entity;

import com.google.gson.annotations.SerializedName;

public class PreSignedPostItem {

    @SerializedName("resource")
    private ResourceItem resource;

    @SerializedName("data")
    private DataItem data;

    public PreSignedPostItem(ResourceItem resource) {
        this.resource = resource;
    }

    public ResourceItem getResource() {
        return resource;
    }

    public void setResource(ResourceItem resource) {
        this.resource = resource;
    }

    public DataItem getData() {
        return data;
    }

    public void setData(DataItem data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "PreSignedPostItem{" +
                ", resource=" + resource +
                '}';
    }
}