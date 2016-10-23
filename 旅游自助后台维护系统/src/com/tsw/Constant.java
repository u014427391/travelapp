package com.tsw;

public class Constant {

	public static String WEB_SERVER_URL = "http://localhost:8080/TourismSystemServer";
	
	public static String PROJECT_URL = "/TourismSystemWeb";
	
	public static String WEB_URL_LOGIN = PROJECT_URL+"/login.html";
	
	public static String WEB_URL_INDEX = PROJECT_URL+ "/index.jsp";
	
	public static String LINE_BASE_URL = "/view/line";
	
	public static String WEB_URL_ADDLINEINFO = PROJECT_URL + LINE_BASE_URL + "/addLineInfo.jsp";
	
	public static String WEB_URL_LINELIST = PROJECT_URL+ LINE_BASE_URL+"/lineList.jsp";
	
	public static String WEB_URL_LINE_SEREVLET = PROJECT_URL + "/GetAllLineInfoServlet";
	
	public static String WEB_URL_EDIT_LINEINFO = PROJECT_URL+ LINE_BASE_URL+"/editLineInfo.jsp";
	
	public static String SPOT_BASE_URL = "/view/spot";
	
	public static String WEB_URL_ADDSPOTINFO = PROJECT_URL + SPOT_BASE_URL + "/addSpotInfo.jsp";
	
	public static String WEB_URL_EDIT_SPOTINFO = PROJECT_URL + SPOT_BASE_URL +"/editSpotInfo.jsp";
	
	public static String WEB_URL_SPOTLIST = PROJECT_URL+ SPOT_BASE_URL+"/spotList.jsp";
	
	public static String WEB_URL_SPOTINFO = PROJECT_URL+ SPOT_BASE_URL+"/editSpotInfo.jsp";
	
	public static String WEB_URL_SPOT_SERVLET = PROJECT_URL + "/GetAllSpotInfoServlet";
	
	public static String ORDER_BASE_URL = "/view/orders";
	
	public static String WEB_URL_UNDISPOSED_ORDER = PROJECT_URL+ORDER_BASE_URL+"/unDisposedOrderList.jsp";

	public static String WEB_URL_DISPOSED_ORDER = PROJECT_URL+ORDER_BASE_URL+"/disposedOrderList.jsp";
	
	public static String WEB_URL_DISPOSED_ORDERLIST = PROJECT_URL + "/GetAllDisposedOrderServlet";

	public static String WEB_URL_UNDISPOSED_ORDERLIST = PROJECT_URL + "/GetAllUnDisposedOrderServlet";
	
	public static String WEB_URL_TIP = PROJECT_URL + "/tip.jsp";
	
	//验证用户登录indentify的内部类
	public static class LoginMsg {
		public static boolean isLeader = false;
	}
	
	//景点、旅游路线图片保存的Path
	public static class ImgPath {
		public static String path;
	}
	
}
