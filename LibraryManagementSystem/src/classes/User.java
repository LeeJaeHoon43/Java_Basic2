package classes;

public class User {
	private String userName;
	private String userEmail;
	private String password;
	private String gender;
	private String phoneNumber;
	private String address;

	public User() {}
	
	public User(String userEmail, String password) {
		super();
		this.userEmail = userEmail;
		this.password = password;
	}

	public User(String userName, String password, String gender, String phoneNumber, String address) {
		super();
		this.userName = userName;
		this.password = password;
		this.gender = gender;
		this.phoneNumber = phoneNumber;
		this.address = address;
	}
	
	public User(String userName, String gender, String phoneNumber, String address) {
		super();
		this.userName = userName;
		this.gender = gender;
		this.phoneNumber = phoneNumber;
		this.address = address;
	}

	public User(String userName, String userEmail, String password, String gender, String phoneNumber, String address) {
		super();
		this.userName = userName;
		this.userEmail = userEmail;
		this.password = password;
		this.gender = gender;
		this.phoneNumber = phoneNumber;
		this.address = address;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}
