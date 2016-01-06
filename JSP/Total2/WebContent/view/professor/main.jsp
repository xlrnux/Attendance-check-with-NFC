<%@page import="vo.Member"%>
<%@page import="vo.Table"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	String prof_id = (String) session.getAttribute("prof_id");
	String level = (String) session.getAttribute("level");
	String lecture_id = (String) session.getAttribute("lecture_id");
	String prof_name = (String) session.getAttribute("prof_name");
	if (!(level.equals("professor"))) {
		// 교수가 아니라면 다른 페이지로 보내버림
		response.sendRedirect("http://220.124.25.180:8080/Total2/");
	}
%>

<!doctype html>
<html id="html">
<head>
<meta charset="utf-8">
<title>CSS3 Property Basic</title>
<script src="/Total2/js/jquery-1.9.0.js"></script>
<script src="/Total2/js/professor/mainClean.js"></script>
<script type="text/javascript">
<!--
var inter1;
var inter2;
function fal(){
	return false;
}
function realTimeCheck(){
	var id = $("#lecroom_id").val(); //입력된 강의실코드
	$("#main_article_div4").load("check.jsp?lecroom_id="+id);
} 
function sjlPrint(){	
	var id = $("input[name=lecture_id]:radio:checked").val();	//라디오로 선택된 과목코드
	$("#lecture_id2").html(id);
	$("#main_article_div3").html(id);
}
function lectureList(){
	mainClean();
	$("#lnb2").html('<a href="#" onclick="return lectureList();">강의시작</a>');
	$("#navigation_h1").html("강의시작(수동)<br>");
	$("#main_article_h1").html("강의를 선택해주세요.<br>");
	$("#main_article_div1").load("/Total2/view/professor/dynamicList_lecture.jsp");
	//$("#main_article_div3").html('강의실 호실 <input type="text" name="lecroom_id" id="lecroom_id" onkeyup="realTimeCheck()" />');
}
function lectureList2(){//출석부용
	mainClean();
	$("#navigation_h1").html("출석부<br>");
	$("#main_article_h1").html("강의를 선택해주세요.<br>");
	$("#main_article_div1").load("/Total2/view/professor/dynamicList_lecture2.jsp");
	//$("#main_article_div3").html('강의실 호실 <input type="text" name="lecroom_id" id="lecroom_id" onkeyup="realTimeCheck()" />');
}
function checkList() {
	var lecture_id = $("input[name=lecture_id]:radio:checked").val(); //라디오로 선택된 과목코드
	var lecroom_id = $("#lecroom_id").val();
	$("#main_article_div3").load("/Total2/view/professor/checkList.jsp?lecture_id=" + lecture_id);

	//setTimeout(getList(), 500);
	return false;
}
	function flagMain(){
		var lecture_id = "<%=lecture_id%>";
		//lecture_id = "AF039";//당연히 지금은 새벽중이니 열린 수업이 없다-_-; 테스트용 코드
		//lecture_id = "NoResult";
		if (lecture_id == "NoResult") {
			//alert(lecture_id);
			lectureList();
			//$("#main_article_div1").load("/Total2/view/professor/dynamicList_lecture.jsp");
			//$("#main_article_div1").load("/Total2/view/professor/dynamicList_lecture.jsp");
		} else {
			
			getList();
		}
	}
	function getList3() {
		var lecture_id = $("input[name=lecture_id]:radio:checked").val(); //라디오로 선택된 과목코드
		var lecroom_id = $("#lecroom_id").val();
		$("#temp").load(
				"/Total2/view/professor/setInfo1.jsp?lecture_id=" + lecture_id
						+ "&lecroom_id=" + lecroom_id);

		
		setTimeout(getList(), 1000);
		
		return false;
	}
	function getOffList() {
		$("#first_label").html("미출석");
		$("#second_label").html("퇴석");
		$("#tab_item1_ul1").load("/Total2/view/professor/offStateCheck.jsp");
		$("#tab_item2_ul1").load("/Total2/view/professor/outStateCheck.jsp");
	}
	function getOnList() {
		$("#temp").load("/Total2/view/professor/setInfo2.jsp");
		$("#navigation_h1").html("자리배치 현황");
		$("#main_article_h1").html("");
		//$("#onList").html("");
		//$("#onListTable").html("");
		$
				.getJSON(
						"/Total2/view/professor/onStateCheck.jsp",
						function(data, status) {
							var width = data[data.length - 2];
							var height = data[data.length - 1];
							//document.write("<table border='1'>");
							//document.write("<caption>　<br>　전　　면　<br>　</caption>");
							//$("#main_article_div1").html("");
							$("#tab_item1_ul1").html("");
							$("#tab_item2_ul1").html("");
							$("#navigation_h1")
									.append(
											$("<form method='post' onsubmit='javascript:return(lectureOff()); location.reload(); '><input type='submit' value='강의 종료'/></form><br>"));
							$("#main_article_div2")
									.append(
											$("<table id=onListTable border='0' class='item2'/>"));
							$("#onListTable").append(
									$("<caption>　<br>　전　　면　<br>　</caption>"));
							//$("#onList").append(
							//function() {
							for ( var h = 0; h < height; h++) {
								//document.write("<tr>");
								$("#onListTable").append(
										$("<tr id= 'r" + h + "'/>"));
								for ( var w = 0; w < width; w++) {
									//document.write("<td id=" + makeId(w, h) + ">　공　석　</td>");
									$("#r" + h).append(
											$("<td id=" + makeId(w, h)
													+ " class='item2'/>"));
									$("#" + makeId(w, h)).html("　공　　석　");
								}
								//document.write("</tr>");
							}

							for ( var i = 0; i < data.length - 2; i++) {
								$(
										"#"
												+ makeId(data[i].width - 1,
														data[i].height - 1))
										.html(
												"<a href='javascript:search("
														+ data[i].id + ")'>"
														+ data[i].id + "<br>"
														+ data[i].name + "</a>");
								//$("#" + makeId(data[i].width - 1, data[i].height - 1)).html("<a href='#' class='btn-example' onclick='layer_open(\"layer2\", "+data[i].id+");return false;'>"+ data[i].id + "<br>" + data[i].name +"</a>");
								//$("#" + makeId(data[i].width - 1, data[i].height - 1)).html("<div class='thumbnail'><img src='http://placehold.it/20x20' /></div><div class='description'><strong>"+ data[i].id +"</strong><br><p>"+data[i].name+"</p></div>");
							}
							//$("#"+makeId(3,4)).html("11013026<br>임현동");
							//ioData();
							//	putInTable();

						});
	}

	function search(id) {
		var popOption = "width=370, height=360, resizable=no, scrollbars=no, status=no;";
		window.open(
				"http://220.124.25.180:8080/Total2/memberSet?flag=search&id="
						+ id, "", popOption);
	}
	function getOnList2() {
		//$("#onList").html("");
		//$("#onListTable").html("");
		$.getJSON("/Total2/view/professor/onStateCheck.jsp", function(data,
				status) {
			var width = data[data.length - 2];
			var height = data[data.length - 1];
			//document.write("<table border='1'>");
			//document.write("<caption>　<br>　전　　면　<br>　</caption>");

			//$("#onList").append($("<table id=onListTable border='1'/>"));
			//$("#onListTable").append($("<caption>　<br>　전　　면　<br>　</caption>"));
			//$("#onList").append(
			//function() {

			for ( var h = 0; h < height; h++) {
				//document.write("<tr>");
				//$("#onListTable").append($("<tr id= 'r" + h + "'/>"));
				for ( var w = 0; w < width; w++) {
					//document.write("<td id=" + makeId(w, h) + ">　공　석　</td>");
					//$("#r" + h).append($("<td id=" + makeId(w, h) + "/>"));
					$("#" + makeId(w, h)).html("　공　　석　");
				}
				//document.write("</tr>");
			}
			for ( var i = 0; i < data.length - 2; i++) {
				//$("#" + makeId(data[i].width - 1, data[i].height - 1)).html("<a href='/Total2/memberSet?flag=search&id="+data[i].id +"'>"+ data[i].id + "<br>" + data[i].name +"</a>");
				$("#" + makeId(data[i].width - 1, data[i].height - 1)).html(
						"<a href='javascript:search(" + data[i].id + ")'>"
								+ data[i].id + "<br>" + data[i].name + "</a>");
				//$("#" + makeId(data[i].width - 1, data[i].height - 1)).html("<div class='thumbnail'><img src='http://placehold.it/20x20' /></div><div class='description'><strong>"+ data[i].id +"</strong><br><p>"+data[i].name+"</p></div>");
			}
			//$("#"+makeId(3,4)).html("11013026<br>임현동");
			//ioData();
			//	putInTable();

		});
	}

	function getList() {
		mainClean();
		
		$("#lnb_2").html('<a href="#" onclick="return getList();">출석자목록</a>');
		$("#navigation_h1").html("처리중입니다.");
		//location.reload('/Total2/view/professor/main.jsp');

		//getOnList();
		setTimeout('getOnList()', 2000);
		setTimeout('getOffList()', 2000);
		setTimeout('getList2()', 2500);

		return false;
	}

	function getList2() {
		inter1 = setInterval("reload(getOffList())", 1000);
		inter2 = setInterval("reload(getOnList2())", 1000);
	}
	function makeId(w, h) {
		var id = "w" + w + "h" + h;
		return id;
	}
	function stopInterval() {
		clearInterval(inter1);
		clearInterval(inter2);

		return false;
	}
	function lectureOff() {
		stopInterval();
		$("#lnb2").html('<a href="#" onclick="return lectureList();">강의시작</a>');
		$("#temp").load("/Total2/view/professor/lectureOff.jsp");
		//location.reload();
	
		//refresh();
		//mainClear();
		//reload(lectureList());
		lectureList();

		return false;
	}

