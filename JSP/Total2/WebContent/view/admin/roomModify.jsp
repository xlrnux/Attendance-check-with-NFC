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
		var lecroom_id = $("#lecroom_id").val(); //입력된 lecroom_id  
		//alert(id);
		var flag = $("#flag").val();
		$("#view").load("/Total2/roomSet?flag="+flag+"&lecroom_id="+lecroom_id);
		//$("#view").html(id);

		$("#form2").html('');
		
		return false;
	}
//-->
</script>
</head>
<body>
	<form id="memberSearchForm" name="memberSearchForm"  method="post" onSubmit="return search();">
	<label for="lecroom_id">호실</label>
	<input id="lecroom_id" name="lecroom_id" type="text"/>
	<input type="hidden" id="flag" name="flag" value="search3"/>
	<input type="submit" id="submit" name="submit" value="조회"/>
	</form>
</body>
</html>

	