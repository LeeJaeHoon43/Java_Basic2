package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainAdminController implements Initializable {

	@FXML
	private AnchorPane mainPane;
	
	@FXML
	private VBox maincontent;

	@FXML
	private ImageView btnClose;

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		try {
			Parent pane = FXMLLoader.load(getClass().getResource("AdminAllBooks.fxml"));
			maincontent.getChildren().setAll(pane);
		} catch (IOException ex) {
			Logger.getLogger(SignInController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	@FXML
	private void addbooks(MouseEvent event) throws IOException {
		Parent pane = FXMLLoader.load(getClass().getResource("AdminAddBook.fxml"));
		maincontent.getChildren().setAll(pane);
	}

	@FXML
	private void showAllbooks(MouseEvent event) throws IOException {
		Parent pane = FXMLLoader.load(getClass().getResource("AdminAllBooks.fxml"));
		maincontent.getChildren().setAll(pane);
	}
	@FXML
	private void addAdminInfo(MouseEvent event) throws IOException{
		Parent pane = FXMLLoader.load(getClass().getResource("AddAdmin.fxml"));
		maincontent.getChildren().setAll(pane);
	}
	
	@FXML
	private void showAllUserinfo(MouseEvent event) throws IOException{
		Parent pane = FXMLLoader.load(getClass().getResource("ShowAllUserInfo.fxml"));
		maincontent.getChildren().setAll(pane);
	}
	@FXML
	private void addOverdue(MouseEvent event) throws IOException{
		Parent pane = FXMLLoader.load(getClass().getResource("AddOverduePage.fxml"));
		maincontent.getChildren().setAll(pane);
	}
	@FXML
	private void overdueManagement(MouseEvent event) throws IOException{
		Parent pane = FXMLLoader.load(getClass().getResource("OverdueManagementPage.fxml"));
		maincontent.getChildren().setAll(pane);
	}
	@FXML
	private void statisticsAction(MouseEvent event) throws IOException{
		Parent pane = FXMLLoader.load(getClass().getResource("statistics.fxml"));
		maincontent.getChildren().setAll(pane);
	}
	@FXML
	private void logoutAction(ActionEvent event) {
		try {
			Parent pane = FXMLLoader.load(getClass().getResource("SignIn.fxml"));
			mainPane.getChildren().setAll(pane);
//			Node node = (Node) event.getSource();
//			Stage stage = (Stage) node.getScene().getWindow();
//			stage.close();
//			Scene scene = new Scene(FXMLLoader.load(getClass().getResource("SignIn.fxml")));
//			stage.setScene(scene);
//			stage.show();
		} catch (IOException ex) {
			System.err.println(ex.getMessage());
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
