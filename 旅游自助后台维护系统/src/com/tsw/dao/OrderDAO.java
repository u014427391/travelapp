package com.tsw.dao;

import java.util.List;

import com.tsw.entitys.Order;

public interface OrderDAO {
	
	public List getDisposedOrder();
	
	public List getUnDisposedOrder();

	public boolean disposedOrder(Order order);
}
