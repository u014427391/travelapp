package com.tsw.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tsw.Constant;
import com.tsw.dao.LineDAO;
import com.tsw.dao.impl.LineDAOImpl;
import com.tsw.entitys.Line;
import com.tsw.utils.TextUtility;

public class UpdateLineInfoServlet extends HttpServlet {

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
		
		request.setCharacterEncoding("UTF-8");
		
		String IdString = new String(request.getParameter("ID").getBytes("iso-8859-1"),"UTF-8");
		int ID = TextUtility.String2Int(IdString);
		String name = new String(request.getParameter("name").getBytes("iso-8859-1"),"UTF-8");
		String catering = new String(request.getParameter("catering").getBytes("iso-8859-1"),"UTF-8");
		String accommodation = new String(request.getParameter("accommodation").getBytes("iso-8859-1"),"UTF-8");
		String traffic = new String(request.getParameter("traffic").getBytes("iso-8859-1"),"UTF-8");
		String priceString = new String(request.getParameter("price").getBytes("iso-8859-1"),"UTF-8");
		Double price = Double.parseDouble(priceString);
		//String picture = Constant.ImgPath.path;
		String contact = new String(request.getParameter("contact").getBytes("iso-8859-1"),"UTF-8");
		String timeString = new String(request.getParameter("tourism_time").getBytes("iso-8859-1"),"UTF-8");
		Date tourism_time = Date.valueOf(timeString);
		
		Line line = new Line();
		line.setID(ID);
		line.setName(name);
		line.setCatering(catering);
		line.setAccommodation(accommodation);
		line.setTraffic(traffic);
		line.setPrice(price);
		//line.setPicture(picture);
		line.setContact(contact);
		line.setTourism_time(tourism_time);
		
		LineDAO ldao = new LineDAOImpl();
		boolean flag = ldao.updateInfo(line);

		if(flag){
			response.sendRedirect(Constant.WEB_URL_LINE_SEREVLET);
		}
//		if(picture!=null){
//			
//		}
//		
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
