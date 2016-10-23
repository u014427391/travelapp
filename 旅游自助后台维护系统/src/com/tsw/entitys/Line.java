package com.tsw.entitys;

import java.io.Serializable;
import java.sql.Date;

/**
 * 旅游路线信息的实体类
 */
public class Line implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/*
	 * 旅游路线的ID 
	 */
	private int ID;
	
	/*
	 * 景点名称
	 */
	private String name;
	
	/*
	 * 餐饮
	 */
	private String catering;
	
	/*
	 * 住宿
	 */
	private String accommodation;
	
	/*
	 * 交通
	 */
	private String traffic;
	
	/*
	 * 价钱
	 */
	private double price;
	
	/*
	 * 联系方式
	 */
	private String contact;
	
	/*
	 * 图片路径
	 */
	private String picture;
	
	/*
	 * 出游时间
	 */
	private Date tourism_time;

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCatering() {
		return catering;
	}

	public void setCatering(String catering) {
		this.catering = catering;
	}

	public String getAccommodation() {
		return accommodation;
	}

	public void setAccommodation(String accommodation) {
		this.accommodation = accommodation;
	}

	public String getTraffic() {
		return traffic;
	}

	public void setTraffic(String traffic) {
		this.traffic = traffic;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public Date getTourism_time() {
		return tourism_time;
	}

	public void setTourism_time(Date tourism_time) {
		this.tourism_time = tourism_time;
	}

}
