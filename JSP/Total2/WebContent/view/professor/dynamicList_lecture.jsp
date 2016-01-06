<%@page import="vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page session="true"%>
<%@ page autoFlush="true"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.util.Hashtable"%>
<%
	request.setCharacterEncoding("UTF-8");
	String prof_id = (String)session.getAttribute("prof_id");
	String level = (String)session.getAttribute("level");
	if (!(level.equals("professor"))) {
		// 교수가 아니라면 다른 페이지로 보내버림
	}
	String lecture_id = (String)session.getAttribute("lecture_id");

	//String prof_id = request.getParameter("prof_id");
	//out.print(prof_id);
	Hashtable listSubj = new Hashtable();
	//String URL = "jdbc:mysql://220.124.24.25:3306/test1", ID = "ar2n89", PASSWORD = "aaaaaaaa";
	String URL = "jdbc:mysql://192.168.0.11:3306/test7", ID = "guest", PASSWORD = "guest";
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
				.executeQuery("SELECT lecture_id, lecture_name FROM lecture WHERE prof_id='"
						+ prof_id + "'");
		if (rs.next()) {
			out.print("<form  method='post' onSubmit='return getList3()'>");
			do {
				out.print("<input type='radio' id='lecture_id' name='lecture_id' value='"
						+ rs.getString(1) + "' OnClick='sjlPrint()'>"
						+ rs.getString(1) + " " + rs.getString(2)
						+ "<br>");
			} while (rs.next());
			out.print("강의실 호실 <input type='text' name='lecroom_id' id='lecroom_id'/>");
			out.print("<input type='hidden' name='lecture_id2' id='lecture_id2'>");
			out.print("<input type='submit'/>");
			out.print("</form>");
		} else {
			out.println("현재 담당중이신 수업이 없습니다.");
		}

	} catch (Exception e) {
		out.print("Exception Error!");
		out.print(e);
	}
	//
%>

