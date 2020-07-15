package gui;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import DataAccess.DatabaseHandler;
import classes.IssuedBook;
import classes.Overdue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class OverdueController implements Initializable{
	@FXML
	private TextField overdueIssueCode;
	@FXML
	private TextField overdueIssueUserEmail;
	@FXML
	private TextField overdueIssueUserName;
	@FXML
	private TextField overdueIssuedBookTitle;
	@FXML
	private TextField overdueISBN;
	@FXML
	private TextField overduePhoneNumber;
	@FXML
	private DatePicker overdueReturndate;
	@FXML
	private Button overdueRequestBtn;
	@FXML
	private Button overdueRefreshBtn;
	
	static ObservableList<IssuedBook> issuedBookList = FXCollections.observableArrayList();
	static ObservableList<Overdue> overdueList = FXCollections.observableArrayList();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		overdueRefreshBtn.setDisable(true);
	}

	@FXML
	public void overdueAction(MouseEvent event)throws SQLException, ClassNotFoundException {
		if (overdueIssueCode.getText().equals("") || overdueIssueUserName.getText().equals("") ||
				overdueIssueUserEmail.getText().equals("") || overdueISBN.getText().equals("") || 
				overdueIssuedBookTitle.getText().equals("") || overduePhoneNumber.getText().equals("") ||
				overdueReturndate.getValue().equals(null)) {
			Alert alert = new Alert(Alert.AlertType.WARNING);
			alert.setTitle("연체 등록 오류");
			alert.setHeaderText("연체 등록 불가");
			alert.setContentText("모든 입력 부분을 입력하세요!!");
			alert.showAndWait();
			return;	
		}else {
			String overdueissuecode = overdueIssueCode.getText();
			String overdueissueuseremail = overdueIssueUserEmail.getText();
			String overdueissueusername = overdueIssueUserName.getText();
			String overdueissuebooktitle = overdueIssuedBookTitle.getText();
			String overdueisbn = overdueISBN.getText();
			String overduephonenumber = overduePhoneNumber.getText();
			LocalDate overdue_returndate = overdueReturndate.getValue();
			Date overduereturndate = Date.valueOf(overdue_returndate);
			
			DatabaseHandler dbAction = new DatabaseHandler();
			issuedBookList = dbAction.getSearchIssuedBooks(overdueissuecode);
			
			for (IssuedBook issuedBook : issuedBookList) {
				if (issuedBook.getIssueCode().equals(overdueissuecode)) {
					Overdue overdue = new Overdue(overdueissuecode, overdueissueuseremail, overdueissueusername, overdueissuebooktitle, overdueisbn, overduephonenumber, overduereturndate);
					if (dbAction.addOverdue(overdue)) {
						Alert alert = new Alert(Alert.AlertType.INFORMATION);
						alert.setTitle("연체 등록");
						alert.setHeaderText("연체 등록 완료");
						alert.setContentText("연체 등록이 완료되었습니다.");
						alert.showAndWait();
						overdueRefreshBtn.setDisable(false);
					}else {
						Alert alert = new Alert(Alert.AlertType.ERROR);
						alert.setTitle("연체 등록");
						alert.setHeaderText("연체 등록 실패");
						alert.setContentText("연체 등록에 실패하였습니다.");
						alert.showAndWait();
					}
				}else {
					Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.setTitle("연체 등록");
					alert.setHeaderText("연체 등록 실패");
					alert.setContentText("해당 대여코드가 존재하지 않습니다.");
					alert.showAndWait();
				}
			}			
		}
	}
	
	@FXML
	public void overdueApply(MouseEvent event)throws SQLException, ClassNotFoundException{
		DatabaseHandler dbHandler = new DatabaseHandler();
		String issudCode = overdueIssueCode.getText();
		overdueList = dbHandler.searchOverdue(issudCode);
		if(dbHandler.overdueIssuedBookInfo(overdueList)) {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("도서 연체 신청");
			alert.setHeaderText("도서 연체 신청 완료");
			alert.setContentText("도서 대여 연체 상태로 변경되었습니다.\t\n해당 연체일까지 꼭 반납 신청을 하시기 바랍니다.");
			alert.showAndWait();
			overdueIssueCode.clear();
			overdueIssueUserEmail.clear();
			overdueIssueUserName.clear();
			overdueISBN.clear();
			overdueIssuedBookTitle.clear();
			overduePhoneNumber.clear();
			overdueReturndate.setValue(null);
		} else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("연체 상태 변경 오류");
			alert.setHeaderText("연체 상태 변경 불가");
			alert.setContentText("연체 상태 변경이 불가합니다.");
			alert.showAndWait();
			return;
		}
	}
}
