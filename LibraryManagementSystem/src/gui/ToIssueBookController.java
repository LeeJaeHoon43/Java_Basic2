package gui;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import DataAccess.DatabaseHandler;
import DataAccess.JdbcUtil;
import classes.BookInfo;
import classes.IssuedBook;
import classes.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class ToIssueBookController implements Initializable {

	@FXML
	private Button issueCodeBtn;
	@FXML
	private Label issueCodeLabel;
	@FXML
	private Button issuebooks;
	@FXML
	private TextField issueCode;
	@FXML
	private TextField issueUserName;
	@FXML
	private TextField issueUserEmail;
	@FXML
	private TextField issuedBookTitle;
	@FXML
	private TextField ISBN;
	@FXML
	private TextField phoneNumber;
	@FXML
	private DatePicker issuedate;
	@FXML
	private DatePicker returndate;

	static ObservableList<User> userInfoList = FXCollections.observableArrayList();

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		issuedate.setValue(LocalDate.now());
	}

	@FXML
	private void issueCode(MouseEvent event) {
		issueCodeBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				int[] numbers = new int[6];
				for(int i = 0; i < numbers.length ; i++){
				    numbers[i] = (int)(Math.random() * 9) + 1;	 
				    // 2. 중복 검사
				    // 배열의 기존 원소(insertCur 이전까지)를
				    // 방금 삽입한 수와 비교해 같은 수가 있다면 insertCur를 앞으로 밀어
				    // 다음 반복에서 같은 칸에 다른 수를 쓰도록 합니다.
				    for(int j = 0; j < i; j ++){
				        if(numbers[i] == numbers[j]){
				            i--; // insertCur를 앞으로 민다
				            break; // 다음 것을 검색할 필요가 없으므로 중복검사 반복을 나갑니다.
				        }
				    }
				}
				String code = "";
				for (int issuecode : numbers) {
					code += (Integer.toString(issuecode));
				}
				issueCodeLabel.setText(code); 
			} 
		});
	}

	@FXML
	private void issueAction(MouseEvent event) throws SQLException, ClassNotFoundException {
		if (issueCode.getText().equals("") || issueUserName.getText().equals("") || issueUserName.getText().equals("") ||
				issuedBookTitle.getText().equals("") || ISBN.getText().equals("") || 
				phoneNumber.getText().equals("")) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("도서 대여 오류");
			alert.setHeaderText("도서 대여 불가");
			alert.setContentText("모든 입력 부분을 입력하세요!!");
			alert.showAndWait();
		}
		LocalDate issueDate = issuedate.getValue();
		LocalDate returnDate = returndate.getValue();
		Date issuedDate = Date.valueOf(issueDate);
		Date returnedDate = Date.valueOf(returnDate);

		DatabaseHandler databaseHandler = new DatabaseHandler();
		userInfoList = databaseHandler.getAlluserInfo();

		String userName = "";
		String userEmail = "";
		String phoneNumber = "";
		for (User user : userInfoList) {
			if (user.getUserEmail().equals(issueUserEmail.getText())) {
				userName = issueUserName.getText();
				userEmail = issueUserEmail.getText();
				phoneNumber = this.phoneNumber.getText();
			}
		}

		String keyword2 = "%" + ISBN.getText() + "%";
		String sql = "SELECT * FROM bookinfomation WHERE ISBN LIKE ?";
		Connection conn = JdbcUtil.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, keyword2);
		ResultSet rs2 = pstmt.executeQuery();
		if(rs2.next()){
			String title = rs2.getString("title");
			String isbn = rs2.getString("isbn");
			String author = rs2.getString("author");
			String publisher = rs2.getString("publisher");
			String categories = rs2.getString("categories");
			String subcategories = rs2.getString("subCategories");
			int year = rs2.getInt("year");
			int total = rs2.getInt("total");

			IssuedBook bookobj = new IssuedBook(issueCode.getText(),userName, userEmail, title, isbn, phoneNumber, issuedDate, returnedDate);
			BookInfo bookinfo = new BookInfo(title,isbn,author,publisher,categories, subcategories, year, total);
			DatabaseHandler dbAction = new DatabaseHandler();
			dbAction.addissuedbooks(bookobj);
			DatabaseHandler dbAction2 = new DatabaseHandler();
			dbAction2.decrementCopies(bookinfo);

			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("도서 대여");
			alert.setHeaderText("도서 대여 완료");
			alert.setContentText("도서 대여가 완료되었습니다.");
			alert.showAndWait();
		}else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("도서 대여");
			alert.setHeaderText("도서 대여 불가");
			alert.setContentText("존재하지 않는 도서번호입니다.");
			alert.showAndWait();
		}
		
		issueCodeLabel.setText("");
		issueCode.clear();
		issueUserName.clear();
		issueUserEmail.clear();
		issueUserName.clear();
		issuedBookTitle.clear();
		ISBN.clear();
		this.phoneNumber.clear();
		issuedate.setValue(null);
		returndate.setValue(null);
	}
}