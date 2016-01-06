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
	if(confirm("정말 수정하시겠습니까?")){
		return true;
	}else{
		return false;
	}
}
function checkPasswd(){
	var pw1 = document.memberModForm.password.value;
	var pw2 = document.memberModForm.password2.value;
	
	if(pw1 != pw2){
		alert("서로 다른 비밀번호를 입력하셨습니다!");
		return false;
	}else{
		return question();
	}
}
</script>
<script>
	//var id = $("#id").val(); //입력된 id 
	//$("#form2").html('<form id="memberModForm" name="memberModForm" action="/Total2/memberSet" method="post" onSubmit="return question()"><input type="hidden" id="id" name="id" value="'+id+'"/><input type="hidden" id="flag" name="flag" value="mod"/><input type="submit" value="수정"/></form>');
</script>
</head>
<body>
	<form id="memberModForm" name="memberModForm" action="/Total2/memberSet" method="post" onSubmit="return checkPasswd()">
	<input type="hidden" id="flag" name="flag" value="mod"/>
	<table id = "searchResult">
		<thead>검색 결과</thead>
		<tbody>
		<tr><td>ID</td><td><input type="text" id="id" name="id" value="<%=result.getId()%>" readonly/></td>
		<tr><td>비밀번호</td><td><input type="password" id="password" name="password" value="<%=result.getPassword()%>"/></td>
		<tr><td>비밀번호</td><td><input type="password" id="password2" name="password2" value="<%=result.getPassword()%>"/></td>		
		<tr><td>이름</td><td><input type="text" id="name" name="name" value="<%=result.getName()%>"/></td>
		<tr><td>휴대전화번호</td><td><input type="tel" id="cell_num" name="cell_num" value="<%=result.getCell_num()%>"/></td>
		<tr><td>집전화번호</td><td><input type="tel" id="home_num" name="home_num" value="<%=result.getHome_num()%>"/></td>
		<tr><td>Email</td><td><input type="email" id="email" name="email" value="<%=result.getEmail()%>"/></td>
		<tr><td>학년</td><td><input type="number" id="grade" name="grade" value="<%=result.getGrade()%>"/></td>
		</tbody>
	</table>
	<input type="submit" value="수정"/></form>
<%
	session.removeAttribute("searchResult");
%>
</body>
</html>

	