package gui;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import DataAccess.DatabaseHandler;
import classes.IssuedBook;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class UserAllIssuedBooksController implements Initializable {

    @FXML
    private TableColumn<IssuedBook, String> issueUserColumn;
    @FXML
    private TableColumn<IssuedBook, String> issueUserEmailColumn;
    @FXML
    private TableColumn<IssuedBook, String> titleColumn;
    @FXML
    private TableColumn<IssuedBook, String> ISBNColumn;
    @FXML
    private TableColumn<IssuedBook, String> phoneNumberColumn;
    @FXML
    private TableColumn<IssuedBook, Date> issuedDateColumn;
    @FXML
    private TableColumn<IssuedBook, Date> returnDateColumn;
    @FXML
    private TableView<IssuedBook> issuedBookstable;

    ObservableList<IssuedBook> issuedBookList = FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        issuedBookstable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        DatabaseHandler dbAction = new DatabaseHandler();
        try {
            issuedBookList = dbAction.getAllissuedbooks();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(AdminAllBooksController.class.getName()).log(Level.SEVERE, null, ex);
        }
        issueUserColumn.setCellValueFactory(new PropertyValueFactory<IssuedBook, String>("issuedUserName"));
        issueUserEmailColumn.setCellValueFactory(new PropertyValueFactory<IssuedBook, String>("issuedUserEmail"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<IssuedBook,String>("bookTitle"));
        ISBNColumn.setCellValueFactory(new PropertyValueFactory<IssuedBook,String>("isbn"));
        phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<IssuedBook,String>("phoneNumber"));
        issuedDateColumn.setCellValueFactory(new PropertyValueFactory<IssuedBook, Date>("issueDate"));
        returnDateColumn.setCellValueFactory(new PropertyValueFactory<IssuedBook, Date>("returnDate"));
        issuedBookstable.setItems(issuedBookList);
    }
}