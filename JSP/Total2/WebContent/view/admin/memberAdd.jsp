<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html >
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

</head>
<body>	
	<form name="memberAddForm" action="/Total2/memberSet" method="post">
		<label for="name">이름</label>
		<input id="name" name="name" type="text"/><br>
		<label for="id">ID</label>
		<input id="id" name="id" type="text"/><br>
		<label for="cell_num">휴대전화번호</label>
		<input id="cell_num" name="cell_num" type="tel"/><br>
		<label for="home_num">자택전화번호</label>
		<input id="home_num" name="home_num" type="tel"/><br>
		<label for="email">이메일 주소</label>
		<input id="email" name="email" type="email"/><br>
		<label for="level">신분</label>
		<input id="level" name="level" type="text"/><br>
		<input type="hidden" id="flag" name="flag" value="add"/>
		<input type="submit" value="등록"/>
	</form>
</body>
</html>