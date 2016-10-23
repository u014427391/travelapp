package com.tsw.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tsw.Constant;
import com.tsw.dao.OrderDAO;
import com.tsw.dao.impl.OrderDAOImpl;
import com.tsw.entitys.Order;
import com.tsw.utils.TextUtility;

public class DisposedOrderServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		String IdString = request.getParameter("id");
		int ID = TextUtility.String2Int(IdString);
		
		String order_status = request.getParameter("order_status");
		int statusID = TextUtility.String2Int(order_status);
		
		if(statusID == 0){
			statusID =1;
		}else if(statusID == 1){
			statusID =0;
		}
		
		Order order = new Order();
		order.setOrder_status(statusID);
		order.setID(ID);
		
		OrderDAO odao = new OrderDAOImpl();
		boolean flag = odao.disposedOrder(order);
		
		if(flag){
			response.sendRedirect(Constant.WEB_URL_DISPOSED_ORDERLIST);
		}
		
		out.flush();
		out.close();
	}

	/**
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
