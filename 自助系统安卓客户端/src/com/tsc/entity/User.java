package com.tsc.entity;

import java.io.Serializable;

/**
 * 
 *	用户信息实体类
 */
public class User implements Serializable{

	/*
	 * 用户ID
	 */
	private int ID;
	
	/*
	 * 用户姓名
	 */
	private String name;
	
	/*
	 * 密码
	 */
	private String password;
	
	/*
	 * 身份证
	 */
	private String identity_number;
	
	/*
	 * 手机号码
	 */
	private String phone_number;

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getIdentity_number() {
		return identity_number;
	}

	public void setIdentity_number(String identity_number) {
		this.identity_number = identity_number;
	}

	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

}
