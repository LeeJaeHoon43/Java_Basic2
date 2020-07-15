package gui;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import DataAccess.DatabaseHandler;
import classes.BookInfo;
import classes.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class ShowAllUserController implements Initializable{

	@FXML
	private TableView<User> userInfoTable;
	@FXML
	private TableColumn<User, String> usernameColumn;
	@FXML
	private TableColumn<User, String> useremailColumn;
	@FXML
	private TableColumn<User, String> usergenderColumn;
	@FXML
	private TableColumn<User, String> phonenumberColumn;
	@FXML
	private TableColumn<User, String> addressColumn;
	@FXML
	private TextField userNameEditField;
	@FXML
	private TextField userEmailEditField;
	@FXML
	private TextField userGenderEditField;
	@FXML
	private TextField phoneNumberEditField;
	@FXML
	private TextField addressEditField;
	@FXML
	private Button updateUserBtn;
	@FXML
	private Button removeUserBtn;
	
	ObservableList<User> userInfoList = FXCollections.observableArrayList();
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		userInfoTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        DatabaseHandler dbAction = new DatabaseHandler();
        try {
            userInfoList = dbAction.getAlluserInfo();
        } catch (SQLException ex) {
            Logger.getLogger(ShowAllUserController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }   
        usernameColumn.setCellValueFactory(new PropertyValueFactory<User, String>("userName"));
        useremailColumn.setCellValueFactory(new PropertyValueFactory<User, String>("userEmail"));
        usergenderColumn.setCellValueFactory(new PropertyValueFactory<User, String>("gender"));
        phonenumberColumn.setCellValueFactory(new PropertyValueFactory<User, String>("phoneNumber"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<User, String>("address"));
        userInfoTable.setItems(userInfoList);
	}
	
	@FXML
	private void updateButtonAction(MouseEvent event) throws SQLException, ClassNotFoundException{
		if (userNameEditField.getText().equals("") || userGenderEditField.getText().equals("") || 
				phoneNumberEditField.getText().equals("") || addressEditField.getText().equals("")) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("경고");
            alert.setContentText("모든 변경 사항을 입력하세요");
            alert.showAndWait();
            return;
		}else {
			userInfoList = userInfoTable.getSelectionModel().getSelectedItems();
			String userEmail = "";
			for (User user : userInfoList) {
				userEmail = user.getUserEmail();
			}
			String userName = userNameEditField.getText();
			String gender = userGenderEditField.getText();
			String phoneNumber = phoneNumberEditField.getText();
			String address = addressEditField.getText();
			
			User user = new User(userName, gender, phoneNumber, address);
			DatabaseHandler databaseHandler = new DatabaseHandler();
			
			if (databaseHandler.updateUserInfo(user, userEmail)) {
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
	            alert.setHeaderText("회원 정보 수정");
	            alert.setContentText("회원 정보 수정이 완료되었습니다");
	            alert.showAndWait(); 
	            userNameEditField.clear();
	            userGenderEditField.clear();
	            phoneNumberEditField.clear();
	            addressEditField.clear();
			}else {
				Alert alert = new Alert(Alert.AlertType.ERROR);
	            alert.setHeaderText("회원 정보 수정");
	            alert.setContentText("회원 정보 수정 실패!!");
	            alert.showAndWait(); 
			}
			DatabaseHandler dbAction = new DatabaseHandler();
	        try {
	            userInfoList = dbAction.getAlluserInfo();
	        } catch (SQLException ex) {
	            Logger.getLogger(ShowAllUserController.class.getName()).log(Level.SEVERE, null, ex);
	        } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	        }   
	        usernameColumn.setCellValueFactory(new PropertyValueFactory<User, String>("userName"));
	        useremailColumn.setCellValueFactory(new PropertyValueFactory<User, String>("userEmail"));
	        usergenderColumn.setCellValueFactory(new PropertyValueFactory<User, String>("gender"));
	        phonenumberColumn.setCellValueFactory(new PropertyValueFactory<User, String>("phoneNumber"));
	        addressColumn.setCellValueFactory(new PropertyValueFactory<User, String>("address"));
	        userInfoTable.setItems(userInfoList);
		}
	}
	@FXML
	private void deleteButtonAction(MouseEvent event) throws SQLException, ClassNotFoundException{
        userInfoList = userInfoTable.getSelectionModel().getSelectedItems();
        userInfoTable.getItems().remove(userInfoList);
        DatabaseHandler databaseHandler = new DatabaseHandler();
        if (databaseHandler.deleteUserInfo(userInfoList)) {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("회원 정보 삭제");
            alert.setContentText("회원 정보 삭제가 완료되었습니다");
            alert.showAndWait(); 
		}else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("회원 정보 삭제");
            alert.setContentText("회원 정보 삭제 실패!!");
            alert.showAndWait(); 
		}
        DatabaseHandler dbAction = new DatabaseHandler();
        try {
            userInfoList = dbAction.getAlluserInfo();
        } catch (SQLException ex) {
            Logger.getLogger(ShowAllUserController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }   
        usernameColumn.setCellValueFactory(new PropertyValueFactory<User, String>("userName"));
        useremailColumn.setCellValueFactory(new PropertyValueFactory<User, String>("userEmail"));
        usergenderColumn.setCellValueFactory(new PropertyValueFactory<User, String>("gender"));
        phonenumberColumn.setCellValueFactory(new PropertyValueFactory<User, String>("phoneNumber"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<User, String>("address"));
        userInfoTable.setItems(userInfoList);
	}
}
