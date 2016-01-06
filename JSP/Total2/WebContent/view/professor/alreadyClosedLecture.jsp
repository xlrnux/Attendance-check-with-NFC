<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page session="true" %>
<%@ page autoFlush="true" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Refresh" content="3;url=/Total2/view/professor/main.jsp">
<title>Login Error!!</title>

<linkhref="http://fonts.googleapis.com/css?family=Anonymous+Pro:regular,italic,bold,bolditalic" rel="stylesheet" type="text/css">
<style>
* {
	margin: 0;
	padding: 0;
	background:  #1478FF;
	color: white;
	font-size: 50px;
	font-family: 'Anonymous Pro', serif;
}
</style>
<script>
	$("#body").html("");
	$("#body").append("<center class='error'>이미 오늘 이 강의를 마치셨습니다.<br/>3초 후 메인 페이지로 이동합니다.</center>");
</script>
</head>

</html>