package ljh.java.exam05;

import java.io.Serializable;

public class Data implements Serializable { // 객체 직렬화.
	private static final long serialVersionUID = 1L;
	private int no;
	private String name;
	private String mail;
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
