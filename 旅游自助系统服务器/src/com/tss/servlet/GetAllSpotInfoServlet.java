package com.tss.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import com.tss.dao.SpotDAO;
import com.tss.dao.impl.SpotDAOImpl;
import com.tss.po.Spot;

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
		
		SpotDAO spotDao = new SpotDAOImpl();
		List<Spot> spots = spotDao.getAllInfo();
		
		List<HashMap<String,Object>> list = new ArrayList<HashMap<String,Object>>();
		JSONObject jsonObject = new JSONObject();
		
		for(int i = 0; i < spots.size(); i++){
			HashMap<String,Object> map = new HashMap<String, Object>();
			map.put("ID", spots.get(i).getID());
			map.put("position", spots.get(i).getPosition());
			map.put("tour_project", spots.get(i).getTour_project());
			map.put("introduction", spots.get(i).getIntroduction());;
			map.put("price", spots.get(i).getPrice());
			map.put("picture", spots.get(i).getPicture());
			map.put("time", spots.get(i).getTime());
			map.put("spot_sort", spots.get(i).getSpot_sort());
			list.add(map);
		}
		
		JSONObject jsonObject2 = new JSONObject();
		try{
			jsonObject2.put("totalNum", spots.size());
			jsonObject2.put("spotsInfo", list);
			
			jsonObject.put("ret", 0);
			jsonObject.put("data", jsonObject2);
		}catch (Exception e) {
			// TODO: handle exception
			try{
				jsonObject.put("ret", 1);
			}catch (Exception ex) {
				// TODO: handle exception
			}
		}
		
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
