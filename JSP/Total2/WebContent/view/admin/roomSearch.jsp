<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html >
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
<!--
function search(){
	var lecroom_id = $("#lecroom_id").val(); //입력된 lecroom_id 
	var flag = $("#flag").val();
	$("#view").load("/Total2/roomSet?flag="+flag+"&lecroom_id="+lecroom_id);
	//$("#view").html(id);
	return false;
}
//-->
</script>
</head>
<body>
	<form id="roomSearchForm" name="roomSearchForm"  method="post" onSubmit="return search();">
		<label for="lecroom_id">호실</label>
		<input id="lecroom_id" name="lecroom_id" type="text"/>
		<input type="hidden" id="flag" name="flag" value="search"/>
		<input type="submit" id="submit" name="submit" value="조회"/>
	</form>
</body>
</html>

	