//-->
</script>

<link
	href="http://fonts.googleapis.com/css?family=Anonymous+Pro:regular,italic,bold,bolditalic"
	rel="stylesheet" type="text/css">
<link href="http://fonts.googleapis.com/earlyaccess/nanumgothic.css"
	rel="stylesheet" type="text/css">
<link
	href="http://fonts.googleapis.com/earlyaccess/nanumgothiccoding.css"
	rel="stylesheet" type="text/css">
<link
	href="http://fonts.googleapis.com/earlyaccess/nanumgothicscript.css"
	rel="stylesheet" type="text/css">
<style type="text/css">
body{
	background: url(/Total2/img/log3.jpg);
	background-size: 100%;
}
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
	font-size: 15px;
	border: 1px solid black;
	border-right: 1px solid black;
	border-bottom: 1px solid black;
}

.table tbody>tr>td {
	line-height: 20px;
	line-width: 30px;
	font-size: 12px;
	width: 60px;
	border-right: 1px solid black;
	border-bottom: 1px solid black;
}

.top {
	vertical-align: top;
}
-->
</style>
<style>
* {
	margin: 0;
	padding: 0;
	
	/*background:  #0500A5;*/
	font-color: black;
	font-family: 'Anonymous Pro', serif;
	font-family: 'Nanum Gothic Coding', sans-serif;
}

