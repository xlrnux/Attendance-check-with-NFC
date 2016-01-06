package dao;

import static util.DbUtil.close;
import static util.DbUtil.commit;
import static util.DbUtil.rollback;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import util.IdUtill;
import vo.Lecture;

public class TimeTableSettingDao {
	Connection con;
	Connection con2;
	Connection con3;

	public TimeTableSettingDao(Connection con) {
		super();
		this.con = con;
		// close(con2);
	}

	public TimeTableSettingDao(Connection con, Connection con2) {
		super();
		this.con = con;
		this.con2 = con2;
	}

	public TimeTableSettingDao(Connection con, Connection con2, Connection con3) {
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

	public boolean roomAdd(String lecroom_id) {
		PreparedStatement pstmt = null;
		try {
			for (int i = 1; i <= 14; i++) {
				for (int j = 2; j <= 6; j++) {
					String sql = "INSERT INTO `test7`.`time_table` (`lecroom_id`, `day_id`, `period_id`, `lecture_id`) VALUES (?, ?, ?, ?)";
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, lecroom_id);
					pstmt.setInt(2, j);
					pstmt.setInt(3, i);
					pstmt.setString(4, "empty");
					pstmt.executeUpdate();
					commit(con);
				}
			}
		} catch (Exception e) {
			rollback(con);
			e.printStackTrace();
			return false;
		} finally {
			close(pstmt);
		}
		return true;
	}

	public boolean timeTableUpdate(ArrayList<String> data) {
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		try {
			String sql = "UPDATE `test7`.`time_table` SET `lecture_id`= ? WHERE `lecroom_id`=? AND`day_id`=? AND `period_id`=?";
			String sql2 = "UPDATE `test7`.`time_table` SET `lecture_id`= ? WHERE `lecture_id`=?";

			pstmt = con.prepareStatement(sql);
			pstmt2 = con2.prepareStatement(sql2);
			// pstmt2 = con2.prepareStatement(sql2);
			IdUtill idUtil = new IdUtill();
			for (int i = 0; i < data.size(); i++) {
				ArrayList<String> data2 = new ArrayList<String>();
				data2 = idUtil.unPackId(data.get(i));
				if ((data2.get(3)).equals("empty")) {

				} else {
					pstmt2.setString(1, "empty"); // empty
					pstmt2.setString(2, (String) data2.get(3)); // lecture_id
					pstmt2.executeUpdate();
					commit(con2);
				}

			}
			for (int i = 0; i < data.size(); i++) {
				ArrayList<String> data2 = new ArrayList<String>();
				data2 = idUtil.unPackId(data.get(i));
				if ((data2.get(3)).equals("empty")) {

				} else {
					System.out.println("lecture_id: " + (String) data2.get(3));
					pstmt.setString(1, (String) data2.get(3)); // lecture_id
					System.out.println("lecroom_id: " + (String) data2.get(2));
					pstmt.setString(2, (String) data2.get(2)); // lecroom_id
					System.out.println("day_id: "
							+ Integer.parseInt(((String) data2.get(0))));
					pstmt.setInt(3, Integer.parseInt(((String) data2.get(0)))); // day_id
					System.out.println("period_id: "
							+ Integer.parseInt(((String) data2.get(1))));
					pstmt.setInt(4, Integer.parseInt(((String) data2.get(1)))); // period_id
					pstmt.executeUpdate();
					commit(con);
				}

			}

		} catch (Exception e) {
			rollback(con);
			rollback(con2);
			e.printStackTrace();
			return false;
		} finally {
			close(pstmt);
			
			close(pstmt2);
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

	public ArrayList<String> getTimeTableList(ArrayList<String> roomList) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<String> timeTableList = new ArrayList<String>();
		try {

			String sql = "SELECT lecture_id FROM time_table WHERE day_id=? AND lecroom_id = ? AND period_id =?";
			pstmt = con.prepareStatement(sql);
			String days[] = { "", "", "월요일", "화요일", "수요일", "목요일", "금요일" };
			int maxClass = 14;
			String empty = "배정가능";
			for (int dayCnt = 2; dayCnt < days.length; dayCnt++) {
				// System.out.println(days[dayCnt]);
				for (int i = 0; i < roomList.size(); i++) {
					// System.out.print("      ");
					// System.out.printf(" %8s ", roomList.get(i));
				}
				// System.out.println();
				for (int classCnt = 1; classCnt <= maxClass; classCnt++) {
					// System.out.printf("%3d ", classCnt);

					for (int roomCnt = 0; roomCnt < roomList.size(); roomCnt++) {
						pstmt.setInt(1, dayCnt);
						pstmt.setString(2, roomList.get(roomCnt));
						pstmt.setInt(3, classCnt);
						rs = pstmt.executeQuery();
						if (rs.next()) {
							timeTableList.add(rs.getString("lecture_id"));
							// System.out.println(days[dayCnt] + " " + classCnt
							// + " " + roomList.get(roomCnt) + " " +
							// rs.getString("lecture_id"));
							// System.out.printf(" %8s ",
							// rs.getString("lecture_id"));
						} else {
							timeTableList.add("0");
							// System.out.println(days[dayCnt] + " " + classCnt
							// + " " + roomList.get(roomCnt) + " 배정가능");
							// System.out.printf(" %8s ", empty);
						}
					}
					// System.out.println();
				}
				// System.out.println();
			}
			// System.out.println();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("TimeTableSetting.java: getTimeTable: error");
		} finally {
			close(pstmt);
			close(rs);
		}
		return timeTableList;
	}

	public HashMap<String, String> getLectureNameTable(
			ArrayList<String> timeTableList) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		HashMap<String, String> map = new HashMap<String, String>();

		try {
			String sql = "SELECT lecture_name FROM lecture WHERE lecture_id = ?";
			pstmt = con.prepareStatement(sql);
			String empty = "";

			for (int i = 0; i < timeTableList.size(); i++) {
				if (timeTableList.get(i).equals("0")) {
					continue;
				}
				pstmt.setString(1, timeTableList.get(i));
				rs = pstmt.executeQuery();
				if (rs.next()) {
					map.put(timeTableList.get(i), rs.getString("lecture_name"));
				} else {
					// 해당 과목에 이름이 없답니다...-_-;
					System.err.println("error! " + timeTableList.get(i)
							+ ": 해당 과목에 이름이 없습니다.");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out
					.println("TimeTableSetting.java: getLectureNameTable: error");
		} finally {
			close(pstmt);
			close(rs);
		}
		return map;
	}
}
