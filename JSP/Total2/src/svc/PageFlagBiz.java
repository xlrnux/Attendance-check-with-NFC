package svc;
import vo.Table;
import vo.Room;
import java.sql.*;
import dao.PageFlagDao;
import static util.DbUtil.*;

/**
 * Servlet implementation class PageFlagBiz
 */
public class PageFlagBiz{
	public Table getTable(String prof_id){
		Table table;
		// TODO Auto-generated method stub
		Connection con = getConnection();
		Connection con2 = getConnection();
		PageFlagDao pageFlagDao = new PageFlagDao(con, con2);
		table = pageFlagDao.getTable(prof_id);
		close(con);
		close(con2);
		return table;
	}
	public Room getRoom(String lecroom_id){
		Room room;
		// TODO Auto-generated method stub
		Connection con = getConnection();
		PageFlagDao pageFlagDao = new PageFlagDao(con);
		room = pageFlagDao.getRoom(lecroom_id);
		close(con);
		return room;
	}
}
