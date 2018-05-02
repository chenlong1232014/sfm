<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
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
	function addBug() {
		$.ajax({
			type : "POST",
			dataType : "json",
			url : "addBug",
			data : $("#myForm").serialize(),
			success : function(data) {
				if (data.result == 1) {
					swal({
						title : data.message,
						icon : "success"
					});
					$('#myForm')[0].reset();
				} else {
					swal({
						title : data.message,
						icon : "error"
					});
				}

			}
		});
	}
	function getPrj() {
		$.ajax({
			type : "POST",
			dataType : "json",
			url : "getDetailPrj",
			data : {
				"pid" : $('#prj_id').val()
			},
			success : function(data) {
				$('#name').html(data.name);
				$('#date').html(data.date);
				$('#status').html(data.status);
				$('#desc').html(data.desc);
				$('#prj_modal').modal('show');
			}
		});
	}
</script>
</head>

<body>
	<form class="form-horizontal" onsubmit="return false" id="myForm"
		method="post" action="##" method="post" role="form">
		<fieldset>
			<div id="legend" class="text-center">
				<legend class="">新建Bug</legend>
			</div>
		</fieldset>
		<div class="form-group">
			<label for="prj_id" class="col-sm-3 control-label">所属项目 </label>
			<div class="col-sm-5">
				<select name="prj_id" id="prj_id"
					class="selectpicker show-tick form-control" data-width="98%"
					data-first-option="false" title='请选择项目(必选)' required
					data-live-search="true">
					<c:forEach var="prj" items="${prjs }">
						<option value="${prj.pid }">${prj.name }</option>
					</c:forEach>
				</select>
			</div>
			<div class="col-sm-2">
				<button onclick="getPrj();" type="button" class="btn btn-info">查看详细</button>
			</div>
		</div>
		<div class="form-group">
			<label for="bugTitle" class="col-sm-3 control-label">Bug名称</label>
			<div class="col-sm-6">
				<input type="text" class="form-control" name="bugTitle"
					id="bugTitle" placeholder="" required>
			</div>
		</div>
		<div class="form-group">
			<label for="bugDesc" class="col-sm-3 control-label">Bug描述</label>
			<div class="col-sm-6">
				<textarea id="bugDesc" name="bugDesc" class="form-control" rows="5"
					required></textarea>
			</div>
		</div>
		<div class="form-group">
			<div class="col-sm-offset-8 col-sm-8">
				<button onclick="addBug();" class="btn btn-success btn-lg">添加</button>
			</div>
		</div>
	</form>
	<div class="modal fade" id="prj_modal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">×</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">项目详细</h4>
				</div>
				<div class="modal-body">
					<div class="table-responsive">
						<table class="table table-hover" >
							<tr>
								<td>负责人</td>
								<td id="name"></td>
							</tr>
							<tr>
								<td>描述</td>
								<td id="desc"></td>
							</tr>
							<tr>
								<td>开始测试日期</td>
								<td id="date"></td>
							</tr>
							<tr>
								<td>状态</td>
								<td id="status"></td>
							</tr>
						</table>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
