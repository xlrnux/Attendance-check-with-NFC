<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html >
<html>
<head>
<meta charset="UTF-8">
<script>
<!--
	function search(){
		var lecture_id = $("#lecture_id").val(); //입력된 id 
		//alert(id);
		var flag = $("#flag").val();
		$("#view").load("/Total2/lectureSet?flag="+flag+"&lecture_id="+lecture_id);
		//$("#view").html(id);

		$("#form2").html('');
		
		return false;
	}
//-->
</script>
</head>
<body>
	<form id="lectureSearchForm" name="lectureSearchForm"  method="post" onSubmit="return search();">
	<label for="lecture_id">강의 코드</label>
	<input id="lecture_id" name="lecture_id" type="text"/>
	<input type="hidden" id="flag" name="flag" value="search2"/>
	<input type="submit" id="submit" name="submit" value="조회"/>
	</form>
</body>
</html>

	