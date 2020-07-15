package gui;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import DataAccess.DatabaseHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class ReturnBookController implements Initializable {

	@FXML
	private TextField issuedUser;
	@FXML
	private TextField isbn;
	@FXML
	private TextField issuedTitle;
	@FXML
	private TextField issuedcode;

	DatabaseHandler databaseHandler = new DatabaseHandler();

	@Override
	public void initialize(URL url, ResourceBundle rb) {
	}

	@FXML
	private void returnAction(MouseEvent event) throws SQLException, ClassNotFoundException {
		if (issuedUser.getText().equals("") || isbn.getText().equals("") || 
				issuedcode.getText().equals("") || issuedTitle.getText().equals("")) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("도서 반납 오류");
			alert.setHeaderText("도서 반납 불가");
			alert.setContentText("모든 입력 부분을 입력하세요!!");
			alert.showAndWait();
		}else {
			String issuecode = issuedcode.getText();
			String keyword = isbn.getText();
			if(databaseHandler.removeissuedbooks(issuecode)) {
				if (databaseHandler.incrementCopies(keyword)) {
					if (databaseHandler.removeOverdue(issuecode)) {
						Alert alert = new Alert(Alert.AlertType.INFORMATION);
						alert.setTitle("도서 반납");
						alert.setHeaderText("도서 반납 완료");
						alert.setContentText("도서 반납 처리가 완료되었습니다.");
						alert.showAndWait();
						issuedUser.clear();
						isbn.clear();
						issuedTitle.clear();
						issuedcode.clear();
					}else {
						Alert alert = new Alert(Alert.AlertType.ERROR);
						alert.setTitle("에러!");
						alert.setHeaderText("도서 반납 불가");
						alert.setContentText("존재하지 않는 도서번호입니다.");
						alert.showAndWait();
					}
				}else {
					Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.setTitle("에러!");
					alert.setHeaderText("도서 반납 불가");
					alert.setContentText("존재하지 않는 도서번호입니다.");
					alert.showAndWait();
				}
			}else {
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setTitle("도서 반납");
				alert.setHeaderText("도서 반납 불가");
				alert.setContentText("존재하지 않는 대여코드입니다.");
				alert.showAndWait();
			}
		}
	}
}
