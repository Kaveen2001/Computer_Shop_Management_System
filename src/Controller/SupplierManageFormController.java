package Controller;

import DB.DBConnection;
import To.Supplier;
import View.TM.SupplierTM;
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

public class SupplierManageFormController {
    public AnchorPane SupplierManageContext;

    public JFXTextField txtSupID;
    public JFXTextField txtSupName;
    public JFXTextField txtSupItem;
    public JFXTextField txtSupDate;

    public Button btnAddSupplier;
    public Button btnNewSupplier;
    public Button btnBackToMenu;

    public TableView<SupplierTM> tblSupplier;
    public TableColumn colSupID;
    public TableColumn colSupName;
    public TableColumn colSupDate;
    public TableColumn colSupItem;
    public TableColumn colOption;

    private String searchText ="";

    public void initialize() throws ClassNotFoundException, SQLException {

        colSupID.setCellValueFactory(new PropertyValueFactory<>("supId"));
        colSupName.setCellValueFactory(new PropertyValueFactory<>("supName"));
        colSupItem.setCellValueFactory(new PropertyValueFactory<>("supItem"));
        colSupDate.setCellValueFactory(new PropertyValueFactory<>("supDate"));
        colOption.setCellValueFactory(new PropertyValueFactory<>("btn"));
        SearchSuppliers(searchText);

        tblSupplier.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(null!=newValue) {  // newValue!=null --> null!=newValue
                SetData(newValue);
            }
        });

        txtSupID.textProperty().addListener((observable, oldValue, newValue) -> {
            SearchSuppliers(searchText);
        });
    }

    private void SetData(SupplierTM tm) {
        txtSupID.setText(tm.getSupId());
        txtSupName.setText(tm.getSupName());
        txtSupItem.setText(tm.getSupItem());
        txtSupDate.setText(tm.getSupDate());
        btnAddSupplier.setText("UPDATE SUPPLIER");

    }

    private void SearchSuppliers(String text) {

        //Search Supplier
        String searchText = "%"+text+"%";
        try{

            ObservableList<SupplierTM> tmList = FXCollections.observableArrayList();
            String sql = "SELECT * FROM supplier WHERE Sup_Name LIKE ? || Sup_Item LIKE ?";
            PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement(sql);
            stm.setString(1,searchText);
            stm.setString(2,searchText);
            ResultSet set = stm.executeQuery();

            while (set.next()){

                Button btn = new Button("Delete");
                SupplierTM supplierTM = new SupplierTM(set.getString(1),set.getString(2),set.getString(3),set.getString(4),btn);
                tmList.add(supplierTM);

                btn.setOnAction(event ->{

                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Are You Sure Do You want To Delete This Supplier?", ButtonType.YES,ButtonType.NO);
                    Optional<ButtonType> buttonType = alert.showAndWait();
                    if (buttonType.get()==ButtonType.YES) {

                        //Delete Supplier
                        try {
                            String sql1 = "DELETE FROM supplier WHERE Sup_Id=?";
                            PreparedStatement stm1 = DBConnection.getInstance().getConnection().prepareStatement(sql1);
                            stm1.setString(1, supplierTM.getSupId());
                            if (stm1.executeUpdate()>0) {
                                SearchSuppliers(searchText);
                                ClearFields();
                                new Alert(Alert.AlertType.INFORMATION, "Supplier Deleted..!").show();
                            } else {
                                new Alert(Alert.AlertType.WARNING, "Try Again..!").show();
                            }
                        }catch (ClassNotFoundException | SQLException e){
                            e.printStackTrace();
                        }
                    }
                });
            }
            tblSupplier.setItems(tmList);
        }catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
    }

    public void AddSupplierOnAction(ActionEvent actionEvent) {

        Supplier s1 = new Supplier(txtSupID.getText(), txtSupName.getText(), txtSupItem.getText(),txtSupDate.getText());

        if (btnAddSupplier.getText().equalsIgnoreCase("ADD SUPPLIER")) {

            // Save Supplier
            try {
                String sql = "INSERT INTO supplier VALUES(?,?,?,?)";
                PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement(sql);
                stm.setString(1,s1.getSupId());
                stm.setString(2,s1.getSupName());
                stm.setString(3,s1.getSupItem());
                stm.setString(4,s1.getSupDate());

                if (stm.executeUpdate() > 0) {
                    SearchSuppliers(searchText);
                    ClearFields();
                    new Alert(Alert.AlertType.INFORMATION, "Supplier Saved..!").show();
                } else {
                    new Alert(Alert.AlertType.WARNING, "Try Again..!").show();
                }
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }

        } else {

            // Update Supplier
            try {
                String sql = "UPDATE supplier SET Sup_Name=?,Sup_Item=?,Sup_Date=? WHERE Sup_Id=? ";
                PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement(sql);
                stm.setString(1,s1.getSupName());
                stm.setString(2,s1.getSupItem());
                stm.setString(3,s1.getSupDate());
                stm.setString(4,s1.getSupId());

                if (stm.executeUpdate() > 0) {
                    SearchSuppliers(searchText);
                    ClearFields();
                    new Alert(Alert.AlertType.INFORMATION, "Supplier Updated..!").show();
                } else {
                    new Alert(Alert.AlertType.WARNING, "Try Again..!").show();
                }
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void ClearFields(){
        txtSupID.clear();
        txtSupName.clear();
        txtSupItem.clear();
        txtSupDate.clear();
    }

    public void NewSupplierOnAction(ActionEvent actionEvent) {
        btnAddSupplier.setText("ADD SUPPLIER");
    }

    public void SearchSupIDOnAction(ActionEvent actionEvent) {

    }

    public void BackToMenuOnAction(ActionEvent actionEvent) throws IOException {

        Stage stage = (Stage)SupplierManageContext.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../View/AdminDashBoardForm.fxml"))));
        stage.centerOnScreen();
    }


}
