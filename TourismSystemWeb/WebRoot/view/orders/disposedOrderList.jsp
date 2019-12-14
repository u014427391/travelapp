<%@page import="com.tsw.Constant"%>
<%@page import="com.tsw.entitys.Line"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html class="no-js">
<head>
	<base href="<%=basePath%>">
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>旅旅自助系统之后台管理系统</title>
  <meta name="description" content="这是一个 table 页面">
  <meta name="keywords" content="table">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
  <meta name="renderer" content="webkit">
  <meta http-equiv="Cache-Control" content="no-siteapp" />
  <link rel="icon" type="image/png" href="<%=basePath %>assets/i/favicon.png">
  <link rel="apple-touch-icon-precomposed" href="<%=basePath %>assets/i/app-icon72x72@2x.png">
  <meta name="apple-mobile-web-app-title" content="Amaze UI" />
  <link rel="stylesheet" href="<%=basePath %>assets/css/amazeui.min.css"/>
  <link rel="stylesheet" href="<%=basePath %>assets/css/admin.css">
</head>
<body>
<!--[if lte IE 9]>
<p class="browsehappy">你正在使用<strong>过时</strong>的浏览器，Amaze UI 暂不支持。 请 <a href="http://browsehappy.com/" target="_blank">升级浏览器</a>
  以获得更好的体验！</p>
<![endif]-->

<!--[if lte IE 9]>
<p class="browsehappy">你正在使用<strong>过时</strong>的浏览器，Amaze UI 暂不支持。 请 <a href="http://browsehappy.com/" target="_blank">升级浏览器</a>
  以获得更好的体验！</p>
<![endif]-->

<%
	  List<Vector> list=(ArrayList<Vector>)session.getAttribute("orders");
%>

<%
	boolean isLeader = Constant.LoginMsg.isLeader;
	
	if(isLeader == true){
		response.getWriter().println("你不可以访问这个页面哦！");
		response.sendRedirect(Constant.WEB_URL_TIP);	
	}
 %>

<header class="am-topbar admin-header">
  <div class="am-topbar-brand">
    <strong>Tourism</strong> <small>旅游自助后台管理系统</small>
  </div>

  <button class="am-topbar-btn am-topbar-toggle am-btn am-btn-sm am-btn-success am-show-sm-only" data-am-collapse="{target: '#topbar-collapse'}"><span class="am-sr-only">导航切换</span> <span class="am-icon-bars"></span></button>

  <div class="am-collapse am-topbar-collapse" id="topbar-collapse">

    <ul class="am-nav am-nav-pills am-topbar-nav am-topbar-right admin-header-list">
      <li><a href="javascript:;"><span class="am-icon-envelope-o"></span> 收件箱 <span class="am-badge am-badge-warning">5</span></a></li>
      <li class="am-dropdown" data-am-dropdown>
        <a class="am-dropdown-toggle" data-am-dropdown-toggle href="javascript:;">
          <span class="am-icon-caret-down">
          		<img src="images/userPic/face.jpg" alt="" class="am-comment-avatar" width="40" height="40">
          </span>
        </a>
        <ul class="am-dropdown-content">
          <li><a href="#"><span class="am-icon-user"></span> 资料</a></li>
          <li><a href="#"><span class="am-icon-cog"></span> 设置</a></li>
          <li><a href="#"><span class="am-icon-power-off"></span> 退出</a></li>
        </ul>
      </li>
      <li><a href="javascript:;" id="admin-fullscreen"><span class="am-icon-arrows-alt"></span> <span class="admin-fullText">开启全屏</span></a></li>
    </ul>
  </div>
</header>

