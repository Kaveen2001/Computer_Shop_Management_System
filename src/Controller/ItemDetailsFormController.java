package Controller;

import DB.DBConnection;
import View.TM.ItemDetailsTM;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
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

public class ItemDetailsFormController {
    public AnchorPane ItemDetailsContext;

    public Button btnBackToMenu;

    public TableView<ItemDetailsTM> tblItemDetails;
    public TableColumn colItemCode;
    public TableColumn colUnitPrice;
    public TableColumn colItemQty;
    public TableColumn colTotal;

    private String searchText ="";

    public void initialize(){

        colItemCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colItemQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        LoadOrderDetails("C001");
    }

    public void LoadOrderDetails(String id){

        try{
            String sql = "SELECT o.orderId,d.itemCode,d.orderId,d.unitPrice,d.qty FROM `order` o INNER JOIN `order Details` d ON o.orderId=d.orderId AND o.orderId=?";
            PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement(sql);
            stm.setString(1,id);
            ResultSet rst = stm.executeQuery();
            ObservableList<ItemDetailsTM> tmList = FXCollections.observableArrayList();

            while (rst.next()){
                double tempUnitPrice = rst.getDouble(4);
                int tempQty = rst.getInt(5);
                double tempTotal = tempUnitPrice * tempQty;
                tmList.add(new ItemDetailsTM(rst.getString(2),tempUnitPrice,tempQty,tempTotal));
            }
            tblItemDetails.setItems(tmList);

        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void BackToMenuOnAction(ActionEvent actionEvent) throws IOException {

        Stage stage =(Stage)ItemDetailsContext.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../View/UserDashBoardForm.fxml"))));
    }
}
