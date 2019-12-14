package com.tsc.service;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import com.tsc.entity.Parameter;

public class AddOrderService {

	public String addOrder(String url,String account,String count,String price,String lineID,String time){
		
		String str = "";
		
		SyncHttp syncHttp = new SyncHttp();
		List<Parameter> params = new ArrayList<Parameter>();
		params.add(new Parameter("account",account));
		params.add(new Parameter("count",count));
		params.add(new Parameter("price",price));
		params.add(new Parameter("lineID",lineID));
		params.add(new Parameter("time",time));
		
		try {
			String retStr = syncHttp.httpPost(url, params);
			JSONObject jsonObject = new JSONObject(retStr);
			
			int retCode = jsonObject.getInt("ret");
			if(retCode == 0){
				str = "success";
			}else{
				str = "error";
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return str;
	}
}