body {
	
	font-style: normal;
	font-weight: 400;
	text-shadow: none;
	text-decoration: none;
	text-transform: none;
	letter-spacing: 0.011em;
	word-spacing: 0em;
	line-height: 1.2;
	scrollbar-3dlight-color: #CBBE94;
	scrollbar-arrow-color: #FFFFFF;
	scrollbar-track-color: #F7F2DF;
	scrollbar-darkshadow-color: #CBBE94;
	scrollbar-face-color: #E0DAC1;
	scrollbar-highlight-color: #F7F2DF;
	scrollbar-shadow-color: #F7F2DF;
}

li {
	list-style: none;
}

a {
	text-decoration: none;
}

img {
	border: 0;
}
</style>
<!-- 헤더 -->
<style>
#main_header {
	/* 중앙 정렬*/
	width: 960px;
	margin: 0 auto;
	/* 절대 좌표 */
	height: 160px;
	position: relative;
}

#main_header>#title {
	position: absolute;
	left: 16px;
	top: 13px;
}

#main_header>#main_gnb {
	position: absolute;
	right: 00px;
	top: 20px;
	width: 179px;
}

#main_header>#main_lnb {
	position: absolute;
	right: 20px;
	bottom: 43px;
	width: 522px;
}
</style>
<!-- 타이틀 -->
<style>
#title {
	font-family: 'Anonymous Pro', serif;
}
</style>

<!-- 메뉴(1)   로그아웃  white-->
<style>
#main_gnb>ul {
	overflow: hidden;
}

#main_gnb>ul>li {
	float: left;
}

#main_gnb>ul>li>a {
	display: block;
	padding: 2px 10px;
	border: 1px solid black;
}

#main_gnb>ul>li>a:hover {
	background: #ECD956;
	color: black;
}

