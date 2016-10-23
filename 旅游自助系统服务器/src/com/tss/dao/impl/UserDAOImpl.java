package com.tss.dao.impl;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import com.tss.dao.UserDAO;
import com.tss.po.Tourist;
import com.tss.po.User;
import com.tss.utils.DBHelperUtil;


public class UserDAOImpl implements UserDAO {

	DBHelperUtil manager;
	String sql = "";
	ResultSet rs;
	
	/**
	 * 登录验证
	 */
	public boolean loginCheck(int account,String password){
		boolean flag = false;
		
		sql = "select * from user where ID=?";
		try{
			manager = DBHelperUtil.createInstance();
			manager.connectDB();
			Object[] params = new Object[]{account}; 
			rs = manager.executeQuery(sql, params);
			if(rs.next()){
				String pwd = rs.getString("password");
				if(password.equals(pwd)){
					flag = true;
				}else{
					flag = false;
				}
			}else{
				flag = false;
			}
			manager.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
			
		return flag;
		

	}

	/**
	 * 会员注册
	 */
	public void register(User user) {
		
		sql = "insert into user(name,password,identity_number,phone_number) " +
				"values(?,?,?,?)";
		
		try {
			
			manager = DBHelperUtil.createInstance();
			manager.connectDB();
			
			Object[] params = new Object[]{
					user.getName(),
					user.getPassword(),
					user.getIdentity_number(),
					user.getPhone_number()
			};
			
			manager.executeUpdate(sql, params);
			manager.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public User getInfoById(int account) {
		User user = new User();
		try {
			manager = DBHelperUtil.createInstance();
			manager.connectDB();
			String sql = "select * from user where ID=?";
			Object params[] = new Object[]{account};
			rs = manager.executeQuery(sql, params);
			while(rs.next()){
				int ID = rs.getInt("ID");
				String name = rs.getString("name");
				String password = rs.getString("password");
				String identity_number = rs.getString("identity_number");
				String phone_number = rs.getString("phone_number");
				user.setID(ID);
				user.setName(name);
				user.setPassword(password);
				user.setIdentity_number(identity_number);
				user.setPhone_number(phone_number);
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return user;
	}

}
