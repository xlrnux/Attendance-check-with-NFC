<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<%@ page import="vo.Lecture"%>
<%@ page import="vo.Member"%>
<%@ page import="vo.Table"%>
<%@ page import="vo.Room"%>
<%@ page import="svc.LectureSettingBiz"%>
<%@ page import="svc.RoomSettingBiz"%>
<%@ page import="svc.UpdateCheckListBiz"%>
<%@ page import="static util.DbUtil.*"%>
<%
	request.setCharacterEncoding("UTF-8");
	String prof_id = (String)session.getAttribute("prof_id");
	System.out.println("-----------------onStateCheck");
	System.out.println(prof_id);
	String level = (String)session.getAttribute("level");
	System.out.println(level);
	String subj_id = (String)session.getAttribute("lecture_id");
	System.out.println(subj_id);
	String type = (String)session.getAttribute("type");
	System.out.println(type);
	String lecroom_id;
	int int_width;
	int int_height;
	String lecture_name;
	int class_count;
	int today;
	if(type.equals("1")){
		//subj_id = "AF039";
		//subj_id = request.getParameter("lecture_id2");
		//lecroom_id = request.getParameter("lecroom_id");
		if(subj_id != null){
			
		}else{
			
		}
		System.out.println("여기까지됫음:38번째줄");
		lecroom_id = (String)session.getAttribute("lecroom_id");
		System.out.println(lecroom_id);
		RoomSettingBiz roomSettingBiz = new RoomSettingBiz();
		Room room = roomSettingBiz.getRoomSearch(lecroom_id);
		
		LectureSettingBiz lectureSettingBiz = new LectureSettingBiz();
		Lecture lecture = lectureSettingBiz.getLectureSearch(subj_id);
		
		int_width = room.getWidth();
		System.out.println(int_width);
		int_height = room.getHeight();
		System.out.println(int_height);
		lecture_name = lecture.getLecture_name();
		System.out.println(lecture_name);
		class_count = lecture.getClass_count() + 1;
		
		session.setAttribute("lecroom_id", lecroom_id);
		session.setAttribute("width", new Integer(int_width));
		session.setAttribute("height", new Integer(int_height)); 
		session.setAttribute("lecture_name", lecture_name);
		session.setAttribute("class_count", new Integer(class_count));
		
		/////////////////////
		System.out.println("getToday");
		System.out.println(lecture.getToday());
		// 여기부터 state 값을 셋팅 철컹철컹
		if(lecture.getToday() == 0){
			lectureSettingBiz.lectureOn(subj_id, lecroom_id, class_count);
			
			// 여기부터 출석부 목록 복사
			UpdateCheckListBiz updateCheckListBiz = new UpdateCheckListBiz();
			updateCheckListBiz.updateCheckList(subj_id,class_count);
			
		}else{
			lectureSettingBiz.lectureOn(subj_id, lecroom_id);
		}
		session.setAttribute("today", new Integer(1));
		//lectureSettingBiz.lectureOn(subj_id, lecroom_id, class_count);
		roomSettingBiz.lectureOn(lecroom_id,subj_id);
		// 출석부 목록 복사하기전, 출석부를 정리. 혹여나 잘못 됫을지도 모르니 state, width, height를 모두 0으로 만든다.
		
		
		session.setAttribute("type", "0");
	}else{	
		lecroom_id = (String)session.getAttribute("lecroom_id");
		int_width = ((Integer)(session.getAttribute("width"))).intValue();
		int_height = ((Integer)(session.getAttribute("height"))).intValue();
		lecture_name = (String)session.getAttribute("lecture_name");
		class_count = ((Integer)(session.getAttribute("class_count"))).intValue();
		today = ((Integer)(session.getAttribute("today"))).intValue();
		
	}
	
	
	String width = String.valueOf(int_width);
	String height = String.valueOf(int_height);
	
	
	Connection conn = null;
	
	String URL = "jdbc:mysql://192.168.0.11:3306/test7", ID = "guest", PASSWORD = "guest";
	try {
		Class cls = Class.forName("com.mysql.jdbc.Driver");
		//out.println("드라이버 검색 성공!<br/>");
		cls.newInstance();
		//out.println("인스턴스 생성 성공!<br/>");
	} catch (ClassNotFoundException e) {
		out.println("드라이버 검색 실패!<br/>");
		e.printStackTrace();
	} catch (InstantiationException e) {
		out.println("인스턴스 생성 실패!<br/>");
	} catch (IllegalAccessException e) {
		out.println("올바르지 않은 접근!<br/>");
	}

	try {
		conn = DriverManager
				.getConnection(URL, ID, PASSWORD);
		//out.println("연결 성공!<br/>");
		Statement stmt = conn.createStatement();
		//out.println("Statement 객체 생성 성공!<br/>");
		ResultSet rs = stmt.executeQuery("SELECT `" + subj_id+"`.student_id, member.name , `" + subj_id+"`.seat_width, `" + subj_id+"`.seat_height FROM test7_check."
						+ subj_id
						+ ",member WHERE `" + subj_id+"`.state = 1 AND member.id = `" + subj_id+"`.student_id AND class_count="+class_count );
		int z = 1;
		String data = "[";
		if(rs.next()){
			do {
				data += "{\"id\" : \"" + rs.getString(1)
						+ "\",\"name\" : \"" + rs.getString(2)
						+ "\", \"width\" : " + rs.getString(3)
						+ ", \"height\" : " + rs.getString(4) + "},";
			} while (rs.next());
		}
		data += width + "," + height + "]";

		out.println(data);

	} catch (SQLException ee) {
		out.println("SQL Error = " + ee.toString());
	}finally{
		conn.close();
	}
%>
