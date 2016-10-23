package com.tss.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import com.tss.dao.UserDAO;
import com.tss.dao.impl.UserDAOImpl;
import com.tss.po.User;
import com.tss.utils.TextUtility;

public class RegisterServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		throw new NotImplementedException();
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String identity_number = request.getParameter("identity_number");
		String phone_number = request.getParameter("phone_number");
		
		JSONObject jObject = new JSONObject();
		
		try {
			
			UserDAO dao = new UserDAOImpl();
			User user = new User();
			user.setName(name);
			user.setPassword(password);
			user.setIdentity_number(identity_number);
			user.setPhone_number(phone_number);
			
			dao.register(user);
			
			jObject.put("ret", 0);
			jObject.put("msg", "ok");
			jObject.put("data", "");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try
			{
				jObject.put("ret", 1);
				jObject.put("msg", "error");
				jObject.put("data", "");
			} catch (JSONException ex)
			{
				ex.printStackTrace();
			}
		}
		
		PrintWriter out = response.getWriter();
		out.println(jObject);
		out.flush();
		out.close();
	}
}
