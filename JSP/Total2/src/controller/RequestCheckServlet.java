package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.RequestCheckBiz;

/**
 * Servlet implementation class RequestCheckIn
 */
@WebServlet(name = "RequestCheck", urlPatterns = { "/Check" })
public class RequestCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RequestCheckServlet() {
        super();
        // TODO Auto-generated constructor stub
        
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession();
		String level = (String)session.getAttribute("level");
		if( !(level.equals("student")) ){
			RequestDispatcher dispatcher = request.getRequestDispatcher("/view/ThisPageIsNotForYou.jsp");
			dispatcher.forward(request, response);
		}
		String student_id = (String)session.getAttribute("student_id");
		
		String student_name = (String)session.getAttribute("student_name");
		String lecroom_id;
		String lecture_id;
		String flag = request.getParameter("flag");
		boolean result;
		RequestCheckBiz requestCheckBiz = new RequestCheckBiz();
		if(flag.equals("checkIn")){
			//체크인 요청
			lecroom_id = request.getParameter("lecroom_id");
			int width = Integer.parseInt((request.getParameter("width")));
			int height = Integer.parseInt((request.getParameter("height")));
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
					session.setAttribute("class_count", new Integer(class_count));
					RequestDispatcher dispatcher = request.getRequestDispatcher("/view/student/main.jsp");
					dispatcher.forward(request, response);
				}else{
					// 출석체크 실패
					RequestDispatcher dispatcher = request.getRequestDispatcher("/view/student/checkInFail.jsp");
					dispatcher.forward(request, response);
				}
			}		
			
		}else{
			// 체크아웃 요청
			lecroom_id = (String)session.getAttribute("lecroom_id");
			//체크아웃 Biz
			
		}
		
		
				
	}

}
