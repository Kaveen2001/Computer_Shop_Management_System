package Controller;

import DB.DBConnection;
import Model.CustomerModel;
import To.Hardware;
import View.TM.CustomerTM;
import View.TM.HardwareTM;
import com.jfoenix.controls.JFXComboBox;
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
import java.util.ArrayList;
import java.util.Optional;

public class HardwareManageFormController {
    public AnchorPane HardwareManageContext;

    public JFXTextField txtItemID;
    public JFXTextField txtItemQty;
    public JFXTextField txtItemPrice;
    public JFXTextField txtItemDesc;

    public Button btnAddHardware;
    public Button btnNewHardware;
    public Button btnBackToMenu;

    public TableView tblHardware;
    public TableColumn colItemID;
    public TableColumn colCusID;
    public TableColumn colItemName;
    public TableColumn colItemQty;
    public TableColumn colItemPrice;
    public TableColumn colWarType;
    public TableColumn colItemDesc;
    public TableColumn colOption;

    public JFXComboBox<String> comCusID;
    public JFXComboBox<String> comWarType;
    public JFXComboBox<String> comItemName;

    private String searchText ="";

    public void initialize() throws ClassNotFoundException, SQLException {

        loadCustomerIDs();
        setDataItemName();
        setDataWarType();
        colItemID.setCellValueFactory(new PropertyValueFactory<>("itemID"));
        colCusID.setCellValueFactory(new PropertyValueFactory<>("cusID"));
        colItemName.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        colItemQty.setCellValueFactory(new PropertyValueFactory<>("itemQty"));
        colItemPrice.setCellValueFactory(new PropertyValueFactory<>("itemPrice"));
        colWarType.setCellValueFactory(new PropertyValueFactory<>("warType"));
        colItemDesc.setCellValueFactory(new PropertyValueFactory<>("itemDesc"));
        colOption.setCellValueFactory(new PropertyValueFactory<>("btn"));
        SearchHardwareItems(searchText);

        tblHardware.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(null!=newValue) {  // newValue!=null --> null!=newValue
                SetData((HardwareTM) newValue);
            }
        });

        txtItemID.textProperty().addListener((observable, oldValue, newValue) -> {
            SearchHardwareItems(searchText);
        });
    }


    private void setDataWarType(){
        /*
        comWarType.getItems().add("1-YEAR-WARRANTY");
        comWarType.getItems().add("2-YEAR-WARRANTY");
        comWarType.getItems().add("3-YEAR-WARRANTY");
        comWarType.getItems().add("4-YEAR-WARRANTY");
         */

        //comItemName.getItems().addAll("1-YEAR-WARRANTY","2-YEAR-WARRANTY","3-YEAR-WARRANTY","4-YEAR-WARRANTY");

        ArrayList<String> list = new ArrayList<>();
        list.add("1-YEAR-WARRANTY");
        list.add("2-YEAR-WARRANTY");
        list.add("3-YEAR-WARRANTY");
        list.add("4-YEAR-WARRANTY");
        ObservableList<String> dataSet = FXCollections.observableArrayList(list);
        comWarType.setItems(dataSet);
    }

    private void setDataItemName(){
        //comItemName.getItems().addAll("RAM","HARD","SSD","KEYBOARD","MOUSE","PEN-DRIVE");

        ArrayList<String> list = new ArrayList<>();
        list.add("RAM");
        list.add("HARD");
        list.add("SSD");
        list.add("KEYBOARD");
        list.add("MOUSE");
        list.add("PEN-DRIVE");
        ObservableList<String> dataSet = FXCollections.observableArrayList(list);
        comItemName.setItems(dataSet);
    }

    /*
    private void setDataItemName(){
       /*
        ArrayList<String> dataSet = new ArrayList<>();
        dataSet.add("RAM");
        dataSet.add("HARD");
        dataSet.add("SSD");
        dataSet.add("KEYBOARD");
        comItemName.setItems(dataSet); //Error

     */
        /*
        ObservableList<String> dataSet = FXCollections.observableArrayList();
        dataSet.add("RAM");
        dataSet.add("HARD");
        dataSet.add("SSD");
        dataSet.add("KEYBOARD");
        comItemName.setItems(dataSet); //Correct

        ArrayList<String> list = new ArrayList<>();
        list.add("RAM");
        list.add("HARD");
        list.add("SSD");
        list.add("KEYBOARD");
        ObservableList<String> dataSet = FXCollections.observableArrayList(list);

        comItemName.setItems(dataSet);
    }
     */
        /*
        comItemName.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue --> {
            System.out.println(newValue);
            txtSelectedLanguage.setText(newValue);
        });

*/
    private void SetData(HardwareTM tm) {
        txtItemID.setText(tm.getItemID());
        String cusId = String.valueOf(comCusID.getValue());
        String itemName = String.valueOf(comItemName.getValue());
        txtItemQty.setText(String.valueOf(tm.getItemQty()));
        txtItemPrice.setText(String.valueOf(tm.getItemPrice()));
        String warType = String.valueOf(comWarType.getValue());
        txtItemDesc.setText(tm.getItemDesc());
        btnAddHardware.setText("UPDATE HARDWARE");

    }

    private void SearchHardwareItems(String text) {

        //Search Hardware
        String searchText = "%"+text+"%";
        try{

            ObservableList<CustomerTM> tmList = FXCollections.observableArrayList();
            String sql = "SELECT * FROM hardware WHERE Cus_Id LIKE ? || Item_Name LIKE ?";
            PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement(sql);
            stm.setString(1,searchText);
            stm.setString(2,searchText);
            ResultSet set = stm.executeQuery();

            while (set.next()){

                Button btn = new Button("Delete");
                HardwareTM hardwareTM = new HardwareTM(set.getString(1),set.getString(2),set.getString(3),set.getInt(4),set.getDouble(5),set.getString(6),set.getString(7),btn);
                tmList.add(hardwareTM);

                btn.setOnAction(event ->{

                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Are You Sure Do You want To Delete This Hardware Item?", ButtonType.YES,ButtonType.NO);
                    Optional<ButtonType> buttonType = alert.showAndWait();
                    if (buttonType.get()==ButtonType.YES) {

                        //Delete Hardware
                        try {
                            String sql1 = "DELETE FROM hardware WHERE Item_Id=?";
                            PreparedStatement stm1 = DBConnection.getInstance().getConnection().prepareStatement(sql1);
                            stm1.setString(1, hardwareTM.getItemID());
                            if (stm1.executeUpdate()>0) {
                                SearchHardwareItems(searchText);
                                ClearFields();
                                new Alert(Alert.AlertType.INFORMATION, "Hardware Item Deleted..!").show();
                            } else {
                                new Alert(Alert.AlertType.WARNING, "Try Again..!").show();
                            }
                        }catch (ClassNotFoundException | SQLException e){
                            e.printStackTrace();
                        }
                    }
                });
            }
            tblHardware.setItems(tmList);
        }catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
    }

    public void AddHardwareOnAction(ActionEvent actionEvent) {

        String cusId = comCusID.getValue();
        String itemName = comItemName.getValue();
        String warType =comWarType.getValue();
        Hardware h1 = new Hardware(txtItemID.getText(),String.valueOf(comCusID.getValue()),String.valueOf(comItemName.getValue()),Integer.parseInt(txtItemQty.getText()),Double.parseDouble(txtItemPrice.getText()),String.valueOf(comWarType.getValue()),txtItemDesc.getText());

        if (btnAddHardware.getText().equalsIgnoreCase("ADD HARDWARE")) {

            // Save Hardware
            try {
                String sql = "INSERT INTO hardware VALUES(?,?,?,?,?,?,?)";
                PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement(sql);
                stm.setString(1,h1.getItemID());
                stm.setString(2,h1.getCusID());
                stm.setString(3,h1.getItemName());
                stm.setInt(4,h1.getItemQty());
                stm.setDouble(5,h1.getItemPrice());
                stm.setString(6,h1.getWarType());
                stm.setString(7,h1.getItemDesc());


                if (stm.executeUpdate() > 0) {
                    SearchHardwareItems(searchText);
                    ClearFields();
                    new Alert(Alert.AlertType.INFORMATION, "Hardware Saved..!").show();
                } else {
                    new Alert(Alert.AlertType.WARNING, "Try Again..!").show();
                }
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }

        } else {

            // Update Hardware
            try {
                String sql = "UPDATE hardware SET Cus_Id=?,Item_Name=?,Item_Qty=?, Item_Price=?, War_Type=?, Item_Desc=? WHERE Item_Id=? ";
                PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement(sql);
                stm.setString(1,h1.getCusID());
                stm.setString(2,h1.getItemName());
                stm.setInt(3,h1.getItemQty());
                stm.setDouble(4,h1.getItemPrice());
                stm.setString(5,h1.getWarType());
                stm.setString(6,h1.getItemDesc());
                stm.setString(7,h1.getItemID());
                if (stm.executeUpdate() > 0) {
                    SearchHardwareItems(searchText);
                    ClearFields();
                    new Alert(Alert.AlertType.INFORMATION, "Hardware Item Updated..!").show();
                } else {
                    new Alert(Alert.AlertType.WARNING, "Try Again..!").show();
                }
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void ClearFields(){
        txtItemID.clear();
        txtItemQty.clear();
        txtItemPrice.clear();
        txtItemDesc.clear();
    }


    private void loadCustomerIDs() throws SQLException, ClassNotFoundException {

            ArrayList<String> customerIDs= CustomerModel.loadCustomerIDs();
            ObservableList<String> strings = FXCollections.observableArrayList(customerIDs);
            comCusID.setItems(strings);
    }


    public void ComCusIDOnAction(ActionEvent actionEvent) {

        //String customerID =CustomerModel.getCustomerName(cmbCustomerID.getValue().toString());
        //txtcustomerName.setText(customerID);
    }

    public void ComWarTypeOnAction(ActionEvent actionEvent) {

    }

    public void ComItemNameOnAction(ActionEvent actionEvent) {

    }

    public void NewHardwareOnAction(ActionEvent actionEvent) {

    }

    public void SearchItemIDOnAction(ActionEvent actionEvent) {
    }

    public void BackToMenuOnAction(ActionEvent actionEvent) throws IOException {

        Stage stage = (Stage)HardwareManageContext.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../View/AdminDashBoardForm.fxml"))));
        stage.centerOnScreen();
    }
}
