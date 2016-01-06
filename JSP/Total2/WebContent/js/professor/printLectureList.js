function printMainList(){
	$("#list").load("/Total2/view/professor/mainList.jsp");
	$("#form").html("");
	$("#view").html("");
	$("#form2").html("");
	//$("#list").html("Hello");
}
function printLectureList(){
	$("#list").load("/Total2/view/professor/lectureList.jsp");
	$("#form").html("");
	$("#view").html("");
	$("#form2").html("");
	//$("#list").html("Hello");
}
function printMemberAdd(){
	$("#list").html('<input type="button" value = "←" name="backMemberSetting" id="backMemberSetting" onClick="printMemberSetting()" >');
	$("#form").load("/Total2/view/admin/memberAdd.jsp");
	$("#view").html("");
	$("#form2").html("");
	//$("#list").html("Hello");
}
function printMemberSearch(){
	$("#list").html('<input type="button" value = "←" name="backMemberSetting" id="backMemberSetting" onClick="printMemberSetting()" >');
	$("#form").load("/Total2/view/admin/memberSearch.jsp");
	$("#view").html("");
	$("#form2").html("");
	//$("#list").html("Hello");
}
function printMemberModify(){
	$("#list").html('<input type="button" value = "←" name="backMemberSetting" id="backMemberSetting" onClick="printMemberSetting()" >');
	$("#form").load("/Total2/view/admin/memberModify.jsp");
	$("#view").html("");
	$("#form2").html("");
	//$("#list").html("Hello");
}
function printMemberDelete(){
	$("#list").html('<input type="button" value = "←" name="backMemberSetting" id="backMemberSetting" onClick="printMemberSetting()" >');
	$("#form").load("/Total2/view/admin/memberDelete.jsp");
	$("#view").html("");
	$("#form2").html("");
	//$("#list").html("Hello");
}

