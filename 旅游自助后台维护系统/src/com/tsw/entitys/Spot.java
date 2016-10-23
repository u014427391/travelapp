package com.tsw.entitys;

import java.io.Serializable;
import java.sql.Date;

/**
 * 景点信息的实体类
 */
public class Spot implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/*
	 * 景点ID
	 */
	private int ID;
	
	/*
	 * 位置
	 */
	private String position;
	
	/*
	 * 游览项目
	 */
	private String tour_project;
	
	/*
	 * 介绍
	 */
	private String introduction;
	
	/*
	 * 价钱
	 */
	private double price;
	
	/*
	 * 图片路径
	 */
	private String picture;
	
	/*
	 * 开发时间
	 */
	private Date time;
	
	/*
	 * 景点分类ID
	 */
	private int spot_sort;

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getTour_project() {
		return tour_project;
	}

	public void setTour_project(String tour_project) {
		this.tour_project = tour_project;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public int getSpot_sort() {
		return spot_sort;
	}

	public void setSpot_sort(int spot_sort) {
		this.spot_sort = spot_sort;
	}
	
}
