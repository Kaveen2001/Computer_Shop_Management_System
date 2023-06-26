package Controller;

import DB.DBConnection;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AdminRegisterFormController {

    public AnchorPane AdminRegisterContext;

    public Button btnAdminRegister;
    public Button btnAdminLogin;

    public JFXTextField txtAdminID;
    public JFXTextField txtAdminName;
    public JFXTextField txtAdminAddress;
    public JFXTextField txtAdminMobileNo;
    public JFXTextField txtAdminEmail;

    public void AdminLoginOnAction(ActionEvent actionEvent) throws IOException {

        Stage stage = (Stage)AdminRegisterContext.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../View/LoginForm.fxml"))));
        stage.centerOnScreen();
    }

    public void AdminRegisterOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        String adminId = txtAdminID.getText();
        String adminName = txtAdminName.getText();
        String adminAddress = txtAdminAddress.getText();
        String adminMobileNo = txtAdminMobileNo.getText();
        String adminEmail = txtAdminEmail.getText();

        PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement("INSERT INTO admin VALUES(?,?,?,?,?)");
        stm.setObject(1,adminId);
        stm.setObject(2,adminName);
        stm.setObject(3,adminAddress);
        stm.setObject(4,adminMobileNo);
        stm.setObject(5,adminEmail);

        if (stm.executeUpdate()>0){
            new Alert(Alert.AlertType.CONFIRMATION,"Admin Added..!").show();
        }else{
            new Alert(Alert.AlertType.WARNING,"Try Again..!").show();
        }
    }
}
