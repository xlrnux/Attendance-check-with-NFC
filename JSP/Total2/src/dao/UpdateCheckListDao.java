package dao;

import static util.DbUtil.close;
import static util.DbUtil.commit;
import static util.DbUtil.rollback;

import java.sql.*;

public class UpdateCheckListDao {
	Connection con;
	Connection con2;

	public UpdateCheckListDao(Connection con) {
		super();
		this.con = con;
	}
	public UpdateCheckListDao(Connection con, Connection con2) {
		super();
		this.con = con;
		this.con2 = con2;
	}
	
	public boolean updateCheckList(String lecture_id, int class_count) {
		Statement stmt = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT `student_id` FROM `course_reg` WHERE `lecture_id` = '"+lecture_id+"'";
			String sql2 = "INSERT INTO `test7_check`.`"+lecture_id+"` (`student_id`, `state`, `class_count`, `in_time`, `out_time`, `seat_width`, `seat_height`, `note`) VALUES (?, 0, ?, 0, 0, 0, 0, NULL)";
			String sql3 = "INSERT INTO `test7_check`.? (`student_id`, `state`, `class_count`) VALUES (?, 0, ?)";
			stmt = con.createStatement();
			pstmt2 = con2.prepareStatement(sql2);
			rs = stmt.executeQuery(sql);
			if(rs.next()){
				do{
					String student_id = rs.getString(1);
					//pstmt2.setString(1, lecture_id);
					pstmt2.setString(1, student_id);
					pstmt2.setInt(2, class_count);
					pstmt2.executeUpdate();
					commit(con2);
				}while(rs.next());
			}else{
				System.out.println("해당과목에는 수강신청 한 사람이 없수와");
				return false;
			}
			
		} catch (Exception e) {
			rollback(con2);
			e.printStackTrace();
			return false;
		} finally {
			close(stmt);
			close(pstmt2);
		}
		return true;
	}
}
