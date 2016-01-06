package dao;

import static util.DbUtil.close;
import static util.DbUtil.commit;
import static util.DbUtil.rollback;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import vo.Lecture;

public class LectureSettingDao {
	Connection con;
	Connection con2;
	Connection con3;

	public LectureSettingDao(Connection con) {
		super();
		this.con = con;
		// close(con2);
	}

	public LectureSettingDao(Connection con, Connection con2) {
		super();
		this.con = con;
		this.con2 = con2;
	}

	public LectureSettingDao(Connection con, Connection con2, Connection con3) {
		super();
		this.con = con;
		this.con2 = con2;
		this.con3 = con3;
	}

	public boolean lectureAdd(Lecture lecture) {
		PreparedStatement pstmt = null;
		Statement stmt = null;
		try {
			String sql = "INSERT INTO `test7`.`lecture` (`lecture_id`, `lecture_name`, `prof_id`, `class_count`, `credit`, `credit_hour` ) VALUES (?, ?, ?, ?, ?, ?)";
			String sql2 = "CREATE TABLE test7_check."
					+ lecture.getLecture_id()
					+ " (student_id CHAR(8) NOT NULL ,state TINYINT(2) NOT NULL ,class_count TINYINT(2) NOT NULL ,in_time TIME NOT NULL ,out_time TIME NOT NULL ,seat_width TINYINT(2) NULL ,seat_height TINYINT(2) NULL ,note TINYTEXT NULL ,PRIMARY KEY (student_id, class_count),CONSTRAINT "
					+ lecture.getLecture_id()
					+ "_fk_1 FOREIGN KEY (student_id) REFERENCES test7.member (id) ON DELETE CASCADE ON UPDATE CASCADE)";
			pstmt = con.prepareStatement(sql);
			stmt = con2.createStatement();
			pstmt.setString(1, lecture.getLecture_id()); // lecture_id
			pstmt.setString(2,
					new String(lecture.getLecture_name().getBytes("8859_1"),
							"UTF-8"));// lecture_name
			pstmt.setString(3, lecture.getProf_id());// prof_id
			pstmt.setInt(4, lecture.getClass_count());// class_count
			pstmt.setInt(5, lecture.getCredit());// credit
			pstmt.setInt(6, lecture.getCredit_hour());// credit_hour

			pstmt.executeUpdate();
			stmt.execute(sql2);
			if (commit(con)) {
				// pstmt.clearBatch();
				if (commit(con2)) {
				} else {
					rollback(con);
					rollback(con2);
				}
			}
		} catch (Exception e) {
			rollback(con);
			rollback(con2);
			e.printStackTrace();
			return false;
		} finally {
			close(pstmt);
			close(stmt);
		}
		return true;
	}

