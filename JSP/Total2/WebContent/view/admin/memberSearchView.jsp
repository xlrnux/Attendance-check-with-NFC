<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="vo.Member" %>
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
	Member result = (Member)session.getAttribute("searchResult");
%>
	<table id = "searchResult">
		<thead>검색 결과</thead>
		<tbody id ="tbody">
		<tr><td>ID</td><td id="rsId"><%=result.getId() %></td>
		<tr><td>이름</td><td><%=result.getName() %></td>
		<tr><td>휴대전화번호</td><td><%=result.getCell_num() %></td>
		<tr><td>집전화번호</td><td><%=result.getHome_num() %></td>
		<tr><td>Email</td><td><%=result.getEmail() %></td>
		<tr><td>학년</td><td><%=result.getGrade() %></td>
		<tr><td>신분</td><td><%=result.getLevel() %></td>
		<tr><td>강의중</td><td><%=result.getState() %></td>
		</tbody>
	</table>
<%
	session.removeAttribute("searchResult");
%>
</body>
</html>

	