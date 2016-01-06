<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="vo.Lecture" %>
<%
	Lecture result = (Lecture)session.getAttribute("searchResult");
	String id=result.getLecture_id();
%>
<!DOCTYPE html >
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
function question(){
	if(confirm("정말 폐강하시겠습니까?")){
		return true;
	}else{
		return false;
	}
}
</script>
<script>
	var lecture_id = $("#lecture_id").val(); //입력된 lecture_id 
	$("#form2").html('<form id="lectureDelForm" name="lectureDelForm" action="/Total2/lectureSet" method="post" onSubmit="return question()"><input type="hidden" id="lecture_id" name="lecture_id" value="'+lecture_id+'"/><input type="hidden" id="flag" name="flag" value="del"/><input type="submit" value="폐강"/></form>');
</script>
</head>
<body>

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

	