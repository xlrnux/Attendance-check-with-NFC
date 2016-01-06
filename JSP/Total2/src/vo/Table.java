package vo;

public class Table {
	private String lecture_id = null;
	private String lecroom_id = null;
	private String day_id = null;
	private int period_id;
	public String getLecture_id() {
		return lecture_id;
	}
	public void setLecture_id(String lecture_id) {
		this.lecture_id = lecture_id;
	}
	public String getLecroom_id() {
		return lecroom_id;
	}
	public void setLecroom_id(String lecroom_id) {
		this.lecroom_id = lecroom_id;
	}
	public String getDay_id() {
		return day_id;
	}
	public void setDay_id(String day_id) {
		this.day_id = day_id;
	}
	public int getPeriod_id() {
		return period_id;
	}
	public void setPeriod_id(int period_id) {
		this.period_id = period_id;
	}
}
