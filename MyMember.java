package ljh.java.vo;

public class MyMember {
	// 멤버
	private String name;
	private int age;
	private String tel;
	private String address;

	// 생성자
	public MyMember() {}
	
	public MyMember(String name, int age, String tel, String address) {
		super();
		this.name = name;
		this.age = age;
		this.tel = tel;
		this.address = address;
	}

	// 메서드
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	// toString
	@Override
	public String toString() {
		return "MyMember [name=" + name + ", age=" + age + ", tel=" + tel + ", address=" + address + "]";
	}
}
