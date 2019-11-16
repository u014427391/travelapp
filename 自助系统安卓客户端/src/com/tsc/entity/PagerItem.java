package com.tsc.entity;

public class PagerItem {
	private String picture_url;
	private String title;
	
	
	public PagerItem(String picture_url, String title) {
		super();
		this.picture_url = picture_url;
		this.title = title;
	}
	public String getPicture_url() {
		return picture_url;
	}
	public void setPicture_url(String picture_url) {
		this.picture_url = picture_url;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	
}
