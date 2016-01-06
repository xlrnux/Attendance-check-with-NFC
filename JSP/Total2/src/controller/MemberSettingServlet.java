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

import svc.MemberSettingBiz;
import vo.Member;
/**
 * Servlet implementation class MemberAdd
 */
@WebServlet("/memberSet")
public class MemberSettingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberSettingServlet() {
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
			String id = request.getParameter("id");
			String name = request.getParameter("name");
			String cell_num = request.getParameter("cell_num");
			String home_num = request.getParameter("home_num");
			String email = request.getParameter("email");
			String level = request.getParameter("level");
			
			Member addMember = new Member();
			
			addMember.setId(id);
			addMember.setName(name);
			addMember.setCell_num(cell_num);
			addMember.setHome_num(home_num);
			addMember.setEmail(email);
			addMember.setLevel(level);
			addMember.setPassword(id);
			addMember.setMac_address(id);
			addMember.setGrade(1);
			addMember.setState("0");
			
			MemberSettingBiz memberSettingBiz = new MemberSettingBiz();
			if(memberSettingBiz.memberAdd(addMember)){
				// 성공하면 어디로 이동하나?
				RequestDispatcher dispatcher = request.getRequestDispatcher("/view/admin/main.jsp");
				dispatcher.forward(request, response);
			}else{
				//뭔가 잘못된경우
			}
		}else if(flag.equals("del")){
			String id = (String)request.getParameter("id");
			System.out.println(id);
			MemberSettingBiz memberSettingBiz = new MemberSettingBiz();
			if(memberSettingBiz.memberDel(id)){
				// 성공하면 어디로 이동하나?
				RequestDispatcher dispatcher = request.getRequestDispatcher("/view/admin/main.jsp");
				dispatcher.forward(request, response);
			}else{
				//뭔가 잘못된 경우
			}
		}else if(flag.equals("mod")){
			String id = request.getParameter("id");
			String password = request.getParameter("password");
			String name = request.getParameter("name");
			String cell_num = request.getParameter("cell_num");
			String home_num = request.getParameter("home_num");
			String email = request.getParameter("email");
			int grade = Integer.parseInt(request.getParameter("grade"));
			
			Member modMember = new Member();
			
			modMember.setId(id);
			modMember.setName(name);
			modMember.setCell_num(cell_num);
			modMember.setHome_num(home_num);
			modMember.setEmail(email);
			modMember.setPassword(password);
			modMember.setGrade(grade);
			MemberSettingBiz memberSettingBiz = new MemberSettingBiz();
			if(memberSettingBiz.memberMod(modMember)){
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
			String id = request.getParameter("id");
			
			MemberSettingBiz memberSettingBiz = new MemberSettingBiz();
			Member member = memberSettingBiz.getMemberSearch(id);
			if(member != null){
				// 성공하면 어디로 이동하나?
				HttpSession session = request.getSession();
				session.setAttribute("searchResult", member);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/view/admin/memberSearchView.jsp");
				dispatcher.forward(request, response);
			}else{
				//뭔가 잘못된 경우
				System.out.println("member is null");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/view/admin/noResult.jsp");
				dispatcher.forward(request, response);
			}
		}else if(flag.equals("search2")){
			String id = request.getParameter("id");
			
			MemberSettingBiz memberSettingBiz = new MemberSettingBiz();
			Member member = memberSettingBiz.getMemberSearch(id);
			if(member != null){
				// 성공하면 어디로 이동하나?
				HttpSession session = request.getSession();
				session.setAttribute("searchResult", member);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/view/admin/memberDeleteView.jsp");
				dispatcher.forward(request, response);
			}else{
				//뭔가 잘못된 경우
				System.out.println("member is null");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/view/admin/noResult.jsp");
				dispatcher.forward(request, response);
			}
		}else if(flag.equals("search3")){
			String id = request.getParameter("id");
			
			MemberSettingBiz memberSettingBiz = new MemberSettingBiz();
			Member member = memberSettingBiz.getMemberSearch(id);
			if(member != null){
				// 성공하면 어디로 이동하나?
				HttpSession session = request.getSession();
				session.setAttribute("searchResult", member);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/view/admin/memberModifyView.jsp");
				dispatcher.forward(request, response);
			}else{
				//뭔가 잘못된 경우
				System.out.println("member is null");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/view/admin/noResult.jsp");
				dispatcher.forward(request, response);
			}
		}else if(flag.equals("search4")){
			String id = request.getParameter("id");
			
			MemberSettingBiz memberSettingBiz = new MemberSettingBiz();
			Member member = memberSettingBiz.getMemberSearch(id);
			if(member != null){
				// 성공하면 어디로 이동하나?
				HttpSession session = request.getSession();
				session.setAttribute("searchResult", member);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/view/admin/memberSearchView2.jsp");
				dispatcher.forward(request, response);
			}else{
				//뭔가 잘못된 경우
				System.out.println("member is null");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/view/admin/noResult.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
}
