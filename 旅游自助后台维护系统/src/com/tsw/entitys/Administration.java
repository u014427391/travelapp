package com.tsw.entitys;

import java.io.Serializable;

/**
 * 旅游局信息的实体类
 */
public class Administration implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/*
	 * 旅游局账号
	 */
	private int tcaID;
	
	/*
	 * 旅游局名称
	 */
	private String tcaName;
	
	/*
	 * 旅游局地点
	 */
	private String tcaAddress;
	
	/*
	 * 旅游局管理范围
	 */
	private String tcaScope;
	
	/*
	 * 旅游局密码
	 */
	private String tcaPassword;
	
	/*
	 * 旅游局身份认证ID
	 */
	private int identify;

	public int getTcaID() {
		return tcaID;
	}

	public void setTcaID(int tcaID) {
		this.tcaID = tcaID;
	}

	public String getTcaName() {
		return tcaName;
	}

	public void setTcaName(String tcaName) {
		this.tcaName = tcaName;
	}

	public String getTcaAddress() {
		return tcaAddress;
	}

	public void setTcaAddress(String tcaAddress) {
		this.tcaAddress = tcaAddress;
	}

	public String getTcaScope() {
		return tcaScope;
	}

	public void setTcaScope(String tcaScope) {
		this.tcaScope = tcaScope;
	}

	public String getTcaPassword() {
		return tcaPassword;
	}

	public void setTcaPassword(String tcaPassword) {
		this.tcaPassword = tcaPassword;
	}

	public int getIdentify() {
		return identify;
	}

	public void setIdentify(int identify) {
		this.identify = identify;
	}

	
}
