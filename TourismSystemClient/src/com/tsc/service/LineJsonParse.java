package com.tsc.service;


import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import com.tsc.activities.SpecifiedLineActivity;
import com.tsc.fragment.AllLineFragment;




public class LineJsonParse {

	public static ArrayList<Map<String, Object>> getList(String urlPath,String requester) throws Exception {

		ArrayList<Map<String, Object>> list= new ArrayList<Map<String, Object>>();
		byte[] data = readParse(urlPath);
		JSONArray array = new JSONArray(new String(data));

		JSONObject item = array.getJSONObject(0);
		String HaveReadAll=URLDecoder.decode(item.getString("HaveReadAll"),"UTF-8");
		if (HaveReadAll.equals("true")) {
			if(requester.equals("SpecifiedLineActivity"))
				SpecifiedLineActivity.HaveReadAll=true;
			else if(requester.equals("AllLineFragment"))
				AllLineFragment.HaveReadAll=true;
		}

		for (int i = 1; i < array.length(); i++) {

			item = array.getJSONObject(i);

			Map<String,Object> map=new HashMap<String, Object>();			
			map.put("ID", URLDecoder.decode(Integer.toString(item.getInt("ID")), "UTF-8"));
			map.put("price", URLDecoder.decode(Double.toString(item.getDouble("price")), "UTF-8"));
			map.put("name", URLDecoder.decode(item.getString("name"),"UTF-8"));
			map.put("tourism_time", URLDecoder.decode(item.getString("tourism_time"),"UTF-8"));
			map.put("catering", URLDecoder.decode(item.getString("catering"),"UTF-8"));
			map.put("accommodation", URLDecoder.decode(item.getString("accommodation"),"UTF-8"));
			map.put("traffic", URLDecoder.decode(item.getString("traffic"),"UTF-8"));
			map.put("picture", URLDecoder.decode(item.getString("picture"),"UTF-8"));
			map.put("contact", URLDecoder.decode(item.getString("contact"),"UTF-8"));
			list.add(map);	
		}
		return list;
	}


	public static ArrayList<Map<String, Object>> getList2(String result,String requester) throws Exception {
		ArrayList<Map<String, Object>> list= new ArrayList<Map<String, Object>>();
		JSONArray array = new JSONArray(result);

		JSONObject item = array.getJSONObject(0);
		String HaveReadAll=URLDecoder.decode(item.getString("HaveReadAll"),"UTF-8");
		if (HaveReadAll.equals("true")) {
			if(requester.equals("SpecifiedLineActivity"))
				SpecifiedLineActivity.HaveReadAll=true;
			else if(requester.equals("AllLineFragment"))
				AllLineFragment.HaveReadAll=true;
		}

		for (int i = 1; i < array.length(); i++) {

			item = array.getJSONObject(i);

			Map<String,Object> map=new HashMap<String, Object>();			
			map.put("ID", URLDecoder.decode(Integer.toString(item.getInt("ID")), "UTF-8"));
			map.put("price", URLDecoder.decode(Double.toString(item.getDouble("price")), "UTF-8"));
			map.put("name", URLDecoder.decode(item.getString("name"),"UTF-8"));
			map.put("tourism_time", URLDecoder.decode(item.getString("tourism_time"),"UTF-8"));
			map.put("catering", URLDecoder.decode(item.getString("catering"),"UTF-8"));
			map.put("accommodation", URLDecoder.decode(item.getString("accommodation"),"UTF-8"));
			map.put("traffic", URLDecoder.decode(item.getString("traffic"),"UTF-8"));
			map.put("picture", URLDecoder.decode(item.getString("picture"),"UTF-8"));
			map.put("contact", URLDecoder.decode(item.getString("contact"),"UTF-8"));
			list.add(map);	
		}
		return list;
	}


