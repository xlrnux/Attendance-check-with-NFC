package vo;

public class Room {
	private String lecroom_id = null;
	private int width;
	private int height;
	private String state = null;
	public String getLecroom_id() {
		return lecroom_id;
	}
	public void setLecroom_id(String lecroom_id) {
		this.lecroom_id = lecroom_id;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
}
