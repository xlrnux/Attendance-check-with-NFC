<%@ page import="vo.Lecture"%>
<%@ page import="vo.Room"%>
<%@ page import="svc.LectureSettingBiz"%>
<%@ page import="svc.RoomSettingBiz"%>
<%@ page import="svc.UpdateCheckListBiz"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	String subj_id = request.getParameter("lecture_id");
	String lecroom_id = request.getParameter("lecroom_id");
	System.out.println("setInfo1.jsp:lecture_id: "+subj_id);
	Room room = new Room();
	Lecture lecture = new Lecture();
	LectureSettingBiz lectureSettingBiz = new LectureSettingBiz();
	RoomSettingBiz roomSettingBiz = new RoomSettingBiz();
	
	room = roomSettingBiz.getRoomSearch(lecroom_id);
	lecture = lectureSettingBiz.getLectureSearch(subj_id);
	if(lecture.getToday() == 2){
		// 강의를 완전히 종료하였습니다. 오늘은 이 강의를 열수 없습니다.
		response.sendRedirect("alreadyClosedLecture.jsp");
	}else{
		session.setAttribute("width", new Integer(room.getWidth()));
		session.setAttribute("height", new Integer(room.getHeight())); 
		System.out.println("setInfo1.jsp:lecture_name: "+lecture.getLecture_name());
		session.setAttribute("lecture_name", lecture.getLecture_name());
		if(lecture.getToday() == 1){
			session.setAttribute("class_count", new Integer(lecture.getClass_count()));
			lectureSettingBiz.lectureOn(subj_id, lecroom_id);
		}else if(lecture.getToday() == 0){
			session.setAttribute("class_count", new Integer(lecture.getClass_count()+1));
			lectureSettingBiz.lectureOn(subj_id, lecroom_id, lecture.getClass_count()+1);
			// 여기부터 출석부 목록 복사
			UpdateCheckListBiz updateCheckListBiz = new UpdateCheckListBiz();
			updateCheckListBiz.updateCheckList(subj_id,lecture.getClass_count()+1);
		}else{
			
			
		}
		session.setAttribute("today", lecture.getToday());
		session.setAttribute("lecture_id", subj_id);
		session.setAttribute("lecroom_id", lecroom_id);
		System.out.println("lecture_id:setInfo1.jsp: " + subj_id);
		System.out.println("lecroom_id:setInfo1.jsp: " + lecroom_id);
		session.setAttribute("type", "2");
		
		roomSettingBiz.lectureOn(lecroom_id,subj_id);
	}
	
%>
