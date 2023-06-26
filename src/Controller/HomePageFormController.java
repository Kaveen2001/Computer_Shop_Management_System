package Controller;

import Util.TopPane;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomePageFormController implements Initializable {
    @FXML
    private Label txtLoarding ;

    public  static Label label;
    @FXML
    private AnchorPane HomePageContext;
    @FXML
    private Button btnLoginForm;
    @FXML
    private Label lblDate;
    @FXML
    private Label lblTime;

    @FXML
    private ProgressBar progressbar;
    public static ProgressBar startProgressBar;


    public void LoginFormOnAction(ActionEvent actionEvent) throws IOException {
        txtLoarding=label;
        Stage stage = (Stage)HomePageContext.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../View/LoginForm.fxml"))));
        stage.centerOnScreen();
    }


    @Override
    public void initialize(URL url, ResourceBundle rb ){
        label=txtLoarding;
        startProgressBar=progressbar;
        TopPane.setDateTime(lblTime,lblDate);
    }
}
