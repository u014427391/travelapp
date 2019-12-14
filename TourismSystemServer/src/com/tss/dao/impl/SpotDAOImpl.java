package com.tss.dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.tss.dao.SpotDAO;
import com.tss.po.Spot;
import com.tss.utils.DBHelperUtil;

public class SpotDAOImpl implements SpotDAO{

	DBHelperUtil manager;
	ResultSet rs;
	String sql = "";
	
	public List<Spot> getAllInfo() {
		List<Spot> spots = new ArrayList<Spot>();
		
		try {
			manager = DBHelperUtil.createInstance();
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
	
	/**
	 * 
	 * 描述：   获取到模糊搜索的景点信息，
	 * 创建人：陈钟涛
	 * 创建时间：2016年3月21日 下午12:57:54      
	 * @version
	 */
	public List<Spot> getSearchInfo(String searchSpot) {
		List<Spot> spots=new ArrayList<Spot>();
		
		try {
			manager=DBHelperUtil.createInstance();
			manager.connectDB();
			
			sql="select * from scenic_spot where position LIKE '%"+searchSpot+"%' or "
							+ "tour_project LIKE '%"+searchSpot+"%'";
		
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

	/**
	 * 根据景点位置进行景点搜索
	 */
	public List<Spot> getInfoByPosition(String position) {
		List<Spot> spots = new ArrayList<Spot>();
		try{
			manager = DBHelperUtil.createInstance();
			manager.connectDB();
			
			sql = "select * from scenic_spot where position like '%"+position+"%'";
			Object[] params =new Object[]{};
			
			rs = manager.executeQuery(sql, params);
			while(rs.next()){
				Spot s = new Spot();
				s.setID(rs.getInt("ID"));
				s.setIntroduction(rs.getString("introduction"));
				s.setPicture(rs.getString("picture"));
				s.setPosition(rs.getString("position"));
				s.setPrice(rs.getDouble("price"));
				s.setSpot_sort(rs.getInt("spot_sort"));
				s.setTime(rs.getDate("time"));
				s.setTour_project(rs.getString("tour_project"));
				s.setSpot_sort(rs.getInt("spot_sort"));
				spots.add(s);
			}
			
			manager.close();
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		return spots;
	}
	
	
	/**
	 * 根据景点类别进行景点搜索
	 * @param position
	 * @return
	 */
	public List<Spot> getInfoBySort(int sort) {
		List<Spot> spots = new ArrayList<Spot>();
		try{
			manager = DBHelperUtil.createInstance();
			manager.connectDB();
			Object[] params = null;
			if(sort == 0){
				sql = "select * from scenic_spot";
				params =new Object[]{};
			}else{
				sql = "select * from scenic_spot where spot_sort=?";	
				params =new Object[]{sort};
			}
		
			rs = manager.executeQuery(sql, params);
			while(rs.next()){
				Spot s = new Spot();
				s.setID(rs.getInt("ID"));
				s.setIntroduction(rs.getString("introduction"));
				s.setPicture(rs.getString("picture"));
				s.setPosition(rs.getString("position"));
				s.setPrice(rs.getDouble("price"));
				s.setSpot_sort(rs.getInt("spot_sort"));
				s.setTime(rs.getDate("time"));
				s.setTour_project(rs.getString("tour_project"));
				s.setSpot_sort(rs.getInt("spot_sort"));
				spots.add(s);
			}
			
			manager.close();
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		return spots;
	}

	/**
	 * 景点信息排序
	 * @param id
	 * 			标志
	 * @return
	 */
	public List<Spot> spotInfoOrder(int id) {
		List<Spot> spots = new ArrayList<Spot>();
		try{
			manager = DBHelperUtil.createInstance();
			manager.connectDB();
			Object[] params = null;
			if(id == 1){
				sql = "select * from scenic_spot order by price desc";
				params =new Object[]{};
			}else if(id == 2){
				sql = "select * from scenic_spot order by time desc";	
				params =new Object[]{};
			}else{
				sql = "select * from scenic_spot";	
				params =new Object[]{};
			}
		
			rs = manager.executeQuery(sql, params);
			while(rs.next()){
				Spot s = new Spot();
				s.setID(rs.getInt("ID"));
				s.setIntroduction(rs.getString("introduction"));
				s.setPicture(rs.getString("picture"));
				s.setPosition(rs.getString("position"));
				s.setPrice(rs.getDouble("price"));
				s.setSpot_sort(rs.getInt("spot_sort"));
				s.setTime(rs.getDate("time"));
				s.setTour_project(rs.getString("tour_project"));
				s.setSpot_sort(rs.getInt("spot_sort"));
				spots.add(s);
			}
			
			manager.close();
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		return spots;
	}
	
}
