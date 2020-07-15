package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("SignIn.fxml"));
        primaryStage.getIcons().add(new Image("file:C:\\Users\\dltjs\\Desktop\\image\\library.png"));
        primaryStage.setTitle("도서관 관리 프로그램");
        primaryStage.setScene(new Scene(root, 1280, 650));
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