#main_gnb>ul>li:first-child>a {
	/*border-radius: 10px 0 0 10px;*/
	
}

#main_gnb>ul>li:last-child>a {
	/*border-radius: 0 10px 10px 0;*/
	
}
</style>

<!-- 메뉴(2)  학생관리 개설 선택 체크 출석부 -->

<style>
#main_lnb>ul {
	overflow: hidden;
}

#main_lnb>ul>li {
	float: left;
}

#main_lnb>ul>li>a {
	display: block;
	padding: 10px 20px;
	border: 1px solid black;
}

#main_lnb>ul>li>a:hover {
	background: #ECD956;
	color: black;
}

#main_lnb>ul>li>a {
	/*border-radius: 10px 10px 10px 10px;*/
	
}
</style>

<!-- 콘텐츠 -->
<style>
#content {
	/* 중앙 정렬 */
	width: 960px;
	margin: 0 auto;
	/* 수평 레이아웃 구성 */
	overflow: hidden;
}

#content>#main_section {
	width: 750px;
	float: left;
}

#content>#main_aside {
	width: 200px;
	float: right;
}
</style>
<!-- 본문 -->
<style>
#main_section>article.main_article {
	margin-bottom: 10px;
	padding: 20px;
	border: 1px solid black;
}
</style>

<!-- 사이드 -->
<style>

/* 첫 번째 탭 */
nth-of-type

 

(1){
display


:

 

block


;
}
input[type=submit]:nth-of-type(1) {
	display: block;
	display: block;
	float: left;
	/* 크기 및 글자 위치 지정*/
	width: 100px;
	height: 30px;
	line-height: 20px;
	text-align: center;
	/* 테두리 지정 */
	box-sizing: border-box;
	border: 1px solid black;
	/* 색상 지정*/
	background: white;
	color: black;
	position: relative;
	left: 650px;
	bottom: 30px;
}

input[type=submit]:nth-of-type(1):hover {
	display: block;
	background: #ECD956;
	display: block;
	float: left;
	/* 크기 및 글자 위치 지정*/
	width: 100px;
	height: 30px;
	line-height: 20px;
	text-align: center;
	/* 테두리 지정 */
	box-sizing: border-box;
	border: 1px solid black;
	/* 색상 지정*/
	background: #ECD956;
	color: black;
	position: relative;
	left: 650px;
	bottom: 30px;
}

aside>input:nth-of-type(1) {
	display: none;
}

aside>input:nth-of-type(1) ~ div:nth-of-type(1) {
	display: none;
}

aside>input:nth-of-type(1):checked ~ div:nth-of-type(1) {
	display: block;
}

/* 두 번째 탭 */
aside>input:nth-of-type(2) {
	display: none;
}

aside>input:nth-of-type(2) ~ div:nth-of-type(2) {
	display: none;
}

aside>input:nth-of-type(2):checked ~ div:nth-of-type(2) {
	display: block;
}

/* 탭 모양 구성 */
aside>section.buttons {
	overflow: hidden;
}

aside>section.buttons>label {
	/* 수평 정렬 */
	display: block;
	float: left;
	/* 크기 및 글자 위치 지정*/
	width: 100px;
	height: 30px;
	line-height: 30px;
	text-align: center;
	/* 테두리 지정 */
	box-sizing: border-box;
	border: 1px solid black;
	/* 색상 지정*/
	background: white;
	color: black;
}

aside>input:nth-of-type(1):checked ~ section.buttons>label:nth-of-type(1)
	{
	background: #ECD956;
	color: white;
}

aside>input:nth-of-type(2):checked ~ section.buttons>label:nth-of-type(2)
	{
	background: #ECD956;
	color: white;
}
</style>
<!-- 목록 -->
<style>
.error {
	margin: 0;
	padding: 0;
	background: #ECD956;
	color: white;
	font-size: 50px;
	font-family: 'Anonymous Pro', serif;
}

.tab_scroll {
	scrollbar-3dlight-color: #d9d9d9;
	scrollbar-arrow-color: #ECD956;
	scrollbar-track-color: #dddddd;
	scrollbar-darkshadow-color: #ECD956;
	scrollbar-face-color: #e92802;
	scrollbar-highlight-color: #ECD956;
	scrollbar-shadow-color: #ECD956;
}

