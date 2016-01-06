package dao;

import static util.DbUtil.close;
import static util.DbUtil.commit;
import static util.DbUtil.rollback;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Random;

import vo.Room;

public class RoomSettingDao {
	Connection con;
	Connection con2;

	public RoomSettingDao(Connection con) {
		super();
		this.con = con;
	}

	public RoomSettingDao(Connection con, Connection con2) {
		super();
		this.con = con;
		this.con2 = con2;
	}

	public boolean roomAdd(Room room) {
		boolean result;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		try {
			String sql = "INSERT INTO `test7`.`lecture_room` (`lecroom_id`, `width`, `height`, `state`) VALUES (?, ?, ?, ?)";
			String sql2 = "INSERT INTO `test7_check`.`tag_id` (`lecroom_id`, `width`, `height`, `tag_id`,  `tag_id_old`, `state`) VALUES (?,?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt2 = con2.prepareStatement(sql2);

			pstmt.setString(1, room.getLecroom_id()); // id
			pstmt.setInt(2, room.getWidth());// width
			pstmt.setInt(3, room.getHeight());// height
			pstmt.setString(4, room.getState());// cell_num
			pstmt.executeUpdate();
			commit(con);

			for (int i = 1; i <= room.getWidth(); i++) {
				for (int j = 1; j <= room.getHeight(); j++) {
					Random random = new Random();
					try {
						pstmt2.setString(1, room.getLecroom_id());
						pstmt2.setInt(2, i);
						pstmt2.setInt(3, j);
						pstmt2.setInt(4, random.nextInt(10000));
						pstmt2.setInt(5, random.nextInt(10000));
						pstmt2.setString(6, "0");
						pstmt2.executeUpdate();
					} catch (Exception e) {
						rollback(con2);
						e.printStackTrace();
						result = false;
					}
					commit(con2);
				}
			}
			result = true;
		} catch (Exception e) {
			rollback(con);
			e.printStackTrace();
			result = false;
		} finally {
			close(pstmt);
			close(pstmt2);
		}
		return result;
	}

	public boolean insert_tagId(String lecroom_id, int width, int height) {
		PreparedStatement pstmt = null;
		boolean result;
		try {
			Random r1 = new Random();
			int tag_id = r1.nextInt(1000);

			String sql = "INSERT INTO `test7_check`.`tag_id` (`lecroom_id`, `width`, `height`, `tag_id`, `tag_id_old`) VALUES (?, ?, ?, ?, ?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, lecroom_id); // id
			pstmt.setInt(2, width);// width
			pstmt.setInt(3, height);// height
			pstmt.setInt(4, tag_id);
			pstmt.setInt(5, tag_id);
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

	public int update_tagId(String lecroom_id, int width, int height, int tag_id) {
		PreparedStatement pstmt = null;
		Random r1 = new Random();
		int tag_id_new = r1.nextInt(1000);
		try {
			String sql = "UPDATE `test7_check`.`tag_id` SET `tag_id`=? `tag_id_old`=? WHERE lecroom_id= ? AND width=? AND height=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(3, lecroom_id); // id
			pstmt.setInt(4, width);// width
			pstmt.setInt(5, height);// height
			pstmt.setInt(1, tag_id_new);
			pstmt.setInt(2, tag_id);
			pstmt.executeUpdate();
			commit(con);
		} catch (Exception e) {
			rollback(con);
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return tag_id_new;
	}

	public boolean roomDel(String lecroom_id) {
		boolean result;
		PreparedStatement pstmt = null;
		try {
			String sql = "DELETE FROM `test7`.`lecture_room` WHERE `lecroom_id`= ? ";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, lecroom_id); // id

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

	public Room getRoomSearch(String lecroom_id) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Room room = null;
		try {
			String sql = "select * from lecture_room where lecroom_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, lecroom_id);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				room = new Room();
				room.setLecroom_id(rs.getString("lecroom_id"));
				room.setWidth(rs.getInt("width"));
				room.setHeight(rs.getInt("height"));
				String state = rs.getString("state");
				System.out.println();
				if (state.equals("0")) {
					room.setState("사용가능");
				} else {
					room.setState(state);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return room;
	}

	public boolean roomMod(Room room) {
		boolean result;
		PreparedStatement pstmt = null;
		try {
			String sql = "UPDATE `test7`.`lecture_room` SET `width`=?, `height`=? WHERE lecroom_id= ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, room.getWidth());// width
			pstmt.setInt(2, room.getHeight());// height
			pstmt.setString(3, room.getLecroom_id());
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

	public boolean lectureOn(String lecroom_id, String lecture_id) {
		boolean result;
		PreparedStatement pstmt = null;
		try {
			String sql = "UPDATE `test7`.`lecture_room` SET `state`=? WHERE `lecroom_id`=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, lecture_id);
			pstmt.setString(2, lecroom_id);

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

	public boolean lectureOff(String lecroom_id) {
		boolean result;
		PreparedStatement pstmt = null;
		try {
			String sql = "UPDATE `test7`.`lecture_room` SET `state`='0' WHERE `lecroom_id`=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, lecroom_id);

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

	public ArrayList<String> getRoomList() {
		ArrayList<String> roomList = new ArrayList<String>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Room room = null;
		try {
			String sql = "select lecroom_id from lecture_room";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				do {
					roomList.add(rs.getString("lecroom_id"));
				} while (rs.next());
			} else {
				// 있을수 없는 일이지만 방이 없다는말.
				// lecture_room 테이블이 텅텅 비었다는말
				System.err.println("test7.lecture_room table is Empty!!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return roomList;
	}
}
