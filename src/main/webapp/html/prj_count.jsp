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

<title>My JSP 'task_count.jsp' starting page</title>

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
<script type="text/javascript" src="./js/echarts.min.js"></script>
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
	window.onload = function() {
		$("#pid").change(function() {
			var opt = $("#pid").val();
			initEcharts(opt);
		});

		$.ajax({
			url : "getPrj?cp=-1&ps=-1",
			success : function(data) {
				$.each(data.rows, function(i, val) {
					$('#pid').append(
							"<option value=" + val.id + ">" + val.title
									+ "</option>");
				});
				$('#pid').selectpicker('refresh');
				$('#pid').selectpicker('render');
			},
		});
	};
	function initEcharts(id) {
		var myChart = echarts.init(document.getElementById('myCharts'));
		// 显示标题，图例和空的坐标轴
		myChart.setOption(option = {
			title : {
				text : '项目统计',
				subtext : '缺陷业务统计',
				x : 'center'
			},
			tooltip : {
				trigger : 'axis',
				axisPointer : {
					type : 'cross'
				}
			},
			legend : {
				orient : 'vertical',
				left : 'left',
				data : [ '发现量', '修复量' ]
			},
			xAxis : [ {
				type : 'category',
				axisTick : {
					alignWithLabel : true
				},
				data : []
			} ],
			yAxis : [ {
				type : 'value',
				name : '缺陷个数',
				min : 0,
				max : 10,
				position : 'left',
				axisLine : {
					lineStyle : {}
				},
				axisLabel : {
					formatter : '{value} 个'
				}
			} ],
			series : [
					{
						name : '发现量',
						type : 'line',
						data : []
					},
					{
						name : '修复量',
						type : 'bar',
						data : [ 2.6, 5.9, 9.0, 26.4, 28.7, 70.7, 175.6, 182.2,
								48.7, 18.8, 6.0, 2.3 ]
					} ]
		});
		myChart.showLoading(); //数据加载完之前先显示一段简单的loading动画
		var findNum = []; 
		var fixNum = [];
		var date = [];
		$.ajax({
			type : 'get',
			url : 'getStcPrj?pid='+id,//请求数据的地址
			dataType : "json", //返回数据形式为json
			success : function(result) {
				//请求成功时执行该函数内容，result即为服务器返回的json对象
				$.each(result, function(index, item) {
					findNum.push(item.findNum); //挨个取出类别并填入类别数组 
					fixNum.push(item.fixNum);
					date.push(item.date);
				});
				myChart.hideLoading(); //隐藏加载动画
				myChart.setOption({ //加载数据图表                
					xAxis : {
						data : date
					},
					series : [ {
						name : '发现量',
						data : findNum
					},
					{
						name : '修复量',
						data : fixNum
					} ]
				});
			},
			error : function(errorMsg) {
				//请求失败时执行该函数
				alert("图表请求数据失败!");
				myChart.hideLoading();
			}
		});
	};
</script>
</head>

<body>
	<div class="row">
		<div class="col-lg-2">
			<select name="pid" id="pid"
				class="selectpicker show-tick form-control" data-width="98%"
				data-first-option="false" title='请选择一个项目' required
				data-live-search="true">
			</select>
		</div>
		<div id="myCharts" class="col-lg-8" style="height:600px;"></div>
		<div class="col-lg-2"></div>
	</div>
</body>
</html>
