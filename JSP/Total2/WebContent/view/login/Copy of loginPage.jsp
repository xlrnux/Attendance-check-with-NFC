<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html >
<html>
<head>
<meta charset="UTF-8">
<title>전자출석체크 시스템 Ver.0.7.4</title>
</head>
<body>
	<form name="loginForm" action="/Total2/web" method="post">
		<label for="id">아이디 </label>
		<input id="id" name="id" type="text"/>
		<label for="password">비밀번호 </label>
		<input id="password" name="password" type="password"/>
		<input id="login" name="login" type="submit" value="로그인"/>
	</form>
</body>
</html>