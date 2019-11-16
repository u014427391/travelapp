package com.tsc;

/**
 * Constane类，保存一些全局的常量和内部类
 * 
 */
public class Constant {

	//WEB服务器
	public static final String URL_WEB_SERVER = "http://172.16.107.29:8080/TourismSystemServer";
	//登录验证的URL
	public static final String URL_Login = URL_WEB_SERVER + "/LoginServlet";
	//注册的URL
	public static final String URL_Register = URL_WEB_SERVER + "/RegisterServlet";
	//获取用户个人信息的URL
	public static final String URL_GETINFO = URL_WEB_SERVER + "/GetInfoByIdServlet";
	//获取用户订单的URL
	public static final String URL_GetOrder = URL_WEB_SERVER + "/GetOrderServlet";
	//提交订单的URL
	public static final String URL_AddOrder = URL_WEB_SERVER + "/AddOrderServlet";
	//获取所有景点信息的URL
	public static final String URL_GetSpot = URL_WEB_SERVER + "/GetAllSpotInfoServlet";
	//根据景点位置进行景点搜索的URL
	public static final String URL_GetSpotInfoByPosition = URL_WEB_SERVER + "/GetInfoByPositionServlet";
	//获取搜索的景点信息
	public static final String URL_SearchSpot=URL_WEB_SERVER + "/GetSearchSpotInfoServlet";
	
	//验证用户是否登录的内部类
	public static class LoginMsg {
		public static boolean isLogin = false;
	}
}
