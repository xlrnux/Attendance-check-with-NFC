<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page session="true" %>
<%@ page autoFlush="true" %>
<%
	session.invalidate();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Refresh" content="2;url=/Total2/index.htm">
<title>Login Error!!</title>
<linkhref="http://fonts.googleapis.com/css?family=Anonymous+Pro:regular,italic,bold,bolditalic" rel="stylesheet" type="text/css">
<style>
* {
	margin: 0;
	padding: 0;
	background:  #1478FF;
	color: white;
	font-size: 50px;
	font-family: 'Anonymous Pro', serif;
}
</style>
</head>
<body>
	<center>
		ID또는 PASSWORD가 잘못되었습니다.<br/>
		2초 후 로그인 페이지로 이동합니다.
	</center>
</body>
</html>