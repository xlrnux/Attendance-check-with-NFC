package dao;

import static util.DbUtil.close;
import static util.DbUtil.commit;
import static util.DbUtil.rollback;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Random;

public class RequestCheckDao {
	Connection con;
	Connection con2;

	public RequestCheckDao(Connection con) {
		super();
		this.con = con;
	}

	public RequestCheckDao(Connection con, Connection con2) {
		super();
		this.con = con;
		this.con2 = con2;
	}

	public String getLecture_id(String lecroom_id) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String lecture_id;
		try {
			String sql = "SELECT lecture_id FROM lecture WHERE state=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, lecroom_id);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				lecture_id = rs.getString("lecture_id");
			} else {
				System.out
						.println("RequestCheckDao.java: getLecture_id: sql: 어라 왜 안나오죠...");
				lecture_id = "NoResult";
			}
		} catch (Exception e) {
			e.printStackTrace();
			lecture_id = "NoResult";
		} finally {
			close(pstmt);
			close(rs);
		}
		return lecture_id;
	}

	public int answerPossible(String student_id, String lecture_id) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int class_count;
		try {
			String sql = "SELECT COUNT(state) as result, state, class_count FROM test7_check.`"
					+ lecture_id
					+ "` WHERE student_id=? AND class_count =(SELECT MAX(class_count) FROM test7_check.`"
					+ lecture_id + "` WHERE student_id = ?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, student_id);
			pstmt.setString(2, student_id);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				if (rs.getInt("result") == 0) {
					// 수강하는 과목이 아님.
					class_count = -21;
				} else if (rs.getInt("result") == 1) {
					if (rs.getInt("state") == 0) {
						// 출석체크 가능
						class_count = rs.getInt("class_count");
					} else if (rs.getInt("state") == 1) {
						// 이미 출석중
						class_count = -31;
					} else if (rs.getInt("state") == 2) {
						// 조퇴했음.
						class_count = -32;
					} else {
						// 예상외 결과
						class_count = -33;
					}
				} else {
					// 예상외 에러
					class_count = -22;
				}
			} else {
				System.out
						.println("RequestCheckDao.java: answerPossible: sql: 어라 왜 안나오죠...");
				class_count = -11;
			}
		} catch (Exception e) {
			e.printStackTrace();
			class_count = -12;
		} finally {
			close(pstmt);
			close(rs);
		}
		return class_count;
	}

	public boolean checkIn(String student_id, String lecture_id,
			int class_count, int width, int height) {
		boolean result;
		PreparedStatement pstmt = null;
		try {
			String sql = "UPDATE `test7_check`.`"
					+ lecture_id
					+ "` SET `state` = 1, `in_time`=(CURTIME()), `seat_width`=?, `seat_height`=? WHERE `student_id`=? AND `class_count`=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, width);
			pstmt.setInt(2, height);
			pstmt.setString(3, student_id);
			pstmt.setInt(4, class_count);
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

	public boolean changeState(String student_id, String lecture_id) {
		boolean result;
		PreparedStatement pstmt = null;
		try {
			String sql = "UPDATE test7.member SET state='" + lecture_id
					+ "' WHERE id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, student_id);
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

	public int isInHere(String lecroom_id, int width, int height, int tag_id) {
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs = null;
		int tag_id_new;
		try {
			String sql = "SELECT tag_id, tag_id_old as result FROM test7_check.`tag_id` WHERE lecroom_id=? AND width=? AND height=? AND (tag_id=? OR tag_id_old=?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, lecroom_id);
			pstmt.setInt(2, width);
			pstmt.setInt(3, height);
			pstmt.setInt(4, tag_id);
			pstmt.setInt(5, tag_id);
			rs = pstmt.executeQuery();
			/*
			if (rs.next()) {
				Random random = new Random();
				tag_id_new = random.nextInt(10000);
				try {
					if (rs.getInt("tag_id") == tag_id) {
						String sql2 = "UPDATE test7_check.tag_id SET tag_id=?, tag_id_old=? WHERE lecroom_id=? AND width=? AND height=?";
						pstmt2 = con2.prepareStatement(sql2);
						pstmt2.setInt(1, tag_id_new);
						pstmt2.setInt(2, tag_id);
						pstmt2.setString(3, lecroom_id);
						pstmt2.setInt(4, width);
						pstmt2.setInt(5, height);

					} else {// else if(rs.getInt("tag_id_old") == tag_id)
						String sql2 = "UPDATE test7_check.tag_id SET tag_id=? WHERE lecroom_id=? AND width=? AND height=?";
						pstmt2 = con2.prepareStatement(sql2);
						pstmt2.setInt(1, tag_id_new);
						pstmt2.setString(2, lecroom_id);
						pstmt2.setInt(3, width);
						pstmt2.setInt(4, height);
					}
					pstmt2.executeUpdate();
					commit(con2);
				} catch (Exception e2) {
					tag_id_new = 10001;
					e2.printStackTrace();
				} finally {
					close(pstmt2);
				}
			} else {
				tag_id_new = 10000;
			}
			*/
		} catch (Exception e) {
			tag_id_new = 10001;
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		//return tag_id_new; //original
		return tag_id;
	}
}
