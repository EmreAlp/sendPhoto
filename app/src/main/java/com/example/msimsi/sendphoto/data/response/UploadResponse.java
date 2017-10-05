package com.example.msimsi.sendphoto.data.response;

import com.example.msimsi.sendphoto.data.entity.DataItem;
import com.google.gson.annotations.SerializedName;

public class UploadResponse{

	@SerializedName("status_code")
	private int statusCode;

	@SerializedName("data")
	private DataItem data;

	@SerializedName("status_txt")
	private String statusTxt;

	public void setStatusCode(int statusCode){
		this.statusCode = statusCode;
	}

	public int getStatusCode(){
		return statusCode;
	}

	public void setData(DataItem data){
		this.data = data;
	}

	public DataItem getData(){
		return data;
	}

	public void setStatusTxt(String statusTxt){
		this.statusTxt = statusTxt;
	}

	public String getStatusTxt(){
		return statusTxt;
	}

	@Override
 	public String toString(){
		return 
			"UploadResponse{" + 
			"status_code = '" + statusCode + '\'' + 
			",data = '" + data + '\'' + 
			",status_txt = '" + statusTxt + '\'' + 
			"}";
		}
}