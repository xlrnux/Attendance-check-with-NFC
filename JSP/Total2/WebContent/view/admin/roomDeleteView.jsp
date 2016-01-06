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
	if(confirm("정말 삭제하시겠습니까?")){
		return true;
	}else{
		return false;
	}
}
</script>
<script>
	var lecroom_id = $("#lecroom_id").val(); //입력된 lecroom_id 
	$("#form2").html('<form id="roomDelForm" name="roomDelForm" action="/Total2/roomSet" method="post" onSubmit="return question()"><input type="hidden" id="lecroom_id" name="lecroom_id" value="'+lecroom_id+'"/><input type="hidden" id="flag" name="flag" value="del"/><input type="submit" value="삭제"/></form>');
</script>
</head>
<body>

	<table id = "searchResult">
		<thead>검색 결과</thead>
		<tbody>
		<tr><td>호실</td><td id="lecroom_id"><%=result.getLecroom_id() %></td>
		<tr><td>좌석(가로)</td><td><%=result.getWidth() %></td>
		<tr><td>좌석(세로)</td><td><%=result.getHeight() %></td>
		<tr><td>사용자</td><td><%=result.getState() %></td>
		
		</tbody>
	</table>
<%
	session.removeAttribute("searchResult");
%>
</body>
</html>

	