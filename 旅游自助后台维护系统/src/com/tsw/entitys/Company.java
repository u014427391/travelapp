package com.tsw.entitys;

import java.io.Serializable;

/**
 * 旅游公司信息的实体类
 */
public class Company implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/*
	 * 公司ID
	 */
	private int tcID;
	
	/*
	 * 公司姓名
	 */
	private String tcName;
	
	/*
	 * 公司地点
	 */
	private String tcAddress;
	
	/*
	 * 公司范围
	 */
	private String tcScope;
	
	/*
	 * 公司密码
	 */
	private String tcPassword;
	
	/*
	 * 管理员身份认证ID
	 */
	private int identify;

	public int getTcID() {
		return tcID;
	}

	public void setTcID(int tcID) {
		this.tcID = tcID;
	}

	public String getTcName() {
		return tcName;
	}

	public void setTcName(String tcName) {
		this.tcName = tcName;
	}

	public String getTcAddress() {
		return tcAddress;
	}

	public void setTcAddress(String tcAddress) {
		this.tcAddress = tcAddress;
	}

	public String getTcScope() {
		return tcScope;
	}

	public void setTcScope(String tcScope) {
		this.tcScope = tcScope;
	}

	public String getTcPassword() {
		return tcPassword;
	}

	public void setTcPassword(String tcPassword) {
		this.tcPassword = tcPassword;
	}

	public int getIdentify() {
		return identify;
	}

	public void setIdentify(int identify) {
		this.identify = identify;
	}

	
	
}
