<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link href="./css/bootstrap.min.css"
	rel="stylesheet">
<link href="./css/signin.css" rel="stylesheet">
</head>

<body class="text-center">
	<div class="container">

		<form class="form-signin" action="login" method="post">
			<h2 class="form-signin-heading">Please sign in</h2>
			<label for="inputAccount" class="sr-only">Email address</label> <input
				type="text" id="inputAccount" name="account" class="form-control"
				placeholder="User Account" required autofocus> <label
				for="inputPassword" class="sr-only">Password</label> <input
				type="password" name="password" id="inputPassword"
				class="form-control" placeholder="Password" required> <br>
			<label class="mt-5 mb-3 text-muted">Tip: <c:if test="${errorMessage != null }">${errorMessage}</c:if> </label>
			<button class="btn btn-lg btn-primary btn-block" type="submit">Sign
				in</button>
			<br>
			<p class="mt-5 mb-3 text-muted">&copy; 2018-2019</p>
		</form>

	</div>
</body>
</html>
