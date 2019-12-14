package com.tsw.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tsw.Constant;
import com.tsw.dao.SpotDAO;
import com.tsw.dao.impl.SpotDAOImpl;
import com.tsw.entitys.Spot;

public class AddSpotInfoServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("UTF-8");

		String introduction = new String(request.getParameter("editorValue").getBytes("iso-8859-1"),"UTF-8");
		String picture = Constant.ImgPath.path;
		String position = new String(request.getParameter("position").getBytes("iso-8859-1"),"UTF-8");
		String priceString = new String(request.getParameter("price").getBytes("iso-8859-1"),"UTF-8");
		Double price = Double.parseDouble(priceString);
		String sortString = new String(request.getParameter("sort").getBytes("iso-8859-1"),"UTF-8");
		int spot_sort = Integer.parseInt(sortString);
//		String timeString = new String(request.getParameter("time").getBytes("iso-8859-1"),"UTF-8");
//		Date time = Date.valueOf(timeString);
		String tour_project = new String(request.getParameter("tour_project").getBytes("iso-8859-1"),"UTF-8");
		
		Spot spot = new Spot();
		spot.setIntroduction(introduction);
		spot.setPicture(picture);
		spot.setPosition(position);
		spot.setPrice(price);
		spot.setSpot_sort(spot_sort);
		spot.setTour_project(tour_project);
		
		SpotDAO spotDao = new SpotDAOImpl();
		boolean flag = spotDao.addInfo(spot);
		if(flag){
			response.sendRedirect(Constant.WEB_URL_SPOT_SERVLET);
		}
		
		out.flush();
		out.close();
	}

	/**
	 * 
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
