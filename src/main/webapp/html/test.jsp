<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
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

<title>My JSP 'mytask.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript" src="./js/jquery-3.2.1.min.js"></script>
<link href="./css/bootstrap.min.css" rel="stylesheet">
<link href="./css/bootstrap-select.css" rel="stylesheet">
<script type="text/javascript" src="./js/bootstrap.min.js"></script>
<script type="text/javascript" src="./js/bootstrap-select.js"></script>
<script type="text/javascript" src="./js/bootstrap-table.js"></script>

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

.task-sa {
	padding: 10px;
	font-size: 15px;
	color: #fff;
}
</style>
<script type="text/javascript">
	window.onload = function() {
		$.ajax({
			type : "POST",
			url : "getPrj?cp=-1&ps=-1",
			success : function(data){
				alert(JSON.stringify(data));
				
			}
		});
		

	};
</script>

</head>

<body>
	<nav class="navbar navbar-default">
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
					aria-expanded="false">个人中心 <span class="caret"></span> </a>
					<ul class="dropdown-menu">
						<li><a href="#">我的任务</a></li>
						<li role="separator" class="divider"></li>
						<li><a href="#">密码修改</a></li>
						<li role="separator" class="divider"></li>
						<li><a href="#">任务统计</a></li>
					</ul>
				</li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li><a class="btn btn-warning" href="#">注销</a></li>
			</ul>
		</div>
		<!-- /.navbar-collapse -->
	</div>
	<!-- /.container-fluid --> </nav>
	<div class="row">
		<div class="dropdown col-lg-2 task-sa bg-success text-center">
			<a href="#" class="dropdown-toggle" data-toggle="dropdown"
				role="button" aria-haspopup="true" aria-expanded="false">筛选任务<span
				class="caret"></span> </a>
			<ul class="dropdown-menu">
				<li><a href="#">已处理</a></li>
				<li role="separator" class="divider"></li>
				<li><a href="#">未处理</a></li>
				<li role="separator" class="divider"></li>
				<li><a href="#">指定项目</a></li>
			</ul>
		</div>
		<div class="table-responsive col-lg-10">
			<table class="table table-hover" id="mytable">

			</table>
		</div>
	</div>
</body>
</html>
