package Controller;

import DB.DBConnection;
import View.TM.OrderTM;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderDetailsFormController {
    public AnchorPane OrderDetailsFormContext;

    public TableView<OrderTM> tblOrderDetails;
    public TableColumn colOrderId;
    public TableColumn colCusName;
    public TableColumn colDate;
    public TableColumn colTotal;
    public TableColumn colOption;

    public Button btnBackToMenu;

    public void initialize(){
        colOrderId.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        colCusName.setCellValueFactory(new PropertyValueFactory<>("cusName"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        colOption.setCellValueFactory(new PropertyValueFactory<>("btn"));
        LoadOrders();
    }

    private void LoadOrders() {

        try{
            String sql = "SELECT * FROM order_Details";
            PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement(sql);
            ResultSet rst = stm.executeQuery();

            ObservableList<OrderTM> tmList = FXCollections.observableArrayList();

            while (rst.next()){
                Button btn = new Button("View More");
                OrderTM tm = new OrderTM(rst.getString(1), rst.getString(2),rst.getDate(3), rst.getDouble(4), btn);
                tmList.add(tm);

                btn.setOnAction(event -> {
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/ItemDetailsForm.fxml"));
                        Parent parent = loader.load();
                        ItemDetailsFormController controller = loader.getController();
                        controller.LoadOrderDetails(tm.getOrderId());
                        Stage stage = new Stage();
                        stage.setScene(new Scene(parent));
                        stage.show();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            }
            tblOrderDetails.setItems(tmList);
        }catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
    }

    public void BackToMenuOnAction(ActionEvent actionEvent) throws IOException {

        Stage stage =(Stage)OrderDetailsFormContext.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../View/UserDashBoardForm.fxml"))));
    }
}
