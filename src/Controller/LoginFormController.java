package Controller;

import Util.TopPane;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class LoginFormController {
    public AnchorPane LoginFormContext;

    public Label lblDate;
    public Label lblTime;

    public AnchorPane LoadAnchorPane;
    public Button btnAdminLogin;
    public Button btnUserLogin;

    public void AdminLoginOnAction(ActionEvent actionEvent) throws IOException {

        Parent load= FXMLLoader.load(getClass().getResource("../View/AdminLoginForm.fxml"));
        LoadAnchorPane.getChildren().clear();
        LoadAnchorPane.getChildren().add(load);
    }

    public void UserLoginOnAction(ActionEvent actionEvent) throws IOException {

        Parent load= FXMLLoader.load(getClass().getResource("../View/UserLoginForm.fxml"));
        LoadAnchorPane.getChildren().clear();
        LoadAnchorPane.getChildren().add(load);
    }

    public void initialize( ){
        TopPane.setDateTime(lblTime,lblDate);
    }
}
