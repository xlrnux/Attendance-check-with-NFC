<%@ page import="java.util.ArrayList" %>
<%@ page import="svc.CheckListBiz" %>
<%@ page import="svc.MemberSettingBiz" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	//String lecture_id = request.getParameter("lecture_id_check");
	String lecture_id = "AD003";
	ArrayList<String> studentList = new ArrayList<String>();
	ArrayList<ArrayList<String>> checkList = new ArrayList<ArrayList<String>>();
	ArrayList<String> nameList = new ArrayList<String>();
	CheckListBiz checkListBiz =new CheckListBiz();
	MemberSettingBiz memSetBiz =new MemberSettingBiz();
	studentList = checkListBiz.getStudentList(lecture_id);
	checkList = checkListBiz.getCheckList(studentList, lecture_id);
	nameList =  memSetBiz.getNameList(studentList);
	//out.println("　　　");
	out.println("<table class='table'><thead><td></td>");
	for(int j = 1; j <= (checkList.get(1)).size(); j++){
		out.println("<td>"+j+"회차</td>");
		//out.println(" " +studentList.get(j)+" ");
	}
	out.println("</thead><tbody>");
	
	int i = 0;
	for(ArrayList<String> a: checkList){
		
		out.println("<td><a href='javascript:search("+studentList.get(i)+")'>"+nameList.get(i)+"</a></td>");
		//out.println(i+"회차: ");
		for(String b: a){
			if(b.equals("0")){
				out.println("<td>결석</td>");
			}else if(b.equals("1")){
				out.println("<td>출석</td>");
			}else if(b.equals("2")){
				out.println("<td>출석</td>");
			}
			
		}
		out.println("</tr>");
		i++;
	}
	out.println("</tbody></table>");
	
	/*
	for(int i = 0; i < checkList.size(); i++){
		for(int j = 0;j < ; j++){
			
		}
	}
	*/
%>
	