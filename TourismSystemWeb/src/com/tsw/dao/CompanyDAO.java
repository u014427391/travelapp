package com.tsw.dao;

import com.tsw.entitys.Company;


public interface CompanyDAO {

	/**
	 * 旅游公司登录验证
	 * @param account
	 * 			账号
	 * @param password
	 * 			密码
	 * @return
	 */
	public String loginCheck(int account,String password);
	
	/**
	 * 通过账号获取用户信息
	 * @param account
	 * 			账号
	 * @return
	 */
	public Company getInfoById(int account);
}
