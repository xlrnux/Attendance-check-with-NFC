package svc;

import static util.DbUtil.close;
import static util.DbUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import vo.Room;
import dao.RoomSettingDao;

public class RoomSettingBiz {
	public boolean roomAdd(Room room){
		Connection con = getConnection();
		Connection con2 = getConnection("test7_check");
		RoomSettingDao memSetDao = new RoomSettingDao(con, con2);
		memSetDao.roomAdd(room);
		close(con);
		close(con2);
		return true;
	}
	public boolean roomDel(String lecroom_id){
		Connection con = getConnection();
		RoomSettingDao memSetDao = new RoomSettingDao(con);
		memSetDao.roomDel(lecroom_id);
		close(con);
		return true;
	}
	public Room getRoomSearch(String lecroom_id){
		// TODO Auto-generated method stub
		Connection con = getConnection();
		RoomSettingDao memSetDao = new RoomSettingDao(con);
		Room room = memSetDao.getRoomSearch(lecroom_id);
		close(con);
		return room;
	}
	public boolean roomMod(Room room){
		Connection con = getConnection();
		RoomSettingDao memSetDao = new RoomSettingDao(con);
		memSetDao.roomMod(room);
		close(con);
		return true;
	} 
	/*
	public int getWidth(String lecroom_id){
		// TODO Auto-generated method stub
		Connection con = getConnection();
		RoomSettingDao memSetDao = new RoomSettingDao(con);
		int width = memSetDao.getWidth(lecroom_id);
		close(con);
		return width;
	}
	public int getHeight(String lecroom_id){
		// TODO Auto-generated method stub
		Connection con = getConnection();
		RoomSettingDao memSetDao = new RoomSettingDao(con);
		int height = memSetDao.getHeight(lecroom_id);
		close(con);
		return height;
	}
	*/
	public boolean lectureOn(String lecroom_id, String lecture_id){
		Connection con = getConnection();
		RoomSettingDao roomSetDao = new RoomSettingDao(con);
		roomSetDao.lectureOn(lecroom_id,lecture_id);
		close(con);
		return true;
	}
	public boolean lectureOff(String lecroom_id){
		Connection con = getConnection();
		RoomSettingDao roomSetDao = new RoomSettingDao(con);
		roomSetDao.lectureOff(lecroom_id);
		close(con);
		return true;
	}
	public ArrayList<String> getRoomList(){
		Connection con = getConnection();
		RoomSettingDao roomSetDao = new RoomSettingDao(con);
		ArrayList<String> roomList = new ArrayList<String>();
		roomList = roomSetDao.getRoomList();
		
		return roomList;
	}
}
