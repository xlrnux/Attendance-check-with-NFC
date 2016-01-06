package vo;

/**
 * Servlet implementation class User
 */
public class Member {
	private String id =null;
	private String password=null;
	private String name=null;
	private String cell_num=null;
	private String home_num=null;
	private String mac_address=null;
	private String email=null;
	private int grade=1;
	private String level=null;
	private String state=null;
	private String device=null;
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCell_num() {
		return cell_num;
	}

	public void setCell_num(String cell_num) {
		this.cell_num = cell_num;
	}

	public String getHome_num() {
		return home_num;
	}

	public void setHome_num(String home_num) {
		this.home_num = home_num;
	}

	public String getMac_address() {
		return mac_address;
	}

	public void setMac_address(String mac_address) {
		this.mac_address = mac_address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	public String getDevice() {
		return device;
	}

	public void setDevice(String device) {
		this.device = device;
	}
}
