<%@ page import="java.util.ArrayList"%>
<%@ page import="svc.TimeTableSettingBiz" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String []alloc_chk = request.getParameterValues("alloc_chk[]");
	ArrayList<String> data = new ArrayList<String>();
	for(String a:alloc_chk){
		String a4 ="";
		String []a2 = a.split("_");
		for(String a3: a2){
		a4 = a4 + a3 + "|";
		}
		a4 = a4 + request.getParameter(a);
		data.add(a4);
	}
	
	TimeTableSettingBiz ttSetBiz = new TimeTableSettingBiz();
	ttSetBiz.timeTableUpdate(data);
	for(int i=0; i < data.size(); i++){
		String []a5 = (data.get(i)).split("|");
		out.print(data.get(i)+"<br>");
	}
	response.sendRedirect("/Total2/view/admin/main.jsp");
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

