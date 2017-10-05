package com.example.msimsi.sendphoto.data.entity;

import com.google.gson.annotations.SerializedName;

public class DataItem {

	@SerializedName("thumb_url")
	private String thumbUrl;

	@SerializedName("img_bytes")
	private int imgBytes;

	@SerializedName("img_attr")
	private String imgAttr;

	@SerializedName("img_width")
	private String imgWidth;

	@SerializedName("thumb_width")
	private int thumbWidth;

	@SerializedName("img_height")
	private String imgHeight;

	@SerializedName("thumb_height")
	private int thumbHeight;

	@SerializedName("img_size")
	private String imgSize;

	@SerializedName("source")
	private String source;

	@SerializedName("img_name")
	private String imgName;

	@SerializedName("img_url")
	private String imgUrl;

	@SerializedName("resized")
	private String resized;

	@SerializedName("img_view")
	private String imgView;

	@SerializedName("delete_key")
	private String deleteKey;

	public void setThumbUrl(String thumbUrl){
		this.thumbUrl = thumbUrl;
	}

	public String getThumbUrl(){
		return thumbUrl;
	}

	public void setImgBytes(int imgBytes){
		this.imgBytes = imgBytes;
	}

	public int getImgBytes(){
		return imgBytes;
	}

	public void setImgAttr(String imgAttr){
		this.imgAttr = imgAttr;
	}

	public String getImgAttr(){
		return imgAttr;
	}

	public void setImgWidth(String imgWidth){
		this.imgWidth = imgWidth;
	}

	public String getImgWidth(){
		return imgWidth;
	}

	public void setThumbWidth(int thumbWidth){
		this.thumbWidth = thumbWidth;
	}

	public int getThumbWidth(){
		return thumbWidth;
	}

	public void setImgHeight(String imgHeight){
		this.imgHeight = imgHeight;
	}

	public String getImgHeight(){
		return imgHeight;
	}

	public void setThumbHeight(int thumbHeight){
		this.thumbHeight = thumbHeight;
	}

	public int getThumbHeight(){
		return thumbHeight;
	}

	public void setImgSize(String imgSize){
		this.imgSize = imgSize;
	}

	public String getImgSize(){
		return imgSize;
	}

	public void setSource(String source){
		this.source = source;
	}

	public String getSource(){
		return source;
	}

	public void setImgName(String imgName){
		this.imgName = imgName;
	}

	public String getImgName(){
		return imgName;
	}

	public void setImgUrl(String imgUrl){
		this.imgUrl = imgUrl;
	}

	public String getImgUrl(){
		return imgUrl;
	}

	public void setResized(String resized){
		this.resized = resized;
	}

	public String getResized(){
		return resized;
	}

	public void setImgView(String imgView){
		this.imgView = imgView;
	}

	public String getImgView(){
		return imgView;
	}

	public void setDeleteKey(String deleteKey){
		this.deleteKey = deleteKey;
	}

	public String getDeleteKey(){
		return deleteKey;
	}

	@Override
	public String toString() {

		return "DataItem{" +
				"thumbUrl='" + thumbUrl + '\'' +
				", imgBytes=" + imgBytes +
				", imgAttr='" + imgAttr + '\'' +
				", imgWidth='" + imgWidth + '\'' +
				", thumbWidth=" + thumbWidth +
				", imgHeight='" + imgHeight + '\'' +
				", thumbHeight=" + thumbHeight +
				", imgSize='" + imgSize + '\'' +
				", source='" + source + '\'' +
				", imgName='" + imgName + '\'' +
				", imgUrl='" + imgUrl + '\'' +
				", resized='" + resized + '\'' +
				", imgView='" + imgView + '\'' +
				", deleteKey='" + deleteKey + '\'' +
				'}';
	}
}