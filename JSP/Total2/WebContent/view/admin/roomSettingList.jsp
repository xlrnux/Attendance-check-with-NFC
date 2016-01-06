<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html >
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
		<input type="button" value = "←" name="roomAdd" id="roomAdd" onClick="printSettingList()" >
		<input type="button" value = "강의실 정보 조회" name="roomSearch" id="roomSearch" onClick="printRoomSearch()" >
		<input type="button" value = "강의실 추가" name="roomAdd" id="roomAdd" onClick="printRoomAdd()" >
		<input type="button" value = "강의실 정보 수정" name="roomModify" id="roomModify" onClick="printRoomModify()" >
		<input type="button" value = "강의실 삭제" name="roomDelete" id="roomDelete" onClick="printRoomDelete()" >
</body>
</html>

	