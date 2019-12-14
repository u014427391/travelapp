package com.tss.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import com.tss.dao.OrderDAO;
import com.tss.dao.impl.OrderDAOImpl;
import com.tss.po.Order;
import com.tss.utils.TextUtility;

public class AddOrderServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		throw new NotImplementedException();
	}

	/**
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		String account = request.getParameter("account");
		//票数，也即出游人数
		String count = request.getParameter("count");
		//总价钱
		String totalPrice = request.getParameter("price");
		//路线ID
		String lineID = request.getParameter("lineID");
		//出游时间
		String travelTime = request.getParameter("time");
		
		JSONObject jsonObject = new JSONObject();
		
		OrderDAO orderDao = new OrderDAOImpl();
		Order order = new Order();
		order.setUser_id(TextUtility.String2Int(account));
		order.setPeople_count(TextUtility.String2Int(count));
		order.setTotal_price(Double.parseDouble(totalPrice));
		order.setLine_id(TextUtility.String2Int(lineID));
		order.setTravel_time(Date.valueOf(travelTime));

		boolean flag = orderDao.addOrder(order);
		
			try {
				if(flag){
					jsonObject.put("ret", 0);
				}else{
					jsonObject.put("ret", 1);
				}
			} catch (Exception e) {
				e.printStackTrace();
				try {
					jsonObject.put("ret", 1);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		
		out.print(jsonObject);
		out.flush();
		out.close();
	}

}
