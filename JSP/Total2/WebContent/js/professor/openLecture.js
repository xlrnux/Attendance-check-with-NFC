function realTimeCheck(){
	var id = $("#lecroom_id").val(); //입력된 강의실코드
	$("#main_article_div4").load("check.jsp?lecroom_id="+id);
} 
function sjlPrint(){	
	var id = $("input[name=subj_id]:radio:checked").val();	//라디오로 선택된 과목코드
	$("#main_article_div2").html(id);
}
function subjectList(){
	$("#main_article_h1").html("강의를 선택해주세요.<br>");
	$("#main_article_div3").html('강의실 호실 <input type="text" name="lecroom_id" id="lecroom_id" onkeyup="realTimeCheck()" >');
	$("#main_article_div1").load("dynamicList_subj.jsp");
}