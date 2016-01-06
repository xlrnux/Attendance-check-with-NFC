<%@page import="vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	Member user = (Member) session.getAttribute("member");
	if (!(user.getLevel().equals("professor"))) {
		// 교수가 아니라면 다른 페이지로 보내버림
	}
%>

<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>CSS3 Property Basic</title>
<script src="/Total2/js/jquery-1.9.0.js"></script>
<script type="text/javascript">
<!--
	function getOffList() {
		$("#first_label").html("미참가자");
		$("#second_label").html("아직미정");
		$("#offList").load("/Total2/view/professor/offStateCheck.jsp");
	}

	function getOnList() {
		$("#navigation_h1").html("자리배치 현황");
		$("#main_article_h1").html("참가자");
		//$("#onList").html("");
		//$("#onListTable").html("");
		$.getJSON("/Total2/view/professor/onStateCheck.jsp", function(data,
				status) {
			var width = data[data.length - 2];
			var height = data[data.length - 1];
			//document.write("<table border='1'>");
			//document.write("<caption>　<br>　전　　면　<br>　</caption>");

			$("#onList").append($("<table id=onListTable border='0' class='item2'/>"));
			$("#onListTable").append($("<caption>　<br>　전　　면　<br>　</caption>"));
			//$("#onList").append(
			//function() {
			for ( var h = 0; h < height; h++) {
				//document.write("<tr>");
				$("#onListTable").append($("<tr id= 'r" + h + "'/>"));
				for ( var w = 0; w < width; w++) {
					//document.write("<td id=" + makeId(w, h) + ">　공　석　</td>");
					$("#r" + h).append($("<td id=" + makeId(w, h) + " class='item2'/>"));
					$("#" + makeId(w, h)).html("　공　　석　");
				}
				//document.write("</tr>");
			}
	
			for ( var i = 0; i < data.length - 2; i++) {
				$("#" + makeId(data[i].width - 1, data[i].height - 1)).html("<a href='javascript:search("+data[i].id+")'>"+ data[i].id + "<br>" + data[i].name +"</a>");
				//$("#" + makeId(data[i].width - 1, data[i].height - 1)).html("<a href='#' class='btn-example' onclick='layer_open(\"layer2\", "+data[i].id+");return false;'>"+ data[i].id + "<br>" + data[i].name +"</a>");
				//$("#" + makeId(data[i].width - 1, data[i].height - 1)).html("<div class='thumbnail'><img src='http://placehold.it/20x20' /></div><div class='description'><strong>"+ data[i].id +"</strong><br><p>"+data[i].name+"</p></div>");
			}
			//$("#"+makeId(3,4)).html("11013026<br>임현동");
			//ioData();
			//	putInTable();

		});
	}
	function search(id){
		var popOption = "width=370, height=360, resizable=no, scrollbars=no, status=no;";
		window.open("http://220.124.25.180:8080/Total2/memberSet?flag=search&id="+id,"",popOption);
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
				$("#" + makeId(data[i].width - 1, data[i].height - 1)).html("<a href='javascript:search("+data[i].id+")'>"+ data[i].id + "<br>" + data[i].name +"</a>");
				//$("#" + makeId(data[i].width - 1, data[i].height - 1)).html("<div class='thumbnail'><img src='http://placehold.it/20x20' /></div><div class='description'><strong>"+ data[i].id +"</strong><br><p>"+data[i].name+"</p></div>");
			}
			//$("#"+makeId(3,4)).html("11013026<br>임현동");
			//ioData();
			//	putInTable();

		});
	}

	function getList() {
		getOffList();
		getOnList();
		getList2();
	}
	function getList2() {
		setInterval("reload(getOffList())", 5000);
		setInterval("reload(getOnList2())", 5000);
	}
	function makeId(w, h) {
		var id = "w" + w + "h" + h;
		return id;
	}
	function makeTable() {

	}
//-->
</script>
<style>
* {
	margin: 0;
	padding: 0;
}

body {
	font-family: 'Times New Roman', serif;
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
	right: 20px;
	top: 20px;
	width: 149px;
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
	font-family: 'Henny Penny', cursive;
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
	background: black;
	color: white;
}

#main_gnb>ul>li:first-child>a {
	border-radius: 10px 0 0 10px;
}

#main_gnb>ul>li:last-child>a {
	border-radius: 0 10px 10px 0;
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
	background: #E11E11;
	color: white;
}

#main_lnb>ul>li>a {
	border-radius: 10px 10px 10px 10px;
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
input:nth-of-type(1) {
	display: none;
}

input:nth-of-type(1) ~ div:nth-of-type(1) {
	display: none;
}

input:nth-of-type(1):checked ~ div:nth-of-type(1) {
	display: block;
}

/* 두 번째 탭 */
input:nth-of-type(2) {
	display: none;
}

input:nth-of-type(2) ~ div:nth-of-type(2) {
	display: none;
}

input:nth-of-type(2):checked ~ div:nth-of-type(2) {
	display: block;
}

/* 탭 모양 구성 */
section.buttons {
	overflow: hidden;
}

section.buttons>label {
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

input:nth-of-type(1):checked ~ section.buttons>label:nth-of-type(1) {
	background: #1478FF;
	color: white;
}

input:nth-of-type(2):checked ~ section.buttons>label:nth-of-type(2) {
	background: #1478FF;
	color: white;
}
</style>
<!-- 목록 -->
<style>
.item {
	overflow: hidden;
	padding: 10px;
	border: 1px solid black;
	border-top: none;
}
.item2{
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

.description>strong {
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
	background: url(loginbg2.jpg) no-repeat;
	background-size: auto;
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
</head>
<body id="body" onLoad="getList()">
	<header id="main_header">
		<hgroup id="title">
			<h1>호원 대학교 출석체크 시스템</h1>

		</hgroup>
		<nav id="main_gnb">
			<ul>
				<li><a href="#"><%=user.getName()%></a></li>
				<li><a href="/Total2/webLogout">로그아웃</a></li>
			</ul>
		</nav>
		<nav id="main_lnb">
			<ul>
				<li><a href="#">학생관리</a></li>

				<li><a href="#">강의시작</a></li>

				<li><a href="#">강의선택</a></li>

				<li><a href="#">출석 체크</a></li>

				<li><a href="#">출석부</a></li>
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
					<div id="onList"></div>
				</article>
			</section>
			<!-- 우측 탭메뉴 -->
			<aside id="main_aside">
				<input id="first" type="radio" name="tab" checked="checked" /> <input
					id="second" type="radio" name="tab" />
				<section class="buttons">
					<label id="first_label" for="first"></label> <label id="second_label" for="second"></label>
				</section>
				<div class="tab_item">
					<ul id="offList">

					</ul>
				</div>
				<div class="tab_item">
					<ul>
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
		<h1>Footer</h1>
	</div>
	
</body>
</html>