package Util;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Navigation {
    private static AnchorPane pane;

    public static void navigate(Routes route, AnchorPane pane) throws IOException {
        Navigation.pane = pane;
        Navigation.pane.getChildren().clear();
        Stage window = (Stage)Navigation.pane.getScene().getWindow();

        switch (route) {
            case HOME_PAGE:
                window.setTitle("HOME PAGE");
                initUI("View/HomePageForm.fxml");
                break;

            case LOGIN:
                window.setTitle("LOGIN FORM");
                initUI("View/LoginForm.fxml");
                break;

            case ADMIN_DASHBOARD:
                window.setTitle("ADMIN DASHBOARD");
                initUI("View/AdminDashBoardForm.fxml");
                break;

            case ADMIN_LOGIN:
                window.setTitle("ADMIN LOGIN FORM");
                initUI("View/AdminLoginForm.fxml");
                break;

            case ADMIN_REGISTER:
                window.setTitle("ADMIN REGISTER FROM");
                initUI("View/AdminRegisterForm.fxml");
                break;

            case USER_DASHBOARD:
                window.setTitle("USER DASHBOARD");
                initUI("View/UserDashBoardForm.fxml");
                break;

            case USER_LOGIN:
                window.setTitle("USER LOGIN FORM");
                initUI("View/UserLoginForm.fxml");
                break;

            case USER_REGISTER:
                window.setTitle("USER REGISTER FORM");
                initUI("View/UserRegisterForm.fxml");
                break;

            case CUSTOMER_MANAGE:
                window.setTitle("CUSTOMER MANAGE FORM");
                initUI("View/CustomerForm.fxml");
                break;

            case COMPUTER_MANAGE:
                window.setTitle("COMPUTER MANAGE FORM");
                initUI("View/ComputerForm.fxml");
                break;

            case HARDWARE_MANAGE:
                window.setTitle("HARDWARE MANAGE FORM");
                initUI("View/HardwareManageForm.fxml");
                break;

            case LAPTOP_MANAGE:
                window.setTitle("LAPTOP MANAGE FORM");
                initUI("View/LaptopForm.fxml");
                break;

            case REPAIR_MANAGE:
                window.setTitle("REPAIR MANAGE FORM");
                initUI("View/RepairManageForm.fxml");
                break;

            case SUPPLIER_MANAGE:
                window.setTitle("SUPPLIER MANAGE FORM");
                initUI("View/SupplierManageForm.fxml");
                break;
            /*
                case PLACE_ORDER:
                window.setTitle("Place Order");
                initUI("PlaceOrderForm.fxml");
                break;
             */

            /*
                case ITEM:
                window.setTitle("Item Manage");
                initUI("ItemForm.fxml");
                break;

             */
            default:
                new Alert(Alert.AlertType.ERROR, "UI Not Found!").show();
        }
    }
    public static void initUI(String location) throws IOException {
        Navigation.pane.getChildren().add(FXMLLoader.load(Navigation.class.getResource("View/" + location)));
    }
}
