<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="vo.Lecture" %>
<%
	Lecture result = (Lecture)session.getAttribute("searchResult");
	String lecture_id=result.getLecture_id();
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
	//$("#form2").html('<form id="lectureModForm" name="lectureModForm" action="/Total2/lectureSet" method="post" onSubmit="return question()"><input type="hidden" id="lecture_id" name="lecture_id" value="'+id+'"/><input type="hidden" id="flag" name="flag" value="mod"/><input type="submit" value="수정"/></form>');
</script>
</head>
<body>
	<form id="lectureModForm" name="lectureModForm" action="/Total2/lectureSet" method="post" onSubmit="return question()">
	<input type="hidden" id="flag" name="flag" value="mod"/>
	<table id = "searchResult">
		<thead>검색 결과</thead>
		<tbody>
		<tr><td>강의 코드</td><td><input type="text" id="lecture_id" name="lecture_id" value="<%=result.getLecture_id()%>" readonly/></td>
		<tr><td>강의 이름</td><td><input type="text" id="lecture_name" name="lecture_name" value="<%=result.getLecture_name()%>"/></td>
		<tr><td>담당교수(id)</td><td><input type="text" id="prof_id" name="prof_id" value="<%=result.getProf_id()%>"/></td>
		<tr><td>담당교수(이름)</td><td><input type="text" id="prof_name" name="prof_name" value="<%=result.getProf_name()%>" readonly/></td>
		<tr><td>단위학점</td><td><input type="number" id="credit" name="credit" value="<%=result.getCredit()%>"/></td>
		<tr><td>할당시간(한주)</td><td><input type="number" id="credit_hour" name="credit_hour" value="<%=result.getCredit_hour()%>"/></td>
		</tbody>
	</table>
	<input type="submit" value="수정"/></form>
<%
	session.removeAttribute("searchResult");
%>
</body>
</html>

	