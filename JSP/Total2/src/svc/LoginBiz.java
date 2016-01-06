package svc;
import vo.Member;
import java.sql.*;
import dao.LoginDao;
import static util.DbUtil.*;

/**
 * Servlet implementation class LoginBiz
 */
public class LoginBiz{
	public Member getMemberLogin(String id, String password){
		// TODO Auto-generated method stub
		Connection con = getConnection();
		LoginDao loginDao = new LoginDao(con);
		Member member = loginDao.getMemberLogin(id, password);
		close(con);
		return member;
	}
	public Member getMemberLogin(String mac_address){
		Connection con = getConnection();
		LoginDao loginDao = new LoginDao(con);
		Member member = loginDao.getMemberLogin(mac_address);
		close(con);
		return member;
	}
}
