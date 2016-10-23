package com.tsw.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tsw.Constant;
import com.tsw.dao.SpotDAO;
import com.tsw.dao.impl.SpotDAOImpl;
import com.tsw.entitys.Spot;
import com.tsw.utils.TextUtility;

public class GetSpotInfoByIdServlet extends HttpServlet {

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
		String spotID = request.getParameter("ID");
		int ID = TextUtility.String2Int(spotID);
		
		SpotDAO spotDao = new SpotDAOImpl();
		Spot spot = spotDao.getInfoById(ID);
		
		HttpSession session = request.getSession();
		
		session.setAttribute("spotInfo", spot);
		
		response.sendRedirect(Constant.WEB_URL_SPOTINFO);
		
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
