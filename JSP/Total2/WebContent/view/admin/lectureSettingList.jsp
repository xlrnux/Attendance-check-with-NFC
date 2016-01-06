<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html >
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
		<input type="button" value = "←" name="lectureAdd" id="lectureAdd" onClick="printSettingList()" >
		<input type="button" value = "강의 정보 조회" name="lectureSearch" id="lectureSearch" onClick="printLectureSearch()" >
		<input type="button" value = "강의 개설" name="lectureAdd" id="lectureAdd" onClick="printLectureAdd()" >
		<input type="button" value = "강의 정보 수정" name="lectureModify" id="lectureModify" onClick="printLectureModify()" >
		<input type="button" value = "폐강" name="lectureDelete" id="lectureDelete" onClick="printLectureDelete()" >
</body>
</html>

	