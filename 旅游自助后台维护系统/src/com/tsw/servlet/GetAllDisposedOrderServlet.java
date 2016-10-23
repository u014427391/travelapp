package com.tsw.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tsw.Constant;
import com.tsw.dao.OrderDAO;
import com.tsw.dao.impl.OrderDAOImpl;

public class GetAllDisposedOrderServlet extends HttpServlet {

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
		
		OrderDAO odao = new OrderDAOImpl();
		
		List orders = odao.getDisposedOrder();
		
		HttpSession session = request.getSession();
		
		session.setAttribute("orders", orders);
		response.sendRedirect(Constant.WEB_URL_DISPOSED_ORDER);
		
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
