package com.tss.dao;

import com.tss.po.User;


public interface UserDAO {

	/**
	 * 登录验证
	 */
	public boolean loginCheck(int account,String password);
	
	/**
	 * 用户注册
	 */
	public void register(User user);
	
	/**
	 * 根据账号获取用户的所有信息
	 * @param account
	 * 			用户账号
	 * @return 
	 */
	public User getInfoById(int account);
	
}
