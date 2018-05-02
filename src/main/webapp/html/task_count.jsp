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
<script type="text/javascript" src="./js/bootstrap.min.js"></script>
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
	
	window.onload = function(){
var myChart = echarts.init(document.getElementById('myCharts'));
		// 显示标题，图例和空的坐标轴
		myChart.setOption(option = {
    title : {
        text: '任务统计',
        subtext: '',
        x:'center'
    },
    tooltip : {
        trigger: 'item',
        formatter: "{a} <br/>{b} : {c} ({d}%)"
    },
    legend: {
        orient: 'vertical',
        left: 'left',
        data: []
    },
    series : [
        {
            name: '任务量',
            type: 'pie',
            radius : '55%',
            center: ['50%', '60%'],
            data:[],
            itemStyle: {
                emphasis: {
                    shadowBlur: 10,
                    shadowOffsetX: 0,
                    shadowColor: 'rgba(0, 0, 0, 0.5)'
                }
            }
        }
    ]
});
		myChart.showLoading(); //数据加载完之前先显示一段简单的loading动画
		var names = []; //类别数组（用于存放饼图的类别）
		var brower = [];
		$.ajax({
			type : 'get',
			url : 'getStcLog',//请求数据的地址
			dataType : "json", //返回数据形式为json
			success : function(result) {
				//请求成功时执行该函数内容，result即为服务器返回的json对象
				$.each(result.list, function(index, item) {
					names.push(item.nodeName); //挨个取出类别并填入类别数组 
					brower.push({
						name : item.nodeName,
						value : item.num
					});
				});
				myChart.hideLoading(); //隐藏加载动画
				myChart.setOption({ //加载数据图表                
					legend : {
						data : names
					},
					series : [ {
						data : brower
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
		<div class="col-lg-2"></div>
		<div id="myCharts" class="col-lg-8" style="height:600px;"></div>
		<div class="col-lg-2"></div>
	</div>
</body>
</html>
