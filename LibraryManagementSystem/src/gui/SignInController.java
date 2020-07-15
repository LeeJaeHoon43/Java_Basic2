package gui;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import DataAccess.DatabaseHandler;
import animations.Shake;
import classes.Admin;
import classes.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class SignInController {

	@FXML
	private AnchorPane mainPane;

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Pane paneInformation;

	@FXML
	private TextField txtEmail;

	@FXML
	private PasswordField txtPassword;

	@FXML
	private Button btnSignIn;

	@FXML
	private Button btnSignUp;

	@FXML
	private Label lblErrors;

	@FXML
	private Button btnExit;

	static ObservableList<User> userInfoList = FXCollections.observableArrayList();
	static ObservableList<Admin> adminInfoList = FXCollections.observableArrayList();

	@FXML
	public void initialize() throws ClassNotFoundException {
		DatabaseHandler databaseHandler = new DatabaseHandler();
		try {
			userInfoList = databaseHandler.getAlluserInfo();
		} catch (SQLException ex) {
			Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
		}
		try {
			adminInfoList = databaseHandler.getAlladminInfo();
		} catch (SQLException ex) {
			Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
		}

		btnSignIn.setOnAction(event -> {
			String emailText = txtEmail.getText().trim(); 
			String passwordText = txtPassword.getText().trim(); 

			if (!emailText.equals("") && !passwordText.equals("")) {
				try {
					loginUser(emailText, passwordText);
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setTitle("로그인 오류");
				alert.setHeaderText(null);
				alert.setContentText("이메일과 비밀번호를 입력하세요");
				alert.showAndWait();
			}
		});

		btnSignUp.setOnAction(event -> {
			try{
				Parent tableViewParent = FXMLLoader.load(getClass().getResource("/gui/SignUp.fxml"));
				Scene tableViewScene = new Scene(tableViewParent);
				Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
				window.setScene(tableViewScene);
				window.show();
			}
			catch (Exception ex){
				ex.printStackTrace();
			}
		});
		btnExit.setOnAction(event -> {
			System.exit(0);
		});
	}

	private void loginUser(String emailText, String passwordText) throws IOException {
		DatabaseHandler dbHandler = new DatabaseHandler();
		User user = new User();
		user.setUserEmail(emailText);
		user.setPassword(passwordText);
		Admin admin = new Admin();
		admin.setEmail(emailText);
		admin.setPassword(passwordText);
		ResultSet userResultSet = dbHandler.getUser(user); 
		ResultSet adminResultSet = dbHandler.getAdmin(admin);
		
		if (txtEmail.getText().equals("admin")) {
			int adminCounter = 0;
			while(true){
				try {
					if (!adminResultSet.next()) break;
				} catch (SQLException e) {
					e.printStackTrace();
				}
				adminCounter++;
			}
			if(adminCounter >= 1){
				Parent pane = FXMLLoader.load(getClass().getResource("/gui/MainAdmin.fxml"));
				mainPane.getChildren().setAll(pane);
			}else if (adminCounter == 0){
				Shake paneAnimation = new Shake(paneInformation);
				paneAnimation.playAnimation();
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setTitle("로그인 오류");
				alert.setHeaderText(null);
				alert.setContentText("잘못된 이메일 또는 비밀번호입니다");
				alert.showAndWait();
			}
		}else {
			int userCounter = 0;
			while(true){
				try {
					if (!userResultSet.next()) break;
				} catch (SQLException e) {
					e.printStackTrace();
				}
				userCounter++;
			}

			if(userCounter > 0){
				Parent pane = FXMLLoader.load(getClass().getResource("/gui/MainPanel.fxml"));
				mainPane.getChildren().setAll(pane);
			}else if (userCounter == 0){
				Shake paneAnimation = new Shake(paneInformation);
				paneAnimation.playAnimation();
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setTitle("로그인 오류");
				alert.setHeaderText(null);
				alert.setContentText("잘못된 이메일 또는 비밀번호입니다");
				alert.showAndWait();
			}
		}
	}
}