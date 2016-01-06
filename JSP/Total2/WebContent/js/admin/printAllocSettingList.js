function printSettingList() {
	$("#list").load("/Total2/view/admin/settingList.jsp");
	$("#form").html("");
	$("#view").html("");
	$("#form2").html("");
	// $("#list").html("Hello");
}
function printAllocSetting() {
	$("#list").load("/Total2/view/admin/allocSettingList.jsp");
	$("#form").html("");
	$("#view").html("");
	$("#form2").html("");
	// $("#list").html("Hello");
}
function printAllocView() {
	$("#list")
			.html(
					'<input type="button" value = "←" name="backAllocSetting" id="backAllocSetting" onClick="printAllocSetting()" >');
	$("#form").load("/Total2/view/admin/timeLectureRoom_View.jsp");
	$("#view").html("");
	$("#form2").html("");
	// $("#list").html("Hello");
}
function printAllocUpdate() {
	$("#list")
			.html(
					'<input type="button" value = "←" name="backAllocSetting" id="backAllocSetting" onClick="printAllocSetting()" >');
	$("#form").load("/Total2/view/admin/timeLectureRoom_alloc.jsp");
	$("#view").html("");
	$("#form2").html("");
	// $("#list").html("Hello");
}
