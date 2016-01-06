<%@ page import="java.sql.*"%>
<%@ page import="vo.Member" %>
<%@ page import="svc.RequestCheckBiz"%>
<%@ page import="svc.LoginBiz"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	System.out.println("MobileProcess 시작");
	String mac_address = request.getParameter("mac_address");
	System.out.println("mac_address: "+ mac_address);
	String lecroom_id = request.getParameter("lecroom_id");
	System.out.println("lecroom_id: "+ lecroom_id);
	int width = Integer.parseInt((request.getParameter("width")));
	System.out.println("width: "+ width);
	int height = Integer.parseInt((request.getParameter("height")));
	System.out.println("height: "+ height);
	String flag = null;
	LoginBiz loginBiz = new LoginBiz();
	Member member = new Member();
	member = loginBiz.getMemberLogin(mac_address);
	boolean result;
	RequestCheckBiz requestCheckBiz = new RequestCheckBiz();
	String student_id = member.getId();
	String level = member.getLevel();
	String lecture_id = member.getState();
	System.out.println("mobile: lecture_id: " + lecture_id);
	if(lecture_id.equals("0")){
		flag = "checkIn";
	}else{
		flag = "checkOut";
	}
	
	System.out.println("mobile: flag: " + flag);
	if(flag.equals("checkIn")){
		//체크인 요청
		lecroom_id = request.getParameter("lecroom_id");
		
		// 체크인 Biz
		lecture_id = requestCheckBiz.getLecture_id(lecroom_id);
		int class_count = requestCheckBiz.answerPossible(student_id, lecture_id);
		if(class_count < 0){
			// answerPossible에서 이상한 결과가 도출됨.
			result = false;
		}else{
			result = requestCheckBiz.CheckIn(student_id, lecture_id, class_count, width, height);
			if(result){
				//출석체크 성공
				session.setAttribute("mobile","checkIn_s");
				
				response.sendRedirect("tagResult.jsp");
			}else{
				// 출석체크 실패
				session.setAttribute("mobile","checkIn_f");
				response.sendRedirect("tagResult.jsp");
			}
		}		
		
	}else{
		// 체크아웃 요청
		lecroom_id = (String)session.getAttribute("lecroom_id");
		//체크아웃 Biz
		
	}
	
	
%>

	