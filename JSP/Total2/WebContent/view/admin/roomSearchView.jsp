<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="vo.Room" %>
<!DOCTYPE html >
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
	//$("#view").html("hello");
</script>
</head>
<body>
<%
	Room result = (Room)session.getAttribute("searchResult");
%>
	<table id = "searchResult">
		<thead>검색 결과</thead>
		<tbody>
		<tr><td>호실</td><td id="rsId"><%=result.getLecroom_id() %></td>
		<tr><td>좌석(가로)</td><td><%=result.getWidth() %></td>
		<tr><td>좌석(세로)</td><td><%=result.getHeight() %></td>
		<tr><td>현재사용자</td><td><%=result.getState() %></td>
		</tbody>
	</table>
<%
	session.removeAttribute("searchResult");
%>
</body>
</html>

	