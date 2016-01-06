package dao;

import java.sql.*;
import java.util.ArrayList;

import vo.Member;
import static util.DbUtil.*;

public class MemberSettingDao {
	Connection con;

	public MemberSettingDao(Connection con) {
		super();
		this.con = con;
	}

	public boolean memberAdd(Member member) {
		PreparedStatement pstmt = null;
		boolean result;
		try {
			String sql = "INSERT INTO `test7`.`member` (`id`, `name`, `password`, `cell_num`, `home_num`, `mac_address`, `email`, `grade`, `level`, `state`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member.getId()); // id
			pstmt.setString(2, new String(member.getName().getBytes("8859_1"),
					"UTF-8"));// name
			pstmt.setString(3, member.getPassword());// password : 기본셋팅은 id와
														// 동일하게. 추후 암호화 할것
			pstmt.setString(4, member.getCell_num());// cell_num
			pstmt.setString(5, member.getHome_num());// home_num
			pstmt.setString(6, member.getMac_address());// mac_address : 기본 셋팅은
														// id와 동일하게
			pstmt.setString(7, member.getEmail());// email
			pstmt.setInt(8, 1);// grade
			pstmt.setString(9, member.getLevel());// level
			pstmt.setString(10, "0");// state
			pstmt.executeUpdate();
			commit(con);
			result = true;
		} catch (Exception e) {
			rollback(con);
			e.printStackTrace();
			result= false;
		} finally {
			close(pstmt);
		}
		return result;
	}

	public boolean memberDel(String id) {
		boolean result;
		PreparedStatement pstmt = null;
		try {
			String sql = "DELETE FROM `test7`.`member` WHERE `id`= ? ";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id); // id

			pstmt.executeUpdate();
			commit(con);
			result = true;
		} catch (Exception e) {
			rollback(con);
			e.printStackTrace();
			result = false;
		} finally {
			close(pstmt);
		}
		return result;
	}

	public Member getMemberSearch(String id) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Member member = null;
		try {
			String sql = "select * from member where id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
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

	public boolean memberMod(Member member) {
		boolean result;
		PreparedStatement pstmt = null;
		try {
			String sql = "UPDATE `test7`.`member` SET `name`=?, `password`=?, `cell_num`=?, `home_num`=?, `email`=?, `grade`=? WHERE id= ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, new String(member.getName().getBytes("8859_1"), "UTF-8"));// name
			pstmt.setString(2, member.getPassword());// password : 기본셋팅은 id와 동일하게. 추후 암호화 할것
			pstmt.setString(3, member.getCell_num());// cell_num
			pstmt.setString(4, member.getHome_num());// home_num
			pstmt.setString(5, member.getEmail());// email
			pstmt.setInt(6, member.getGrade());// grade
			pstmt.setString(7, member.getId());
			pstmt.executeUpdate();
			commit(con);
			result= true;
		} catch (Exception e) {
			rollback(con);
			e.printStackTrace();
			result =  false;
		} finally {
			close(pstmt);
		}
		return result;
	}
	public ArrayList<String> getNameList(ArrayList<String> studentList) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<String> nameList =  new ArrayList<String>();
		try {
			String sql = "select name from member where id = ?";
			pstmt = con.prepareStatement(sql);
			for(int i=0; i < studentList.size(); i++ ){
				System.out.println(studentList.get(i));
				pstmt.setString(1, studentList.get(i));
				rs = pstmt.executeQuery();
				if (rs.next()) {
					nameList.add(rs.getString("name"));
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return nameList;
	}
}
