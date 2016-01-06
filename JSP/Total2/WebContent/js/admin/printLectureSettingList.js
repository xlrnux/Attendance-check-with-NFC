function printSettingList() {
	$("#list").load("/Total2/view/admin/settingList.jsp");
	$("#form").html("");
	$("#view").html("");
	$("#form2").html("");
	// $("#list").html("Hello");
}
function printLectureSetting() {
	$("#list").load("/Total2/view/admin/lectureSettingList.jsp");
	$("#form").html("");
	$("#view").html("");
	$("#form2").html("");
	// $("#list").html("Hello");
}
function printLectureAdd() {
	$("#list")
			.html(
					'<input type="button" value = "←" name="backLectureSetting" id="backLectureSetting" onClick="printLectureSetting()" >');
	$("#form").load("/Total2/view/admin/lectureAdd.jsp");
	$("#view").html("");
	$("#form2").html("");
	// $("#list").html("Hello");
}
function printLectureSearch() {
	$("#list")
			.html(
					'<input type="button" value = "←" name="backLectureSetting" id="backLectureSetting" onClick="printLectureSetting()" >');
	$("#form").load("/Total2/view/admin/lectureSearch.jsp");
	$("#view").html("");
	$("#form2").html("");
	// $("#list").html("Hello");
}
function printLectureModify() {
	$("#list")
			.html(
					'<input type="button" value = "←" name="backLectureSetting" id="backLectureSetting" onClick="printLectureSetting()" >');
	$("#form").load("/Total2/view/admin/lectureModify.jsp");
	$("#view").html("");
	$("#form2").html("");
	// $("#list").html("Hello");
}
function printLectureDelete() {
	$("#list")
			.html(
					'<input type="button" value = "←" name="backLectureSetting" id="backLectureSetting" onClick="printLectureSetting()" >');
	$("#form").load("/Total2/view/admin/lectureDelete.jsp");
	$("#view").html("");
	$("#form2").html("");
	// $("#list").html("Hello");
}
