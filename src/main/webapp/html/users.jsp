<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
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
<script type="text/javascript" src="./js/bootstrap.min.js"></script>
<script type="text/javascript" src="./js/bootstrap-table.js"></script>
<script type="text/javascript" src="./js/bootstrap-table-zh-CN.js"></script>
<script type="text/javascript" src="./js/bootstrap-table-toolbar.js"></script>
<script type="text/javascript" src="./js/sweetalert.min.js"></script>

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

.search {
	margin-bottom: 10px;
	margin-right: 10px;
}

.btn {
	margin-right: 10px;
}
</style>
<script type="text/javascript">
	window.onload = function() {

		initTable('getUsers');
		initRoleTable();

	};
	function initTable(url) {
		var t = $("#mytable")
				.bootstrapTable(
						{
							url : url,
							classes : 'table table-bordered',
							method : 'get',
							dataType : "json",
							striped : true,//设置为 true 会有隔行变色效果  
							undefinedText : "空",//当数据为 undefined 时显示的字符  
							pagination : true, //分页  
							toolbar : "#toolbar",
							// paginationLoop:true,//设置为 true 启用分页条无限循环的功能。  
							//showToggle: "true",//是否显示 切换试图（table/card）按钮  
							//showColumns: "true",//是否显示 内容列下拉框  
							pageNumber : 1,//如果设置了分页，首页页码  
							// showPaginationSwitch:true,//是否显示 数据条数选择框  
							pageSize : 5,//如果设置了分页，页面数据条数  
							pageList : [ 5, 10 ], //如果设置了分页，设置可供选择的页面数据条数。设置为All 则显示所有记录。  
							paginationPreText : '‹',//指定分页条中上一页按钮的图标或文字,这里是<  
        paginationNextText: '›',//指定分页条中下一页按钮的图标或文字,这里是>  
							// singleSelect: false,//设置True 将禁止多选  
							search : true, //显示搜索框  
							searchOnEnterKey : true,
							searchText : '输入你想搜索的内容',//初始化搜索文字
							searchAlign : 'right',//指定 搜索框 水平方向的位置。'left' or 'right'	
							data_local : "zh-CN",//表格汉化  
							sidePagination : "server", //服务端处理分页  
							showRefresh : true, //显示刷新按钮
							formatLoadingMessage : function() {
								return '请稍等，正在加载中...';
							},
							formatNoMatches : function() { //没有匹配的结果  
								return '没有相关记录';
							},
							onLoadError : function(data) {
								$('#reportTable').bootstrapTable('removeAll');
							},
							queryParams : function(params) {//自定义参数，这里的参数是传给后台的，我这是是分页用的  
								return {//这里的params是table提供的  
									cp : params.offset / params.limit,//从数据库第几条记录开始  
									ps : params.limit,
									search : params.search
								//找多少条  
								};
							},
							idField : "id",//指定主键列  
							uniqueId : "id",
							columns : [
									{
										checkbox : true,
										valign : 'middle'
									},
									{
										title : '#',//行号
										formatter : function(value, row, index) {
											return index + 1;
										},
										align : 'center',//水平居中 
										valign : 'middle',
										sortable : false,
										searchable : false
									},
									{
										title : '用户名',
										field : 'account',
										align : 'center',
										valign : 'middle',
										sortable : true,
										searchable : true

									},
									{
										title : '姓名',
										field : 'name',
										align : 'center',
										valign : 'middle',
										sortable : true,
										searchable : true
									},
									{
										title : '密码',
										field : 'password',
										valign : 'middle',
										align : 'center',
									},
									{
										title : '性别',
										field : 'sex',
										valign : 'middle',
										align : 'center',
									},
									{
										title : '电话',
										field : 'telephone',
										valign : 'middle',
										align : 'center',
									},
									{
										title : '操作',
										field : 'id',
										align : 'center',
										valign : 'middle',
										sortable : false,
										searchable : false,
										formatter : function(value, row, index) {//自定义显示可以写标签哦~ 
											return '<button class="btn btn-info" href="#" mce_href="#" onclick="showEdit(\''
													+ row.id
													+ '\')">修改</button><button class="btn btn-warning" href="#" mce_href="#" onclick="showGrantRole(\''
													+ row.id
													+ '\')">角色配置</button> ';
										}
									}

							/*  {  
							      title: '真实姓名',  
							      field: 'realName',  
							      align: 'center'  
							  },  
							  {  
							      //EMAIL  
							      title: '邮箱',  
							      field: 'email',  
							      align: 'center'  
							  },  
							  {  
							      //部门名字  
							      title: '部门',  
							      field: 'dept.dname',//可以直接取到属性里面的属性，赞  
							      align: 'center'  
							  },  
							  {  
							      title: '状态',  
							      field: 'userStatus',  
							      align: 'center',  
							      formatter: function (value, row, index) {//自定义显示，这三个参数分别是：value该行的属性，row该行记录，index该行下标  
							          return row.userStatus == 0 ? "正常" : row.userStatus == 1 ? "请假" : "离职";  
							      }  
							
							  },  
							  {  
							      title: '操作',  
							      field: 'userId',  
							      align: 'center',  
							      formatter: function (value, row, index) {//自定义显示可以写标签哦~  
							          return '<a href="#" mce_href="#" onclick="edit(\'' + row.userId + '\')">操作</a> ';  
							      }  
							  }*/

							]
						});

		t.on('load-success.bs.table', function(data) {//table加载成功后的监听函数  
			console.log("load success");
		});
	}
	function showEdit(id) {
		var row = $('#mytable').bootstrapTable('getRowByUniqueId', id);
		$("#name").val(row.name);
		$("#uid").val(row.id);
		if (row.sex == '女') {
			document.getElementById('women').checked = true;
		} else {
			document.getElementById('man').checked = true;
		}
		$("#account").val(row.account);
		$("#password").val(row.password);
		$("#telephone").val(row.telephone);
		$('#modal').modal('show');
	}
	function uptUser() {
		$.ajax({
			type : "POST",
			dataType : "json",
			url : "userUpt",
			data : $("#userForm").serialize(),
			success : function(data) {
				if (data.result == 1) {
					$("#mytable").bootstrapTable('refresh');
					$('#modal').modal('hide');
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
	function showGrantRole(id) {
		$("#roletable").bootstrapTable('uncheckAll');
		var row = $('#mytable').bootstrapTable('getRowByUniqueId', id);
		$.each(row.roles, function(i, val) {
			$("#roletable").bootstrapTable("checkBy", {
				field : "id",
				values : [ val.rid ]
			});
		});
		$("#r_uid").val(id);
		$('#r_modal').modal('show');
	}
	function grantUser() {
		var id = $("#r_uid").val();
		var rows = $("#roletable").bootstrapTable('getSelections');
		var ids = [];
		$.each(rows, function(i, val) {
			ids.push(val.id);
		});
		$.ajax({
			type : 'POST',
			url : 'userGrant',
			traditional : true,
			data : {
				uid : id,
				rids : ids
			},
			dataType : "json",
			success : function(data) {
				if (data.result == 1) {
					$("#mytable").bootstrapTable('refresh');
					$('#r_modal').modal('hide');
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
	function initRoleTable() {
		var t = $("#roletable").bootstrapTable({
			url : 'getRoles',
			classes : 'table table-bordered',
			method : 'get',
			dataType : "json",
			striped : true,//设置为 true 会有隔行变色效果  
			undefinedText : "空",//当数据为 undefined 时显示的字符  
			pagination : false, //分页  
			data_local : "zh-CN",//表格汉化  
			sidePagination : "server", //服务端处理分页  
			formatLoadingMessage : function() {
				return '请稍等，正在加载中...';
			},
			formatNoMatches : function() { //没有匹配的结果  
				return '没有相关记录';
			},
			onLoadError : function(data) {
				$('#reportTable').bootstrapTable('removeAll');
			},
			idField : "id",//指定主键列  
			uniqueId : "id",
			columns : [ {
				checkbox : true,
				valign : 'middle'
			}, {
				title : '#',//行号
				formatter : function(value, row, index) {
					return index + 1;
				},
				align : 'center',//水平居中 
				valign : 'middle',
				sortable : false,
				searchable : false
			}, {
				title : '角色名称',
				field : 'roleName',
				align : 'center',
				valign : 'middle',
				sortable : true,
				searchable : true
			}

			]
		});

		t.on('load-success.bs.table', function(data) {//table加载成功后的监听函数  
			console.log("load success");
		});
	}

	function showAdd() {
		$('#add_modal').modal('show');
	}
	function addUser() {
		$.ajax({
			type : 'POST',
			url : 'userAdd',
			data : $("#user_add_form").serialize(),
			dataType : "json",
			success : function(data) {
				if (data.result == 1) {
					$("#mytable").bootstrapTable('refresh');
					$('#add_modal').modal('hide');
					$('#user_add_form')[0].reset();
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
	function deleteUser(){
		var rows = $("#mytable").bootstrapTable('getSelections');
		var ids = [];
		$.each(rows, function(i, val) {
			ids.push(val.id);
		});
		$.ajax({
			type : 'POST',
			url : 'userDelete',
			traditional : true,
			data : {
				"ids" : ids
			},
			dataType : "json",
			success : function(data) {
				if (data.result == 1) {
					$("#mytable").bootstrapTable('refresh');
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
	<div class="row">
		<div class="col-lg-2"></div>
		<div class="table-responsive col-lg-8">
			<table class="table table-hover" id="mytable">

			</table>
		</div>
		<div class="col-lg-2"></div>
	</div>
	<div id="toolbar" class="btn-group">
		<button id="btn_add" type="button" class="btn btn-success"
			onclick="showAdd();">
			<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>用户新增
		</button>
		<button id="btn_delete" type="button" class="btn btn-danger"
			onclick="deleteUser()">
			<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>选择删除
		</button>
	</div>
	<div class="modal fade" id="modal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">×</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">用户编辑</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal" id="userForm" role="form">
						<div class="form-group">
							<input type="hidden" name="uid" id="uid">
						</div>
						<div class="form-group">
							<label for="name" class="col-sm-2 control-label">姓名</label>
							<div class="col-sm-9">
								<input type="text" id="name" name="name"
									class="form-control well" />
							</div>
						</div>

						<div class="form-group">
							<label for="sex" class="col-sm-2 control-label">性别</label>
							<div class="col-sm-9">
								<input type="radio" name="sex" id="man" value="男"
									style="margin-left:10px;" />男 <input type="radio" name="sex"
									id="women" value="女" style="margin-left:10px;" />女
							</div>
						</div>
						<div class="form-group">
							<label for="account" class="col-sm-2 control-label">用户名</label>
							<div class="col-sm-9">
								<input type="text" id="account" name="account"
									class="form-control well" />
							</div>
						</div>
						<div class="form-group">
							<label for="password" class="col-sm-2 control-label">密码</label>
							<div class="col-sm-9">
								<input type="password" id="password" name="password"
									class="form-control well" placeholder="请输入密码" />
							</div>
						</div>
						<div class="form-group">
							<label for="telephone" class="col-sm-2 control-label">电话</label>
							<div class="col-sm-9">
								<input type="text" id="telephone" name="telephone"
									class="form-control well" />
							</div>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary" onclick="uptUser()">保存</button>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="r_modal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">×</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">角色配置</h4>
				</div>
				<div class="modal-body">
					<input type="hidden" name="r_uid" id="r_uid">
					<div class="table-responsive">
						<table class="table table-hover" id="roletable">

						</table>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary" onclick="grantUser()">保存</button>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="add_modal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">×</span>
					</button>
					<h4 class="modal-title">用户新增</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal" id="user_add_form" role="form">
						<div class="form-group">
							<label for="name" class="col-sm-2 control-label">姓名</label>
							<div class="col-sm-9">
								<input type="text" name="name" class="form-control well" />
							</div>
						</div>

						<div class="form-group">
							<label for="sex" class="col-sm-2 control-label">性别</label>
							<div class="col-sm-9">
								<input type="radio" name="sex" value="男"
									style="margin-left:10px;" />男 <input type="radio" name="sex"
									value="女" style="margin-left:10px;" />女
							</div>
						</div>
						<div class="form-group">
							<label for="account" class="col-sm-2 control-label">用户名</label>
							<div class="col-sm-9">
								<input type="text" name="account" class="form-control well" />
							</div>
						</div>
						<div class="form-group">
							<label for="text" class="col-sm-2 control-label">密码</label>
							<div class="col-sm-9">
								<input type="text" name="password" class="form-control well" />
							</div>
						</div>
						<div class="form-group">
							<label for="telephone" class="col-sm-2 control-label">电话</label>
							<div class="col-sm-9">
								<input type="text" name="telephone" class="form-control well" />
							</div>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary" onclick="addUser()">保存</button>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
