package com.tsw.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.tsw.Constant;
import com.tsw.dao.LineDAO;
import com.tsw.dao.impl.LineDAOImpl;
import com.tsw.entitys.Line;


public class AddLineInfoServlet extends HttpServlet {

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
		String accommodation = new String(request.getParameter("accommodation").getBytes("iso-8859-1"),"UTF-8");
		String catering = new String(request.getParameter("catering").getBytes("iso-8859-1"),"UTF-8");
		String contact = new String(request.getParameter("contact").getBytes("iso-8859-1"),"UTF-8");
		String name = new String(request.getParameter("name").getBytes("iso-8859-1"),"UTF-8");
		String picture = Constant.ImgPath.path;
		String priceString = new String(request.getParameter("price").getBytes("iso-8859-1"),"UTF-8");
		Double price = Double.parseDouble(priceString);
		String timeString = new String(request.getParameter("time").getBytes("iso-8859-1"),"UTF-8");
		Date tourism_time = Date.valueOf(timeString);
		String traffic = new String(request.getParameter("traffic").getBytes("iso-8859-1"),"UTF-8");
		
		LineDAO ldao = new LineDAOImpl();
		Line line = new Line();
		line.setAccommodation(accommodation);
		line.setCatering(catering);
		line.setContact(contact);
		line.setName(name);
		line.setPicture(picture);
		line.setPrice(price);
		line.setTourism_time(tourism_time);
		line.setTraffic(traffic);
		ldao.addInfo(line);
				
		response.sendRedirect(Constant.WEB_URL_LINE_SEREVLET);
		 
				
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
