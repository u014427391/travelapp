package com.tsc.entity;

import java.io.Serializable;


/**
 * 游客信息的实体类
 */
public class Tourist implements Serializable{

	/*
	 * ID号,主键
	 */
	private int ID;
	
	/*
	 * 订单ID号，外键
	 */
	private int order_id;
	
	/*
	 * 游客姓名
	 */
	private String name;
	
	/*
	 * 身份证号
	 */
	private String identity_number;

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public int getOrder_id() {
		return order_id;
	}

	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdentity_number() {
		return identity_number;
	}

	public void setIdentity_number(String identity_number) {
		this.identity_number = identity_number;
	}
	
}
