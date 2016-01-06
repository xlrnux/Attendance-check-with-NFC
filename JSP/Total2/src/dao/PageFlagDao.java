package dao;

import static util.DbUtil.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import vo.Table;
import vo.Room;

public class PageFlagDao {
	Connection con;
	Connection con2;

	public PageFlagDao(Connection con) {
		super();
		this.con = con;
	}

	public PageFlagDao(Connection con, Connection con2) {
		super();
		this.con = con;
		this.con2 = con2;
	}

	public Table getTable(String prof_id) {
		Table table = new Table();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs2 = null;
		try {

			table.setLecture_id("NoResult");
			table.setLecroom_id("NoResult");
			String sql2 = "SELECT COUNT(state) as result, state FROM lecture WHERE prof_id=? AND state!='0'";
			pstmt2 = con2.prepareStatement(sql2);
			pstmt2.setString(1, prof_id);
			rs2 = pstmt2.executeQuery();

			if (rs2.next()) {
				if (rs2.getInt("result") == 0) {
					System.out.println("result: " + rs2.getInt("result"));
					try {
						String sql = "SELECT time_table.lecture_id, time_table.lecroom_id, time_table.day_id, time_table.period_id FROM lecture, time_table WHERE lecture.lecture_id = time_table.lecture_id AND lecture.prof_id=? AND time_table.day_id=(WEEKDAY(NOW())+2) AND time_table.period_id=(SELECT period_id FROM period WHERE start_time < CURTIME() AND end_time > CURTIME())";
						
						pstmt = con.prepareStatement(sql);
						pstmt.setString(1, prof_id);
						rs = pstmt.executeQuery();

						if (rs.next()) {

							table.setLecture_id(rs
									.getString("time_table.lecture_id"));
							System.out.println("lecture_id: "
									+ rs.getString("time_table.lecture_id"));

							table.setLecroom_id(rs
									.getString("time_table.lecroom_id"));
							System.out.println("lecroom_id: "
									+ rs.getString("time_table.lecroom_id"));
							table.setDay_id(rs.getString("time_table.day_id"));
							table.setPeriod_id(rs
									.getInt("time_table.period_id"));
						}
					} catch (Exception e2) {

					} finally {
						close(pstmt);
						close(rs);
					}
				} else if (rs2.getInt("result") == 1) {
					table.setLecroom_id(rs2.getString("state"));
				} else {
					System.out
							.println("PageFlagDao.java: sql2의 결과로 나온 result의 값이 0도 1도아님. 왜 강의가 두개이상 열려있음?격하게 잘못되었음.");
				}
			} else {
				System.out
						.println("PageFlagDao.java: sql2의 결과가 텅텅비어있음. 격하게 잘못되었음.");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
			close(pstmt2);
			close(rs2);
		}
		return table;
	}

	public Room getRoom(String lecroom_id) {
		Room room = new Room();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT width, height, state FROM lecture_room WHERE lecroom_id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, lecroom_id);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				room.setWidth(rs.getInt("width"));
				room.setHeight(rs.getInt("height"));
				room.setState(rs.getString("state"));
			} else {
				System.out
						.println("PageFlagDao.java: getRoom: sql: 어라 왜 안나오죠...");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return room;
	}
}
