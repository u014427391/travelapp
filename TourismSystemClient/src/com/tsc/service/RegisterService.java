package com.tsc.service;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import com.tsc.entity.Parameter;

public class RegisterService {

	public String register(String url, String account, String password){
		String str = "";
		//同步加载
		SyncHttp syncHttp = new SyncHttp();
		//数组列表
		List<Parameter> params = new ArrayList<Parameter>();
		params.add(new Parameter("account",account));
		params.add(new Parameter("password",password));
		try {
			//获取服务器返回的数据
			String retStr = syncHttp.httpPost(url, params);
			JSONObject jsonObject = new JSONObject(retStr);
			//获取返回码
			int retCode = jsonObject.getInt("ret");
			//返回码为0说明请求成功
			if(retCode == 0){
				//请求成功
				str = "success";
			}else{
				str = "error";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}
}
