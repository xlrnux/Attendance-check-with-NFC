package svc;

import static util.DbUtil.close;
import static util.DbUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import vo.Room;
import vo.Tag_ID;
import dao.RoomSettingDao;
import dao.TagSettingDao;

public class TagSettingBiz {
	public ArrayList<Tag_ID> getSeatData(String lecroom_id) {
		// TODO Auto-generated method stub
		ArrayList<Tag_ID> tagList = new ArrayList<Tag_ID>();
		Connection con = getConnection("test7_check");
		TagSettingDao tagSetDao = new TagSettingDao(con);
		tagList = tagSetDao.getSeatData(lecroom_id);
		close(con);
		return tagList;
	}
	public Tag_ID getSeatData(String lecroom_id, int width, int height) {
		// TODO Auto-generated method stub
		Tag_ID tagData = new Tag_ID();
		Connection con = getConnection("test7_check");
		TagSettingDao tagSetDao = new TagSettingDao(con);
		tagData = tagSetDao.getSeatData(lecroom_id, width, height);
		close(con);
		return tagData;
	}

	public boolean roomMod(Room room) {
		Connection con = getConnection();
		RoomSettingDao memSetDao = new RoomSettingDao(con);
		memSetDao.roomMod(room);
		close(con);
		return true;
	}

	/*
	 * public int getWidth(String lecroom_id){ // TODO Auto-generated method
	 * stub Connection con = getConnection(); RoomSettingDao memSetDao = new
	 * RoomSettingDao(con); int width = memSetDao.getWidth(lecroom_id);
	 * close(con); return width; } public int getHeight(String lecroom_id){ //
	 * TODO Auto-generated method stub Connection con = getConnection();
	 * RoomSettingDao memSetDao = new RoomSettingDao(con); int height =
	 * memSetDao.getHeight(lecroom_id); close(con); return height; }
	 */
	public boolean lectureOn(String lecroom_id, String lecture_id) {
		Connection con = getConnection();
		RoomSettingDao roomSetDao = new RoomSettingDao(con);
		roomSetDao.lectureOn(lecroom_id, lecture_id);
		close(con);
		return true;
	}

	public boolean lectureOff(String lecroom_id) {
		Connection con = getConnection();
		RoomSettingDao roomSetDao = new RoomSettingDao(con);
		roomSetDao.lectureOff(lecroom_id);
		close(con);
		return true;
	}

	public ArrayList<String> getRoomList() {
		Connection con = getConnection();
		RoomSettingDao roomSetDao = new RoomSettingDao(con);
		ArrayList<String> roomList = new ArrayList<String>();
		roomList = roomSetDao.getRoomList();

		return roomList;
	}
}
