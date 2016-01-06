<%@ page import="svc.LectureSettingBiz"%>
<%@ page import="svc.RoomSettingBiz"%>
<%@ page import="javax.servlet.http.HttpServlet"%>
<%@ page import="javax.servlet.http.HttpServletRequest"%>
<%@ page import="javax.servlet.http.HttpServletResponse"%>
<%@ page import="javax.servlet.http.HttpSession"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	String prof_id = (String)session.getAttribute("prof_id");
	String level = (String)session.getAttribute("level");
	String subj_id = (String)session.getAttribute("lecture_id");
	String lecroom_id = (String)session.getAttribute("lecroom_id");
	
	//session.invalidate();
	session.removeAttribute("width");
	session.removeAttribute("height");
	session.removeAttribute("lecture_id");
	session.removeAttribute("lecture_name");
	session.removeAttribute("today");
	session.setAttribute("type", "1");
	LectureSettingBiz lectureSettingBiz = new LectureSettingBiz();
	RoomSettingBiz roomSettingBiz = new RoomSettingBiz();
	
	lectureSettingBiz.lectureOff(subj_id);
	roomSettingBiz.lectureOff(lecroom_id);
	
%>
<!DOCTYPE html >
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
</body>
</html>

	