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

		initTable('getBugs');

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
							undefinedText : "无",//当数据为 undefined 时显示的字符  
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
										title : '缺陷名称',
										field : 'title',
										align : 'center',
										valign : 'middle',
										sortable : true,
										searchable : true

									},
									{
										title : '上报人',
										field : 'reporter',
										align : 'center',
										valign : 'middle',
										sortable : true,
										searchable : true
									},
									{
										title : '解决人',
										field : 'resolver',
										valign : 'middle',
										align : 'center',
									},
									{
										title : '验证人',
										field : 'identifier',
										valign : 'middle',
										align : 'center',
									}, 
									{
										title : '状态',
										field : 'status',
										valign : 'middle',
										align : 'center',
									}, 
									{
										title : '测试轮数',
										field : 'count',
										valign : 'middle',
										align : 'center',
									}, 
									{
										title : '所属项目',
										field : 'prj',
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
													+ '\')">修改</button>                                                                                                                                                                                                                                                                                                                                                                                                          ';
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
		$("#bid").val(row.id);
		$("#title").val(row.title);
		$('#modal').modal('show');
	}
	function uptBug() {
		$.ajax({
			type : "POST",
			dataType : "json",
			url : "bugUpt",
			data : $("#bugForm").serialize(),
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

	function deleteBug(){
		var rows = $("#mytable").bootstrapTable('getSelections');
		var ids = [];
		var count = [];
		$.each(rows, function(i, val) {
			ids.push(val.id);
			count.push(val.count);
		});
		$.ajax({
			type : 'POST',
			url : 'bugDelete',
			traditional : true,
			data : {
				"ids" : ids,
				"count" : count
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
		<button id="btn_delete" type="button" class="btn btn-danger"
			onclick="deleteBug()">
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
					<h4 class="modal-title" id="myModalLabel">缺陷编辑</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal" id="bugForm" role="form">
						<div class="form-group">
							<input type="hidden" name="bid" id="bid">
						</div>
						<div class="form-group">
							<label for="name" class="col-sm-2 control-label">缺陷标题</label>
							<div class="col-sm-9">
								<input type="text" id="title" name="title"
									class="form-control well" />
							</div>
						</div>

					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary" onclick="uptBug()">保存</button>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
