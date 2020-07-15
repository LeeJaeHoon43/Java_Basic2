package classes;

import java.sql.Date;

public class Overdue {
	private String issueCode;
	private String userEmail;
	private String userName;
	private String title;
	private String isbn;
	private String phoneNumber;
	private Date returnDate;
	
	
	public Overdue() {}

	public Overdue(String issueCode, String userEmail, String userName, String title, String isbn,
			String phoneNumber, Date returnDate) {
		super();
		this.issueCode = issueCode;
		this.userEmail = userEmail;
		this.userName = userName;
		this.title = title;
		this.isbn = isbn;
		this.phoneNumber = phoneNumber;
		this.returnDate = returnDate;
	}

	public String getIssueCode() {
		return issueCode;
	}

	public void setIssueCode(String issueCode) {
		this.issueCode = issueCode;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}
}
