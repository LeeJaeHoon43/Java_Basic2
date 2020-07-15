package DataAccess;

import classes.Admin;
import classes.BookInfo;
import classes.IssuedBook;
import classes.Overdue;
import classes.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.*;

public class DatabaseHandler{

	public void signUpUser(User user) throws SQLException {
		String sql = "INSERT INTO useraccounts VALUES(?,?,?,?,?,?)";
		Connection conn = JdbcUtil.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, user.getUserName());
		pstmt.setString(2, user.getUserEmail());
		pstmt.setString(3, user.getPassword());
		pstmt.setString(4, user.getGender());
		pstmt.setString(5, user.getPhoneNumber());
		pstmt.setString(6, user.getAddress());
		pstmt.executeUpdate();
	}

	public ResultSet getUser(User user) {
		String sql = "SELECT * FROM useraccounts WHERE userEmail =? AND password =?";
		Connection conn = JdbcUtil.getConnection();
		ResultSet rs = null;
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getUserEmail());
			pstmt.setString(2, user.getPassword());
			rs = pstmt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return rs;
	}

	public ResultSet getAdmin(Admin admin) {
		Connection conn = JdbcUtil.getConnection();
		ResultSet rs = null;
		String sql = "SELECT * FROM adminaccounts WHERE email =? AND password =?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, admin.getEmail());
			pstmt.setString(2, admin.getPassword());
			rs = pstmt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

	public ObservableList<BookInfo> getAllbookInfo() throws SQLException, ClassNotFoundException {
		ObservableList<BookInfo> bookInfoList = FXCollections.observableArrayList();
		Connection conn = JdbcUtil.getConnection();
		String sql = "SELECT * FROM bookinfomation ORDER BY isbn ASC";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()){
			String title = rs.getString("title");
			String isbn = rs.getString("isbn");
			String author = rs.getString("author");
			String publisher = rs.getString("publisher");
			String categories = rs.getString("categories");
			String subcategories = rs.getString("subCategories");
			int year = rs.getInt("year");
			int total = rs.getInt("total");
			BookInfo bookinfo = new BookInfo(title,isbn,author,publisher,categories, subcategories, year, total);
			bookInfoList.add(bookinfo);
		}
		return bookInfoList;
	}

	public boolean addAdminInfo(Admin admin) throws SQLException, ClassNotFoundException{
		String sql = "INSERT INTO adminaccounts VALUES(adminid_seq.nextval,?,?)";
		Connection conn = JdbcUtil.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, admin.getEmail());
		pstmt.setString(2, admin.getPassword());
		if (pstmt.executeUpdate() > 0) {
			return true;
		}else {
			return false;
		}
	}

	public String addbookInfo(BookInfo bookinfo) throws SQLException, ClassNotFoundException {
		String sql = "INSERT INTO bookinfomation VALUES(?,?,?,?,?,?,?,?)";
		Connection conn = JdbcUtil.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, bookinfo.getTitle());
		pstmt.setString(2, bookinfo.getIsbn());
		pstmt.setString(3, bookinfo.getAuthor());
		pstmt.setString(4, bookinfo.getPublisher());
		pstmt.setString(5, bookinfo.getCategories());
		pstmt.setString(6, bookinfo.getSubcategories());
		pstmt.setInt(7, bookinfo.getYear());
		pstmt.setInt(8, bookinfo.getTotal());

		if(pstmt.executeUpdate() > 0){
			return "도서 정보 등록 완료";
		}else{
			return "도서 정보 등록 실패";
		}

	}

	public String updateuserInfo(User userinfo) throws SQLException, ClassNotFoundException {
		String sql = "UPDATE useraccounts SET password = ? WHERE userEmail = ?";
		Connection conn = JdbcUtil.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, userinfo.getPassword());
		pstmt.setString(2, userinfo.getUserEmail());
		if(pstmt.executeUpdate() > 0){
			return "회원 정보 수정 완료";
		}else{
			return "회원 정보 수정 실패";
		}
	}

	public ObservableList<User> getAlluserInfo() throws SQLException, ClassNotFoundException {
		ObservableList<User> userInfoList = FXCollections.observableArrayList();
		String sql = "SELECT * FROM useraccounts";
		Connection conn = JdbcUtil.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()){
			String userName = rs.getString("userName");
			String userEmail = rs.getString("userEmail");
			String password = rs.getString("password");
			String gender = rs.getString("gender");
			String phoneNumber = rs.getString("phoneNumber");
			String address = rs.getString("address");

			User userinfo = new User(userName, userEmail, password, gender, phoneNumber, address);
			userInfoList.add(userinfo);
		}
		return userInfoList;
	}

	public ObservableList<Admin> getAlladminInfo() throws SQLException, ClassNotFoundException {
		ObservableList<Admin> adminInfoList = FXCollections.observableArrayList();
		String sql = "SELECT * FROM adminaccounts";
		Connection conn = JdbcUtil.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()){
			int id = rs.getInt("id");
			String email = rs.getString("email");
			String password = rs.getString("password");
			Admin admininfo = new Admin(id, email, password);
			adminInfoList.add(admininfo);
		}
		return adminInfoList;
	}

	public String updatebookInfo(BookInfo bookinfo) throws SQLException, ClassNotFoundException {
		String sql = "UPDATE bookinfomation SET title = ?, author = ?, publisher = ?, categories = ?, subCategories = ?, year = ?, total = ? WHERE isbn = ?";
		Connection conn = JdbcUtil.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, bookinfo.getTitle());
		pstmt.setString(2, bookinfo.getAuthor());
		pstmt.setString(3, bookinfo.getPublisher());
		pstmt.setString(4, bookinfo.getCategories());
		pstmt.setString(5, bookinfo.getSubcategories());
		pstmt.setInt(6, bookinfo.getYear());
		pstmt.setInt(7, bookinfo.getTotal());
		pstmt.setString(8, bookinfo.getIsbn());
		if(pstmt.executeUpdate() > 0){
			return "도서 정보 수정 완료";
		}else{
			return "도서 정보 수정 실패";
		}
	}

	public boolean updateUserInfo(User user, String useremail) throws SQLException, ClassNotFoundException{
		String sql = "UPDATE useraccounts SET userName = ?, gender = ?, phoneNumber = ?, address = ? WHERE userEmail = ?";
		Connection conn = JdbcUtil.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, user.getUserName());
		pstmt.setString(2, user.getGender());
		pstmt.setString(3, user.getPhoneNumber());
		pstmt.setString(4, user.getAddress());
		pstmt.setString(5, useremail);
		if(pstmt.executeUpdate() > 0) {
			return true;
		}else {
			return false;
		}
	}
	public boolean deleteUserInfo(ObservableList<User> selectedUsers) throws SQLException, ClassNotFoundException{
		String sql = "DELETE FROM useraccounts WHERE userEmail = ?";
		Connection conn = JdbcUtil.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		for (User user : selectedUsers) {
			pstmt.setString(1, user.getUserEmail());
		}		
		if(pstmt.executeUpdate() > 0) {
			return true;
		}else {
			return false;
		}
	}

	public ObservableList<IssuedBook> getAllissuedbooks() throws SQLException, ClassNotFoundException {
		ObservableList<IssuedBook> issuedbookList = FXCollections.observableArrayList();
		String sql = "SELECT * FROM issuedbooksinfomation";
		Connection conn = JdbcUtil.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()){
			String issueCode = rs.getString("issueCode");
			String issuedUserName = rs.getString("issuedUserName");
			String issuedUserEmail = rs.getString("issuedUserEmail");
			String bookTitle = rs.getString("bookTitle");
			String isbn = rs.getString("ISBN");
			String phoneNumber = rs.getString("phoneNumber");
			Date issueDate = rs.getDate("issueDate");
			Date returnDate = rs.getDate("returnDate");
			IssuedBook bookinfo = new IssuedBook(issueCode,issuedUserName,issuedUserEmail,bookTitle,isbn,phoneNumber,issueDate,returnDate);
			issuedbookList.add(bookinfo);
		}
		return issuedbookList;
	}

	public ObservableList<Overdue> getAllOverdueInfo() throws SQLException, ClassNotFoundException{
		ObservableList<Overdue> overdueList = FXCollections.observableArrayList();
		String sql = "SELECT * FROM overdue";
		Connection conn = JdbcUtil.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			String overdueissuecode = rs.getString("overdueIssueCode");
			String overdueissueuseremail = rs.getString("overdueIssueUserEmail");
			String overdueissueusername = rs.getString("overdueIssueUserName");
			String overdueissuedbooktitle = rs.getString("overdueIssuedBookTitle");
			String overdueisbn = rs.getString("isbn");
			String overduephonenumber = rs.getString("overduePhoneNumber");
			Date overduereturndate = rs.getDate("overdueReturnDate");
			Overdue overdue = new Overdue(overdueissuecode, overdueissueuseremail, overdueissueusername, overdueissuedbooktitle, overdueisbn, overduephonenumber, overduereturndate);
			overdueList.add(overdue);
		}
		return overdueList;
	}

	public ObservableList<IssuedBook> getSearchIssuedBooks(String code)throws SQLException, ClassNotFoundException{
		ObservableList<IssuedBook> issuedbookList = FXCollections.observableArrayList();
		String sql = "SELECT * FROM issuedbooksinfomation WHERE issueCode = ?";
		Connection conn = JdbcUtil.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, code);
		ResultSet rs = pstmt.executeQuery();
		if (rs.next()) {
			String issueCode = rs.getString("issueCode");
			String issuedUserName = rs.getString("issuedUserName");
			String issuedUserEmail = rs.getString("issuedUserEmail");
			String bookTitle = rs.getString("bookTitle");
			String isbn = rs.getString("ISBN");
			String phoneNumber = rs.getString("phoneNumber");
			Date issueDate = rs.getDate("issueDate");
			Date returnDate = rs.getDate("returnDate");
			IssuedBook bookinfo = new IssuedBook(issueCode,issuedUserName,issuedUserEmail,bookTitle,isbn,phoneNumber,issueDate,returnDate);
			issuedbookList.add(bookinfo);
		}
		return issuedbookList;
	}

	public String addissuedbooks(IssuedBook bookobj) throws SQLException, ClassNotFoundException {
		String sql = "INSERT INTO issuedbooksinfomation VALUES(?,?,?,?,?,?,?,?)";
		Connection conn = JdbcUtil.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, bookobj.getIssueCode());
		pstmt.setString(2, bookobj.getIssuedUserName());
		pstmt.setString(3, bookobj.getIssuedUserEmail());
		pstmt.setString(4, bookobj.getBookTitle());
		pstmt.setString(5, bookobj.getIsbn());
		pstmt.setString(6, bookobj.getPhoneNumber());		
		pstmt.setDate(7, bookobj.getIssueDate());				
		pstmt.setDate(8, bookobj.getReturnDate());	
		if(pstmt.executeUpdate() > 0){
			return "대출 도서 등록 완료";
		}else{
			return "대출 도서 등록 실패";
		}
	}

	public boolean removeissuedbooks(String issuecode) throws SQLException, ClassNotFoundException {
		String sql = "DELETE FROM issuedbooksinfomation WHERE issueCode = ?";
		Connection conn = JdbcUtil.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, issuecode);
		if (pstmt.executeUpdate() > 0) {
			return true;
		}else {
			return false;
		}
	}

	public String decrementCopies(BookInfo bookinfo) throws SQLException, ClassNotFoundException {
		String sql = "UPDATE bookinfomation SET total = ?-1 WHERE ISBN = ?";
		Connection conn = JdbcUtil.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, bookinfo.getTotal());
		pstmt.setString(2, bookinfo.getIsbn());

		if(pstmt.executeUpdate() > 0){
			return "도서 정보 수정 완료";
		}else{
			return "도서 정보 수정 실패";
		}
	}

	public boolean incrementCopies(String isbn) throws SQLException, ClassNotFoundException {
		String sql = "UPDATE bookinfomation SET total = total + 1 WHERE ISBN = ?";
		Connection conn = JdbcUtil.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, isbn);
		if(pstmt.executeUpdate() > 0){
			return true;
		}else{
			return false;
		}
	}

	public String deletebooks(ObservableList<BookInfo> selectedBooks) throws SQLException, ClassNotFoundException {
		String sql = "DELETE FROM bookinfomation WHERE ISBN = ?";
		Connection conn = JdbcUtil.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		for(BookInfo bookinfo : selectedBooks){
			pstmt.setString(1, bookinfo.getIsbn());
			pstmt.executeUpdate();
		}
		return null;
	}

	public boolean deleteOverdue(ObservableList<Overdue> selectedOverdueList) throws SQLException, ClassNotFoundException{
		String sql = "DELETE FROM overdue WHERE overdueIssueCode = ?";
		Connection conn = JdbcUtil.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		for (Overdue overdue : selectedOverdueList) {
			pstmt.setString(1, overdue.getIssueCode());
		}		
		if(pstmt.executeUpdate() > 0) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean removeOverdue(String issuecode) throws SQLException, ClassNotFoundException{
		String sql = "DELETE FROM overdue WHERE overdueIssueCode = ?";
		Connection conn = JdbcUtil.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, issuecode);
		if(pstmt.executeUpdate() > 0) {
			return true;
		}else {
			return false;
		}
	}
	
	public ObservableList<Overdue> searchOverdue(String issueCode) throws SQLException, ClassNotFoundException{
		ObservableList<Overdue> overduelist = FXCollections.observableArrayList();
		String sql = "SELECT * FROM overdue WHERE overdueIssueCode = ?";
		Connection conn = JdbcUtil.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, issueCode);
		ResultSet rs = pstmt.executeQuery();
		if (rs.next()) {
			String issuecode = rs.getString("overdueIssueCode");
			String issueuseremail = rs.getString("overdueIssueUserEmail");
			String issueusername = rs.getString("overdueIssueUserName");
			String issuedbooktitle = rs.getString("overdueIssuedBookTitle");
			String isbn = rs.getString("isbn");
			String phonenumber = rs.getString("overduePhoneNumber");
			Date returndate = rs.getDate("overdueReturnDate");
			Overdue overdue = new Overdue(issuecode, issueuseremail, issueusername, issuedbooktitle, isbn, phonenumber, returndate);
			overduelist.add(overdue);
		}
		return overduelist;
	}


	public Date searchReturnDate(String issueCode) throws SQLException, ClassNotFoundException{
		Date searchreturndate = null;
		String sql = "SELECT * FROM issuedbooksinfomation WHERE issueCode = ?";
		Connection conn = JdbcUtil.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, issueCode);
		ResultSet rs = pstmt.executeQuery();
		if (rs.next()) {
			searchreturndate = rs.getDate("returnDate");
		}
		return searchreturndate;
	}

	public boolean addOverdue(Overdue overdue) throws SQLException, ClassNotFoundException{
		String sql = "INSERT INTO overdue VALUES(?,?,?,?,?,?,?)";
		Connection conn = JdbcUtil.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, overdue.getIssueCode());
		pstmt.setString(2, overdue.getUserEmail());
		pstmt.setString(3, overdue.getUserName());
		pstmt.setString(4, overdue.getTitle());
		pstmt.setString(5, overdue.getIsbn());
		pstmt.setString(6, overdue.getPhoneNumber());
		pstmt.setDate(7, overdue.getReturnDate());
		if (pstmt.executeUpdate() > 0) {
			return true;
		}else {
			return false;
		}
	}

	public boolean overdueIssuedBookInfo(ObservableList<Overdue> overdueList) throws SQLException, ClassNotFoundException{
		String sql = "UPDATE issuedbooksinfomation SET returnDate = ? WHERE issueCode = ?";
		Connection conn = JdbcUtil.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		for (Overdue overduelist : overdueList) {
			pstmt.setDate(1, overduelist.getReturnDate());
			pstmt.setString(2, overduelist.getIssueCode());
		}
		if (pstmt.executeUpdate() > 0) {
			return true;
		}else {
			return false;
		}
	}
}
