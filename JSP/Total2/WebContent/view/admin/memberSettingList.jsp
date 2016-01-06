<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html >
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
		<input type="button" value = "←" name="memberAdd" id="memberAdd" onClick="printSettingList()" >
		<input type="button" value = "구성원 조회" name="memberSearch" id="memberSearch" onClick="printMemberSearch()" >
		<input type="button" value = "구성원 추가" name="memberAdd" id="memberAdd" onClick="printMemberAdd()" >
		<input type="button" value = "구성원 정보 수정" name="memberModify" id="memberModify" onClick="printMemberModify()" >
		<input type="button" value = "구성원 삭제" name="memberDelete" id="memberDelete" onClick="printMemberDelete()" >
</body>
</html>

	