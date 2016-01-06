package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.LectureSettingBiz;
import vo.Lecture;
/**
 * Servlet implementation class LectureAdd
 */
@WebServlet("/lectureSet")
public class LectureSettingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LectureSettingServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String flag = request.getParameter("flag");
		if(flag.equals("add")){
			String lecture_id = request.getParameter("lecture_id");
			String lecture_name = request.getParameter("lecture_name");
			String prof_id = request.getParameter("prof_id");
			int class_count = 0;
			int credit = Integer.parseInt(request.getParameter("credit"));
			int credit_hour = Integer.parseInt(request.getParameter("credit_hour"));
			
			Lecture addLecture = new Lecture();
			
			addLecture.setLecture_id(lecture_id);
			addLecture.setLecture_name(lecture_name);
			addLecture.setProf_id(prof_id);
			addLecture.setClass_count(class_count);
			addLecture.setCredit(credit);
			addLecture.setCredit_hour(credit_hour);
			
			LectureSettingBiz lectureSettingBiz = new LectureSettingBiz();
			if(lectureSettingBiz.lectureAdd(addLecture)){
				// 성공하면 어디로 이동하나?
				RequestDispatcher dispatcher = request.getRequestDispatcher("/view/admin/main.jsp");
				dispatcher.forward(request, response);
			}else{
				//뭔가 잘못된경우
			}
		}else if(flag.equals("del")){
			String lecture_id = (String)request.getParameter("lecture_id");
			System.out.println(lecture_id);
			LectureSettingBiz lectureSettingBiz = new LectureSettingBiz();
			if(lectureSettingBiz.lectureDel(lecture_id)){
				// 성공하면 어디로 이동하나?
				RequestDispatcher dispatcher = request.getRequestDispatcher("/view/admin/main.jsp");
				dispatcher.forward(request, response);
			}else{
				//뭔가 잘못된 경우
			}
		}else if(flag.equals("mod")){
			String lecture_id = request.getParameter("lecture_id");
			int credit_hour = Integer.parseInt(request.getParameter("credit_hour"));
			String lecture_name = request.getParameter("lecture_name");
			String prof_id = request.getParameter("prof_id");
			int credit = Integer.parseInt(request.getParameter("credit"));
			
			Lecture modLecture = new Lecture();
			
			modLecture.setLecture_id(lecture_id);
			modLecture.setLecture_name(lecture_name);
			modLecture.setProf_id(prof_id);
			modLecture.setCredit(credit);
			modLecture.setCredit_hour(credit_hour);
			LectureSettingBiz lectureSettingBiz = new LectureSettingBiz();
			if(lectureSettingBiz.lectureMod(modLecture)){
				// 성공하면 어디로 이동하나?
				RequestDispatcher dispatcher = request.getRequestDispatcher("/view/admin/main.jsp");
				dispatcher.forward(request, response);
			}else{
				//뭔가 잘못된 경우
			}
		}		
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String flag = request.getParameter("flag");
		if(flag.equals("search")){
			String lecture_id = request.getParameter("lecture_id");
			
			LectureSettingBiz lectureSettingBiz = new LectureSettingBiz();
			Lecture lecture = lectureSettingBiz.getLectureSearch(lecture_id);
			if(lecture != null){
				// 성공하면 어디로 이동하나?
				HttpSession session = request.getSession();
				session.setAttribute("searchResult", lecture);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/view/admin/lectureSearchView.jsp");
				dispatcher.forward(request, response);
			}else{
				//뭔가 잘못된 경우
				System.out.println("lecture is null");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/view/admin/noResult.jsp");
				dispatcher.forward(request, response);
			}
		}else if(flag.equals("search2")){
			String lecture_id = request.getParameter("lecture_id");
			
			LectureSettingBiz lectureSettingBiz = new LectureSettingBiz();
			Lecture lecture = lectureSettingBiz.getLectureSearch(lecture_id);
			if(lecture != null){
				// 성공하면 어디로 이동하나?
				HttpSession session = request.getSession();
				session.setAttribute("searchResult", lecture);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/view/admin/lectureDeleteView.jsp");
				dispatcher.forward(request, response);
			}else{
				//뭔가 잘못된 경우
				System.out.println("lecture is null");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/view/admin/noResult.jsp");
				dispatcher.forward(request, response);
			}
		}else if(flag.equals("search3")){
			String lecture_id = request.getParameter("lecture_id");
			
			LectureSettingBiz lectureSettingBiz = new LectureSettingBiz();
			Lecture lecture = lectureSettingBiz.getLectureSearch(lecture_id);
			if(lecture != null){
				// 성공하면 어디로 이동하나?
				HttpSession session = request.getSession();
				session.setAttribute("searchResult", lecture);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/view/admin/lectureModifyView.jsp");
				dispatcher.forward(request, response);
			}else{
				//뭔가 잘못된 경우
				System.out.println("lecture is null");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/view/admin/noResult.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
}
