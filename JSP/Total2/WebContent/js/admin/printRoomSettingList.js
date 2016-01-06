function printSettingList() {
	$("#list").load("/Total2/view/admin/settingList.jsp");
	$("#form").html("");
	$("#view").html("");
	$("#form2").html("");
	// $("#list").html("Hello");
}
function printRoomSetting() {
	$("#list").load("/Total2/view/admin/roomSettingList.jsp");
	$("#form").html("");
	$("#view").html("");
	$("#form2").html("");
	// $("#list").html("Hello");
}
function printRoomAdd() {
	$("#list")
			.html(
					'<input type="button" value = "←" name="backRoomSetting" id="backRoomSetting" onClick="printRoomSetting()" >');
	$("#form").load("/Total2/view/admin/roomAdd.jsp");
	$("#view").html("");
	$("#form2").html("");
	// $("#list").html("Hello");
}
function printRoomSearch() {
	$("#list")
			.html(
					'<input type="button" value = "←" name="backRoomSetting" id="backRoomSetting" onClick="printRoomSetting()" >');
	$("#form").load("/Total2/view/admin/roomSearch.jsp");
	$("#view").html("");
	$("#form2").html("");
	// $("#list").html("Hello");
}
function printRoomModify() {
	$("#list")
			.html(
					'<input type="button" value = "←" name="backRoomSetting" id="backRoomSetting" onClick="printRoomSetting()" >');
	$("#form").load("/Total2/view/admin/roomModify.jsp");
	$("#view").html("");
	$("#form2").html("");
	// $("#list").html("Hello");
}
function printRoomDelete() {
	$("#list")
			.html(
					'<input type="button" value = "←" name="backRoomSetting" id="backRoomSetting" onClick="printRoomSetting()" >');
	$("#form").load("/Total2/view/admin/roomDelete.jsp");
	$("#view").html("");
	$("#form2").html("");
	// $("#list").html("Hello");
}
