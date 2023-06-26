package Controller;

import DB.DBConnection;
import Model.Item;
import View.TM.ItemTM;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javax.swing.text.View;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class ItemFormController {
    public AnchorPane ItemManageContext;

    public JFXTextField txtItemCode;
    public JFXTextField txtItemName;
    public JFXTextField txtItemUnitPrice;
    public JFXTextField txtItemQtyOnHand;

    public Button btnBackToMenu;
    public Button btnNewItem;
    public Button btnAddItem;

    public TableView<ItemTM> tblItemManage;
    public TableColumn colItemCode;
    public TableColumn colItemName;
    public TableColumn colItemUnitPrice;
    public TableColumn colItemQtyOnHand;
    public TableColumn colOption;

    private String searchText ="";

    public void initialize(){

        colItemCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        colItemName.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        colItemUnitPrice.setCellValueFactory(new PropertyValueFactory<>("itemUnitPrice"));
        colItemQtyOnHand.setCellValueFactory(new PropertyValueFactory<>("itemQtyOnHand"));
        colOption.setCellValueFactory(new PropertyValueFactory<>("btn"));
        SearchItem(searchText);

        tblItemManage.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(null!=newValue) {  // newValue!=null --> null!=newValue
                SetData(newValue);
            }
        });

        txtItemCode.textProperty().addListener((observable, oldValue, newValue) -> {
            SearchItem(searchText);
        });
    }

    private void SetData(ItemTM itemTM) {
        txtItemCode.setText(itemTM.getCode());
        txtItemName.setText(itemTM.getName());
        txtItemUnitPrice.setText(String.valueOf(itemTM.getUnitPrice()));
        txtItemQtyOnHand.setText(String.valueOf(itemTM.getQtyOnHand()));
        btnAddItem.setText("UPDATE ITEM");
    }

    public void NewItemOnAction(ActionEvent actionEvent) {
        btnAddItem.setText("ADD ITEM");
    }

    public void AddItemOnAction(ActionEvent actionEvent) {
        Item i1 = new Item(txtItemCode.getText(),txtItemName.getText(),Double.parseDouble(txtItemUnitPrice.getText()),Integer.parseInt(txtItemQtyOnHand.getText()));

        if(btnAddItem.getText().equalsIgnoreCase("ADD Item")){

            // Add Item
            try {
                String sql = "INSERT INTO item VALUES(?,?,?,?)";
                PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement(sql);
                stm.setString(1, i1.getItemCode());
                stm.setString(2, i1.getItemName());
                stm.setDouble(3, i1.getItemUnitPrice());
                stm.setInt(4, i1.getItemQtyOnHand());

                if (stm.executeUpdate() > 0) {
                    SearchItem(searchText);
                    ClearFields();
                    new Alert(Alert.AlertType.INFORMATION, "Item Added..!").show();
                } else {
                    new Alert(Alert.AlertType.WARNING, "Try Again..!").show();
                }
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }

        }else{
            // Update Item
            try {
                String sql = "UPDATE item SET Item_Name=?,Item_UnitPrice=?,Item_QtyOnHand=? WHERE Item_Code=?";
                PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement(sql);
                stm.setString(1,i1.getItemName());
                stm.setDouble(2, i1.getItemUnitPrice());
                stm.setInt(3,i1.getItemQtyOnHand());
                stm.setString(4, i1.getItemCode());
                if (stm.executeUpdate() > 0) {
                    SearchItem(searchText);
                    ClearFields();
                    new Alert(Alert.AlertType.INFORMATION, "Item Updated..!").show();
                } else {
                    new Alert(Alert.AlertType.WARNING, "Try Again..!").show();
                }
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }
    }


    private void SearchItem(String text) {

        //Search Item
        String searchText = "%"+text+"%";
        try{

            ObservableList<ItemTM> tmList = FXCollections.observableArrayList();
            String sql = "SELECT * FROM item WHERE Item_Name LIKE ? || Unit_Price LIKE ?";
            PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement(sql);
            stm.setString(1,searchText);
            stm.setString(2,searchText);
            ResultSet set = stm.executeQuery();

            while (set.next()){

                Button btn = new Button("Delete");
                ItemTM itemTM = new ItemTM(set.getString(1),set.getString(2),set.getDouble(3),set.getInt(4),btn);
                tmList.add(itemTM);

                btn.setOnAction(event ->{

                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Are You Sure Do You want To Delete This Item?", ButtonType.YES,ButtonType.NO);
                    Optional<ButtonType> buttonType = alert.showAndWait();
                    if (buttonType.get()==ButtonType.YES) {

                        //Delete Item
                        try {
                            String sql1 = "DELETE FROM item WHERE Item_Code=?";
                            PreparedStatement stm1 = DBConnection.getInstance().getConnection().prepareStatement(sql1);
                            stm1.setString(1, itemTM.getCode());
                            if (stm1.executeUpdate()>0) {
                                SearchItem(searchText);
                                ClearFields();
                                new Alert(Alert.AlertType.INFORMATION, "Item Deleted..!").show();
                            } else {
                                new Alert(Alert.AlertType.WARNING, "Try Again..!").show();
                            }
                        }catch (ClassNotFoundException | SQLException e){
                            e.printStackTrace();
                        }
                    }
                });
            }
            tblItemManage.setItems(tmList);
        }catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
    }

    private void ClearFields() {
        txtItemCode.clear();
        txtItemName.clear();
        txtItemUnitPrice.clear();
        txtItemQtyOnHand.clear();
    }

    public void BackToMenuOnAction(ActionEvent actionEvent) throws IOException {

        Stage stage = (Stage)ItemManageContext.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../View/AdminDashBoardForm.fxml"))));
        stage.centerOnScreen();
    }
}
