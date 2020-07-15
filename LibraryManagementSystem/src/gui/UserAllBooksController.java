package gui;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import DataAccess.DatabaseHandler;
import DataAccess.JdbcUtil;
import classes.BookInfo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class UserAllBooksController implements Initializable {

	@FXML
	private TableColumn<BookInfo, String> titleColumn;
	@FXML
	private TableColumn<BookInfo, String> isbnColumn;
	@FXML
	private TableColumn<BookInfo, String> authorColumn;
	@FXML
	private TableColumn<BookInfo, String> publisherColumn;
	@FXML
	private TableColumn<BookInfo, Integer> totalColumn;
	@FXML
	private TableView<BookInfo> bookInfoTable;

	@FXML
	private RadioButton rbTitle;

	@FXML
	private RadioButton rbAuthor;

	@FXML
	private TextField searchField;

	@FXML
	private Button searchButton;

	@FXML
	private Button refreshButton;

	static ObservableList<BookInfo> bookInfoList = FXCollections.observableArrayList();
	static ObservableList<BookInfo> bookInfoListTwo = FXCollections.observableArrayList();

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		bookInfoTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		DatabaseHandler dbAction = new DatabaseHandler();
		try {
			bookInfoList= dbAction.getAllbookInfo();
		} catch (SQLException | ClassNotFoundException ex) {
			Logger.getLogger(UserAllBooksController.class.getName()).log(Level.SEVERE, null, ex);
		}
		titleColumn.setCellValueFactory(new PropertyValueFactory<BookInfo, String>("title"));
		isbnColumn.setCellValueFactory(new PropertyValueFactory<BookInfo,String>("isbn"));
		authorColumn.setCellValueFactory(new PropertyValueFactory<BookInfo,String>("author"));
		publisherColumn.setCellValueFactory(new PropertyValueFactory<BookInfo,String>("publisher"));
		totalColumn.setCellValueFactory(new PropertyValueFactory<BookInfo,Integer>("total"));
		bookInfoTable.setItems(bookInfoList);
	}
	@FXML
	public void radioButtonSelect(ActionEvent actionEvent) {
		if(rbAuthor.isSelected()){
		}
	}

	@FXML
	public void refreshAction(MouseEvent event) {
		bookInfoTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		DatabaseHandler dbAction = new DatabaseHandler();
		try {
			bookInfoList = dbAction.getAllbookInfo();
		} catch (SQLException ex) {
			Logger.getLogger(UserAllBooksController.class.getName()).log(Level.SEVERE, null, ex);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		titleColumn.setCellValueFactory(new PropertyValueFactory<BookInfo, String>("title"));
		isbnColumn.setCellValueFactory(new PropertyValueFactory<BookInfo,String>("isbn"));
		authorColumn.setCellValueFactory(new PropertyValueFactory<BookInfo,String>("author"));
		publisherColumn.setCellValueFactory(new PropertyValueFactory<BookInfo,String>("publisher"));
		totalColumn.setCellValueFactory(new PropertyValueFactory<BookInfo,Integer>("total"));
		bookInfoTable.setItems(bookInfoList);
	}

	@FXML
	private void searchAction(MouseEvent event) throws SQLException {
		bookInfoListTwo.clear();
		if (searchField.getText().equals("")) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setContentText("검색어를 입력하세요!!");
			alert.showAndWait();
		}
		Connection conn = JdbcUtil.getConnection();  
		if(rbAuthor.isSelected()){
			String keyword1 = "%" + searchField.getText() + "%";
			String sql = "SELECT * FROM bookinfomation WHERE author LIKE ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, keyword1);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				String title = rs.getString("title");
				String isbn = rs.getString("isbn");
				String author = rs.getString("author");
				String publisher = rs.getString("publisher");
				int total = rs.getInt("total");
				BookInfo bookinfo = new BookInfo(title,isbn,author,publisher,total);
				bookInfoListTwo.add(bookinfo);
				bookInfoTable.setItems(bookInfoListTwo);
			}
		}

		if(rbTitle.isSelected()){
			String keyword2 = "%" + searchField.getText() + "%";
			String sql2 = "SELECT * FROM bookinfomation WHERE title LIKE ?";
			PreparedStatement pstmt1 = conn.prepareStatement(sql2);
			pstmt1.setString(1, keyword2);
			ResultSet rs = pstmt1.executeQuery();
			while(rs.next()) {
				String title = rs.getString("title");
				String isbn = rs.getString("isbn");
				String author = rs.getString("author");
				String publisher = rs.getString("publisher");
				int total = rs.getInt("total");
				BookInfo bookinfo = new BookInfo(title,isbn,author,publisher,total);
				bookInfoListTwo.add(bookinfo);
				bookInfoTable.setItems(bookInfoListTwo);
			}
		}
	}
}
