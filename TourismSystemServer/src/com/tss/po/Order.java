package com.tss.po;

import java.sql.Date;

public class Order {

	/*
	 * 订单ID
	 */
	private int ID;
	
	/*
	 * 下单用户ID
	 */
	private int user_id;
	
	/*
	 * 出行人数
	 */
	private int people_count;
	
	/*
	 * 总价
	 */
	private double total_price;
	
	/*
	 * 下单时间
	 */
	private Date  order_time;
	
	/*
	 * 旅游路线ID
	 */
	private int line_id;
	
	/*
	 * 出游时间
	 */
	private Date travel_time;
	
	/*
	 * 订单是否处理
	 */
	private int order_status;

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getPeople_count() {
		return people_count;
	}

	public void setPeople_count(int people_count) {
		this.people_count = people_count;
	}

	public double getTotal_price() {
		return total_price;
	}

	public void setTotal_price(double total_price) {
		this.total_price = total_price;
	}

	public Date getOrder_time() {
		return order_time;
	}

	public void setOrder_time(Date order_time) {
		this.order_time = order_time;
	}

	public int getLine_id() {
		return line_id;
	}

	public void setLine_id(int line_id) {
		this.line_id = line_id;
	}

	public Date getTravel_time() {
		return travel_time;
	}

	public void setTravel_time(Date travel_time) {
		this.travel_time = travel_time;
	}

	public int getOrder_status() {
		return order_status;
	}

	public void setOrder_status(int order_status) {
		this.order_status = order_status;
	}

	
}
