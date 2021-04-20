package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import sample.controllers.LoginPageController;
import sample.models.Employee;

import java.io.IOException;

public class Main extends Application {

    private Stage stage;
    public Employee emp;

    public void setEmp(Employee emp) {
        this.emp = emp;
    }

    @Override
    public void start(Stage stage){
        try{
            FXMLLoader load = new FXMLLoader();
            load.setLocation(Main.class.getResource("views/login.fxml"));
            StackPane login = (StackPane) load.load();
            Scene scene = new Scene(login, 220, 100);
            stage.setScene(scene);
            LoginPageController loginPageController = load.getController();
            loginPageController.initialize(this, stage);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showMain(Stage stage) throws IOException {
        FXMLLoader mainload = new FXMLLoader();
        mainload.setLocation(Main.class.getResource("views/main.fxml"));
        AnchorPane main = (AnchorPane) mainload.load();
        Scene mainscene = new Scene(main, 100, 100);
        stage.setScene(mainscene);
        stage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
