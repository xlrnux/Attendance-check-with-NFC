package vo;

/**
 * Servlet implementation class User
 */
public class Lecture {
	private String lecture_id =null;
	private String prof_id=null;
	private String prof_name=null;
	private String lecture_name=null;
	private String state=null;
	private int today;
	private int class_count;	
	private int credit;
	private int credit_hour;
	public String getLecture_id() {
		return lecture_id;
	}
	public void setLecture_id(String lecture_id) {
		this.lecture_id = lecture_id;
	}
	public String getProf_id() {
		return prof_id;
	}
	public void setProf_id(String prof_id) {
		this.prof_id = prof_id;
	}
	public String getProf_name() {
		return prof_name;
	}
	public void setProf_name(String prof_name) {
		this.prof_name = prof_name;
	}
	public String getLecture_name() {
		return lecture_name;
	}
	public void setLecture_name(String lecture_name) {
		this.lecture_name = lecture_name;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public int getToday() {
		return today;
	}
	public void setToday(int today) {
		this.today = today;
	}
	public int getClass_count() {
		return class_count;
	}
	public void setClass_count(int class_count) {
		this.class_count = class_count;
	}
	public int getCredit() {
		return credit;
	}
	public void setCredit(int credit) {
		this.credit = credit;
	}
	public int getCredit_hour() {
		return credit_hour;
	}
	public void setCredit_hour(int credit_hour) {
		this.credit_hour = credit_hour;
	}
	
	
	
}
