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
function disabled_(){
	//alert("과목을 선택해주세요!");
	$('input:checkbox:checked:enabled').attr('disabled', true);
}

</script>
<script type="text/javascript">
<!--

	function test() {
		$("#2_1_5327").append("HelloWorld!");
	}
	/*
	function count_chk(){
	var form = document.alloc_tlrForm;
	check_max = 4;//일단 최대 4개로 설정. 추후 세션이용해서 동적으로 처리할것.
	check_count = 0;
	for(i = 0; i < f["alloc_chk[]"].length; i++){
	if(form["alloc_chk"].[i].checked) check_count++;
	}
	
	if(check_count > check_max){
	alert("해당 과목은 일주일에 "+check_max+"시간 수업합니다.");
	return false;
	}
	}
	 */

	var numbers = 0;
	var maxnum = 0;
	var lecture_id ="";
	var lecture_name= "";
	$(function() {
		// 체크박스 클릭시 
		$('input:checkbox').click(function(event) {
			var self = $(this);
			var label = self.next();
			var list = $('div.list');
			var credit_hour = maxnum;
			// 선택되면 
			if (this.checked) {
				//alert(self.val());
				if(numbers ==0 && credit_hour == 0){
					alert("강의를 먼저 선택해주세요!");
				}
				if (numbers == credit_hour) {
					//$('input:checkbox:checked:not:disabled').checked.attr('disabled', true);
					self.removeAttr("checked");
					//alert("해당 과목은 한주 " + credit_hour + "시간 수업합니다.");
					
				} else {
					$('input:hidden#'+self.val()).val(lecture_id);
					$('span#'+self.val()).html(lecture_name+"<br>"+lecture_id);
					numbers += 1;
					if(numbers == credit_hour){
						$('select').attr('disabled', false);
					}else{
						$('select').attr('disabled', true);
					}
				}
				//alert(self.val());
				// 선택 안되면 
			} else {
				$('input:hidden#'+self.val()).val("");
				$('span#'+self.val()).html("배정가능<br>이곳에 배정")
				numbers -= 1;
				if(numbers == 0){
					$('select').attr('disabled', false);
				}else{
					$('select').attr('disabled', true);
				}
				//alert(self.val());
			}
			
		})
	});
	$(function() {
		$('input:submit').click(function() {
			var self = $(this);
			var label = self.next();
			var list = $('div.list');
			var credit_hour = maxnum;
			// 선택되면 
			if (numbers != credit_hour) {
				alert("할당시간을 덜 채우셨습니다." + numbers + "/" + credit_hour);
				return false;
			}
			$('input:checkbox').attr('disabled', false);
		})
	});

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
	
	$(function() {
		$('select').click(function(event) {
			var self = $(this);
			if(self.val() == "-- 과목을 선택해주세요 --"){
				return false;
			}
			if(numbers == maxnum){
			
			}else if( numbers == 0){
				
			}else{
				alert("과목을 바꾸기 전에 시간을 전부 할당해주세요.또는 할당을 전부 취소해주세요");
				return false;
			}
			//alert(self.val());
			
			var tmp = (self.val()).split('|');
			lecture_id = tmp[0];
			
			lecture_name = Number(tmp[1]);
			maxnum = tmp[2];
			alert(lecture_id+" "+lecture_name+" "+maxnum);
			numbers = 0;
			//var checked_dis = $('input:checkbox:checked:disabled');
			var checked_en = $('input:checkbox:checked:enabled');
			//$('input:checkbox:checked:enabled').attr('disabled',true); //되는것
			//$('input:checkbox:checked').disable();
			//var checked = (input:checkbox).checked;
			checked_en.attr('disabled', true);
			//checked_dis.attr('disabled', false);
			//$(checked).disable();
			alert($('hidden[value='+lecture_id+']').val());
			
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
	font-size: 20px;
	border: 1px solid black;
	border-right: 1px solid black;
	border-bottom: 1px solid black;
}

.table tbody>tr>td {
	line-height: 30px;
	line-width: 80px;
	font-size: 12px;
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
	<form onSubmit="return flase">
		<select id="selecter" name="과목선택">
		<option>-- 과목을 선택해주세요 --</option>
		<%
			ArrayList<Lecture> lectureList = new ArrayList<Lecture>();
			LectureSettingBiz lecSetBiz = new LectureSettingBiz();
			lectureList = lecSetBiz.getLectureList();
			
			
			for(Lecture lec: lectureList){
				out.println("<option>");
				out.println(lec.getLecture_id()+"|"+lec.getLecture_name()+"|"+lec.getCredit_hour());
				out.println("</option>");
			}
		%>
		</select>
	</form>
	<input type="button" id="mon" value="월요일 접기">
	<input type="button" id="tue" value="화요일 접기">
	<input type="button" id="wed" value="수요일 접기">
	<input type="button" id="thur" value="목요일 접기">
	<input type="button" id="fri" value="금요일 접기">
	<form name="alloc_tlrForm" action="/Total2/view/admin/timeTableUpdateBiz.jsp"
		method="post">
		<input type="submit" value="등록"> <input type="button"
			value="test">
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
			out.println("<td><input type='hidden' id='"+tid+"' name='"+tid+"'><span id='"+tid+"'>배정가능<br>이곳에 배정</span><input name='alloc_chk[]' type='checkbox' value='"+idUtill.packId(dayCnt,classCnt, roomList.get(roomCnt) )+"'></td>");
				}else{
			//System.out.println(days[dayCnt] + " " + classCnt + " " + roomList.get(roomCnt) + " " + rs.getString("lecture_id"));
			System.out.printf(" %8s ",  lecture_id );
			//out.println("<td>"+ lectureMap.get(timeTableList.get(index)) +"<br>"+ timeTableList.get(index)+"</td>");	//original
			out.println("<td><input type='hidden' id='"+tid+"' name='"+tid+"' value='"+ lecture_id +"'><span id='"+tid+"'>" + lectureMap.get(timeTableList.get(index)) +"<br>"+lecture_id+"</span><input name='alloc_chk[]' type='checkbox' value='"+idUtill.packId(dayCnt,classCnt, roomList.get(roomCnt) )+"' checked></td>");
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

