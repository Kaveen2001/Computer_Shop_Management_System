
package Controller;

import DB.DBConnection;
import To.Repair;
import View.TM.RepairTM;
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

public class RepairManageFormController {
    public AnchorPane RepairManageContext;

    public JFXTextField txtID;
    public JFXTextField txtRepairItemName;
    public JFXTextField txtRepairState;
    public JFXTextField txtRecivedDate;
    public JFXTextField txtReturnDate;
    public JFXTextField txtRepairDesc;
    public JFXTextField txtRepairItemPrice;

    public Button btnAddRepair;
    public Button btnBackToMenu;
    public Button btnNewRepair;

    public TableView<RepairTM> tblRepairManage;
    public TableColumn colJobID;
    public TableColumn colRepairItemName;
    public TableColumn colRepairState;
    public TableColumn colRecivedDate;
    public TableColumn colReturnDate;
    public TableColumn colRepairDesc;
    public TableColumn colRepairItemPrice;
    public TableColumn colOption;

    private String searchText ="";

    public void initialize() throws SQLException, ClassNotFoundException {

        colJobID.setCellValueFactory(new PropertyValueFactory<>("jobId"));
        colRepairItemName.setCellValueFactory(new PropertyValueFactory<>("repairItemName"));
        colRepairState.setCellValueFactory(new PropertyValueFactory<>("repairState"));
        colRecivedDate.setCellValueFactory(new PropertyValueFactory<>("recivedDate"));
        colReturnDate.setCellValueFactory(new PropertyValueFactory<>("returnDate"));
        colRepairDesc.setCellValueFactory(new PropertyValueFactory<>("repairDesc"));
        colRepairItemPrice.setCellValueFactory(new PropertyValueFactory<>("repairItemPrice"));
        colOption.setCellValueFactory(new PropertyValueFactory<>("btn"));
        SearchRepairs(searchText);

        tblRepairManage.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(null!=newValue) {  // newValue!=null --> null!=newValue
                SetData(newValue);
            }
        });

        txtID.textProperty().addListener((observable, oldValue, newValue) -> {
            SearchRepairs(searchText);
        });
    }

    private void SetData(RepairTM tm) {
        txtID.setText(tm.getJobId());
        txtRepairItemName.setText(tm.getRepairItemName());
        txtRepairState.setText(tm.getRepairState());
        txtRecivedDate.setText(tm.getRecivedDate());
        txtReturnDate.setText(tm.getReturnDate());
        txtRepairDesc.setText(tm.getRepairDesc());
        txtRepairItemPrice.setText(String.valueOf(tm.getRepairItemPrice()));
        btnAddRepair.setText("UPDATE REPAIR");

    }

    private void SearchRepairs(String text) {

        //Search Repairs
        String searchText = "%"+text+"%";
        try{

            ObservableList<RepairTM> tmList = FXCollections.observableArrayList();
            String sql = "SELECT * FROM repairs WHERE RepairItem_Name LIKE ? || Repair_State LIKE ?";
            PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement(sql);
            stm.setString(1,searchText);
            stm.setString(2,searchText);
            ResultSet set = stm.executeQuery();

            while (set.next()){

                Button btn = new Button("Delete");
                RepairTM repairTM = new RepairTM(set.getString(1),set.getString(2),set.getString(3),set.getString(4),set.getString(5),set.getString(6), set.getDouble(7),btn);
                tmList.add(repairTM);

                btn.setOnAction(event ->{

                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Are You Sure Do You want To Delete This Repair?", ButtonType.YES,ButtonType.NO);
                    Optional<ButtonType> buttonType = alert.showAndWait();
                    if (buttonType.get()==ButtonType.YES) {

                        //Delete Repairs
                        try {
                            String sql1 = "DELETE FROM repairs WHERE Job_Id=?";
                            PreparedStatement stm1 = DBConnection.getInstance().getConnection().prepareStatement(sql1);
                            stm1.setString(1, repairTM.getJobId());
                            if (stm1.executeUpdate()>0) {
                                SearchRepairs(searchText);
                                ClearFields();
                                new Alert(Alert.AlertType.INFORMATION, "Repair Deleted..!").show();
                            } else {
                                new Alert(Alert.AlertType.WARNING, "Try Again..!").show();
                            }
                        }catch (ClassNotFoundException | SQLException e){
                            e.printStackTrace();
                        }
                    }
                });
            }
            tblRepairManage.setItems(tmList);
        }catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
    }

    public void AddRepairOnAction(ActionEvent actionEvent) {

        Repair r1 = new Repair(txtID.getText(), txtRepairItemName.getText(), txtRepairState.getText(),txtRecivedDate.getText(),txtReturnDate.getText(),txtRepairDesc.getText(),Double.parseDouble(txtRepairItemPrice.getText()));

        if (btnAddRepair.getText().equalsIgnoreCase("ADD REPAIR")) {

            // Add Repairs
            try {
                String sql = "INSERT INTO repairs VALUES(?,?,?,?,?,?,?)";
                PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement(sql);
                stm.setString(1, r1.getJobId());
                stm.setString(2, r1.getRepairItemName());
                stm.setString(3, r1.getRepairState());
                stm.setString(4, r1.getRecivedDate());
                stm.setString(5, r1.getReturnDate());
                stm.setString(6, r1.getRepairDesc());
                stm.setDouble(7, r1.getRepairItemPrice());


                if (stm.executeUpdate() > 0) {
                    SearchRepairs(searchText);
                    ClearFields();
                    new Alert(Alert.AlertType.INFORMATION, "Repair Saved..!").show();
                } else {
                    new Alert(Alert.AlertType.WARNING, "Try Again..!").show();
                }
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }

        } else {

            // Update Repair
            try {
                String sql = "UPDATE repairs SET RepairItem_Name=?,Repair_State=?,Recived_Date=?, Return_Date=?, Repair_Desc=?, RepairItem_Price=? WHERE Job_Id=? ";
                PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement(sql);
                stm.setString(1, r1.getRepairItemName());
                stm.setString(2, r1.getRepairState());
                stm.setString(3, r1.getRecivedDate());
                stm.setString(4, r1.getReturnDate());
                stm.setString(5, r1.getRepairDesc());
                stm.setDouble(6, r1.getRepairItemPrice());
                stm.setString(7, r1.getJobId());
                if (stm.executeUpdate() > 0) {
                    SearchRepairs(searchText);
                    ClearFields();
                    new Alert(Alert.AlertType.INFORMATION, "Repair Updated..!").show();
                } else {
                    new Alert(Alert.AlertType.WARNING, "Try Again..!").show();
                }
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void ClearFields(){
        txtID.clear();
        txtRepairItemName.clear();
        txtRepairState.clear();
        txtRecivedDate.clear();
        txtReturnDate.clear();
        txtRepairDesc.clear();
        txtRepairItemPrice.clear();
    }


    public void NewRepairOnAction(ActionEvent actionEvent) {
        btnAddRepair.setText("ADD REPAIR");
    }

    public void BackToMenuOnAction(ActionEvent actionEvent) throws IOException {

        Stage stage = (Stage)RepairManageContext.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../View/UserDashBoardForm.fxml"))));
        stage.centerOnScreen();
    }

    public void SearchJobIDOnAction(ActionEvent actionEvent) {
    }
}
