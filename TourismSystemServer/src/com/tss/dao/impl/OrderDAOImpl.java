package com.tss.dao.impl;

import java.util.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import com.tss.dao.OrderDAO;
import com.tss.po.Order;
import com.tss.utils.DBHelperUtil;

public class OrderDAOImpl implements OrderDAO{

	DBHelperUtil manager;
	String sql = "";
	ResultSet rs;
	
	public List getInfoById(int id) {
		List list = new ArrayList();
		
		try{
			manager = DBHelperUtil.createInstance();
			manager.connectDB();
			
			sql = "select line.picture,line.name,order_time,total_price from orders,line,user " +
					"where user.ID=orders.user_id and line.ID=orders.line_id and orders.user_id=?";
			Object[] params = new Object[]{id};
			rs = manager.executeQuery(sql, params);
			while(rs.next()){
				Vector vector = new Vector();
				vector.add(rs.getString("picture"));
				vector.add(rs.getString("name"));
				vector.add(rs.getDate("order_time"));
				vector.add(rs.getDouble("total_price"));
				list.add(vector);
			}
			manager.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}

	public Order getOrderById(int id) {
		Order order = new Order();
		
		try {
			manager = DBHelperUtil.createInstance();
			manager.connectDB();
			
			sql = "select * from orders where ID=?";
			Object[] params = new Object[]{id};
			rs = manager.executeQuery(sql, params);
			while(rs.next()){
				order.setID(rs.getInt("ID"));
				order.setOrder_time(rs.getDate("order_time"));
				order.setTotal_price(rs.getDouble("total_price"));
			}
			
			manager.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return order;
	}

	/**
	 * 新增订单
	 * @param order
	 * 			订单信息的实体类
	 * @return
	 */
	public boolean addOrder(Order order) {
		boolean flag = false;
		
		try {
			manager = DBHelperUtil.createInstance();
			manager.connectDB();
			
			sql = "insert into orders(user_id,people_count,total_price,order_time,line_id,travel_time) " +
					"values (?,?,?,?,?,?)";
			
			Object[] params = new Object[]{
					order.getUser_id(),
					order.getPeople_count(),
					order.getTotal_price(),
					new Date(),
					order.getLine_id(),
					order.getTravel_time()
			};
			
			flag = manager.executeUpdate(sql, params);
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		return flag;
	}

}
