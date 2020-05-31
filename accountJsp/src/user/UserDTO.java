package user;

// user의 정보를 담는 곳
public class UserDTO {

	private String email;
	private String userName;
	private String password;
	private String phoneNumber;
	private String secondPhoneNumber;
	private boolean isMember;
	private boolean isAdmin;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getSecondPhoneNumber() {
		return secondPhoneNumber;
	}
	public void setSecondPhoneNumber(String secondPhoneNumber) {
		this.secondPhoneNumber = secondPhoneNumber;
	}
	public boolean isMember() {
		return isMember;
	}
	public void setMember(boolean isMember) {
		this.isMember = isMember;
	}
	public boolean isAdmin() {
		return isAdmin;
	}
	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	
	// 기본 생성자
	public UserDTO(String email, String userName, String password, String phoneNumber, String secondPhoneNumber,
			boolean isMember, boolean isAdmin) {
		super();
		this.email = email;
		this.userName = userName;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.secondPhoneNumber = secondPhoneNumber;
		this.isMember = isMember;
		this.isAdmin = isAdmin;
	}
	
}