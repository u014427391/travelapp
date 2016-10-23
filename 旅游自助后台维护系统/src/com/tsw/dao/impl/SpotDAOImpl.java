package com.tsw.dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.tsw.dao.SpotDAO;
import com.tsw.entitys.Spot;
import com.tsw.utils.DBHelper;

public class SpotDAOImpl implements SpotDAO{

	DBHelper manager;
	ResultSet rs;
	String sql = "";
	
	/**
	 * 获取所有的景点信息
	 */
	public List<Spot> getAllInfo() {
		List<Spot> spots = new ArrayList<Spot>();
		
		try {
			manager = DBHelper.createInstance();
			manager.connectDB();
			
			sql = "select * from scenic_spot";
			
			Object[] params = new Object[]{};
			
			rs = manager.executeQuery(sql, params);
			
			while(rs.next()){
				Spot spot = new Spot();
				spot.setID(rs.getInt("ID"));
				spot.setPosition(rs.getString("position"));
				spot.setTour_project(rs.getString("tour_project"));
				spot.setPrice(rs.getDouble("price"));
				spot.setIntroduction(rs.getString("introduction"));
				spot.setPicture(rs.getString("picture"));
				spot.setTime(rs.getDate("time"));
				spot.setSpot_sort(rs.getInt("spot_sort"));
				spots.add(spot);
			}
			manager.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return spots;
	}

	public Spot getInfoById(int ID) {
		Spot spot = new Spot();
		try{
			manager = DBHelper.createInstance();
			manager.connectDB();
			
			sql = "select * from scenic_spot where ID=?";
			Object[] params = new Object[]{ID};
			
			rs = manager.executeQuery(sql, params);
			
			while(rs.next()){
				spot.setID(rs.getInt("ID"));
				spot.setTour_project(rs.getString("tour_project"));
				spot.setIntroduction(rs.getString("introduction"));
				spot.setPosition(rs.getString("position"));
				spot.setPicture(rs.getString("picture"));
				spot.setPrice(rs.getDouble("price"));
				spot.setSpot_sort(rs.getInt("spot_sort"));
				spot.setTime(rs.getDate("time"));
			}
			manager.close();
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		return spot;
	}

	/**
	 * 新增景点信息
	 */
	public boolean addInfo(Spot spot) {
		boolean flag = false;
		try {
			manager = DBHelper.createInstance();
			manager.connectDB();
			
			sql = "insert into scenic_spot(introduction,picture,position,price,spot_sort,time,tour_project) values(?,?,?,?,?,?,?)";
			Object[] params = new Object[]{
					spot.getIntroduction(),
					spot.getPicture(),
					spot.getPosition(),
					spot.getPrice(),
					spot.getSpot_sort(),
					new Date(),
					spot.getTour_project()
			};
			
			flag = manager.executeUpdate(sql, params);
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		return flag;
	}

	/**
	 * 更新景点信息
	 */
	public boolean updateInfo(Spot spot) {
		boolean flag = false;
		try{
			manager = DBHelper.createInstance();
			manager.connectDB();
			
			sql = "update scenic_spot set position =?,tour_project=?,introduction=?,price=? where ID=?";
				
			Object[] params = new Object[]{
					spot.getPosition(),
					spot.getTour_project(),
					spot.getIntroduction(),
					spot.getPrice(),
//					spot.getSpot_sort(),
					spot.getID()
			};
			
			flag = manager.executeUpdate(sql, params);
			
			manager.close();
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		return flag;
	}

	
}
