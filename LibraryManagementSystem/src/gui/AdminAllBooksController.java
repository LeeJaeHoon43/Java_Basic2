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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class AdminAllBooksController implements Initializable {

    @FXML
    private TableColumn<BookInfo, String> titleColumn;
    @FXML
    private TableColumn<BookInfo, String> isbnColumn;
    @FXML
    private TableColumn<BookInfo, String> authorColumn;
    @FXML
    private TableColumn<BookInfo, String> publisherColumn;
    @FXML
    private TableColumn<BookInfo, String> categoriesColumn;
    @FXML
    private TableColumn<BookInfo, String> subcategoriesColumn;
    @FXML
    private TableColumn<BookInfo, Integer> yearColumn;
    @FXML
    private TableColumn<BookInfo, Integer> totalColumn;
    @FXML
    private TableView<BookInfo> bookInfoTable;
    @FXML
    private TextField titleEditField;
    @FXML
    private TextField AuthorEditField;
    @FXML
    private TextField PublisherEditField;
    @FXML
    private TextField CategoriesEditField;
    @FXML
    private TextField SubCategoriesEditField;
    @FXML
    private TextField YearEditField;
    @FXML
    private TextField TotalEditField;
    @FXML
    private TextField ISBNEditField;
    
    static ObservableList<BookInfo> bookInfoList = FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        bookInfoTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        DatabaseHandler dbAction = new DatabaseHandler();
        try {
            bookInfoList = dbAction.getAllbookInfo();
        } catch (SQLException ex) {
            Logger.getLogger(AdminAllBooksController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        titleColumn.setCellValueFactory(new PropertyValueFactory<BookInfo, String>("title"));
        isbnColumn.setCellValueFactory(new PropertyValueFactory<BookInfo,String>("isbn"));
        authorColumn.setCellValueFactory(new PropertyValueFactory<BookInfo,String>("author"));
        publisherColumn.setCellValueFactory(new PropertyValueFactory<BookInfo,String>("publisher"));
        categoriesColumn.setCellValueFactory(new PropertyValueFactory<BookInfo, String>("categories"));
        subcategoriesColumn.setCellValueFactory(new PropertyValueFactory<BookInfo, String>("subcategories"));
        yearColumn.setCellValueFactory(new PropertyValueFactory<BookInfo,Integer>("year"));
        totalColumn.setCellValueFactory(new PropertyValueFactory<BookInfo,Integer>("total"));

        bookInfoTable.setItems(bookInfoList);
    }

    @FXML
    private void updateButtonAction(MouseEvent event) throws SQLException, ClassNotFoundException {
        if(titleEditField.getText().equals("") || ISBNEditField.getText().equals("") || AuthorEditField.getText().equals("") ||
                PublisherEditField.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("모든 변경 사항을 입력하세요");
            alert.showAndWait();
            return;
        }

        String newTitle = titleEditField.getText();
        String bookisbn = ISBNEditField.getText();
        String newAuthor = AuthorEditField.getText();
        String newPublisher = PublisherEditField.getText();
        String newCategories = CategoriesEditField.getText();
        String newSubCategories = SubCategoriesEditField.getText();
        int newYear = Integer.parseInt(YearEditField.getText());
        int newTotal = Integer.parseInt(TotalEditField.getText());

        BookInfo bookinfoobj = new BookInfo(newTitle, bookisbn, newAuthor, newPublisher, newCategories, newSubCategories, newYear, newTotal);
        DatabaseHandler dbAction = new DatabaseHandler();
        dbAction.updatebookInfo(bookinfoobj);

        titleEditField.clear();
        ISBNEditField.clear();
        AuthorEditField.clear();
        PublisherEditField.clear();
        CategoriesEditField.clear();
        SubCategoriesEditField.clear();
        YearEditField.clear();
        TotalEditField.clear();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText("도서 정보 수정 완료");
        alert.showAndWait();

        try{
            Parent tableViewParent = FXMLLoader.load(getClass().getResource("/gui/MainAdmin.fxml"));
            Scene tableViewScene = new Scene(tableViewParent);
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(tableViewScene);
            window.show();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }

    @FXML
    private void deleteButtonAction(MouseEvent event) throws SQLException, ClassNotFoundException {
        ObservableList<BookInfo> selectedBooks = FXCollections.observableArrayList();
        selectedBooks = bookInfoTable.getSelectionModel().getSelectedItems();
        AdminAddBookController.bookInfoList.removeAll(selectedBooks);
        DatabaseHandler dbAction = new DatabaseHandler();
        dbAction.deletebooks(selectedBooks);

        try{
            Parent tableViewParent = FXMLLoader.load(getClass().getResource("/gui/MainAdmin.fxml"));
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