<div class="am-cf admin-main">
  <!-- sidebar start -->
  <div class="admin-sidebar">
    <ul class="am-list admin-sidebar-list">
      <li><a href="index.jsp"><span class="am-icon-home"></span> 首页</a></li>
      <li class="admin-parent">
        <a class="am-cf" data-am-collapse="{target: '#collapse-nav'}"><span class="am-icon-file"></span> 景点管理 <span class="am-icon-angle-right am-fr am-margin-right"></span></a>
        <ul class="am-list am-collapse admin-sidebar-sub am-in" id="collapse-nav">
          <li><a href="<%=basePath %>view/spot/addLineInfo.jsp" class="am-cf"><span class="am-icon-check"></span> 新增景点<span class="am-icon-star am-fr am-margin-right admin-icon-yellow"></span></a></li>
          <li><a href="<%=basePath %>GetAllSpotInfoServlet"><span class="am-icon-calendar"></span> 景点维护</a></li>
        </ul>
      </li>
      <li class="admin-parent">
        <a class="am-cf" data-am-collapse="{target: '#ul-nav'}"><span class="am-icon-file"></span> 旅游路线管理 <span class="am-icon-angle-right am-fr am-margin-right"></span></a>
        <ul class="am-list am-collapse admin-sidebar-sub am-in" id="ul-nav">
          <li><a href="<%=basePath %>view/line/addLineInfo.jsp" class="am-cf"><span class="am-icon-pencil-square-o"></span> 开发旅游路线<span class="am-icon-star am-fr am-margin-right admin-icon-yellow"></span></a></li>
          <li><a href="<%=basePath %>GetAllLineInfoServlet"><span class="am-icon-calendar"></span> 旅游路线维护</a></li>
        </ul>
      </li>
      <li><a href="login.html"><span class="am-icon-sign-out"></span> 退出系统</a></li>
    </ul>

    <div class="am-panel am-panel-default admin-sidebar-panel">
      <div class="am-panel-bd">
        <p><span class="am-icon-bookmark"></span> 公告</p>
        <p>旅游自助系统的后台管理系统</p>
      </div>
    </div>

    <div class="am-panel am-panel-default admin-sidebar-panel">
      <div class="am-panel-bd">
        <p><span class="am-icon-tag"></span> wiki</p>
        <p>Welcome to the Amaze wiki!</p>
      </div>
    </div>
  </div>
  <!-- sidebar end -->

  <!-- content start -->
  <div class="admin-content">

    <div class="am-cf am-padding">
      <div class="am-fl am-cf">
      	<strong><a href="<%=basePath %>index.jsp">首页</a></strong>/ <small>已处理订单</small>
      </div>
    </div>

    <div class="am-g">
      <div class="am-u-md-6 am-cf">
        <div class="am-fl am-cf">
          <div class="am-btn-toolbar am-fl">
            <div class="am-btn-group am-btn-group-xs">
              <button type="button" class="am-btn am-btn-default"><span class="am-icon-plus"></span> 新增</button>
              <button type="button" class="am-btn am-btn-default"><span class="am-icon-save"></span> 保存</button>
              <button type="button" class="am-btn am-btn-default"><span class="am-icon-archive"></span> 审核</button>
              <button type="button" class="am-btn am-btn-default"><span class="am-icon-trash-o"></span> 删除</button>
            </div>

            <div class="am-form-group am-margin-left am-fl">
              <select>
                <option value="option1">所有类别</option>
                <option value="option2">宗教文化</option>
                <option value="option3">名称古迹</option>
                <option value="option3">自然景点</option>
                <option value="option3">红色旅游</option>
                <option value="option3">历史景点</option>
              </select>
            </div>
          </div>
        </div>
      </div>
      <div class="am-u-md-3 am-cf">
        <div class="am-fr">
          <div class="am-input-group am-input-group-sm">
            <input type="text" class="am-form-field">
                <span class="am-input-group-btn">
                  <button class="am-btn am-btn-default" type="button">搜索</button>
                </span>
          </div>
        </div>
      </div>
    </div>

    <div class="am-g">
      <div class="am-u-sm-12">
        <form class="am-form">
          <table class="am-table am-table-striped am-table-hover table-main">
            <thead>
              <tr>
              	<th>路线图片</th>
                <th>路线名称</th>
                <th>订单时间</th>
                <th>订单状态</th>
                <th>订单总价</th>
                <th>购票人姓名</th>
                <th>操作</th>
              </tr>
          </thead>
          <tbody>
          <%
	if(list!=null&&list.size()>0){
		 for(int i=0;i<list.size();i++){
	%>
            <tr>
              <td><img width="60xp" height="60xp" class="am-img-circle am-img-thumbnail" src=<%=Constant.WEB_SERVER_URL+list.get(i).elementAt(0) %> /></td>
              <td><%=list.get(i).elementAt(1) %></td>
              <td><%=list.get(i).elementAt(2) %></td>
              <%
              
              	if(list.get(i).elementAt(3).toString().equals("0")){
               %>
              	<td>待处理订单</td>
               <%
               }else{
                %>
                <td>已处理接单</td>
                <%
                }
                %>
              <td><%=list.get(i).elementAt(5) %></td>
              <td><%=list.get(i).elementAt(6) %></td>
              <td>
                <div class="am-btn-toolbar">
                  <div class="am-btn-group am-btn-group-xs">
                    <a style="background: white;" href="<%=basePath %>DisposedOrderServlet?order_status=1&id=<%=list.get(i).elementAt(4) %>" class="am-btn am-btn-default am-btn-xs am-text-danger" onclick="{if(confirm('退单成功！')){return true;}return false;}"><span class="am-icon-trash-o"></span>退单</a>
                  </div>
                </div>
              </td>
            </tr>
       <%	
		 }
	 }
		%>     
          </tbody>
        </table>
          <div class="am-cf">
  共 <%=list.size() %> 条记录
  <div class="am-fr">
    <ul class="am-pagination">
      <li class="am-disabled"><a href="#">«</a></li>
      <li class="am-active"><a href="#">1</a></li>
      <li><a href="#">2</a></li>
      <li><a href="#">3</a></li>
      <li><a href="#">4</a></li>
      <li><a href="#">5</a></li>
      <li><a href="#">»</a></li>
    </ul>
  </div>
</div>
          <hr />
          <p>注：旅游订单信息</p>
        </form>
      </div>

    </div>
  </div>
  <!-- content end -->
</div>

<footer>
  <hr>
  <p class="am-padding-center">© 2016 旅游自助系统 <a href="#" target="_blank">后台管理系统</a></p>
</footer>

<!--[if lt IE 9]>
<script src="assets/js/jquery1.11.1.min.js"></script>
<script src="assets/js/modernizr.js"></script>
<script src="assets/js/polyfill/rem.min.js"></script>
<script src="assets/js/polyfill/respond.min.js"></script>
<script src="assets/js/amazeui.legacy.js"></script>
<![endif]-->

<!--[if (gte IE 9)|!(IE)]><!-->
<script src="assets/js/jquery.min.js"></script>
<script src="assets/js/amazeui.min.js"></script>
<!--<![endif]-->
<script src="assets/js/app.js"></script>

</body>
</html>
