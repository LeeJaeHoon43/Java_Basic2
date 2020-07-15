package gui;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import DataAccess.DatabaseHandler;
import classes.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class SignUpController {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private PasswordField txtPassword;

	@FXML
	private PasswordField txtConfirmPassword;

	@FXML
	private Button btnSignUp;

	@FXML
	private Label lblErrors;

	@FXML
	private TextField txtUserName;

	@FXML
	private TextField txtEmail;

	@FXML
	private TextField txtPhoneNumber;

	@FXML
	private TextField txtAddress;

	@FXML
	private RadioButton radioBtnMale;

	@FXML
	private RadioButton radioBtnFemale;

	@FXML
	private Button btnSignIn;

	static ObservableList<User> useraccountsList = FXCollections.observableArrayList();
	
	@FXML
	void handleSignUpButtonAction(ActionEvent event) throws Exception {
	}

	@FXML
	void initialize() {
		btnSignUp.setOnAction(event -> {
			try {
				signUpNewUser();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		});
		btnSignIn.setOnAction(event -> {
			try{
				Parent tableViewParent = FXMLLoader.load(getClass().getResource("/gui/SignIn.fxml"));
				Scene tableViewScene = new Scene(tableViewParent);
				Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
				window.setScene(tableViewScene);
				window.show();
			}
			catch (Exception ex){
				ex.printStackTrace();
			}
		});
	}

	private void signUpNewUser() throws SQLException, ClassNotFoundException {
		DatabaseHandler databaseHandler = new DatabaseHandler();
		String userName = txtUserName.getText();
		String phoneNumber = txtPhoneNumber.getText();
		String address = txtAddress.getText();
		String email = txtEmail.getText();
		String password = txtPassword.getText();
		String confirmPassword = txtConfirmPassword.getText();
		String gender = "";

		if(radioBtnMale.isSelected())
			gender = "남자";
		if(radioBtnFemale.isSelected())
			gender = "여자";

		if (txtUserName.getText().equals("") || txtPhoneNumber.getText().equals("") || txtEmail.getText().equals("") ||
				txtPassword.getText().equals("") || txtConfirmPassword.getText().equals("")){
			Alert alert = new Alert(Alert.AlertType.WARNING);
			alert.setTitle("회원가입 오류");
			alert.setHeaderText(null);
			alert.setContentText("모든 입력 부분을 입력하세요!!");
			alert.showAndWait();
			return;
		}
		useraccountsList = databaseHandler.getAlluserInfo();
		for (User user : useraccountsList) {
			if (txtEmail.getText().equals(user.getUserEmail())) {
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setTitle("이메일 중복");
				alert.setHeaderText(null);
				alert.setContentText("이미 존재하는 이메일입니다.");
				alert.showAndWait();
			}
		}

		if (password.equals(confirmPassword)) {
			User user = new User(userName, email, password, gender, phoneNumber, address);
			databaseHandler.signUpUser(user);
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("회원가입 성공");
			alert.setHeaderText(null);
			alert.setContentText("회원가입 완료");
			alert.showAndWait();

			txtUserName.clear();
			txtPhoneNumber.clear();
			txtAddress.clear();
			radioBtnMale.setSelected(false);
			radioBtnFemale.setSelected(false);
			txtEmail.clear();
			txtPassword.clear();
			txtConfirmPassword.clear();
			lblErrors.setText("");
		}else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("비밀번호 오류");
			alert.setHeaderText(null);
			alert.setContentText("비밀번호가 일치하지 않습니다.");
			alert.showAndWait();
			return;
		}
	}
}
