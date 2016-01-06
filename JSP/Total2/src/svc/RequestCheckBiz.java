package svc;

import static util.DbUtil.close;
import static util.DbUtil.getConnection;

import java.sql.Connection;

import dao.RequestCheckDao;

public class RequestCheckBiz {
	// 현재 이곳에서 강의중인 강의코드 반환
	public String getLecture_id(String lecroom_id) {
		// TODO Auto-generated method stub
		String lecture_id;
		Connection con = getConnection();
		RequestCheckDao requestCheckDao = new RequestCheckDao(con);
		lecture_id = requestCheckDao.getLecture_id(lecroom_id);
		close(con);
		return lecture_id;
	}
	

	public int answerPossible(String student_id, String lecture_id) {
		Connection con = getConnection();
		int class_count;
		RequestCheckDao requestCheckDao = new RequestCheckDao(con);
		class_count = requestCheckDao.answerPossible(student_id, lecture_id);
		close(con);
		return class_count;
	}
	
	public int isInHere(String lecroom_id, int width, int height, int tag_id) {
		Connection con = getConnection("test7_check");
		Connection con2 = getConnection("test7_check");
		RequestCheckDao requestCheckDao = new RequestCheckDao(con, con2);
		int id_tag_new = requestCheckDao.isInHere(lecroom_id, width, height, tag_id);
		close(con);
		close(con2);
		return id_tag_new;
	}

	public boolean CheckIn(String student_id, String lecture_id, int class_count, int width, int height) {
		Connection con = getConnection();
		Connection con2 = getConnection();
		RequestCheckDao requestCheckDao1 = new RequestCheckDao(con);
		RequestCheckDao requestCheckDao2 = new RequestCheckDao(con2);
		boolean result = requestCheckDao1.checkIn(student_id, lecture_id,	class_count, width, height);

		if (result) {
			result = requestCheckDao2.changeState(student_id, lecture_id);
		}
		close(con);
		close(con2);
		return result;
	}
	
}
