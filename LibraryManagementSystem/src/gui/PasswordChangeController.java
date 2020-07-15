package gui;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import DataAccess.DatabaseHandler;
import classes.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class PasswordChangeController implements Initializable{

	@FXML
	private Label notification;
	@FXML
	private TextField emailField;
	@FXML
	private PasswordField passField;
	@FXML
	private PasswordField confirmpassField;
	@FXML	
	private Button passwordChange;

	static ObservableList<User> userInfoList = FXCollections.observableArrayList();

	@Override
	public void initialize(URL location, ResourceBundle resources) {	
		DatabaseHandler databaseHandler = new DatabaseHandler();
		try {
			userInfoList = databaseHandler.getAlluserInfo();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	@FXML
	private void passwordChangeAction(MouseEvent event) throws SQLException, ClassNotFoundException {
		String email = emailField.getText();
		String password = passField.getText();
		String confirmpass = confirmpassField.getText();

		if(!password.equals(confirmpass)){
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("비밀번호 오류");
			alert.setHeaderText(null);
			alert.setContentText("비밀번호가 일치하지 않습니다.");
			alert.showAndWait();
		}else{
			for(User user : userInfoList){
				if(emailField.getText().equals(user.getUserEmail())){
					User user2 = new User(email,password);
					DatabaseHandler dbAction = new DatabaseHandler();
					dbAction.updateuserInfo(user2);
					Alert alert = new Alert(Alert.AlertType.INFORMATION);
					alert.setTitle("비밀번호 변경 완료");
					alert.setHeaderText(null);
					alert.setContentText("비밀번호 변경이 완료되었습니다");
					alert.showAndWait();
					emailField.clear();
					confirmpassField.clear();
					passField.clear();
				}
			}
		}
	}
}
