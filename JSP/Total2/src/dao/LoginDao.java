package dao;

import java.sql.*;
import vo.Member;
import static util.DbUtil.*;

public class LoginDao {
	Connection con;

	public LoginDao(Connection con) {
		super();
		this.con = con;
	}
	public Member getMemberLogin(String id, String password) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Member member = null;
		try {
			String sql = "select * from member where id = ? and password = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, password);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				member = new Member();
				member.setId(rs.getString("id"));
				member.setName(rs.getString("name"));
				member.setPassword(rs.getString("password"));
				member.setCell_num(rs.getString("cell_num"));
				member.setHome_num(rs.getString("home_num"));
				member.setMac_address(rs.getString("mac_address"));
				member.setEmail(rs.getString("email"));
				member.setGrade(rs.getInt("grade"));
				member.setLevel(rs.getString("level"));
				member.setState(rs.getString("state"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return member;
	}
	public Member getMemberLogin(String mac_address) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Member member = null;
		try {
			String sql = "select * from member where mac_address = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, mac_address);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				member = new Member();
				member.setId(rs.getString("id"));
				member.setName(rs.getString("name"));
				member.setPassword(rs.getString("password"));
				member.setCell_num(rs.getString("cell_num"));
				member.setHome_num(rs.getString("home_num"));
				member.setMac_address(rs.getString("mac_address"));
				member.setEmail(rs.getString("email"));
				member.setGrade(rs.getInt("grade"));
				member.setLevel(rs.getString("level"));
				if(rs.getString("state") == null){
					member.setState("NoResult");
				}else{
					member.setState(rs.getString("state"));
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return member;
	}
}
