package Controller;

import Util.TopPane;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminDashBoardFormController {
    public AnchorPane AdminDashBoardContext;

    public JFXButton btnComputerManage;
    public JFXButton btnLaptopManage;
    public JFXButton btnHardwareManage;
    public JFXButton btnSupplierManage;

    public Label lblDate;
    public Label lblTime;

    public Button btnAdminLogOut;
    public AnchorPane LoadAnchorPaneContext;
    public JFXButton btnItemManage;

    public void ComputerManageOnAction(ActionEvent actionEvent) throws IOException {
        /*
        Stage stage = (Stage)LoadAnchorPaneContext.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../View/ComputerForm.fxml"))));
        stage.centerOnScreen();
         */


        Parent load= FXMLLoader.load(getClass().getResource("../View/ComputerForm.fxml"));
        LoadAnchorPaneContext.getChildren().clear();
        LoadAnchorPaneContext.getChildren().add(load);


      // Navigation.navigate(Routes.COMPUTER_MANAGE,LoadAnchorPaneContext);
    }

    public void LaptopManageOnAction(ActionEvent actionEvent) throws IOException {


        Parent load= FXMLLoader.load(getClass().getResource("../View/LaptopForm.fxml"));
        LoadAnchorPaneContext.getChildren().clear();
        LoadAnchorPaneContext.getChildren().add(load);

       // Navigation.navigate(Routes.LAPTOP_MANAGE,LoadAnchorPaneContext);
    }

    public void HardwareManageOnAction(ActionEvent actionEvent) throws IOException {


        Parent load= FXMLLoader.load(getClass().getResource("../View/HardwareManageForm.fxml"));
        LoadAnchorPaneContext.getChildren().clear();
        LoadAnchorPaneContext.getChildren().add(load);


       // Navigation.navigate(Routes.HARDWARE_MANAGE,LoadAnchorPaneContext);
    }

    public void SupplierManageOnAction(ActionEvent actionEvent) throws IOException {


        Parent load= FXMLLoader.load(getClass().getResource("../View/SupplierManageForm.fxml"));
        LoadAnchorPaneContext.getChildren().clear();
        LoadAnchorPaneContext.getChildren().add(load);


       // Navigation.navigate(Routes.SUPPLIER_MANAGE,LoadAnchorPaneContext);
    }

    public void AdminLogOutOnAction(ActionEvent actionEvent) throws IOException {


        Stage stage = (Stage)AdminDashBoardContext.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../View/LoginForm.fxml"))));
        stage.centerOnScreen();


      // Navigation.navigate(Routes.LOGIN,AdminDashBoardContext);
    }

    public void ItemManageOnAction(ActionEvent actionEvent) throws IOException {

        Parent load= FXMLLoader.load(getClass().getResource("../View/ItemForm.fxml"));
        LoadAnchorPaneContext.getChildren().clear();
        LoadAnchorPaneContext.getChildren().add(load);
    }

    public void initialize( ){
        TopPane.setDateTime(lblTime,lblDate);
    }

}
