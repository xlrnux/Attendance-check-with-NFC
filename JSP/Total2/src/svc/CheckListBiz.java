package svc;

import static util.DbUtil.close;
import static util.DbUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import dao.CheckListDao;

public class CheckListBiz {
	public ArrayList<ArrayList<String>> getCheckList(ArrayList<String> studentList, String lecture_id) {
		// TODO Auto-generated method stub
		ArrayList<ArrayList<String>> checkList = new ArrayList<ArrayList<String>>();
		Connection con = getConnection();
		CheckListDao checkListDao = new CheckListDao(con);
		checkList = checkListDao.getCheckList(studentList, lecture_id);
		close(con);
		return checkList;
	}
	public ArrayList<String> getStudentList(String lecture_id) {
		// TODO Auto-generated method stub
		ArrayList<String> studentList = new ArrayList<String>();
		Connection con = getConnection();
		CheckListDao checkListDao = new CheckListDao(con);
		studentList = checkListDao.getStudentList(lecture_id);
		close(con);
		return studentList;
	}
}
