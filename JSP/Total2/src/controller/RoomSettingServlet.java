package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.RoomSettingBiz;
import svc.TimeTableSettingBiz;
import vo.Room;
/**
 * Servlet implementation class MemberAdd
 */
@WebServlet("/roomSet")
public class RoomSettingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RoomSettingServlet() {
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
			String lecroom_id = request.getParameter("lecroom_id");
			int width = Integer.parseInt(request.getParameter("width"));
			int height = Integer.parseInt(request.getParameter("height"));
			String state = "0";
			
			Room addRoom = new Room();
			addRoom.setLecroom_id(lecroom_id);
			addRoom.setWidth(width);
			addRoom.setHeight(height);
			addRoom.setState(state);
			
			RoomSettingBiz roomSettingBiz = new RoomSettingBiz();
			if(roomSettingBiz.roomAdd(addRoom)){
				TimeTableSettingBiz ttSetBiz = new TimeTableSettingBiz();
				ttSetBiz.roomAdd(lecroom_id);
				// 성공하면 어디로 이동하나?
				RequestDispatcher dispatcher = request.getRequestDispatcher("/view/admin/main.jsp");
				dispatcher.forward(request, response);
			}else{
				//뭔가 잘못된경우
			}
		}else if(flag.equals("del")){
			String lecroom_id = (String)request.getParameter("lecroom_id");
			System.out.println(lecroom_id);
			RoomSettingBiz roomSettingBiz = new RoomSettingBiz();
			if(roomSettingBiz.roomDel(lecroom_id)){
				// 성공하면 어디로 이동하나?
				RequestDispatcher dispatcher = request.getRequestDispatcher("/view/admin/main.jsp");
				dispatcher.forward(request, response);
			}else{
				//뭔가 잘못된 경우
			}
		}else if(flag.equals("mod")){
			String lecroom_id = request.getParameter("lecroom_id");
			int width = Integer.parseInt(request.getParameter("width"));
			int height = Integer.parseInt(request.getParameter("height"));
			Room modRoom = new Room();
			
			modRoom.setLecroom_id(lecroom_id);
			modRoom.setWidth(width);
			modRoom.setHeight(height);
			
			RoomSettingBiz roomSettingBiz = new RoomSettingBiz();
			if(roomSettingBiz.roomMod(modRoom)){
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
			String lecroom_id = request.getParameter("lecroom_id");
			
			RoomSettingBiz roomSettingBiz = new RoomSettingBiz();
			Room room = roomSettingBiz.getRoomSearch(lecroom_id);
			if(room != null){
				// 성공하면 어디로 이동하나?
				HttpSession session = request.getSession();
				session.setAttribute("searchResult", room);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/view/admin/roomSearchView.jsp");
				dispatcher.forward(request, response);
			}else{
				//뭔가 잘못된 경우
				System.out.println("invalid room");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/view/admin/noResult.jsp");
				dispatcher.forward(request, response);
			}
		}else if(flag.equals("search2")){
			String lecroom_id = request.getParameter("lecroom_id");
			
			RoomSettingBiz roomSettingBiz = new RoomSettingBiz();
			Room room = roomSettingBiz.getRoomSearch(lecroom_id);
			if(room != null){
				// 성공하면 어디로 이동하나?
				HttpSession session = request.getSession();
				session.setAttribute("searchResult", room);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/view/admin/roomDeleteView.jsp");
				dispatcher.forward(request, response);
			}else{
				//뭔가 잘못된 경우
				System.out.println("invalid room");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/view/admin/noResult.jsp");
				dispatcher.forward(request, response);
			}
		}else if(flag.equals("search3")){
			String lecroom_id = request.getParameter("lecroom_id");
			
			RoomSettingBiz roomSettingBiz = new RoomSettingBiz();
			Room room = roomSettingBiz.getRoomSearch(lecroom_id);
			if(room != null){
				// 성공하면 어디로 이동하나?
				HttpSession session = request.getSession();
				session.setAttribute("searchResult", room);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/view/admin/roomModifyView.jsp");
				dispatcher.forward(request, response);
			}else{
				//뭔가 잘못된 경우
				System.out.println("invalid room");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/view/admin/noResult.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
}
