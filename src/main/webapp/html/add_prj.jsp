<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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

<title>My JSP 'addBug.jsp' starting page</title>

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
<script type="text/javascript" src="./js/sweetalert.min.js"></script>
<script type="text/javascript">
	function addPrj(){
		$.ajax({
			type : "POST",
			dataType : "json",
			url : "addPrj",
			data : $("#myForm").serialize(),
			success : function(data){
				if(data.result==1){
					swal({
					title : data.message,
					icon : "success"});
					$('#myForm')[0].reset();
				}
				else{
					swal({
					title : data.message,
					icon : "error"});
				}
				
			}
		});
	}
</script>
</head>

<body>
	<form class="form-horizontal" onsubmit="return false" id="myForm" method="post" action="##" method="post" role="form">
		<fieldset>
			<div id="legend" class="text-center">
				<legend class="">项目添加</legend>
			</div>
		</fieldset>
		<div class="form-group">
			<label for="name" class="col-sm-3 control-label">项目名称</label>
			<div class="col-sm-6">
				<input type="text" class="form-control" name="name" id="name" placeholder="" required>
			</div>
		</div>
		<div class="form-group">
			<label for="description" class="col-sm-3 control-label">项目描述</label>
			<div class="col-sm-6">
				<textarea id="description" name="description" class="form-control" rows="5" required></textarea>
			</div>
		</div>
		<div class="form-group">
			<div class="col-sm-offset-8 col-sm-8">
				<button onclick="addPrj();" class="btn btn-success btn-lg">添加</button>
			</div>
		</div>
	</form>
</body>
</html>
