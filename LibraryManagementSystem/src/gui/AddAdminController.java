package gui;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import DataAccess.DatabaseHandler;
import classes.Admin;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class AddAdminController implements Initializable {

	@FXML
	private TextField adminEmail;
	@FXML
	private TextField adminPassword;
	@FXML
	private Button addAdminBtn;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}
	
	@FXML
	private void addAdminAction(MouseEvent event) throws SQLException, ClassNotFoundException{
		DatabaseHandler databaseHandler = new DatabaseHandler();
		String adminemail = adminEmail.getText();
		String adminpassword = adminPassword.getText();
		Admin admin = new Admin(adminemail, adminpassword);
		if(databaseHandler.addAdminInfo(admin)) {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("관리자 계정 등록");
			alert.setHeaderText(null);
			alert.setContentText("관리자 계정 등록이 완료되었습니다.");
			alert.showAndWait();
			adminEmail.clear();
			adminPassword.clear();
		}else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("관리자 계정 등록 실패");
			alert.setHeaderText(null);
			alert.setContentText("관리자 계정 등록에 실패하였습니다");
			alert.showAndWait();
		}
	}
}
