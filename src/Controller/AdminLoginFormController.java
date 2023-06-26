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

public class AdminLoginFormController {
    public AnchorPane AdminLoginContext;
    public JFXTextField txtAdminName;
    public JFXPasswordField txtAdminPassword;
    public Button btnAdminLogin;
    public Button btnAdminRegister;

    public void AdminLoginOnAction(ActionEvent actionEvent) throws IOException {
        if (txtAdminName.getText().equals("admin") & txtAdminPassword.getText().equals("1234")) {
            Stage stage = (Stage)AdminLoginContext.getScene().getWindow();
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../View/AdminDashBoardForm.fxml"))));
            stage.centerOnScreen();
        }
    }

    public void AdminRegisterOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage)AdminLoginContext.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../View/AdminRegisterForm.fxml"))));
        stage.centerOnScreen();
    }
}
