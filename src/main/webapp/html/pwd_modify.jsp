<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<% 
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'pwd_modify.jsp' starting page</title>
    
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
<script type="text/javascript" src="./js/bootstrap.min.js"></script>
<script type="text/javascript" src="./js/sweetalert.min.js"></script>
<script type="text/javascript">
	function pwdModify(){
		$.ajax({
			type : "POST",
			dataType : "json",
			url : "pwdModify",
			data : $("#myForm").serialize(),
			success : function(data){
				if(data.result==1){
					swal({
					title : data.message,
					icon : "success"});
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
  <form class="form-horizontal" onsubmit="return false" id="myForm" method="post" action="##" role="form">
		<fieldset>
			<div id="legend" class="text-center">
				<legend class="">密码修改</legend>
			</div>
		</fieldset>
		<div class="form-group">
				<label for="oldPwd" class="col-sm-3 control-label">旧密码</label>
			<div class="col-sm-6">
				<input type="text" class="form-control" name="oldPwd" id="oldPwd" placeholder="" required>
			</div>
		</div>
		<div class="form-group">
			<label for="newPwd" class="col-sm-3 control-label">新密码</label>
			<div class="col-sm-6">
				<input type="text" class="form-control" name="newPwd" id="newPwd" placeholder="" required>
			</div>
		</div>
		<div class="form-group">
			<label for="newPwdMore" class="col-sm-3 control-label">再次输入新密码</label>
			<div class="col-sm-6">
				<input type="text" class="form-control" name="newPwdMore" id="newPwdMore" placeholder="" required>
			</div>
		</div>
		<div class="form-group">
			<div class="col-sm-offset-8 col-sm-8">
				<button onclick="pwdModify();" class="btn btn-success btn-lg">提交</button>
			</div>
		</div>
	</form>
  </body>
</html>
