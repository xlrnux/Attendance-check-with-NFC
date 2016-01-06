<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page session="true"%>
<%@ page autoFlush="true"%>

<!DOCTYPE html >
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="/Total1/js/jquery-1.9.0.js"></script>
<script src="/Total1/js/makeTable.js"></script>
<script type="text/javascript">
<!--
	function getOffList() {
		$("#offList").load("offStateCheck.jsp");
	}

	function getOnList() {
		$.getJSON("onStateCheck.jsp", function(data, status) {
			var width = data[data.length - 2];
			var height = data[data.length - 1];
			//document.write("<table border='1'>");
			//document.write("<caption>　<br>　전　　면　<br>　</caption>");
			
			$("#onList").append($("<table id=onListTable border='1'/>"));
			$("#onListTable").append($("<caption>　<br>　전　　면　<br>　</caption>"));
			//$("#onList").append(
			//function() {
			for ( var h = 0; h < height; h++) {
				//document.write("<tr>");
				$("#onListTable").append($("<tr id= 'r" + h + "'/>"));
				for ( var w = 0; w < width; w++) {
					//document.write("<td id=" + makeId(w, h) + ">　공　석　</td>");
					$("#r" + h).append($("<td id=" + makeId(w, h) + "/>"));
					$("#"+makeId(w,h)).html("　공　　석　");
				}
				//document.write("</tr>");
			}

			for ( var i = 0; i < data.length - 2; i++) {
				$("#" + makeId(data[i].width - 1, data[i].height - 1)).html(data[i].id + "<br>" + data[i].name);
			}
			//$("#"+makeId(3,4)).html("11013026<br>임현동");
			//ioData();
			//	putInTable();

		});
	}

	function getList() {
		getOffList();
		getOnList();
	}
	function makeId(w, h) {
		var id = "w" + w + "h" + h;
		return id;
	}
	function makeTable() {
		
	}
//-->
</script>
</head>
<body onLoad="getList()">
	미참가자
	<br>
	<span id="offList"></span>
	<br>
	<br>
	<br> 참가자
	<br>
	<aside>
		<div id="onList"></div>
	</aside>
</body>
</html>

