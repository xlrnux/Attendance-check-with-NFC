<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html >
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="/Total2/css/layerPop.css" type="text/css" rel="stylesheet">
<script src="/Total2/js/jquery-1.9.0.js"></script>
<script src="/Total2/js/layerPop.js"></script>



</head>
<body>
	<a href="#" class="btn-example"	onclick="layer_open('layer2');return false;">예제-2 보기</a>
	<div class="layer">
		<div class="bg"></div>
		<div id="layer2" class="pop-layer">
			<div class="pop-container">
				<div class="pop-conts">
					<!--content //-->
					<p class="ctxt mb20">
						Thank you.<br> Your registration was submitted successfully.<br>
						Selected invitees will be notified by e-mail on JANUARY 24th.<br>
						<br> Hope to see you soon!
					</p>

					<div class="btn-r">
						<a href="#" class="cbtn">Close</a>
					</div>
					<!--// content-->
				</div>
			</div>
		</div>
	</div>

</body>
</html>

