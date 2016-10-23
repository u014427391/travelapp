package com.tss.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

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

public class GetInfoByIdServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		String account = request.getParameter("account");
		int ID = TextUtility.String2Int(account);
		
		UserDAO userDao = new UserDAOImpl();
		User user = userDao.getInfoById(ID);
		JSONObject jsonObject = new JSONObject();
		try {
			JSONObject jsonObject2 = new JSONObject();
			
			
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("ID", user.getID());
			map.put("name", user.getName());
			map.put("password", user.getPassword());
			map.put("identity_number", user.getIdentity_number());
			map.put("phone_number", user.getPhone_number());
			jsonObject2.put("userInfo",map);
			
			
			jsonObject.put("data", jsonObject2);
			jsonObject.put("msg", "ok");
			jsonObject.put("ret", 0);
		} catch (Exception e) {
			try {
				jsonObject.put("data", "");
				jsonObject.put("msg", "error");
				jsonObject.put("ret", 1);
			} catch (JSONException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		
		PrintWriter out = response.getWriter();
		out.print(jsonObject);
		out.flush();
		out.close();
	}

	/**
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		throw new NotImplementedException();
	}

}
