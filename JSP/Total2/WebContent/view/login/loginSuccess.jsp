<%@page import = "vo.Member" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html >
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	Member user = (Member)session.getAttribute("user");
%>
<%=user.getName() %>님 로그인 성공<br>	
<%=user.getLevel() %>
</body>
</html>

	