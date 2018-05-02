<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'login.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<link href="./assets/css/bootstrap.min.css" rel="stylesheet" />
<link rel="stylesheet" href="./assets/css/font-awesome.min.css" />

<!--[if IE 7]>
		  <link rel="stylesheet" href="./assets/css/font-awesome-ie7.min.css" />
		<![endif]-->

<!-- page specific plugin styles -->

<!-- fonts -->



<!-- ace styles -->

<link rel="stylesheet" href="./assets/css/ace.min.css" />
<link rel="stylesheet" href="./assets/css/ace-rtl.min.css" />
<link rel="stylesheet" href="./assets/css/ace-skins.min.css" />

<!--[if lte IE 8]>
		  <link rel="stylesheet" href="assets/css/ace-ie.min.css" />
		<![endif]-->

<!-- inline styles related to this page -->

<!-- ace settings handler -->

<script src="./assets/js/ace-extra.min.js"></script>
<script src="./assets/js/jquery-2.0.3.min.js"></script>
<script src="./js/bootstrap-closable-tab.js"></script>
<script type="text/javascript">
	window.jQuery
			|| document.write("<script src='assets/js/jquery-2.0.3.min.js'>"
					+ "<"+"/script>");
</script>

<!-- <![endif]-->

<!--[if IE]>
<script type="text/javascript">
 window.jQuery || document.write("<script src='assets/js/jquery-1.10.2.min.js'>"+"<"+"/script>");
</script>
<![endif]-->

<script type="text/javascript">
	if ("ontouchend" in document)
		document.write("<script src='./assets/js/jquery.mobile.custom.min.js'>"
				+ "<"+"/script>");
</script>
<script src="./assets/js/bootstrap.min.js"></script>
<script src="./assets/js/typeahead-bs2.min.js"></script>

<!-- page specific plugin scripts -->

<!-- ace scripts -->

<script src="./assets/js/ace-elements.min.js"></script>
<script src="./assets/js/ace.min.js"></script>
<script type="text/javascript">
	window.onload = function() {
		$(".submenu").attr("style", "display:none;");
		$(".fun").each(function() {
			var $this = $(this);
			tmp = $this.attr("href");
			$this.attr("data", tmp);
			$this.attr("href", "javascript:void(0)");
		});
		$(".fun").click(function() {
			var $this = $(this);
			closableTab.addTab({
				id : $this.attr("id"),
				name : $this.text(),
				url : $this.attr("data"),
				closable : true
			});

		});
	};
</script>
<style type="text/css">
.main-content>.nav {
	position: relative;
	border: none;
	top: 8px;
	left: 8px;
}
}
</style>
</head>

