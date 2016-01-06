package svc;

import static util.DbUtil.close;
import static util.DbUtil.getConnection;

import java.sql.Connection;

import dao.UpdateCheckListDao;

public class UpdateCheckListBiz {
	public boolean updateCheckList(String lecture_id, int class_count){
		Connection con = getConnection();
		Connection con2 = getConnection("test7_check");
		UpdateCheckListDao updateCheckListDao = new UpdateCheckListDao(con, con2);
		updateCheckListDao.updateCheckList(lecture_id, class_count);
		close(con);
		close(con2);
		return true;
	}
}
