<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="vo.Lecture" %>
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
	Lecture result = (Lecture)session.getAttribute("searchResult");
%>
	<table id = "searchResult">
		<thead>검색 결과</thead>
		<tbody>
		<tr><td>강의 코드</td><td id="lecture_id"><%=result.getLecture_id()%></td>
		<tr><td>강의 이름</td><td><%=result.getLecture_name()%></td>
		<tr><td>담당교수</td><td><%=result.getProf_id()%></td>
		<tr><td>단위학점</td><td><%=result.getCredit()%></td>
		<tr><td>할당시간(한주)</td><td><%=result.getCredit_hour()%></td>
		</tbody>
	</table>
<%
	session.removeAttribute("searchResult");
%>
</body>
</html>

	