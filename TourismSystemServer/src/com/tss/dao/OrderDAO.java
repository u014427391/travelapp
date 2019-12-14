package com.tss.dao;

import java.util.List;

import com.tss.po.Order;

public interface OrderDAO {

	/**
	 * 根据用户账号获取所有的订单
	 * @param id
	 * 			账号
	 * @return
	 */
	public List getInfoById(int id);
	
	
	public Order getOrderById(int id);
	
	/**
	 * 新增订单
	 * @param order
	 * 			订单信息的实体类
	 * @return
	 */
	public boolean addOrder(Order order);
	
}
