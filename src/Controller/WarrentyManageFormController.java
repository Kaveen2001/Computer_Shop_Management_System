package Controller;

import DB.DBConnection;
import To.Warranty;
import View.TM.WarrantyTM;
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

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class WarrentyManageFormController {
    public AnchorPane WarrentyManageContext;

    public JFXTextField txtWarID;
    public JFXTextField txtItemID;
    public JFXTextField txtItemName;
    public JFXTextField txtWarType;
    public JFXTextField txtItemPrice;
    public JFXTextField txtItemDesc;

    public Button btnAddWarranty;
    public Button btnBackToMenu;
    public Button btnNewWarranty;

    public TableView<WarrantyTM> tblWarranty;
    public TableColumn colWarID;
    public TableColumn colItemID;
    public TableColumn colItemName;
    public TableColumn colWarType;
    public TableColumn colItemDesc;
    public TableColumn colItemPrice;
    public TableColumn colOption;

    private String searchText ="";

    public void initialize() throws ClassNotFoundException, SQLException {

        colWarID.setCellValueFactory(new PropertyValueFactory<>("warID"));
        colItemID.setCellValueFactory(new PropertyValueFactory<>("itemID"));
        colItemName.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        colWarType.setCellValueFactory(new PropertyValueFactory<>("warType"));
        colItemDesc.setCellValueFactory(new PropertyValueFactory<>("itemDesc"));
        colItemPrice.setCellValueFactory(new PropertyValueFactory<>("itemPrice"));
        colOption.setCellValueFactory(new PropertyValueFactory<>("btn"));
        SearchWarranty(searchText);

        tblWarranty.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(null!=newValue) {  // newValue!=null --> null!=newValue
                SetData(newValue);
            }
        });

        txtWarID.textProperty().addListener((observable, oldValue, newValue) -> {
            SearchWarranty(searchText);
        });
    }

    private void SetData(WarrantyTM tm) {
        txtWarID.setText(tm.getWarID());
        txtItemID.setText(tm.getItemID());
        txtItemName.setText(tm.getItemName());
        txtWarType.setText(tm.getWarType());
        txtItemDesc.setText(tm.getItemDesc());
        txtItemPrice.setText(String.valueOf(tm.getItemPrice()));
        btnAddWarranty.setText("UPDATE WARRANTY");

    }

    private void SearchWarranty(String text) {

        //Search Warranty
        String searchText = "%"+text+"%";
        try{

            ObservableList<WarrantyTM> tmList = FXCollections.observableArrayList();
            String sql = "SELECT * FROM warrenty WHERE Item_Id LIKE ? || Item_Name LIKE ?";
            PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement(sql);
            stm.setString(1,searchText);
            stm.setString(2,searchText);
            ResultSet set = stm.executeQuery();

            while (set.next()){

                Button btn = new Button("Delete");
                WarrantyTM warrantyTM = new WarrantyTM(set.getString(1),set.getString(2),set.getString(3),set.getString(4),set.getString(5),set.getDouble(6),btn);
                tmList.add(warrantyTM);

                btn.setOnAction(event ->{

                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Are You Sure Do You want To Delete This Item Warranty?", ButtonType.YES,ButtonType.NO);
                    Optional<ButtonType> buttonType = alert.showAndWait();
                    if (buttonType.get()==ButtonType.YES) {

                        //Delete Warranty
                        try {
                            String sql1 = "DELETE FROM warrenty WHERE War_Id=?";
                            PreparedStatement stm1 = DBConnection.getInstance().getConnection().prepareStatement(sql1);
                            stm1.setString(1, warrantyTM.getWarID());
                            if (stm1.executeUpdate()>0) {
                                SearchWarranty(searchText);
                                ClearFields();
                                new Alert(Alert.AlertType.INFORMATION, "Warranty Deleted..!").show();
                            } else {
                                new Alert(Alert.AlertType.WARNING, "Try Again..!").show();
                            }
                        }catch (ClassNotFoundException | SQLException e){
                            e.printStackTrace();
                        }
                    }
                });
            }
            tblWarranty.setItems(tmList);
        }catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
    }

    public void AddWarrantyOnAction(ActionEvent actionEvent) {

        Warranty w1 = new Warranty(txtWarID.getText(), txtItemID.getText(), txtItemName.getText(),txtWarType.getText(),txtItemDesc.getText(),Double.parseDouble(txtItemPrice.getText()));

        if (btnAddWarranty.getText().equalsIgnoreCase("ADD WARRANTY")) {

            // Add Warranty
            try {
                String sql = "INSERT INTO warrenty VALUES(?,?,?,?,?,?)";
                PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement(sql);
                stm.setString(1, w1.getWarID());
                stm.setString(2, w1.getItemID());
                stm.setString(3, w1.getItemName());
                stm.setString(4,w1.getWarType());
                stm.setString(5,w1.getItemDesc());
                stm.setDouble(6,w1.getItemPrice());


                if (stm.executeUpdate() > 0) {
                    SearchWarranty(searchText);
                    ClearFields();
                    new Alert(Alert.AlertType.INFORMATION, "Warranty Saved..!").show();
                } else {
                    new Alert(Alert.AlertType.WARNING, "Try Again..!").show();
                }
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }

        } else {

            // Update Warranty
            try {
                String sql = "UPDATE warrenty SET Item_Id=?,Item_Name=?,War_Type=?, Item_Desc=?, Item_Price=? WHERE War_Id=? ";
                PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement(sql);
                stm.setString(1, w1.getItemID());
                stm.setString(2,w1.getItemName());
                stm.setString(3,w1.getWarType());
                stm.setString(4, w1.getItemDesc());
                stm.setDouble(5, w1.getItemPrice());
                stm.setString(6, w1.getWarID());
                if (stm.executeUpdate() > 0) {
                    SearchWarranty(searchText);
                    ClearFields();
                    new Alert(Alert.AlertType.INFORMATION, "Warranty Updated..!").show();
                } else {
                    new Alert(Alert.AlertType.WARNING, "Try Again..!").show();
                }
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void ClearFields(){
        txtWarID.clear();
        txtItemID.clear();
        txtItemName.clear();
        txtWarType.clear();
        txtItemDesc.clear();
        txtItemPrice.clear();
    }
    public void NewWarrantyOnAction(ActionEvent actionEvent) {
        btnAddWarranty.setText("ADD WARRANTY");
    }

    public void SearchWarIDOnAction(ActionEvent actionEvent) {
    }

    public void BackToMenuOnAction(ActionEvent actionEvent) throws IOException {

        Stage stage = (Stage)WarrentyManageContext.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../View/UserDashBoardForm.fxml"))));
        stage.centerOnScreen();
    }
}
