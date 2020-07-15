package gui;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import DataAccess.DatabaseHandler;
import classes.BookInfo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class AdminAddBookController implements Initializable {

	@FXML
	private TextField bookTitle;
	@FXML
	private TextField bookID;
	@FXML
	private TextField bookAuthor;
	@FXML
	private TextField bookPublisher;
	@FXML
	private Button addbookButton;
	@FXML
	private TextField bookTotal;
	@FXML
	private TextField bookCategory;
	@FXML
	private TextField bookSubCategory;
	@FXML
	private TextField bookYear;

	static ObservableList<BookInfo> bookInfoList = FXCollections.observableArrayList();

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		DatabaseHandler dbAction = new DatabaseHandler();
		try {
			bookInfoList = dbAction.getAllbookInfo();
		} catch (SQLException | ClassNotFoundException ex) {
			Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	@FXML
	private void addbookbuttonAction(MouseEvent event) throws SQLException, ClassNotFoundException {
		DatabaseHandler dbAction = new DatabaseHandler();
		bookInfoList = dbAction.getAllbookInfo();
		if(bookTitle.getText().equals("") || bookID.getText().equals("") || bookAuthor.getText().equals("") ||
				bookPublisher.getText().equals("") || bookCategory.getText().equals("") || bookSubCategory.getText().equals("") ||
				bookYear.getText().equals("") || bookTotal.getText().equals("")) {
			Alert alert = new Alert(Alert.AlertType.WARNING);
			alert.setTitle("도서 등록 불가");
			alert.setHeaderText(null);
			alert.setContentText("모든 입력 부분을 입력하세요!!");
			alert.showAndWait();
			return;
		}else {
			String title = bookTitle.getText();
			String isbn = bookID.getText();
			String author = bookAuthor.getText();
			String publisher = bookPublisher.getText();
			String category = bookCategory.getText();
			String subcategory = bookSubCategory.getText();
			int year = Integer.parseInt(bookYear.getText());
			int total = Integer.parseInt(bookTotal.getText());
			
			for (BookInfo bookInfo : bookInfoList) {
				if (isbn.equals(bookInfo.getIsbn())) {
					Alert alert = new Alert(Alert.AlertType.WARNING);
					alert.setTitle("도서 등록 불가");
					alert.setHeaderText(null);
					alert.setContentText("존재하는 도서번호(ISBN)입니다.");
					alert.showAndWait();
					bookID.clear();
				}
			}
			BookInfo bookobj = new BookInfo(title,isbn,author,publisher,category,subcategory,year,total);
			dbAction = new DatabaseHandler();
			dbAction.addbookInfo(bookobj);
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("도서 등록");
			alert.setHeaderText(null);
			alert.setContentText("도서 등록 완료");
			alert.showAndWait();

			bookTitle.clear();
			bookID.clear();
			bookAuthor.clear();
			bookPublisher.clear();
			bookCategory.clear();
			bookSubCategory.clear();
			bookYear.clear();
			bookTotal.clear();
		}
	}
}