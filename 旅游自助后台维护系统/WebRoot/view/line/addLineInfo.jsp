<%@page import="com.tsw.Constant"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html class="no-js">
  <head>
    <base href="<%=basePath%>">
    
    <title>旅旅自助系统之后台管理系统</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	 
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>Amaze UI Admin user Examples</title>
	<meta name="description" content="这是一个 user 页面">
	<meta name="keywords" content="user">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
	<meta name="renderer" content="webkit">
	<meta http-equiv="Cache-Control" content="no-siteapp" />
	<link rel="icon" type="image/png" href="assets/i/favicon.png">
	<link rel="apple-touch-icon-precomposed" href="assets/i/app-icon72x72@2x.png">
	<meta name="apple-mobile-web-app-title" content="Amaze UI" />
	<link rel="stylesheet" href="assets/css/amazeui.min.css"/>
	<link rel="stylesheet" href="assets/css/admin.css">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" charset="utf-8" src="<%=basePath %>ueditor1_4_3_2/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="<%=basePath %>ueditor1_4_3_2/ueditor.all.min.js"> </script>
    <!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
    <!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
    <script type="text/javascript" charset="utf-8" src="<%=basePath %>ueditor1_4_3_2/lang/zh-cn/zh-cn.js"></script>

	<script src="<%=basePath %>js/bootstrap.min.js"></script>
	<script src="<%=basePath %>js/jquery-1.9.0.js"></script>
	
	<link rel="stylesheet" type="text/css" href="css/progress.css">
<script type="text/javascript" src="js/uploadAjax.js" charset="UTF-8"></script>
	
	
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
          <li><a href="<%=basePath %>view/spot/addSpotInfo.jsp" class="am-cf"><span class="am-icon-check"></span> 新增景点<span class="am-icon-star am-fr am-margin-right admin-icon-yellow"></span></a></li>
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
      	<strong class="am-text-primary am-text-lg"><a href="<%=basePath %>GetAllLineInfoServlet">旅游路线管理</a></strong> / <small>开发新旅游路线</small>
      </div>
    </div>

    <hr/>
    
    <div class="am-g">
      <div class="am-u-sm-12" align="left">
    	<div class="am-form-group">
		<form action="<%=basePath %>UploadLineImgServlet" name="f1" id="f1" method="post" enctype="multipart/form-data" target="if">
	 		<div class="field">
                <a class="button input-file" href="javascript:void(0);">+ 浏览文件<input size="30" type="file" name="logo" data-validate="required:请选择上传文件,regexp#.+.(jpg|jpeg|png|gif)$:只能上传jpg|gif|png格式文件" /></a>
             </div>
             <br>
             <input type="reset" name="res1" value="重置"  class="am-btn am-btn-primary" />
             <input type="submit" name="but1" value="提交" onclick="go();" class="am-btn am-btn-primary" />
		</form><br>
		<div id="pro" class="pro" align="left" style="height:1px;width:600px;">
 		<img alt="" src="photo/progress.png" width="0px" id="imgpro">
		</div>
  		<span id="prop" style="width:15px;height:15px;display: none;">0%</span>
  		<br><br>
		<iframe id="if" name="if" src="" style="display: none"></iframe>
		
	   </div>
     </div>
   </div>
    

    <div class="am-g">

      <div class="am-u-sm-12 am-u-md-4 am-u-md-push-8">
        <div class="am-panel am-panel-default">
          <div class="am-panel-bd">
            <div class="am-g">
              <div class="am-u-md-4">
                <img class="am-img-circle am-img-thumbnail" src="<%=basePath %>images/spotImg.png" alt=""/>
              </div>
              <div class="am-u-md-8">
                	旅游公司系统管理员需要按照协议管理系统
              </div>
            </div>
          </div>
        </div>

        <div class="am-panel am-panel-default">
          <div class="am-panel-bd">
            <div class="user-info">
              <p>用户协议</p>
            </div>
            <div class="user-info">
              <p>管理员不可以窃取用户信息</p>
              <p>景点开发需要保护好当地环境</p>
            </div>
          </div>
        </div>

      </div>

      <div class="am-u-sm-12 am-u-md-8 am-u-md-pull-4">
        <form class="am-form am-form-horizontal" action="<%=basePath %>AddLineInfoServlet">
          <div class="am-form-group">
            <label class="am-u-sm-3 am-form-label">景点名称</label>
            <div class="am-u-sm-9">
              <input type="text" placeholder="景点名称" name="name" data-validate="required:请填写景点名称">
            </div>
          </div>

          <div class="am-form-group">
            <label class="am-u-sm-3 am-form-label">餐饮</label>
            <div class="am-u-sm-9">
              <input type="text" placeholder="输入餐饮信息" name="catering" data-validate="required:请填写餐饮信息">
            </div>
          </div>

          <div class="am-form-group">
            <label class="am-u-sm-3 am-form-label">住宿</label>
            <div class="am-u-sm-9">
              <input type="text" placeholder="输入住宿信息" name="accommodation" data-validate="required:请输入住宿信息">
            </div>
          </div>
          
           <div class="am-form-group">
            <label class="am-u-sm-3 am-form-label">交通</label>
            <div class="am-u-sm-9">
              <input type="text" placeholder="输入交通信息" name="traffic" data-validate="required:请输入交通信息">
            </div>
          </div>
          
           <div class="am-form-group">
            <label class="am-u-sm-3 am-form-label">价钱</label>
            <div class="am-u-sm-9">
              <input type="text" placeholder="输入价钱信息" name="price" data-validate="required:请输入价钱信息">
            </div>
          </div>
          <!-- 
          <div class="am-form-group">
           <label class="am-u-sm-3 am-form-label">图片</label>
          </div>
			-->          
           <div class="am-form-group">
            <label class="am-u-sm-3 am-form-label">电话</label>
            <div class="am-u-sm-9">
              <input type="text" placeholder="输入联系信息" name="contact" data-validate="required:请输入联系信息">
            </div>
          </div>
          
          <div class="am-form-group">
          	<label class="am-u-sm-3 am-form-label">出游时间</label>
            <div class="am-u-sm-9">
              <input type="date" placeholder="输入出游时间" name="time" data-validate="required:请输入出游时间">
            </div>
          </div>


          <div class="am-form-group">
            <div class="am-u-sm-9 am-u-sm-push-3">
              <button type="submit" class="am-btn am-btn-primary">保存信息</button>
            </div>
          </div>
          
          
          
        </form>
      </div>
    </div>
  </div>
  <!-- content end -->




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
