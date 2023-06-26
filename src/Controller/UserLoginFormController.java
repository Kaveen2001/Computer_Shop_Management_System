package Controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class UserLoginFormController {
    public AnchorPane UserLoginContext;
    public JFXTextField txtUserName;
    public JFXPasswordField txtUserPassword;
    public Button btnUserLogin;
    public Button btnUserRegister;

    public void UserLoginOnAction(ActionEvent actionEvent) throws IOException {

        if (txtUserName.getText().equals("kaveen") & txtUserPassword.getText().equals("2001")) {
            Stage stage = (Stage) UserLoginContext.getScene().getWindow();
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../View/UserDashBoardForm.fxml"))));
            stage.centerOnScreen();
        }
    }

    public void UserRegisterOnAction(ActionEvent actionEvent) throws IOException {

        Stage stage = (Stage)UserLoginContext.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../View/UserRegisterForm.fxml"))));
        stage.centerOnScreen();
    }
}