	public static List<Map<String, Object>> UpdateList(String urlPath,List<Map<String, Object>> list,String requester) throws Exception {

		byte[] data = readParse(urlPath);
		JSONArray array = new JSONArray(new String(data));

		JSONObject item = array.getJSONObject(0);
		String HaveReadAll=URLDecoder.decode(item.getString("HaveReadAll"),"UTF-8");
		if (HaveReadAll.equals("true")) {
			if(requester.equals("SpecifiedLineActivity"))
				SpecifiedLineActivity.HaveReadAll=true;
			else if(requester.equals("AllLineFragment"))
				AllLineFragment.HaveReadAll=true;
		}


		for (int i = 1; i < array.length(); i++) {

			item = array.getJSONObject(i);

			Map<String,Object> map=new HashMap<String, Object>();			
			map.put("ID", URLDecoder.decode(Integer.toString(item.getInt("ID")), "UTF-8"));
			map.put("price", URLDecoder.decode(Double.toString(item.getDouble("price")), "UTF-8"));
			map.put("name", URLDecoder.decode(item.getString("name"),"UTF-8"));
			map.put("tourism_time", URLDecoder.decode(item.getString("tourism_time"),"UTF-8"));
			map.put("catering", URLDecoder.decode(item.getString("catering"),"UTF-8"));
			map.put("accommodation", URLDecoder.decode(item.getString("accommodation"),"UTF-8"));
			map.put("traffic", URLDecoder.decode(item.getString("traffic"),"UTF-8"));
			map.put("picture", URLDecoder.decode(item.getString("picture"),"UTF-8"));
			map.put("contact", URLDecoder.decode(item.getString("contact"),"UTF-8"));
			list.add(map);	
		}
		return list;
	}
	
	
	 public static List<Map<String, Object>> UpdateList2(String result,List<Map<String, Object>> list,String requester) throws Exception {

	        JSONArray array = new JSONArray(result);

	        JSONObject item = array.getJSONObject(0);
	        String HaveReadAll=URLDecoder.decode(item.getString("HaveReadAll"),"UTF-8");
	        if (HaveReadAll.equals("true")) {
	        	if(requester.equals("SpecifiedLineActivity"))
	        		SpecifiedLineActivity.HaveReadAll=true;
	        	else if(requester.equals("AllLineFragment"))
	        		AllLineFragment.HaveReadAll=true;
			}
	        
	        
	        for (int i = 1; i < array.length(); i++) {

	                item = array.getJSONObject(i);
	               
	                Map<String,Object> map=new HashMap<String, Object>();			
					map.put("ID", URLDecoder.decode(Integer.toString(item.getInt("ID")), "UTF-8"));
					map.put("price", URLDecoder.decode(Double.toString(item.getDouble("price")), "UTF-8"));
					map.put("name", URLDecoder.decode(item.getString("name"),"UTF-8"));
					map.put("tourism_time", URLDecoder.decode(item.getString("tourism_time"),"UTF-8"));
					map.put("catering", URLDecoder.decode(item.getString("catering"),"UTF-8"));
					map.put("accommodation", URLDecoder.decode(item.getString("accommodation"),"UTF-8"));
					map.put("traffic", URLDecoder.decode(item.getString("traffic"),"UTF-8"));
					map.put("picture", URLDecoder.decode(item.getString("picture"),"UTF-8"));
					map.put("contact", URLDecoder.decode(item.getString("contact"),"UTF-8"));
					list.add(map);	
	        }
	       return list;
	}


	public static byte[] readParse(String urlPath) throws Exception {

		ByteArrayOutputStream outStream = new ByteArrayOutputStream();

		byte[] data = new byte[1024];

		int len = 0;

		URL url = new URL(urlPath);

		HttpURLConnection conn = (HttpURLConnection) url.openConnection();

		InputStream inStream = conn.getInputStream();

		while ((len = inStream.read(data)) != -1) {

			outStream.write(data, 0, len);

		}

		inStream.close();

		return outStream.toByteArray();

	}
}
