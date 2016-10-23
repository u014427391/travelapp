<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html class="no-js">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>旅旅自助系统之后台管理系统</title>
  <meta name="description" content="这是一个 index 页面">
  <meta name="keywords" content="index">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
  <meta name="renderer" content="webkit">
  <meta http-equiv="Cache-Control" content="no-siteapp" />
  <link rel="icon" type="image/png" href="assets/i/favicon.png">
  <link rel="apple-touch-icon-precomposed" href="assets/i/app-icon72x72@2x.png">
  <meta name="apple-mobile-web-app-title" content="Amaze UI" />
  <link rel="stylesheet" href="assets/css/amazeui.min.css"/>
  <link rel="stylesheet" href="assets/css/admin.css">
</head>
<body>
<!--[if lte IE 9]>
<p class="browsehappy">你正在使用<strong>过时</strong>的浏览器，Amaze UI 暂不支持。 请 <a href="http://browsehappy.com/" target="_blank">升级浏览器</a>
  以获得更好的体验！</p>
<![endif]-->

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
      <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">首页</strong> / <small>后台管理</small></div>
    </div>

    <ul class="am-avg-sm-1 am-avg-md-4 am-margin am-padding am-text-center admin-content-list ">
      <li><a href="GetAllUnDisposedOrderServlet" class="am-text-success"><span class="am-icon-btn am-icon-file-text"></span><br/>新增订单<br/>2</a></li>
      <li><a href="GetAllDisposedOrderServlet" class="am-text-warning"><span class="am-icon-btn am-icon-briefcase"></span><br/>成交订单<br/>3</a></li>
      <li><a href="#" class="am-text-danger"><span class="am-icon-btn am-icon-recycle"></span><br/>昨日访问<br/>3</a></li>
      <li><a href="#" class="am-text-secondary"><span class="am-icon-btn am-icon-user-md"></span><br/>APP用户<br/>10</a></li>
    </ul>

    <div class="am-g">
      <div class="am-u-sm-12">
        <table class="am-table am-table-bd am-table-striped admin-content-table">
          <thead>
          <tr>
            <th>ID</th><th>用户名</th><th>成交订单</th><th>管理</th>
          </tr>
          </thead>
          <tbody>
          <tr><td>1</td><td>John Clark</td><td><span class="am-badge am-badge-success">+20</span></td>
            <td>
              <div class="am-dropdown" data-am-dropdown>
                <button class="am-btn am-btn-default am-btn-xs am-dropdown-toggle" data-am-dropdown-toggle><span class="am-icon-cog"></span> <span class="am-icon-caret-down"></span></button>
                <ul class="am-dropdown-content">
                  <li><a href="#">1. 封号</a></li>
                  <li><a href="#">2. 开启</a></li>
                </ul>
              </div>
            </td>
          </tr>
          <tr><td>2</td><td>风清扬</td><td><span class="am-badge am-badge-danger">+2</span></td>
            <td>
              <div class="am-dropdown" data-am-dropdown>
                <button class="am-btn am-btn-default am-btn-xs am-dropdown-toggle" data-am-dropdown-toggle><span class="am-icon-cog"></span> <span class="am-icon-caret-down"></span></button>
                <ul class="am-dropdown-content">
                   <li><a href="#">1. 封号</a></li>
                  <li><a href="#">2. 开启</a></li>
                </ul>
              </div>
            </td>
          </tr>
          <tr><td>3</td><td>詹姆斯</td><td><span class="am-badge am-badge-warning">+10</span></td>
            <td>
              <div class="am-dropdown" data-am-dropdown>
                <button class="am-btn am-btn-default am-btn-xs am-dropdown-toggle" data-am-dropdown-toggle><span class="am-icon-cog"></span> <span class="am-icon-caret-down"></span></button>
                <ul class="am-dropdown-content">
                  <li><a href="#">1. 封号</a></li>
                  <li><a href="#">2. 开启</a></li>
                </ul>
              </div>
            </td>
          </tr>
          <tr><td>4</td><td>云适配</td><td><span class="am-badge am-badge-secondary">+50</span></td>
            <td>
              <div class="am-dropdown" data-am-dropdown>
                <button class="am-btn am-btn-default am-btn-xs am-dropdown-toggle" data-am-dropdown-toggle><span class="am-icon-cog"></span> <span class="am-icon-caret-down"></span></button>
                <ul class="am-dropdown-content">
                  <li><a href="#">1. 封号</a></li>
                  <li><a href="#">2. 开启</a></li>
                </ul>
              </div>
            </td>
          </tr>

          <tr>
            <td>5</td><td>呵呵呵</td>
            <td><span class="am-badge">+22</span></td>
            <td>
              <div class="am-dropdown" data-am-dropdown>
                <button class="am-btn am-btn-default am-btn-xs am-dropdown-toggle" data-am-dropdown-toggle><span class="am-icon-cog"></span> <span class="am-icon-caret-down"></span></button>
                <ul class="am-dropdown-content">
                  <li><a href="#">1. 封号</a></li>
                  <li><a href="#">2. 开启</a></li>
                </ul>
              </div>
            </td>
          </tr>
          </tbody>
        </table>
      </div>
    </div>

        <div class="am-panel am-panel-default">
          <div class="am-panel-hd am-cf" data-am-collapse="{target: '#collapse-panel-3'}">留言<span class="am-icon-chevron-down am-fr" ></span></div>
          <div class="am-panel-bd am-collapse am-in am-cf" id="collapse-panel-3">
            <ul class="am-comments-list admin-content-comment">
              <li class="am-comment">
                <a href="#"><img src="images/userPic/face.jpg" alt="" class="am-comment-avatar" width="48" height="48"></a>
                <div class="am-comment-main">
                  <header class="am-comment-hd">
                    <div class="am-comment-meta"><a href="#" class="am-comment-author">某人</a> 评论于 <time>2014-7-12 15:30</time></div>
                  </header>
                  <div class="am-comment-bd"><p>白鹭洲城市公园：晚八点音乐喷泉，每周末文化广场活动，均有专业乐队或歌舞表演。</p>
                  </div>
                </div>
              </li>
			  <li class="am-comment">
                <a href="#"><img src="images/userPic/face.jpg" alt="" class="am-comment-avatar" width="48" height="48"></a>
                <div class="am-comment-main">
                  <header class="am-comment-hd">
                    <div class="am-comment-meta"><a href="#" class="am-comment-author">某人</a> 评论于 <time>2014-7-12 15:30</time></div>
                  </header>
                  <div class="am-comment-bd"><p>南华路文化吧一条街：这条路上都是五十年代的归国华侨建的老别墅，都具有典型的闽南民居的特色。街区夹在厦门大学和鹭江大学中间，依山伴海，人文气息浓厚，目前已成为厦门文化氛围最浓的一条路，有很多有特色的文化吧和咖啡馆。由于离大学近，这里的消费人群通常都是些教授、学生、喜欢艺术或文学的青年，现在也成为白领们聚集的地方。 </p>
                  </div>
                </div>
              </li>
            </ul>
            <ul class="am-pagination am-fr admin-content-pagination">
              <li class="am-disabled"><a href="#">&laquo;</a></li>
              <li class="am-active"><a href="#">1</a></li>
              <li><a href="#">2</a></li>
              <li><a href="#">3</a></li>
              <li><a href="#">4</a></li>
              <li><a href="#">5</a></li>
              <li><a href="#">&raquo;</a></li>
            </ul>
          </div>
          
          
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
