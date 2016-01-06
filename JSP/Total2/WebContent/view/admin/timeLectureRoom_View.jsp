<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="svc.RoomSettingBiz"%>
<%@ page import="svc.LectureSettingBiz"%>
<%@ page import="svc.TimeTableSettingBiz"%>
<%@ page import="vo.Lecture"%>
<%@ page import="util.IdUtill"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="/Total2/js/jquery-1.9.0.js"></script>
<script type="text/javascript">
</script>
<script type="text/javascript">
	$(function() {
		$('input:button#mon').click(function(event) {
			var self = $(this);
			//this.remove();
			//$("#2_1_5334").html("Hello World!");
			if (self.val().length == 7) {
				$("table#2").show();
				self.val("월요일 접기");
			} else {
				!$("table#2").hide();
				self.val("월요일 펼치기");
			}
		})
	});
	$(function() {
		$('input:button#tue').click(function(event) {
			var self = $(this);
			if (self.val().length == 7) {
				$("table#3").show();
				self.val("화요일 접기");
			} else {
				!$("table#3").hide();
				self.val("화요일 펼치기");
			}
		})
	});
	$(function() {
		$('input:button#wed').click(function(event) {
			var self = $(this);
			if (self.val().length == 7) {
				$("table#4").show();
				self.val("수요일 접기");
			} else {
				!$("table#4").hide();
				self.val("수요일 펼치기");
			}
		})
	});
	$(function() {
		$('input:button#thur').click(function(event) {
			var self = $(this);
			if (self.val().length == 7) {
				$("table#5").show();
				self.val("목요일 접기");
			} else {
				!$("table#5").hide();
				self.val("목요일 펼치기");
			}
		})
	});
	$(function() {
		$('input:button#fri').click(function(event) {
			var self = $(this);
			if (self.val().length == 7) {
				$("table#6").show();
				self.val("금요일 접기");
			} else {
				!$("table#6").hide();
				self.val("금요일 펼치기");
			}
		})
	});
	
//-->
</script>
<style type="text/css">
<!--
.table {
	text-align: center;
	border-top: 1px solid black;
	border-left: 1px solid black;
	border-collapse: collapse;
}

.table caption {
	font-size: 30px;
	border: 1px solid black;
}

.table thead>tr>td {
	font-weight: bold;
	font-size: 30px;
	border: 1px solid black;
	border-right: 1px solid black;
	border-bottom: 1px solid black;
}

.table tbody>tr>td {
	line-height: 30px;
	line-width: 80px;
	font-size: 20px;
	width: 120px;
	border-right: 1px solid black;
	border-bottom: 1px solid black;
}

.top {
	vertical-align: top;
}
-->
</style>
</head>
<body onload="disabled_()">
	
	<input type="button" id="mon" value="월요일 접기">
	<input type="button" id="tue" value="화요일 접기">
	<input type="button" id="wed" value="수요일 접기">
	<input type="button" id="thur" value="목요일 접기">
	<input type="button" id="fri" value="금요일 접기">
	<form name="alloc_tlrForm" action="#" method="post">
		<%
			String days[] = {"","","월요일", "화요일", "수요일", "목요일", "금요일"};
			ArrayList<String> roomList = new ArrayList<String>();
			
			
			RoomSettingBiz roomSetBiz = new RoomSettingBiz();
			roomList = roomSetBiz.getRoomList();
			IdUtill idUtill = new IdUtill();
			TimeTableSettingBiz ttSetBiz = new TimeTableSettingBiz();
			ArrayList<String> timeTableList = new ArrayList<String>();	
			timeTableList = ttSetBiz.getTimeTableList(roomList);
			HashMap<String, String> lectureMap = new HashMap<String, String>();
			lectureMap = ttSetBiz.getLectureNameTable(timeTableList);
			int maxClass = 14;
			String empty = "배정가능";
			int index = 0;
			
			//풀다운 메뉴. 강의를 선택.
			//out.println("<select name='select_lecture'>");
			for(int i =0; i < roomList.size(); i++){
				//out.println("<option value=")
			}
			out.println("<table><tr>");
			
			for (int dayCnt = 2; dayCnt < days.length; dayCnt++) {
				if(dayCnt % 2 == 0){
					out.println("</tr><tr>");
				}
				out.println("<td class='top'>");
				System.out.println(days[dayCnt]);
				out.println("<table id='"+dayCnt+"' class='table'><thead><caption>"+days[dayCnt]+"</caption><tr>");
				out.println("<td>교시</td>");
				for(int i = 0; i < roomList.size(); i++){
			//System.out.print("      ");
			System.out.printf(" %8s ", roomList.get(i));
			out.println("<td>"+roomList.get(i)+"</td>");
				}		
				System.out.println();
				out.println("</tr></thead>");
				out.println("<tbody>");
				for (int classCnt = 1; classCnt <= maxClass; classCnt++) {
			System.out.printf("%3d ", classCnt);
			out.println("<tr>");
			if(classCnt < 10){
				out.println("<td>"+classCnt+"교시</td>");
			}else{
				int classCnt_tmp = classCnt-9;
				out.println("<td>"+ classCnt_tmp +"교시<br>야간</td>");
			}
			for (int roomCnt = 0; roomCnt < roomList.size(); roomCnt++) {
				String tid = idUtill.packId(dayCnt, classCnt, roomList.get(roomCnt));
				String lecture_id = timeTableList.get(index);
				if(timeTableList.get(index).equals("empty")){
			//System.out.println(days[dayCnt] + " " + classCnt + " " + roomList.get(roomCnt) + " 배정가능");
			System.out.printf(" %8s ", empty);
			out.println("<td><input type='hidden' id='"+tid+"' name='"+tid+"'><span id='"+tid+"'>배정가능</td>");
				}else{
			//System.out.println(days[dayCnt] + " " + classCnt + " " + roomList.get(roomCnt) + " " + rs.getString("lecture_id"));
			System.out.printf(" %8s ",  lecture_id );
			//out.println("<td>"+ lectureMap.get(timeTableList.get(index)) +"<br>"+ timeTableList.get(index)+"</td>");	//original
			out.println("<td><input type='hidden' id='"+tid+"' name='"+tid+"' value='"+ lecture_id +"'><span id='"+tid+"'>" + lectureMap.get(timeTableList.get(index)) +"<br>"+lecture_id+"</span></td>");
				}
				index++;
			}
			System.out.println();
			out.println("</tr>");
				}
				
				System.out.println();
				out.println("</tbody></table>");
				out.println("</details>");
				
			}
			out.println("</td>");
			out.println("</tr></table>");
		%>

	</form>

</body>
</html>

