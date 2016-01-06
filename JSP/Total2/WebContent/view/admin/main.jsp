<%@page import="vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	Member user = (Member) session.getAttribute("member");
	if (!(user.getLevel().equals("admin"))) {
		// 관리자가 아니라면 다른 페이지로 보내버림
	}
%>
<!DOCTYPE html >
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="/Total2/js/jquery-1.9.0.js"></script>
<script src="/Total2/js/admin/printSettingList.js"></script>
<script src="/Total2/js/admin/printRoomSettingList.js"></script>
<script src="/Total2/js/admin/printLectureSettingList.js"></script>
<script src="/Total2/js/admin/printAllocSettingList.js"></script>
<style>
body {
	background: url(/Total2/img/log2.jpg);
	background-size: 100%;
}

input[type="submit"] {
	width: 250px;
	height: 50px;
	color: white;
	background-color: #99CF00;
	text-align: center;
	font-size: 30px;
	line-height: 50px;
	/*gradient styles*/
	background: -webkit-gradient(linear, 0% 0%, 0% 100%, from(#99CF00),
		to(#6DB700));
	background: -moz-linear-gradient(19% 75% 90deg, #6DB700, #99CF00);
	/*border styles*/
	border-left: solid 1px #c3f83a;
	border-top: solid 1px #c3f83a;
	border-right: solid 1px #82a528;
	border-bottom: solid 1px #58701b;
	-moz-border-radius: 10px;
	-webkit-border-radius: 10px;
	border-radius: 10px;
	-webkit-gradient: (linear, 0% 0%, 0% 100%, from(#99CF00), to(#6DB700))
}
input[type="button"] {
	width: 200px;
	height: 50px;
	color: white;
	background-color: #99CF00;
	text-align: center;
	font-size: 23px;
	line-height: 50px;
	/*gradient styles*/
	background: -webkit-gradient(linear, 0% 0%, 0% 100%, from(#99CF00),
		to(#6DB700));
	background: -moz-linear-gradient(19% 75% 90deg, #6DB700, #99CF00);
	/*border styles*/
	border-left: solid 1px #c3f83a;
	border-top: solid 1px #c3f83a;
	border-right: solid 1px #82a528;
	border-bottom: solid 1px #58701b;
	-moz-border-radius: 10px;
	-webkit-border-radius: 10px;
	border-radius: 10px;
	-webkit-gradient: (linear, 0% 0%, 0% 100%, from(#99CF00), to(#6DB700))
}
.button h3 {
 font-size: 20px;
 line-height: 35px;
 font-family: helvetica, sans-serif;
}
 
.button p {
 font-size: 12px;
 line-height: 4px;
 font-family: helvetica, sans-serif;
}
 
a {
 text-decoration: none;
 color: fff;
}
 
input[type="button"]:hover {
 background: -webkit-gradient(linear, 0% 0%, 0% 100%, from(#6DB700), to(#99CF00));
 background: -moz-linear-gradient(19% 75% 90deg,#99CF00, #6DB700);
}
input[type="submit"]:hover {
 background: -webkit-gradient(linear, 0% 0%, 0% 100%, from(#6DB700), to(#99CF00));
 background: -moz-linear-gradient(19% 75% 90deg,#99CF00, #6DB700);
}

.title {
	font-size: 4.5em;
}
</style>
</head>
<body onload="printSettingList()">
	<p class="title">관리자 모드입니다.</p>
	<form action="/Total2/webLogout">
		<input type="submit" value="로그아웃">
	</form>
	<br>
	<br>
	<br>
	<span id="list"> </span>
	<span id="form"> </span>
	<span id="view"> </span>
	<span id="form2"> </span>
	<br>
	<br>
	<br>

</body>
</html>

