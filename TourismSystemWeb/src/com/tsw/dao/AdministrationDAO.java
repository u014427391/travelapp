package com.tsw.dao;

import com.tsw.entitys.Administration;

public interface AdministrationDAO {

	/**
	 * 旅游局管理员登录验证
	 */
	public String loginCheck(int account,String password);
	
	/**
	 * 通过账号获取用户信息
	 * @param account
	 * 			账号
	 * @return
	 */
	public Administration getInfoById(int account);
}
