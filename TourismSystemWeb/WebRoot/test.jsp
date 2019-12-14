<%@page import="java.net.URLDecoder"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'test.jsp' starting page</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style type="text/css">  
		.news {  
		    width: 800px;  
		    margin: 0 auto;  
		}  
</style>  
	

  </head>
  
  <body>
     <%  
        request.setCharacterEncoding("UTF-8"); 
      	String newsbody = new String(request.getParameter("editorValue").getBytes("iso-8859-1"),"UTF-8");  
    %>  
  
    <div class="news">  
        <%=newsbody%>  
  
    </div>  

  </body>
</html>