.tab_item {
	width: 200px;
	height: 426px;
	overflow-y: scroll;
	overflow-x: hidden;
	border: 3px solid;
	border: #ECD956;
}

.item {
	overflow: hidden;
	padding: 10px;
	border: 1px solid black;
	border-top: none;
}

.item2 {
	overflow: hidden;
	padding: 10px;
	border: 1px solid black;
	color: black;
}

.thumbnail {
	float: left;
}

.description {
	float: left;
	margin-left: 10px;
}

.description>strong1 {
	display: #1347EB;
	width: 120px;
	white-space: nowrap;
	overflow: hidden;
	text-overflow: ellipsis;
}
</style>
<!-- 푸터 -->
<style>
#main_footer {
	/* 중앙 정렬 */
	width: 960px;
	margin: 0 auto;
	margin-bottom: 10px;
	/* 테두리 */
	box-sizing: border-box;
	padding: 10px;
	border: 1px solid black;
	/* 글자 정렬 */
	text-align: center;
}
</style>


<style>

/* body 태그를 중앙 정렬합니다. */
body {
	width: 960px;
	margin: 0 auto;
}

#section {
	width: 200px;
	float: left;
}

#aside {
	width: 760px;
	float: right;
}

#wrap {
	overflow: hidden;
}
</style>

<!--  HTML -->
</head>
<body id="body" onLoad="flagMain()">
	<header id="main_header">
		<hgroup id="title">
			<h1></h1>

		</hgroup>
		<nav id="main_gnb">
			<ul>
				<li><a href="#"><%=prof_name%></a></li>
				<li><a href="/Total2/webLogout">로그아웃</a></li>
			</ul>
		</nav>
		<nav id="main_lnb">
			<ul>
				<li id="lnb_2"><a href="#" onclick="return lectureList();">강의시작</a></li>
				<li id="lnb_3"><a href="#" onclick="return lectureList2();">출석부</a></li>
			</ul>
		</nav>

	</header>

	<div id="navigation">
		<h1 id="navigation_h1"></h1>
	</div>

	<div id="wrap">
		<div id="content">
			<section id="main_section">
				<article class="main_article">
					<h1 id="main_article_h1"></h1>
					<div id="main_article_div1" name="main_article_div1"></div>
					<div id="main_article_div2" name="main_article_div2"></div>
					<div id="main_article_div3" name="main_article_div3"></div>
					<div id="main_article_div4" name="main_article_div4"></div>
				</article>
			</section>
			<!-- 우측 탭메뉴 -->
			<aside id="main_aside">
				<input id="first" type="radio" name="tab" checked="checked" /> <input
					id="second" type="radio" name="tab" />
				<section class="buttons">
					<label id="first_label" for="first"></label> <label
						id="second_label" for="second"></label>
				</section>
				<!-- <div class="tab_item" style="width:215px;height:426px;overflow-y:scroll;overflow-x:hidden;"> -->
				<div>
					<ul id="tab_item1_ul1" class="tab_item">

					</ul>
				</div>
				<div class="tab_item"
					style="width: 200px; height: 426px; overflow-y: scroll; overflow-x: hidden;">
					<ul id="tab_item2_ul1">
						<li class="item"><a href="#">
								<div class="thumbnail">
									<img src="http://placehold.it/45x45" />
								</div>
								<div class="description">
									<strong>CSS3 Transition</strong>
									<p>2012-03-15</p>
								</div>
						</a></li>
						<li class="item"><a href="#">
								<div class="thumbnail">
									<img src="http://placehold.it/45x45" />
								</div>
								<div class="description">
									<strong>CSS3 Animation</strong>
									<p>2012-03-15</p>
								</div>
						</a></li>
						<li class="item"><a href="#">
								<div class="thumbnail">
									<img src="http://placehold.it/45x45" />
								</div>
								<div class="description">
									<strong>CSS3 Border</strong>
									<p>2012-03-15</p>
								</div>
						</a></li>
						<li class="item"><a href="#">
								<div class="thumbnail">
									<img src="http://placehold.it/45x45" />
								</div>
								<div class="description">
									<strong>CSS3 Box</strong>
									<p>2012-03-15</p>
								</div>
						</a></li>
					</ul>
				</div>
			</aside>
		</div>
	</div>
	<div id="footer">
		<h1></h1>
	</div>
	<div id="temp"></div>
</body>
</html>
