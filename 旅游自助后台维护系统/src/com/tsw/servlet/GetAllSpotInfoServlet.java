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
import com.tsw.dao.SpotDAO;
import com.tsw.dao.impl.SpotDAOImpl;
import com.tsw.entitys.Spot;

public class GetAllSpotInfoServlet extends HttpServlet {

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
		
		boolean isLeader = Constant.LoginMsg.isLeader;
		
		if(isLeader == false){
			response.getWriter().println("你不可以访问这个页面哦！");
			response.sendRedirect(Constant.WEB_URL_TIP);	
		}else{
			SpotDAO spotDao = new SpotDAOImpl();
			List<Spot> spots =spotDao.getAllInfo();
			
			HttpSession session = request.getSession();
			session.setAttribute("spotList", spots);
			
			response.sendRedirect(Constant.WEB_URL_SPOTLIST);
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
