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
		var id = $("#id").val(); //입력된 id 
		//alert(id);
		var flag = $("#flag").val();
		$("#view").load("/Total2/memberSet?flag="+flag+"&id="+id);
		//$("#view").html(id);

		$("#form2").html('');
		
		return false;
	}
//-->
</script>
</head>
<body>
	<form id="memberSearchForm" name="memberSearchForm"  method="post" onSubmit="return search();">
		<label for="id">ID</label>
		<input id="id" name="id" type="text"/>
		<input type="hidden" id="flag" name="flag" value="search2"/>
		<input type="submit" id="submit" name="submit" value="조회"/>
	</form>	
</body>
</html>

	