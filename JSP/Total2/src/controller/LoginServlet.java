package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.LectureSettingBiz;
import svc.LoginBiz;
import svc.PageFlagBiz;
import svc.RoomSettingBiz;
import svc.UpdateCheckListBiz;
import vo.Lecture;
import vo.Member;
import vo.Room;
import vo.Table;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/web")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id = request.getParameter("id");
		String password = request.getParameter("password");

		LoginBiz loginBiz = new LoginBiz();
		Member member = loginBiz.getMemberLogin(id, password);

		if (member != null) {
			HttpSession session = request.getSession();
			//session.setMaxInactiveInterval(-1);
			if (member.getLevel().equals("admin")) {
				session.setAttribute("member", member);
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("/view/admin/main.jsp");
				dispatcher.forward(request, response);
			} else if (member.getLevel().equals("professor")) {
				PageFlagBiz pageFlagBiz = new PageFlagBiz();
				Table table = pageFlagBiz.getTable(id);
				Room room = null;
				Lecture lecture = null;
				RoomSettingBiz roomSettingBiz = new RoomSettingBiz();
				LectureSettingBiz lectureSettingBiz = new LectureSettingBiz();
				
				int class_count;
				System.out.println(table.getLecroom_id());
				if (table.getLecroom_id().equals("NoResult")) {
					session.setAttribute("type", "1");
				} else {
					if (table.getLecture_id().equals("NoResult")) {
						room = pageFlagBiz.getRoom(table.getLecroom_id());
						table.setLecture_id(room.getState());
						lecture = lectureSettingBiz.getLectureSearch(table.getLecture_id());
						session.setAttribute("lecture_name",lecture.getLecture_name());
						class_count = lecture.getClass_count();
					} else {
						room = roomSettingBiz.getRoomSearch(table.getLecroom_id());						
						String lecture_id = table.getLecture_id();
						System.out.println("lecture_id2: "+lecture_id);
						lecture = lectureSettingBiz.getLectureSearch(lecture_id);
						System.out.println(lecture.getLecture_name());
						session.setAttribute("lecture_name",lecture.getLecture_name());
						
						
						class_count = lecture.getClass_count()+1;
						// 여기부터 state 값을 셋팅 철컹철컹
						if(lecture.getToday() == 0){
							lectureSettingBiz.lectureOn(table.getLecture_id(), table.getLecroom_id(), class_count);
							// 여기부터 출석부 목록 복사
							UpdateCheckListBiz updateCheckListBiz = new UpdateCheckListBiz();
							updateCheckListBiz.updateCheckList(lecture_id,class_count);
							roomSettingBiz.lectureOn(table.getLecroom_id(),table.getLecture_id());
						}else if(lecture.getToday() == 1){
							System.out.println(lecture.getToday());
							lectureSettingBiz.lectureOn(table.getLecture_id(), table.getLecroom_id());
							roomSettingBiz.lectureOn(table.getLecroom_id(),table.getLecture_id());
						}else {
							
						}
						
					}
					session.setAttribute("class_count", new Integer(class_count));
					session.setAttribute("type", "0");
					session.setAttribute("width", new Integer(room.getWidth()));
					session.setAttribute("height",new Integer(room.getHeight()));
					session.setAttribute("lecroom_id", table.getLecroom_id());
				}

				session.setAttribute("prof_id", id);
				session.setAttribute("level", member.getLevel());
				session.setAttribute("prof_name", member.getName());
				session.setAttribute("lecture_id", table.getLecture_id());
				

				RequestDispatcher dispatcher = request
						.getRequestDispatcher("/view/professor/main.jsp");
				dispatcher.forward(request, response);
			} else if (member.getLevel().equals("assistant")) {
				session.setAttribute("member", member);
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("/view/assistant/main.jsp");
				dispatcher.forward(request, response);
			} else if (member.getLevel().equals("student")) {
				session.setAttribute("student_id", member.getId());
				session.setAttribute("level", member.getLevel());
				session.setAttribute("student_name", member.getName());
				if(member.getState() == null){
					// state가 null 이라는것은 현재 강의를 듣고 있지 않다는것. 출석체크가 가능하다는것.
					session.setAttribute("lecroom_id", "NoResult");
				}else{
					session.setAttribute("lecroom_id", member.getState());
				}
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("/view/student/main.jsp");
				dispatcher.forward(request, response);
			} else if (member.getLevel().equals("guest")) {
				session.setAttribute("member", member);
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("/view/guest/main.jsp");
				dispatcher.forward(request, response);
			}
		} else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/view/login/loginErr.jsp");
			dispatcher.forward(request, response);
		}
	}
}
