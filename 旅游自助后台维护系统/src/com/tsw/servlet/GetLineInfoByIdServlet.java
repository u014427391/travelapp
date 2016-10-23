package com.tsw.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tsw.Constant;
import com.tsw.dao.LineDAO;
import com.tsw.dao.impl.LineDAOImpl;
import com.tsw.entitys.Line;
import com.tsw.utils.TextUtility;

public class GetLineInfoByIdServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		String lineId = request.getParameter("ID");
		int ID = TextUtility.String2Int(lineId);
		
		LineDAO lineDao = new LineDAOImpl();
		Line line = lineDao.getInfoById(ID);
		
		HttpSession session = request.getSession();
		session.setAttribute("lineInfo", line);
		
		response.sendRedirect(Constant.WEB_URL_EDIT_LINEINFO);
		
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
