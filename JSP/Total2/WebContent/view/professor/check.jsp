<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<%
	request.setCharacterEncoding("UTF-8");
	String lecroom_id = request.getParameter("lecroom_id");
	//String URL = "jdbc:mysql://220.124.24.25:3306/test1", ID = "ar2n89", PASSWORD = "aaaaaaaa";
	String URL = "jdbc:mysql://192.168.0.11:3306/test6", ID = "guest", PASSWORD = "guest";
	try {
		//Class.forName("org.gjt.mm.mysql.Driver");
		Class.forName("com.mysql.jdbc.Driver");

		//out.println("드라이버 검색 성공!");
	} catch (ClassNotFoundException e) {
		out.println("드라이버 검색 실패" + e.toString());
		e.printStackTrace();
	}

	try {
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Connection con = DriverManager.getConnection(URL, ID, PASSWORD);
		Statement stmt = con.createStatement();
		ResultSet rs = stmt
				.executeQuery("SELECT state FROM lecture_room WHERE lecroom_id='"
						+ lecroom_id + "'");
		String rt_lecroom_id = null;
		while (rs.next()) {
			rt_lecroom_id = rs.getString(1);
		}
		String result = "";
		if (rt_lecroom_id == null) {
			result = "없는 강의실 입니다.";
		} else {
			if (rt_lecroom_id.equals("0")){
				result = "사용 가능합니다.";
			} else {
				result = "이미 사용중인 강의실입니다.";
			}
		}
		out.print(result);
	} catch (Exception e) {
		out.print("Exception Error!");
		out.print(e);
	}
	//
%>

	