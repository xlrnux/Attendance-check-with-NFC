<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html >
<html>
<head>
<meta charset="UTF-8">
</head>
<body>	
	<form name="roomAddForm" action="/Total2/roomSet" method="post">
		<label for="lecroom_id">호실</label>
		<input id="lecroom_id" name="lecroom_id" type="text"/><br>
		<label for="width">좌석(가로)</label>
		<input id="width" name="width" type="text"/><br>
		<label for="height">좌석(세로)</label>
		<input id="height" name="height" type="text"/><br>
		<input type="hidden" id="flag" name="flag" value="add"/>
		<input type="submit" value="등록"/>
	</form>
</body>
</html>

	