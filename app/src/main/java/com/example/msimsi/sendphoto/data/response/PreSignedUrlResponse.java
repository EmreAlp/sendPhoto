package com.example.msimsi.sendphoto.data.response;

import com.example.msimsi.sendphoto.data.entity.DataItem;
import com.google.gson.annotations.SerializedName;

/**
 * Created by MSIMSI on 1.10.2017.
 * All rights reserved.
 */

public class PreSignedUrlResponse {

	@SerializedName("data")
	private DataItem data;

	public void setData(DataItem data){
		this.data = data;
	}

	public DataItem getData(){
		return data;
	}

	@Override
 	public String toString(){
		return 
			"PreSignedUrlResponse{" +
			"data = '" + data + '\'' + 
			"}";
		}
}