<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<!doctype html>
<html>

<head>

<meta charset="utf-8">
<title>전자출석체크 시스템 Ver.0.7.4</title>
<style>

/* body 태그를 중앙 정렬합니다. */
body {
	width: 1350px;
	margin: 0 auto;
	background: url(/Total2/img/log2.jpg);
	background-size: 100%;
}

#aside {
	width: 200px;
	float: left;
}

#section {
	width: 500px;
	float: right;
}
.submit_btn{
	border-bottom: 1px dotted #000000;
	border-bottom: 1px dotted #000000;
	border-bottom-left-radius: 20px border-bottom-right-radius:   50px 7px;
	border-top-left-radius: 20px border-top-right-radius:   50px 7px;
	padding: 15px;
	width: 280px;
	margin-bottom: 30px;
	color: #ffffff;
	background-color: #665544;
	border: none;
	border-radius: 5px;
	width: 80px;
}
input {
	border-bottom: 1px dotted #000000;
	border-bottom: 1px dotted #000000;
	border-bottom-left-radius: 20px border-bottom-right-radius:   50px 7px;
	border-top-left-radius: 20px border-top-right-radius:   50px 7px;
	padding: 15px;
	width: 280px;
	margin-bottom: 30px;
}

input:focus {
	border: 1px dotted #dcdcdc;
	outline: none;
}

input#submit {
	color: #ffffff;
	background-color: #665544;
	border: none;
	border-radius: 5px;
	width: 80px;
}

input#submit:hover {
	color: #665544;
	background-color: #efefef;
}

fieldset {
	width: 350px;
	border: 1px solid #2731ED;
	border-radius: 10px;
	padding: 20px;
	text-align: right;
}

legend {
	background-color: #efefef;
	border: 1px solid #000000;
	border-radius: 10px;
	padding: 20px 30px;
	text-align: left;
	text-transform: uppercase;
}

home {
	float: right;
}

#wrap {
	overflow: hidden;
}
</style>
</head>
<body>
	<div id="header">
		<h1></h1>
	</div>
	<div id="navigation">
		<h1></h1>
	</div>
	<div id="wrap">
		<div id="aside">
			<h1></h1>
			<p></p>
		</div>
		<p>&nbsp;</p>
		<p>&nbsp;</p>
		<p>&nbsp;</p>
		<p>&nbsp;</p>
		<p>&nbsp;</p>
		<p>&nbsp;</p>
		<p>&nbsp;</p>
		<p>&nbsp;</p>
		<div id="section">
			<form name="loginForm" action="/Total2/web" method="post">
				<fieldset>

					<div>
						<label for="id" class="title"><img
							src="/Total2/img/id.png" width="21" height="15" /></label> <input
							type="text" id="id" name="id" />
					</div>
					<div>
						<label for="password" class="title"> <img
							src="/Total2/img/pw.png" width="28" height="15" /></label> <input
							type="password" id="password" name="password" />
					</div>
					<input class="submit_btn" type="submit" value="로그인" id="login" name="login" />
					</p>
				</fieldset>
			</form>
		</div>
	</div>
	</div>
	<div id="footer">
		<h1></h1>
		<table width="995" border="0" cellspacing="0" cellpadding="0"
			align="right">
			<tr>
				<td width="885" height="30" align="right" valign="middle">HOME&nbsp;│&nbsp;
					Sitemap&nbsp; &nbsp;</td>
				<td width="86" align="right" valign="middle"><label
					for="familySite"></label> <script>
function goto(str) {
  temp = str.split(",");
  loc = temp[0];
  opt = temp[1];
  if(opt)
    open(loc,"");
  else
    location = loc;
}
</script> <select onChange="goto(this.value)">

						<option value="/Total2/view/login/loginPage.jsp">-----Family
							Site-----</option>
						<option value="http://intra.howon.ac.kr/HOWON/index.html">
							인트라넷</option>
						<option value="http://www.howon.ac.kr/">학교홈페이지</option>
						<option value="http://house.howon.ac.kr/main.php">학교 생활관</option>
				</select></td>


			</tr>
		</table>




	</div>


</body>
</html>
