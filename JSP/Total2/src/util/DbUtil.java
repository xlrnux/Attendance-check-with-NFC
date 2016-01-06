package util;
import java.sql.*;

/**
 * Servlet implementation class DbUtil
 */
//JSP 2.2 & Servlet 3.0 (출판 혜지원, 저자 오정원) P.509 부터 참조
public class DbUtil {
	static{
		try{
			Class.forName("com.mysql.jdbc.Driver");
			//Class: 자바 API에서 제공되는 클래스
			//forName: 파라미터로 지정된 클래스를 인스턴스 메모리에 올리는 역할을 하는 메소드
			// 쉽게 말하면 MySQL과 애플리케이션이 연동하기 위해 MySQL 드라이버를 메모리로 올리는 부분(마침표 삭제)
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
	}
	public static Connection getConnection(){
		Connection con = null;
		try{
			con = DriverManager.getConnection("jdbc:mysql://192.168.0.11:3306/test7", "guest", "guest");
			con.setAutoCommit(false);
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return con;
	}
	public static Connection getConnection(String database){
		Connection con = null;
		try{
			String URL = "jdbc:mysql://192.168.0.11:3306/"+database;
			con = DriverManager.getConnection(URL, "guest", "guest");
			con.setAutoCommit(false);
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return con;
	}
	public static void close(Statement stmt){
		try{
			stmt.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	public static void close(ResultSet rs){
		try{
			rs.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	public static void close(Connection con){
		try{
			con.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	public static boolean commit(Connection con){
		try{
			con.commit();
			System.out.println("commit success");
			return true;
		}
		catch(Exception e){
			System.out.println("commit fail");
			return false;
		}
	}
	public static void rollback(Connection con){
		try{
			con.rollback();
			System.out.println("rollback success");
		}
		catch(Exception e){
			System.out.println("rollback fail");
		}
	}
}