	public boolean lectureDel(String lecture_id) {
		PreparedStatement pstmt = null;
		PreparedStatement pstmt3 = null;
		Statement stmt2 = null;
		String sql = "DELETE FROM time_table WHERE lecture_id = ?";
		String sql2 = "drop table test7_check." + lecture_id;
		String sql3 = "DELETE FROM lecture WHERE lecture_id = ?";
		try {
			// String sql =
			// "DELETE FROM `lecture`, `time_table` WHERE `lecture.lecture_id`= ? OR time_table.lecture_id = ?";

			stmt2 = con2.createStatement();
			stmt2.execute(sql2);

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, lecture_id); // lecture_id
			pstmt.executeUpdate();

			commit(con2);
			commit(con);
			/*
			 * if(commit(con2)){ if(commit(con)){
			 * 
			 * }else{ rollback(con2); rollback(con); } }
			 */
		} catch (Exception e) {
			// rollback(con2);
			rollback(con);

			e.printStackTrace();
			return false;
		} finally {
			try {
				pstmt3 = con3.prepareStatement(sql3);
				pstmt3.setString(1, lecture_id); // lecture_id
				pstmt3.executeUpdate();
				commit(con3);
			} catch (Exception e2) {

			} finally {
				close(pstmt3);
			}
			close(stmt2);
			close(pstmt);

		}
		return true;
	}

	public Lecture getLectureSearch(String lecture_id) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Lecture lecture = null;
		try {
			System.out.println("lecture_id3:LectureSettingDao.java: "
					+ lecture_id);
			String sql = "select lecture_id, lecture_name, prof_id, member.name, class_count, today, lecture.state, credit, credit_hour FROM lecture, member WHERE lecture_id = ? AND prof_id = member.id";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, lecture_id);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				lecture = new Lecture();
				lecture.setLecture_id(rs.getString("lecture_id"));
				lecture.setLecture_name(rs.getString("lecture_name"));
				System.out.println("lecture_name:LectureSettingDao.java: "
						+ rs.getString("lecture_name"));
				lecture.setProf_id(rs.getString("prof_id"));
				lecture.setProf_name(rs.getString("member.name"));
				lecture.setClass_count(rs.getInt("class_count"));
				lecture.setCredit(rs.getInt("credit"));
				lecture.setCredit_hour(rs.getInt("credit_hour"));
				lecture.setToday(rs.getInt("today"));
				lecture.setState(rs.getString("lecture.state"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return lecture;
	}

	public ArrayList<Lecture> getLectureList() {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		ArrayList<Lecture> lectureList = new ArrayList<Lecture>();
		try {
			String sql = "select lecture_id, lecture_name, credit_hour FROM lecture WHERE lecture_id !='empty'";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				do {
					Lecture lecture = new Lecture();
					lecture.setLecture_id(rs.getString("lecture_id"));
					lecture.setLecture_name(rs.getString("lecture_name"));
					lecture.setCredit_hour(rs.getInt("credit_hour"));
					lectureList.add(lecture);
				} while (rs.next());
			}else{
				//아직 개설된 강의가 없는것
				System.err.println("아직 개설된 강의가 없습니다.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return lectureList;
	}

	/*
	 * public Lecture getLectureList() { PreparedStatement pstmt = null;
	 * ResultSet rs = null; Lecture lecture = null; try {
	 * System.out.println("lecture_id3:LectureSettingDao.java: " + lecture_id);
	 * String sql =
	 * "select lecture_id, lecture_name, prof_id, member.name, class_count, today, lecture.state, credit, credit_hour FROM lecture, member WHERE lecture_id = ? AND prof_id = member.id"
	 * ; pstmt = con.prepareStatement(sql); pstmt.setString(1, lecture_id); rs =
	 * pstmt.executeQuery();
	 * 
	 * if (rs.next()) { lecture = new Lecture();
	 * lecture.setLecture_id(rs.getString("lecture_id"));
	 * lecture.setLecture_name(rs.getString("lecture_name"));
	 * System.out.println("lecture_name:LectureSettingDao.java: " +
	 * rs.getString("lecture_name"));
	 * lecture.setProf_id(rs.getString("prof_id"));
	 * lecture.setProf_name(rs.getString("member.name"));
	 * lecture.setClass_count(rs.getInt("class_count"));
	 * lecture.setCredit(rs.getInt("credit"));
	 * lecture.setCredit_hour(rs.getInt("credit_hour"));
	 * lecture.setToday(rs.getInt("today"));
	 * lecture.setState(rs.getString("lecture.state")); } } catch (Exception e)
	 * { e.printStackTrace(); } finally { close(pstmt); close(rs); } return
	 * lecture; }
	 */

	public boolean lectureMod(Lecture lecture) {
		PreparedStatement pstmt = null;
		try {
			String sql = "UPDATE `test7`.`lecture` SET `lecture_name`=?, `prof_id`=?, `class_count`=?, `credit`=?, `credit_hour`=? WHERE lecture_id= ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,
					new String(lecture.getLecture_name().getBytes("8859_1"),
							"UTF-8"));// lecture_name
			pstmt.setString(2, lecture.getProf_id());// prof_id
			pstmt.setInt(3, lecture.getClass_count());// class_count
			pstmt.setInt(4, lecture.getCredit());// credit
			pstmt.setInt(5, lecture.getCredit_hour());// credit_hour
			pstmt.setString(6, lecture.getLecture_id());
			pstmt.executeUpdate();
			commit(con);
		} catch (Exception e) {
			rollback(con);
			e.printStackTrace();
			return false;
		} finally {
			close(pstmt);
		}
		return true;
	}

	public boolean lectureOff(String lecture_id) {
		PreparedStatement pstmt = null;
		try {
			String sql = "UPDATE `test7`.`lecture` SET `state`='0', `today`=2 WHERE `lecture_id`=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, lecture_id);
			pstmt.executeUpdate();
			commit(con);
		} catch (Exception e) {
			rollback(con);
			e.printStackTrace();
			return false;
		} finally {
			close(pstmt);
		}
		return true;
	}

	public boolean lectureOn(String lecture_id, String lecroom_id,
			int class_count) {
		PreparedStatement pstmt = null;
		try {
			String sql = "UPDATE `test7`.`lecture` SET `state`=?, `class_count`=?, `today`=1 WHERE `lecture_id`=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, lecroom_id);
			pstmt.setInt(2, class_count);
			pstmt.setString(3, lecture_id);
			pstmt.executeUpdate();
			commit(con);
		} catch (Exception e) {
			rollback(con);
			e.printStackTrace();
			return false;
		} finally {
			close(pstmt);
		}
		return true;
	}

	public boolean lectureOn(String lecture_id, String lecroom_id) {
		PreparedStatement pstmt = null;
		try {
			String sql = "UPDATE `test7`.`lecture` SET `state`=? WHERE `lecture_id`=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, lecroom_id);
			pstmt.setString(2, lecture_id);
			pstmt.executeUpdate();
			commit(con);
		} catch (Exception e) {
			rollback(con);
			e.printStackTrace();
			return false;
		} finally {
			close(pstmt);
		}
		return true;
	}
}
