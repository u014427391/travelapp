package com.tsw.dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.tsw.dao.LineDAO;
import com.tsw.entitys.Line;
import com.tsw.utils.DBHelper;

public class LineDAOImpl implements LineDAO {

	DBHelper manager;
	ResultSet rs;
	String sql = "";
	
	
	/**
	 * 获取所有的旅游路线信息
	 */
	public List<Line> getAllInfo() {
		List<Line> lines = new ArrayList<Line>();
		try {
			manager = DBHelper.createInstance();
			manager.connectDB();
			
			sql = "select * from line";
			Object[] params = new Object[]{};
			ResultSet rs = manager.executeQuery(sql, params);
			while(rs.next()){
				Line line = new Line();
				line.setID(rs.getInt("ID"));
				line.setName(rs.getString("name"));
				line.setContact(rs.getString("contact"));
				line.setPrice(rs.getDouble("price"));
				line.setAccommodation(rs.getString("accommodation"));
				line.setCatering(rs.getString("catering"));
				line.setTraffic(rs.getString("traffic"));
				line.setPicture(rs.getString("picture"));
				line.setTourism_time(rs.getDate("tourism_time"));
				lines.add(line);
			}
			manager.close();
		} catch (Exception e) {
			e.printStackTrace();
		} 		
		return lines;
	}


	/**
	 * 通过ID获取旅游路线信息
	 */
	public Line getInfoById(int ID) {
		Line line = new Line();
		try {
			manager = DBHelper.createInstance();
			manager.connectDB();
			
			sql = "select * from line where ID=?";
			Object[] params = new Object[]{ID};
			rs = manager.executeQuery(sql, params);
			while(rs.next()){
				line.setID(rs.getInt("ID"));
				line.setName(rs.getString("name"));
				line.setAccommodation(rs.getString("accommodation"));
				line.setCatering(rs.getString("catering"));
				line.setTraffic(rs.getString("traffic"));
				line.setContact(rs.getString("contact"));
				line.setPicture(rs.getString("picture"));
				line.setPrice(rs.getDouble("price"));
				line.setTourism_time(rs.getDate("tourism_time"));
			}
			manager.close();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return line;
	}


	public boolean addInfo(Line line) {
		boolean flag = false;
		try {
			manager = DBHelper.createInstance();
			manager.connectDB();
			
			sql = "insert into line(name,catering,accommodation,traffic,price,picture,contact,tourism_time) values(?,?,?,?,?,?,?,?)";
			Object[] params = new Object[]{
					line.getName(),
					line.getCatering(),
					line.getAccommodation(),
					line.getTraffic(),
					line.getPrice(),
					line.getPicture(),
					line.getContact(),
					line.getTourism_time()
			};
			
			flag = manager.executeUpdate(sql, params);
			
			manager.close();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		return flag;
	}


	public int getCount() {
		int count = 0;
		try{
			manager = DBHelper.createInstance();
			manager.connectDB();
			
			sql = "select count(*) from line";
			Object[] params = new Object[]{};
			
			rs = manager.executeQuery(sql, params);
			while(rs.next()){
				count = rs.getInt(1);
			}
			
			manager.close();
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		return count;
	}


	public boolean updateInfo(Line line) {
		boolean flag = false;
		try{
			manager = DBHelper.createInstance();
			manager.connectDB();
			
			sql = "update line set name=?,catering=?,accommodation=?," +
					"traffic=?,price=?,contact=?,tourism_time=? where ID=?";
			
			Object[] params = new Object[]{
					line.getName(),
					line.getCatering(),
					line.getAccommodation(),
					line.getTraffic(),
					line.getPrice(),
					line.getContact(),
					line.getTourism_time(),
					line.getID()
			};
			
			flag = manager.executeUpdate(sql, params);
			
			manager.close();
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return flag;
	}

}
