package gui;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import DataAccess.DatabaseHandler;
import classes.BookInfo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class MainPanelController {
	
	@FXML
	private AnchorPane mainPane;
	
	@FXML
	private TableView<BookInfo> tblBooksData;

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Button btnBookList;

	@FXML
	private Button btnIssueBooks;

	@FXML
	private Button btnIssuedBooksList;

	@FXML
	private Button btnReturnBooks;

	@FXML
	private Button btnOverdue;
	
	@FXML
	private Button BtnRefreshOverdue;

	@FXML
	private Button btnLogout;

	@FXML
	private Button btnPasswordChange;

	@FXML
	private GridPane pnPersonalInfo;

	@FXML
	private Pane pnlStatus;

	@FXML
	private Label lblMiniStatus;

	@FXML
	private Label lblStatus;

	@FXML
	private ImageView btnClose;

	@FXML
	private VBox personalInfoContent;

	@FXML
	private VBox VboxSections;

	@FXML
	private AnchorPane AnchorPane;

	static ObservableList<BookInfo> bookInfoList = FXCollections.observableArrayList();
	static ObservableList<BookInfo> bookInfoListtwo = FXCollections.observableArrayList();

	DatabaseHandler dbAction = new DatabaseHandler();
	@FXML
	public void initialize(){
		try {
			bookInfoList = dbAction.getAllbookInfo();
			Parent pane = FXMLLoader.load(getClass().getResource("UserAllBooks.fxml"));
			personalInfoContent.getChildren().setAll(pane);
		} catch (SQLException | ClassNotFoundException | IOException ex) {
			Logger.getLogger(UserAllBooksController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	@FXML
	public void handleClicks(ActionEvent event) throws IOException, ClassNotFoundException, SQLException, InvocationTargetException {
		if (event.getSource() == btnBookList) {
			Parent pane = FXMLLoader.load(getClass().getResource("UserAllBooks.fxml"));
			personalInfoContent.getChildren().setAll(pane);
		} else if (event.getSource() == btnIssueBooks) {			
			Parent pane = FXMLLoader.load(getClass().getResource("ToIssueBook.fxml"));
			personalInfoContent.getChildren().setAll(pane);
		} else if (event.getSource() == btnIssuedBooksList) {
			Parent pane = FXMLLoader.load(getClass().getResource("UserAllIssuedBooks.fxml"));
			personalInfoContent.getChildren().setAll(pane);
		} else if (event.getSource() == btnReturnBooks) {
			try {
				Parent pane = FXMLLoader.load(getClass().getResource("ReturnBook.fxml"));
				personalInfoContent.getChildren().setAll(pane);
			} catch (IOException ex) {
				System.err.println(ex.getMessage());
			}
		} else if(event.getSource() == btnOverdue) {
			Parent pane = FXMLLoader.load(getClass().getResource("UserAddOverdue.fxml"));
			personalInfoContent.getChildren().setAll(pane);
		} else if(event.getSource() == BtnRefreshOverdue) {
			DatabaseHandler databaseHandler = new DatabaseHandler();
		} else if (event.getSource() == btnLogout) {
			try {
				Parent pane = FXMLLoader.load(getClass().getResource("SignIn.fxml"));
				mainPane.getChildren().setAll(pane);
			} catch (IOException ex) {
				System.err.println(ex.getMessage());
			}
		} else if (event.getSource() == btnPasswordChange) {
			Parent pane = FXMLLoader.load(getClass().getResource("PasswordChange.fxml"));
			personalInfoContent.getChildren().setAll(pane);
		}
	}

	@FXML
	void handleClose(MouseEvent event) {
		if (event.getSource() == btnClose) {
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
		}
	}
}


