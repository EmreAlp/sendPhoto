package com.example.msimsi.sendphoto.data.entity;

import com.google.gson.annotations.SerializedName;

public class PhotoItem {

	@SerializedName("name")
	private String name;

	@SerializedName("location")
	private String location;

	@SerializedName("url")
	private String url;

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setLocation(String location){
		this.location = location;
	}

	public String getLocation(){
		return location;
	}

	public void setUrl(String url){
		this.url = url;
	}

	public String getUrl(){
		return url;
	}

	@Override
 	public String toString(){
		return 
			"PhotoItem{" +
			"name = '" + name + '\'' + 
			",location = '" + location + '\'' + 
			",url = '" + url + '\'' + 
			"}";
		}
}