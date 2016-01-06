<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="vo.Room" %>
<%
	Room result = (Room)session.getAttribute("searchResult");
	String lecroom_id=result.getLecroom_id();
%>
<!DOCTYPE html >
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
function question(){
	if(confirm("정말 수정하시겠습니까?")){
		return true;
	}else{
		return false;
	}
}
</script>
<script>
	//var id = $("#id").val(); //입력된 id 
	//$("#form2").html('<form id="memberModForm" name="memberModForm" action="/Total2/memberSet" method="post" onSubmit="return question()"><input type="hidden" id="id" name="id" value="'+id+'"/><input type="hidden" id="flag" name="flag" value="mod"/><input type="submit" value="수정"/></form>');
</script>
</head>
<body>
	<form id="roomModForm" name="roomModForm" action="/Total2/roomSet" method="post" onSubmit="return question()">
	<input type="hidden" id="flag" name="flag" value="mod"/>
	<table id = "searchResult">
		<thead>검색 결과</thead>
		<tbody>
		<tr><td>호실</td><td><input type="text" id="lecroom_id" name="lecroom_id" value="<%=result.getLecroom_id()%>" readonly/></td>
		<tr><td>좌석(가로)</td><td><input type="number" id="width" name="width" value="<%=result.getWidth()%>"/></td>
		<tr><td>좌석(세로)</td><td><input type="number" id="height" name="height" value="<%=result.getHeight()%>"/></td>
		</tbody>
	</table>
	<input type="submit" value="수정"/></form>
<%
	session.removeAttribute("searchResult");
%>
</body>
</html>

	