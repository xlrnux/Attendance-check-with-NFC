<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<%
	request.setCharacterEncoding("UTF-8");
	//String subj_id = (String) session.getAttribute("subj_id");
	//String prof_id = (String) session.getAttribute("prof_id");
	//String lecroom_id = (String) session.getAttribute("lecroom_id");

	//String subj_id = "AF039";
	String subj_id = (String)session.getAttribute("lecture_id");
	int class_count = ((Integer)(session.getAttribute("class_count"))).intValue();
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
	}finally{
		
	}

	try {
		conn = DriverManager
				.getConnection(URL, ID, PASSWORD);
		//out.println("연결 성공!<br/>");
		Statement stmt = conn.createStatement();
		//out.println("Statement 객체 생성 성공!<br/>");
		ResultSet rs = stmt.executeQuery("SELECT `member`.`name`, `" + subj_id+"`.`student_id` FROM `test7_check`.`"
				+ subj_id + "`, `member` WHERE `" + subj_id+"`.`state` = 2 AND `member`.`id` = `" + subj_id+"`.`student_id` AND `class_count`="+class_count);
		int i = 1;
		while (rs.next()) {
			out.println("<li class='item'><a href='javascript:search("+rs.getString(2)+")'><div class='thumbnail'><img src='http://placehold.it/45x45' /></div><div class='description'><strong>"+rs.getString(1)+"</strong><p>"+rs.getString(2)+"</p></div></a></li>");
		}
		
	} catch (SQLException ee) {
		out.println("SQL Error = " + ee.toString());
	}finally{
		conn.close();
	}
%>
