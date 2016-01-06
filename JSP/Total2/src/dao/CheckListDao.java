package dao;

import static util.DbUtil.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class CheckListDao {
	Connection con;
	Connection con2;
	public  CheckListDao(Connection con) {
		super();
		this.con = con;
	}

	public  CheckListDao(Connection con, Connection con2) {
		super();
		this.con = con;
		this.con2 = con2;
	}
	

	public ArrayList<String> getStudentList(String lecture_id) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<String> studentList = new ArrayList<String>();
		
		try {
			String sql = "SELECT student_id FROM test7.course_reg WHERE lecture_id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, lecture_id);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				do{
					studentList.add(rs.getString("student_id"));
				}while(rs.next());
			} else {
				System.out
						.println("CheckListDao.java: getStudentList: sql:이 과목을 수강신청한 학생이 없습니다.");
				studentList.add("NoResult");
			}
		} catch (Exception e) {
			e.printStackTrace();
			studentList.add("NoResult");
		} finally {
			close(pstmt);
			close(rs);
		}
		return studentList;
	}
	public ArrayList<ArrayList<String>> getCheckList(ArrayList<String> studentList, String lecture_id) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<ArrayList<String>> checkList = new ArrayList<ArrayList<String>>();
		ArrayList<String> checkData = new ArrayList<String>();
		try {
			String sql = "SELECT state FROM test7_check."+lecture_id+" WHERE student_id=?";
			pstmt = con.prepareStatement(sql);
			for(int i=0; i < studentList.size(); i++){
				checkData = new ArrayList<String>();
				pstmt.setString(1, studentList.get(i));
				rs = pstmt.executeQuery();
				if(rs.next()){
					do{
						checkData.add(rs.getString("state"));
					}while(rs.next());
				}else{
					//해당 학생은 출석명부에 없음
					//즉, 매개변수로 들어온 studnetList가 잘못됨
				}
				checkList.add(checkData);
			}
		} catch (Exception e) {
			e.printStackTrace();
			lecture_id = "NoResult";
		} finally {
			close(pstmt);
			close(rs);
		}
		return checkList;
	}

}
