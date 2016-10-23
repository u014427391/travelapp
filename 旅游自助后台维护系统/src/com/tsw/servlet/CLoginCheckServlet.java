package com.tsw.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tsw.Constant;
import com.tsw.dao.AdministrationDAO;
import com.tsw.dao.CompanyDAO;
import com.tsw.dao.impl.AdministrationDAOImpl;
import com.tsw.dao.impl.CompanyDAOImpl;
import com.tsw.entitys.Company;
import com.tsw.utils.TextUtility;

public class CLoginCheckServlet extends HttpServlet {

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
		String account = request.getParameter("account2");
		String password = request.getParameter("password2");
		
		int ID = TextUtility.String2Int(account);
		
		CompanyDAO cdao = new CompanyDAOImpl();
		String flag = cdao.loginCheck(ID, password);
		
		HttpSession session=request.getSession();
		
		if(flag.equals("1")){
			Constant.LoginMsg.isLeader = false;
			response.sendRedirect(Constant.WEB_URL_INDEX);
			out.println("<script language='javascript'>function ale(){alert('登录成功!');}</script>");
		}else if(flag.equals("2")){
			response.sendRedirect(Constant.WEB_URL_LOGIN);
			out.println("<script language='javascript'>function ale(){alert('密码错误哦!');}</script>");
		}else if(flag.equals("0")){
			response.sendRedirect(Constant.WEB_URL_LOGIN);
			out.println("<script language='javascript'>function ale(){alert('账号错误哦!');}</script>");
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
