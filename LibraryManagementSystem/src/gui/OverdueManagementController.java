package gui;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import DataAccess.DatabaseHandler;
import classes.Overdue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class OverdueManagementController implements Initializable {

	@FXML
	private TableView<Overdue> overdueInfoTable;
	@FXML
	private TableColumn<Overdue, String> issueCodeColumn;
	@FXML
	private TableColumn<Overdue, String> issuedUserEmailColumn;
	@FXML
	private TableColumn<Overdue, String> issuedUserNameColumn;
	@FXML
	private TableColumn<Overdue, String> issuedBookTitleColumn;
	@FXML
	private TableColumn<Overdue, String> issuedBookIsbnColumn;
	@FXML
	private TableColumn<Overdue, String> phoneNumberColumn;
	@FXML
	private TableColumn<Overdue, Date> returnDateColumn;
	@FXML
	private TextField issuedCodeEditField;
	@FXML
	private TextField issuedUserEmailEditField;
	@FXML
	private TextField issuedUserNameEditField;
	@FXML
	private TextField issuedBookTitleEditField;
	@FXML
	private TextField issuedBookIsbnEditField;
	@FXML
	private DatePicker returnDateEditField;
	@FXML
	private TextField phoneNumberEditField;
	@FXML
	private Button updateOverdueBtn;
	@FXML
	private Button deleteOverdueBtn;

	ObservableList<Overdue> overdueList = FXCollections.observableArrayList();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		overdueInfoTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		DatabaseHandler dbAction = new DatabaseHandler();
		try {
			overdueList = dbAction.getAllOverdueInfo();
		} catch (SQLException ex) {
			Logger.getLogger(OverdueManagementController.class.getName()).log(Level.SEVERE, null, ex);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		issueCodeColumn.setCellValueFactory(new PropertyValueFactory<Overdue, String>("issueCode"));
		issuedUserEmailColumn.setCellValueFactory(new PropertyValueFactory<Overdue, String>("userEmail"));
		issuedUserNameColumn.setCellValueFactory(new PropertyValueFactory<Overdue, String>("userName"));
		issuedBookTitleColumn.setCellValueFactory(new PropertyValueFactory<Overdue, String>("title"));
		issuedBookIsbnColumn.setCellValueFactory(new PropertyValueFactory<Overdue, String>("isbn"));
		phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<Overdue, String>("phoneNumber"));
		returnDateColumn.setCellValueFactory(new PropertyValueFactory<Overdue, Date>("returnDate"));
		overdueInfoTable.setItems(overdueList);
	}

	@FXML
	private void overduedeleteAction(MouseEvent event)throws SQLException, ClassNotFoundException{
		overdueList = overdueInfoTable.getSelectionModel().getSelectedItems();
		overdueInfoTable.getItems().remove(overdueList);
		String isbn = "";
		String issuecode = "";
		for (Overdue overduelist : overdueList) {
			isbn = overduelist.getIsbn();
			issuecode = overduelist.getIssueCode();
		}
		DatabaseHandler databaseHandler = new DatabaseHandler();
		if (databaseHandler.removeissuedbooks(issuecode)) {
			if (databaseHandler.incrementCopies(isbn)) {
				if(databaseHandler.removeOverdue(issuecode)) {
					Alert alert = new Alert(Alert.AlertType.INFORMATION);
					alert.setTitle("연체 정보 삭제");
					alert.setHeaderText("연체 정보 삭제 완료");
					alert.setContentText("해당 대여코드의 연체 상태가 해제되었습니다.");
					alert.showAndWait();
				}	
			}else {
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setTitle("연체 정보 삭제");
				alert.setHeaderText("연체 정보 삭제 실패");
				alert.setContentText("연체 정보 삭제가 정상적으로 실행되지 않았습니다.");
				alert.showAndWait();
			}
		}
		
		DatabaseHandler handler = new DatabaseHandler();
		try {
			overdueList = handler.getAllOverdueInfo();
		} catch (SQLException ex) {
			Logger.getLogger(OverdueManagementController.class.getName()).log(Level.SEVERE, null, ex);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		issueCodeColumn.setCellValueFactory(new PropertyValueFactory<Overdue, String>("issueCode"));
		issuedUserEmailColumn.setCellValueFactory(new PropertyValueFactory<Overdue, String>("userEmail"));
		issuedUserNameColumn.setCellValueFactory(new PropertyValueFactory<Overdue, String>("userName"));
		issuedBookTitleColumn.setCellValueFactory(new PropertyValueFactory<Overdue, String>("title"));
		issuedBookIsbnColumn.setCellValueFactory(new PropertyValueFactory<Overdue, String>("isbn"));
		phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<Overdue, String>("phoneNumber"));
		returnDateColumn.setCellValueFactory(new PropertyValueFactory<Overdue, Date>("returnDate"));
		overdueInfoTable.setItems(overdueList);
	}
}
