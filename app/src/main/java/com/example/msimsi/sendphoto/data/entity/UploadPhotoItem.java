package com.example.msimsi.sendphoto.data.entity;

import com.google.gson.annotations.SerializedName;

public class UploadPhotoItem{

	@SerializedName("photo")
	private PhotoItem photo;

	public void setPhoto(PhotoItem photo){
		this.photo = photo;
	}

	public PhotoItem getPhoto(){
		return photo;
	}

	@Override
 	public String toString(){
		return 
			"UploadPhotoItem{" + 
			"photo = '" + photo + '\'' + 
			"}";
		}
}