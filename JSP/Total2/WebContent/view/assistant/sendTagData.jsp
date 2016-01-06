<%@ page import="svc.TagSettingBiz" %>
<%@ page import="vo.Tag_ID" %>
<%@ page import="java.util.ArrayList" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String flag = request.getParameter("flag");
	String lecroom_id = request.getParameter("lecroom_id");
	//String flag = "room";
	//String lecroom_id = "5334";
	TagSettingBiz tagSetBiz = new TagSettingBiz();
	if(flag.equals("room")){
		// 강의실 단위 요청일때
		ArrayList<Tag_ID> dataList = new ArrayList<Tag_ID>();
		dataList = tagSetBiz.getSeatData(lecroom_id);
		out.print(dataList.get(0).getWidth()+"|");
		out.print(dataList.get(0).getHeight()+"|");
		out.print(dataList.get(0).getTag_id()+"|");
		out.print(dataList.get(0).getTag_id_old());
		for(int i = 1; i < dataList.size(); i++){
			out.print("||");
			out.print(dataList.get(i).getWidth()+"|");
			out.print(dataList.get(i).getHeight()+"|");
			out.print(dataList.get(i).getTag_id()+"|");
			out.print(dataList.get(i).getTag_id_old());
		}
	}else if(flag.equals("seat")){
		// 좌석단위 요청일때
		String _width = request.getParameter("width");
		String _height = request.getParameter("height");
		int width = Integer.parseInt(_width);
		int height = Integer.parseInt(_height);
		Tag_ID tagData = new Tag_ID();
		tagData = tagSetBiz.getSeatData(lecroom_id, width, height);
		out.print(tagData.getWidth()+"|"+tagData.getHeight()+"|"+tagData.getTag_id()+"|"+tagData.getTag_id_old());
	}
%>
	