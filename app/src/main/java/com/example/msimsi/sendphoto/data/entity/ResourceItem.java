package com.example.msimsi.sendphoto.data.entity;

import com.google.gson.annotations.SerializedName;

public class ResourceItem {

	@SerializedName("content_type")
	private String contentType;

	public void setContentType(String contentType){
		this.contentType = contentType;
	}

	public String getContentType(){
		return contentType;
	}

	@Override
 	public String toString(){
		return 
			"ResourceItem{" +
			"content_type = '" + contentType + '\'' + 
			"}";
		}
}