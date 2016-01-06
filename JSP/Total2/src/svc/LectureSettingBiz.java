package svc;

import static util.DbUtil.close;
import static util.DbUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import vo.Lecture;
import dao.LectureSettingDao;

public class LectureSettingBiz {
	public boolean lectureAdd(Lecture lecture){
		Connection con = getConnection();
		Connection con2 = getConnection("test7_check");
		LectureSettingDao lecSetDao = new LectureSettingDao(con, con2);
		lecSetDao.lectureAdd(lecture);
		close(con);
		close(con2);
		return true;
	}
	public boolean lectureDel(String lecture_id){
		Connection con = getConnection();
		Connection con2 = getConnection("test7_check");
		Connection con3 = getConnection();
		LectureSettingDao lecSetDao = new LectureSettingDao(con, con2, con3);
		lecSetDao.lectureDel(lecture_id);
		close(con);
		close(con2);
		close(con3);
		return true;
	}
	public ArrayList<Lecture> getLectureList() {
		Connection con = getConnection();
		ArrayList<Lecture> lectureList = new ArrayList<Lecture>();
		LectureSettingDao lecSetDao = new LectureSettingDao(con);
		lectureList = lecSetDao.getLectureList();
		return lectureList;
	}
	public Lecture getLectureSearch(String lecture_id){
		// TODO Auto-generated method stub
		Connection con = getConnection();
		LectureSettingDao lecSetDao = new LectureSettingDao(con);
		Lecture lecture = lecSetDao.getLectureSearch(lecture_id);
		close(con);
		return lecture;
	}
	public boolean lectureMod(Lecture lecture){
		Connection con = getConnection();
		LectureSettingDao lecSetDao = new LectureSettingDao(con);
		lecSetDao.lectureMod(lecture);
		close(con);
		return true;
	}
	public boolean lectureOff(String lecture_id){
		Connection con = getConnection();
		LectureSettingDao lecSetDao = new LectureSettingDao(con);
		lecSetDao.lectureOff(lecture_id);
		close(con);
		return true;
	}
	public boolean lectureOn(String lecture_id, String lecroom_id, int class_count){
		Connection con = getConnection();
		LectureSettingDao lecSetDao = new LectureSettingDao(con);
		lecSetDao.lectureOn(lecture_id, lecroom_id, class_count);
		close(con);
		return true;
	}
	public boolean lectureOn(String lecture_id, String lecroom_id){
		Connection con = getConnection();
		LectureSettingDao lecSetDao = new LectureSettingDao(con);
		lecSetDao.lectureOn(lecture_id, lecroom_id);
		close(con);
		return true;
	}

}
