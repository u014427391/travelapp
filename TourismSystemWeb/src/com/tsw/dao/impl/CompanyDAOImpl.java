package com.tsw.dao.impl;

import java.io.IOException;
import java.sql.ResultSet;

import com.tsw.dao.CompanyDAO;
import com.tsw.entitys.Company;
import com.tsw.utils.DBHelper;

public class CompanyDAOImpl implements CompanyDAO{

	DBHelper manager;
	ResultSet rs;
	String sql = "";
	
	/**
	 * 旅游公司管理员登录验证
	 */
	public String loginCheck(int account, String password) {
		String flag ="0";
		try {
			manager = DBHelper.createInstance();
			manager.connectDB();
			
			sql = "select * from company where tcID=?";
			Object[] params = new Object[]{account};
			
			rs = manager.executeQuery(sql, params);
			if(rs.next()){
				String pwd = rs.getString("tcPassword");
				if(pwd.equals(password)){
					flag = "1";
				}else{
					flag = "2";
				}
			}else{
				flag = "0";
			}
			
			manager.close();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		return flag;
	}

	/**
	 * 通过账号获取用户信息
	 * @param account
	 * 			账号
	 * @return
	 */
	public Company getInfoById(int account) {
		Company c = new Company();
		try {
			manager = DBHelper.createInstance();
			manager.connectDB();
			
			sql = "select * from company where tcID=?";
			Object[] params = new Object[]{account};
			
			rs = manager.executeQuery(sql, params);
			if(rs.next()){
				c.setIdentify(rs.getInt("identify"));
				c.setTcAddress(rs.getString("tcAddress"));
				c.setTcID(rs.getInt("tcID"));
				c.setTcName(rs.getString("tcName"));
				c.setTcPassword(rs.getString("tcPassword"));
				c.setTcScope(rs.getString("tcScope"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return c;
	}

}
