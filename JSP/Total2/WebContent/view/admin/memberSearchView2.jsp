<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="vo.Member" %>
<!DOCTYPE html >
<html>
<head>
<meta charset="UTF-8">
</head>
<body>
<%
	Member result = (Member)session.getAttribute("searchResult");
%>
		 ID: <%=result.getId() %> <br>
		 이름:  <%=result.getName() %><br> 
		 휴대전화번호:  <%=result.getCell_num() %><br> 
		 집전화번호:  <%=result.getHome_num() %><br> 
		 Email:  <%=result.getEmail() %><br> 
		 학년:  <%=result.getGrade() %><br> 
		 신분:  <%=result.getLevel() %><br> 
		 강의중:  <%=result.getState() %><br> 
<%
	session.removeAttribute("searchResult");
%>
</body>
</html>

	