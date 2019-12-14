package com.tsw.dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import com.tsw.dao.OrderDAO;
import com.tsw.entitys.Order;
import com.tsw.utils.DBHelper;

public class OrderDAOImpl implements OrderDAO {
	
	DBHelper manager;
	ResultSet rs;
	String sql = "";

	public List getDisposedOrder() {
		List orders = new ArrayList();
		
		try {
			manager = DBHelper.createInstance();
			manager.connectDB();
			
			sql = "select picture,line.name,order_time,order_status,orders.ID,total_price,user.name from orders,line,user " +
					"where user.ID=orders.user_id and line.ID=orders.line_id and order_status=1";
			Object[] params = new Object[]{};
			rs = manager.executeQuery(sql, params);
			while(rs.next()){
				Vector vector = new Vector();
				vector.add(rs.getString("line.picture"));
				vector.add(rs.getString("line.name"));
				vector.add(rs.getDate("order_time"));
				vector.add(rs.getInt("order_status"));
				vector.add(rs.getInt("orders.ID"));
				vector.add(rs.getDouble("total_price"));
				vector.add(rs.getString("user.name"));
				orders.add(vector);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return orders;
	}

	public List<Order> getUnDisposedOrder() {
		List orders = new ArrayList();
		
		try {
			manager = DBHelper.createInstance();
			manager.connectDB();
			
			sql = "select picture,line.name,order_time,order_status,orders.ID,total_price,user.name from orders,line,user " +
					"where user.ID=orders.user_id and line.ID=orders.line_id and order_status=0";
			Object[] params = new Object[]{};
			rs = manager.executeQuery(sql, params);
			while(rs.next()){
				Vector vector = new Vector();
				vector.add(rs.getString("picture"));
				vector.add(rs.getString("line.name"));
				vector.add(rs.getDate("order_time"));
				vector.add(rs.getInt("order_status"));
				vector.add(rs.getInt("orders.ID"));
				vector.add(rs.getDouble("total_price"));
				vector.add(rs.getString("user.name"));
				orders.add(vector);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return orders;
	}

	public boolean disposedOrder(Order order) {
		boolean flag = false;
		try{
			manager = DBHelper.createInstance();
			manager.connectDB();
			
			sql = "update orders set order_status = ? where ID=?";
			Object[] params = new Object[]{
					order.getOrder_status(),
					order.getID()
			};
			
			flag = manager.executeUpdate(sql, params);
			
			manager.close();
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		return flag;
	}

}
