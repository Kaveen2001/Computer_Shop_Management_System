package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class UserRegisterFormController {
    public AnchorPane UserLoginContext;
    public AnchorPane UserRegisterContext;
    public Button btnUserRegister;
    public Button btnLogin;

    public void UserLoginOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage)UserLoginContext.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../View/UserDashBoardForm.fxml"))));
        stage.centerOnScreen();
    }

    public void UserRegisterOnAction(ActionEvent actionEvent) {

    }

    public void LoginOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage)UserRegisterContext.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../View/LoginForm.fxml"))));
        stage.centerOnScreen();
    }
}
