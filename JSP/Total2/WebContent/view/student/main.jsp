<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String student_id = (String) session.getAttribute("student_id");
	String student_name = (String) session.getAttribute("student_name");
	String level = (String) session.getAttribute("level");
	String lecroom_id = (String) session.getAttribute("lecroom_id");
%>
<!DOCTYPE html >
<html>
<head>
<meta charset="UTF-8">
<title>전자출석체크 시스템(학생용)</title>
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
	background: -webkit-gradient(linear, 0% 0%, 0% 100%, from(#6DB700),
		to(#99CF00));
	background: -moz-linear-gradient(19% 75% 90deg, #99CF00, #6DB700);
}

input[type="submit"]:hover {
	background: -webkit-gradient(linear, 0% 0%, 0% 100%, from(#6DB700),
		to(#99CF00));
	background: -moz-linear-gradient(19% 75% 90deg, #99CF00, #6DB700);
}

.title {
	font-size: 4.5em;
}
</style>
</head>
<body>
	<form method="post" action="/Total2/webLogout">
		<input type="submit" value="로그아웃">
	</form>
	<form method="post" action="/Total2/Check" onSubmit="">
		<label for="lecroom_id">강의실 </label> <input type="text"
			id="lecroom_id" name="lecroom_id"><br> <label
			for="width">좌석(왼쪽에서)</label> <input type="text" id="width"
			name="width"><br> <label for="height">좌석(앞에서)</label> <input
			type="hidden" id="flag" name="flag" value="checkIn"> <input
			type="text" id="height" name="height"><br> <input
			type="submit" alt="출석체크 요청" value="출석체크 요청">
	</form>
</body>
</html>

