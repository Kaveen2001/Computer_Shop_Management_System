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

public class UserDashBoardFormController {
    public AnchorPane UserDashBoardContext;
    public AnchorPane LoadAnchorPaneContext;

    public JFXButton btnCustomerManage;
    public JFXButton btnRepairManage;
    public JFXButton btnWarrentyManage;
    public JFXButton btnPlaceOrderForm;

    public Label lblDate;
    public Label lblTime;

    public Button btnUserLogOut;
    public JFXButton btnOrderDetails;


    public void CustomerManageOnAction(ActionEvent actionEvent) throws IOException {

        Parent load= FXMLLoader.load(getClass().getResource("../View/CustomerForm.fxml"));
        LoadAnchorPaneContext.getChildren().clear();
        LoadAnchorPaneContext.getChildren().add(load);
    }

    public void RepairManageOnAction(ActionEvent actionEvent) throws IOException {

        Parent load= FXMLLoader.load(getClass().getResource("../View/RepairManageForm.fxml"));
        LoadAnchorPaneContext.getChildren().clear();
        LoadAnchorPaneContext.getChildren().add(load);
    }

    public void WarrentyManageOnAction(ActionEvent actionEvent) throws IOException {

        Parent load= FXMLLoader.load(getClass().getResource("../View/WarrentyManageForm.fxml"));
        LoadAnchorPaneContext.getChildren().clear();
        LoadAnchorPaneContext.getChildren().add(load);
    }

    public void PlaceOrderFormOnAction(ActionEvent actionEvent) throws IOException {

        Parent load= FXMLLoader.load(getClass().getResource("../View/PlaceOrderForm.fxml"));
        LoadAnchorPaneContext.getChildren().clear();
        LoadAnchorPaneContext.getChildren().add(load);
    }

    public void OrderDetailsFormOnAction(ActionEvent actionEvent) throws IOException {

        Parent load= FXMLLoader.load(getClass().getResource("../View/OrderDetailsForm.fxml"));
        LoadAnchorPaneContext.getChildren().clear();
        LoadAnchorPaneContext.getChildren().add(load);
    }

    public void UserLogOutOnAction(ActionEvent actionEvent) throws IOException {

        Stage stage = (Stage)UserDashBoardContext.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../View/LoginForm.fxml"))));
        stage.centerOnScreen();
    }

    public void initialize( ){
        TopPane.setDateTime(lblTime,lblDate);
    }
}
