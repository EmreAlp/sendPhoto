package com.example.msimsi.sendphoto.data.entity;

import com.google.gson.annotations.SerializedName;

public class DataItem {

	@SerializedName("x-amz-date")
	private String xAmzDate;

	@SerializedName("success_action_status")
	private String successActionStatus;

	@SerializedName("x-amz-signature")
	private String xAmzSignature;

	@SerializedName("acl")
	private String acl;

	@SerializedName("key")
	private String key;

	@SerializedName("x-amz-algorithm")
	private String xAmzAlgorithm;

	@SerializedName("url")
	private String url;

	@SerializedName("Content-Type")
	private String contentType;

	@SerializedName("policy")
	private String policy;

	@SerializedName("x-amz-credential")
	private String xAmzCredential;

	public void setXAmzDate(String xAmzDate){
		this.xAmzDate = xAmzDate;
	}

	public String getXAmzDate(){
		return xAmzDate;
	}

	public void setSuccessActionStatus(String successActionStatus){
		this.successActionStatus = successActionStatus;
	}

	public String getSuccessActionStatus(){
		return successActionStatus;
	}

	public void setXAmzSignature(String xAmzSignature){
		this.xAmzSignature = xAmzSignature;
	}

	public String getXAmzSignature(){
		return xAmzSignature;
	}

	public void setAcl(String acl){
		this.acl = acl;
	}

	public String getAcl(){
		return acl;
	}

	public void setKey(String key){
		this.key = key;
	}

	public String getKey(){
		return key;
	}

	public void setXAmzAlgorithm(String xAmzAlgorithm){
		this.xAmzAlgorithm = xAmzAlgorithm;
	}

	public String getXAmzAlgorithm(){
		return xAmzAlgorithm;
	}

	public void setUrl(String url){
		this.url = url;
	}

	public String getUrl(){
		return url;
	}

	public void setContentType(String contentType){
		this.contentType = contentType;
	}

	public String getContentType(){
		return contentType;
	}

	public void setPolicy(String policy){
		this.policy = policy;
	}

	public String getPolicy(){
		return policy;
	}

	public void setXAmzCredential(String xAmzCredential){
		this.xAmzCredential = xAmzCredential;
	}

	public String getXAmzCredential(){
		return xAmzCredential;
	}

	@Override
 	public String toString(){
		return 
			"DataItem{" +
			"x-amz-date = '" + xAmzDate + '\'' + 
			",success_action_status = '" + successActionStatus + '\'' + 
			",x-amz-signature = '" + xAmzSignature + '\'' + 
			",acl = '" + acl + '\'' + 
			",key = '" + key + '\'' + 
			",x-amz-algorithm = '" + xAmzAlgorithm + '\'' + 
			",url = '" + url + '\'' + 
			",content-Type = '" + contentType + '\'' + 
			",policy = '" + policy + '\'' + 
			",x-amz-credential = '" + xAmzCredential + '\'' + 
			"}";
		}
}