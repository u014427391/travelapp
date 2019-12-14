package com.tsw.dao.impl;

import java.sql.ResultSet;

import com.tsw.dao.AdministrationDAO;
import com.tsw.entitys.Administration;
import com.tsw.utils.DBHelper;

public class AdministrationDAOImpl implements AdministrationDAO {

	DBHelper manager;
	ResultSet rs;
	String sql = "";
	
	/**
	 * 旅游局管理员登录验证
	 */
	public String loginCheck(int account, String password) {
		String flag ="0";
		try {
			manager = DBHelper.createInstance();
			manager.connectDB();
			
			sql = "select * from administration where tcaID=?";
			Object[] params = new Object[]{account};
			
			rs = manager.executeQuery(sql, params);
			if(rs.next()){
				String pwd = rs.getString("tcaPassword");
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

	public Administration getInfoById(int account) {
		Administration a = new Administration();
		try {
			manager = DBHelper.createInstance();
			manager.connectDB();
			
			sql = "select * from administration where tcaID=?";
			Object[] params = new Object[]{account};
			rs = manager.executeQuery(sql, params);
			
			if(rs.next()){
				a.setIdentify(rs.getInt("identify"));
				a.setTcaAddress(rs.getString("tcaAddress"));
				a.setTcaID(rs.getInt("tcaID"));
				a.setTcaName(rs.getString("tcaName"));
				a.setTcaPassword(rs.getString("tcaPassword"));
				a.setTcaScope(rs.getString("tcaScope"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return a;
	}
}
