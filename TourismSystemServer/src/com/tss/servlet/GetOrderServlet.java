package com.tss.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import com.tss.dao.OrderDAO;
import com.tss.dao.impl.OrderDAOImpl;
import com.tss.utils.TextUtility;

public class GetOrderServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 */
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		String account = request.getParameter("account");
		int ID = TextUtility.String2Int(account);
		
		OrderDAO orderDao = new OrderDAOImpl();
		@SuppressWarnings("unchecked")
		List<Vector> orderList = orderDao.getInfoById(ID);
		
		List<HashMap<String, Object>> list = new ArrayList<HashMap<String,Object>>();
		
		for(Vector vector : orderList){
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("picPath", vector.get(0));
			map.put("name", vector.get(1));
			map.put("order_time", vector.get(2));
			map.put("total_price", vector.get(3));
			list.add(map);
		}
		
		JSONObject jsonObject = new JSONObject();
		try {
			JSONObject jsonObject2 = new JSONObject();
			jsonObject2.put("totalNum", orderList.size());
			jsonObject2.put("orderList", list);
			
			jsonObject.put("ret", 0);
			jsonObject.put("data", jsonObject2);
			
		} catch (Exception e) {
			e.printStackTrace();
			try {
				jsonObject.put("ret", 1);
			} catch (JSONException e1) {
				e1.printStackTrace();
			}
		}
		
		out.print(jsonObject);
		out.flush();
		out.close();
	}

	/**
	 */
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		throw new NotImplementedException();
	}

}
