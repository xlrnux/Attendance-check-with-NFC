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
	<form name="lectureAddForm" action="/Total2/lectureSet" method="post">
		<label for="lecture_name">강의 이름</label>
		<input id="lecture_name" name="lecture_name" type="text"/><br>
		<label for="lecture_id">강의 코드</label>
		<input id="lecture_id" name="lecture_id" type="text"/><br>
		<label for="prof_id">담당 교수(id)</label>
		<input id="prof_id" name="prof_id" type="text"/><br>
		<label for="credit">(단위)학점</label>
		<input id="credit" name="credit" type="number" /><br>
		<label for="credit_hour">할당시간(한주)</label>
		<input id="credit_hour" name="credit_hour" type="number"/><br>
		<input type="hidden" id="flag" name="flag" value="add"/>
		<input type="submit" value="등록"/>
	</form>
</body>
</html>