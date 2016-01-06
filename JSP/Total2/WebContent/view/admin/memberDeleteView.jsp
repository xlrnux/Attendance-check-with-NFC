<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="vo.Member" %>
<%
	Member result = (Member)session.getAttribute("searchResult");
	String id=result.getId();
%>
<!DOCTYPE html >
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
function question(){
	if(confirm("정말 삭제하시겠습니까?")){
		return true;
	}else{
		return false;
	}
}
</script>
<script>
	var id = $("#id").val(); //입력된 id 
	$("#form2").html('<form id="memberDelForm" name="memberDelForm" action="/Total2/memberSet" method="post" onSubmit="return question()"><input type="hidden" id="id" name="id" value="'+id+'"/><input type="hidden" id="flag" name="flag" value="del"/><input type="submit" value="삭제"/></form>');
</script>
</head>
<body>
	<table id = "searchResult">
		<thead>검색 결과</thead>
		<tbody>
		<tr><td>ID</td><td id="id"><%=result.getId() %></td>
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

	