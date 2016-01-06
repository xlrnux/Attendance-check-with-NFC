package svc;

import vo.Member;

import java.sql.*;
import java.util.ArrayList;

import dao.MemberSettingDao;
import static util.DbUtil.*;

public class MemberSettingBiz {
	public boolean memberAdd(Member member){
		Connection con = getConnection();
		MemberSettingDao memSetDao = new MemberSettingDao(con);
		memSetDao.memberAdd(member);
		close(con);
		return true;
	}
	public boolean memberDel(String id){
		Connection con = getConnection();
		MemberSettingDao memSetDao = new MemberSettingDao(con);
		memSetDao.memberDel(id);
		close(con);
		return true;
	}
	public Member getMemberSearch(String id){
		// TODO Auto-generated method stub
		Connection con = getConnection();
		MemberSettingDao memSetDao = new MemberSettingDao(con);
		Member member = memSetDao.getMemberSearch(id);
		close(con);
		return member;
	}
	public boolean memberMod(Member member){
		Connection con = getConnection();
		MemberSettingDao memSetDao = new MemberSettingDao(con);
		memSetDao.memberMod(member);
		close(con);
		return true;
	}
	public ArrayList<String> getNameList(ArrayList<String> studentList) {
		Connection con = getConnection();
		ArrayList<String> nameList = new ArrayList<String>();
		MemberSettingDao memSetDao = new MemberSettingDao(con);
		nameList = memSetDao.getNameList(studentList);
		close(con);
		return nameList;
	}
}
