package classes;

import java.sql.Date;

public class IssuedBook{
	private String issueCode;
	private String issuedUserName;
	private String issuedUserEmail;
	private String bookTitle;
	private String isbn;
	private String phoneNumber;
	private Date issueDate;
	private Date returnDate;
	
	public IssuedBook() {}

	public IssuedBook(String issueCode, String issuedUserName, String bookTitle, String isbn, String phoneNumber,
			Date issueDate, Date returnDate) {
		super();
		this.issueCode = issueCode;
		this.issuedUserName = issuedUserName;
		this.bookTitle = bookTitle;
		this.isbn = isbn;
		this.phoneNumber = phoneNumber;
		this.issueDate = issueDate;
		this.returnDate = returnDate;
	}
	
	
	public IssuedBook(String issueCode, String issuedUserName, String issuedUserEmail, String bookTitle, String isbn,
			String phoneNumber, Date issueDate, Date returnDate) {
		super();
		this.issueCode = issueCode;
		this.issuedUserName = issuedUserName;
		this.issuedUserEmail = issuedUserEmail;
		this.bookTitle = bookTitle;
		this.isbn = isbn;
		this.phoneNumber = phoneNumber;
		this.issueDate = issueDate;
		this.returnDate = returnDate;
	}

	public String getIssuedUserEmail() {
		return issuedUserEmail;
	}

	public void setIssuedUserEmail(String issuedUserEmail) {
		this.issuedUserEmail = issuedUserEmail;
	}

	public String getIssueCode() {
		return issueCode;
	}

	public void setIssueCode(String issueCode) {
		this.issueCode = issueCode;
	}

	public String getIssuedUserName() {
		return issuedUserName;
	}

	public void setIssuedUserName(String issuedUserName) {
		this.issuedUserName = issuedUserName;
	}

	public String getBookTitle() {
		return bookTitle;
	}

	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
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

	public Date getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}
}