<body>
	<div class="navbar navbar-default" style="height:45px;" id="navbar">
		<script type="text/javascript">
			try {
				ace.settings.check('navbar', 'fixed');
			} catch (e) {
			}
		</script>

		<div class="navbar-container" id="navbar-container">
			<div class="navbar-header pull-left">
				<a href="#" class="navbar-brand"> <small> <i
						class="icon-leaf"></i> 软件缺陷管理
				</small>
				</a>
				<!-- /.brand -->
			</div>
			<!-- /.navbar-header -->

			<div class="navbar-header pull-right" role="navigation">
				<ul class="nav ace-nav">


					<li class="light-blue"><a data-toggle="dropdown" href="#"
						class="dropdown-toggle"> <img class="nav-user-photo"
							src="./img/cat.png" alt="Jason's Photo" /> <span
							class="user-info"> <small>欢迎,</small>
								${sessionScope.name }
						</span> <i class="icon-caret-down"></i>
					</a>

						<ul class="dropdown-menu">
							<li><a href="logout"> <i class="icon-off"></i> 注销
							</a></li>
						</ul></li>
				</ul>
				<!-- /.ace-nav -->
			</div>
			<!-- /.navbar-header -->
		</div>
		<!-- /.container -->
	</div>
	<div class="main-container" id="main-container">
		<div class="main-container-inner">
			<div class="sidebar" id="sidebar">
				<div class="sidebar-shortcuts" id="sidebar-shortcuts">
					<ul class="nav nav-list">
						<li><a href="#" class="dropdown-toggle"> <i
								class="icon-user"></i> <span class="menu-text"> 个人中心</span> <b
								class="arrow icon-angle-down"></b>
						</a>

							<ul class="submenu">
								<shiro:hasAnyRoles name="测试人员,测试主管,开发人员,评审员">
								<li><a href="myTask" id="1" class="fun"> <i
										class="icon-double-angle-right"></i> 我的任务
								</a></li>
								</shiro:hasAnyRoles>
								<li><a href="toPwdModify" id="2" class="fun"> <i
										class="icon-double-angle-right"></i> 密码修改
								</a></li>
								<shiro:hasAnyRoles name="测试人员,测试主管,开发人员,评审员">
								<li><a href="countTask" id="3" class="fun"> <i
										class="icon-double-angle-right"></i> 任务统计
								</a></li>
								</shiro:hasAnyRoles>
							</ul></li>
						<shiro:hasRole name="项目经理">
						<li><a href="#" class="dropdown-toggle"> <i
								class="icon-desktop"></i> <span class="menu-text"> 项目管理</span> <b
								class="arrow icon-angle-down"></b>
						</a>

							<ul class="submenu">
								<li><a href="skipPrj" id="sp" class="fun"> <i
										class="icon-double-angle-right"></i> 项目添加
								</a></li>

								<li><a href="getMyPrj" id="gmp" class="fun"> <i
										class="icon-double-angle-right"></i> 项目查看
								</a></li>
 
								<li><a href="toPrjCount" id="pc" class="fun"> <i
										class="icon-double-angle-right"></i> 项目统计
								</a></li>
							</ul></li>
						</shiro:hasRole>
						<shiro:hasAnyRoles name="测试人员,测试主管,开发人员,评审员">
						<li><a href="#" class="dropdown-toggle"> <i
								class="icon-hdd"></i> <span class="menu-text"> 缺陷事务</span> <b
								class="arrow icon-angle-down"></b>
						</a>

							<ul class="submenu">
								<shiro:hasPermission name="新建缺陷">
								<li><a href="getPrjs" id="7" class="fun"> <i
										class="icon-double-angle-right"></i> 缺陷添加
								</a></li>
								</shiro:hasPermission>
								<shiro:hasPermission name="确认缺陷是否存在">
								<li><a href="getForExistBugs" id="8" class="fun"> <i
										class="icon-double-angle-right"></i> 缺陷确认
								</a></li>
								</shiro:hasPermission>
								<shiro:hasPermission name="缺陷分配">
								<li><a href="getForAssignBugs" id="9" class="fun"> <i
										class="icon-double-angle-right"></i> 缺陷分配
								</a></li>
								</shiro:hasPermission>
								<shiro:hasPermission name="缺陷修复">
								<li><a href="getForFixBugs" id="10" class="fun"> <i
										class="icon-double-angle-right"></i> 缺陷修复
								</a></li>
								</shiro:hasPermission>
								<shiro:hasPermission name="确认缺陷是否修复">
								<li><a href="getForValBugs" id="11" class="fun"> <i
										class="icon-double-angle-right"></i> 缺陷验证
								</a></li>
								</shiro:hasPermission>
								<shiro:hasPermission name="评审">
								<li><a href="getForAplBugs" id="12" class="fun"> <i
										class="icon-double-angle-right"></i> 缺陷评审
								</a></li>
								</shiro:hasPermission>
				
							</ul></li>
							</shiro:hasAnyRoles>
							<shiro:hasRole name="系统管理员">
							<li><a href="#" class="dropdown-toggle"> <i
								class="icon-key"></i> <span class="menu-text"> 全局管理</span> <b
								class="arrow icon-angle-down"></b>
						</a>

							<ul class="submenu">
								<li><a href="toBug" id="bug" class="fun"> <i
										class="icon-double-angle-right"></i> 缺陷管理
								</a></li>

								<li><a href="toPrj" id="prj" class="fun"> <i
										class="icon-double-angle-right"></i> 项目管理
								</a></li>

								<li><a href="toLog" id="log" class="fun"> <i
										class="icon-double-angle-right"></i> 日志管理
								</a></li>
								<li><a href="toRole" id="role" class="fun"> <i
										class="icon-double-angle-right"></i> 权限管理
								</a></li>
								<li><a href="toUser" id="user" class="fun"> <i
										class="icon-double-angle-right"></i> 用户管理
								</a></li>
							</ul></li>
							</shiro:hasRole>
					</ul>
				</div>
			</div>
			<div class="main-content">
				<ul class="nav nav-tabs" role="tablist">
				</ul>
				<div class="tab-content" style="width:100%;"></div>
			</div>
		</div>
</body>
</html>
