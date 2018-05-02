<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
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
<script type="text/javascript" src="./js/jquery-3.2.1.min.js"></script>
<link href="./css/bootstrap.min.css" rel="stylesheet">
<script type="text/javascript" src="./js/bootstrap.min.js"></script>
<link href="./css/carousel.css" rel="stylesheet">
<style type="text/css">
* {
	font-family: "微软雅黑";
}

body {
	padding: 0px;
	margin: 0px;
}

a,a:hover {
	text-decoration: none;
}
</style>
<script type="text/javascript">
	function addBug() {
		window.location.href = 'getPrjs';
	}
	function existBug() {
		window.location.href = 'getForExistBugs';
	}
	function assignBug() {
		window.location.href = 'getForAssignBugs';
	}
	function fixBug() {
		window.location.href = 'getForFixBugs';
	}
	function valBug() {
		window.location.href = 'getForValBugs';
	}
	function addPrj() {
		window.location.href = 'skipPrj';
	}
	function aplBug() {
		window.location.href = 'getForAplBugs';
	}
	function noAccess() {
		alert('没有相关权限！');
	}
	window.onload = function() {
		$.getJSON("getAccess", function(data) {
			$.each(data, function(i, n) {
				if (n) {
				} else {
					$(".img-circle").eq(i.slice(2) - 1).attr("onclick",
							"noAccess()");
				}
			});
		});

		$.getJSON("isManager", function(data) {
			if (data.result) {
			} else {

				$("#prj").attr("onclick", "noAccess()");
			}
		});

	};
</script>
</head>

<body>
	<header> <nav class="navbar navbar-default">
	<div class="container-fluid">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
				aria-expanded="false">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="#"><i
				class="glyphicon glyphicon-cloud"></i>软件缺陷管理</a>
		</div>

		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<li class=""><a href="#">首页 <span class="sr-only">(current)</span>
				</a></li>
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-haspopup="true"
					aria-expanded="false">个人中心 <span class="caret"></span>
				</a>
					<ul class="dropdown-menu">
						<li><a href="myTask">我的任务</a></li>
						<li role="separator" class="divider"></li>
						<li><a href="#">密码修改</a></li>
						<li role="separator" class="divider"></li>
						<li><a href="countTask">任务统计</a></li>
					</ul></li>
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-haspopup="true"
					aria-expanded="false">项目管理 <span class="caret"></span>
				</a>
					<ul class="dropdown-menu">
						<li><a href="getMyPrj">查看项目</a></li>
						<li role="separator" class="divider"></li>
						<li><a href="#">项目统计</a></li>
						<li role="separator" class="divider"></li>
						<li><a href="#">项目添加</a></li>
					</ul></li>

			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li><a class="btn btn-warning" href="#">注销</a></li>
			</ul>
		</div>
		<!-- /.navbar-collapse -->
	</div>
	<!-- /.container-fluid --> </nav> </header>
	<main role="main">
	<div class="container marketing">
		<div class="row">
			<div class="col-lg-4">
				<img class="img-circle" src="./img/21.jpg" onclick="addBug()" alt=""
					width="140" height="140">
				<h2>新建</h2>
			</div>
			<div class="col-lg-4">
				<img class="img-circle" src="./img/21.jpg" alt="" width="140"
					onclick="existBug()" height="140">
				<h2>确认存在</h2>
			</div>
			<div class="col-lg-4">
				<img class="img-circle" src="./img/21.jpg" alt="" width="140"
					onclick="assignBug()" height="140">
				<h2>分配</h2>
			</div>
			<div class="col-lg-3">
				<img class="img-circle" src="./img/21.jpg" alt="" width="140"
					onclick="fixBug()" height="140">
				<h2>修复</h2>
			</div>
			<div class="col-lg-3">
				<img class="img-circle" src="./img/21.jpg" alt="" width="140"
					onclick="valBug()" height="140">
				<h2>确认修复</h2>
			</div>
			<div class="col-lg-3">
				<img class="img-circle" src="./img/21.jpg" alt="" width="140"
					onclick="aplBug()" height="140">
				<h2>评审</h2>
			</div>
			<div class="col-lg-3">
				<img id="prj" class="img-circle" src="./img/21.jpg" alt=""
					onclick="addPrj()" width="140" height="140">
				<h2>项目添加</h2>
			</div>
		</div>
	</div>
	</main>

</body>
</html>
