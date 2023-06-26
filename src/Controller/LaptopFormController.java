package Controller;

import DB.DBConnection;
import To.Laptop;
import View.TM.LaptopTM;
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

public class LaptopFormController {
    public AnchorPane LaptopFormContext;

    public JFXTextField txtLapID;
    public JFXTextField txtLapSerialNo;
    public JFXTextField txtLapBrand;
    public JFXTextField txtLapType;
    public JFXTextField txtWarType;
    public JFXTextField txtLapPrice;

    public Button btnAddLaptop;
    public Button btnNewLaptop;
    public Button btnBackToMenu;

    public TableView<LaptopTM> tblLaptop;
    public TableColumn colLapID;
    public TableColumn colLapSerialNo;
    public TableColumn colLapBrand;
    public TableColumn colLapType;
    public TableColumn colWarType;
    public TableColumn colLapPrice;
    public TableColumn colOption;

    private String searchText ="";

    public void initialize() throws ClassNotFoundException, SQLException {

        colLapID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colLapSerialNo.setCellValueFactory(new PropertyValueFactory<>("serialNo"));
        colLapBrand.setCellValueFactory(new PropertyValueFactory<>("brand"));
        colLapType.setCellValueFactory(new PropertyValueFactory<>("lapType"));
        colWarType.setCellValueFactory(new PropertyValueFactory<>("warType"));
        colLapPrice.setCellValueFactory(new PropertyValueFactory<>("lapPrice"));
        colOption.setCellValueFactory(new PropertyValueFactory<>("btn"));
        SearchLaptops(searchText);

        tblLaptop.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(null!=newValue) {  // newValue!=null --> null!=newValue
                SetData(newValue);
            }
        });

        txtLapID.textProperty().addListener((observable, oldValue, newValue) -> {
            SearchLaptops(searchText);
        });
    }

    private void SetData(LaptopTM tm) {
        txtLapID.setText(tm.getId());
        txtLapSerialNo.setText(tm.getSerialNo());
        txtLapBrand.setText(tm.getBrand());
        txtLapType.setText(tm.getLapType());
        txtWarType.setText(tm.getWarType());
        txtLapPrice.setText(String.valueOf(tm.getLapPrice()));
        btnAddLaptop.setText("UPDATE LAPTOP");

    }

    private void SearchLaptops(String text) {

        //Search Laptop
        String searchText = "%"+text+"%";
        try{

            ObservableList<LaptopTM> tmList = FXCollections.observableArrayList();
            String sql = "SELECT * FROM laptop WHERE Lap_SerialNo LIKE ? || Lap_Brand LIKE ?";
            PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement(sql);
            stm.setString(1,searchText);
            stm.setString(2,searchText);
            ResultSet set = stm.executeQuery();

            while (set.next()){

                Button btn = new Button("Delete");
                LaptopTM laptopTM = new LaptopTM(set.getString(1),set.getString(2),set.getString(3),set.getString(4),set.getString(5),set.getDouble(6),btn);
                tmList.add(laptopTM);

                btn.setOnAction(event ->{

                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Are You Sure Do You want To Delete This Laptop?", ButtonType.YES,ButtonType.NO);
                    Optional<ButtonType> buttonType = alert.showAndWait();
                    if (buttonType.get()==ButtonType.YES) {

                        //Delete Laptop
                        try {
                            String sql1 = "DELETE FROM laptop WHERE Lap_Id=?";
                            PreparedStatement stm1 = DBConnection.getInstance().getConnection().prepareStatement(sql1);
                            stm1.setString(1, laptopTM.getId());
                            if (stm1.executeUpdate()>0) {
                                SearchLaptops(searchText);
                                ClearFields();
                                new Alert(Alert.AlertType.INFORMATION, "Laptop Deleted..!").show();
                            } else {
                                new Alert(Alert.AlertType.WARNING, "Try Again..!").show();
                            }
                        }catch (ClassNotFoundException | SQLException e){
                            e.printStackTrace();
                        }
                    }
                });
            }
            tblLaptop.setItems(tmList);
        }catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
    }

    public void AddLaptopOnAction(ActionEvent actionEvent) {

        Laptop l1 = new Laptop(txtLapID.getText(), txtLapSerialNo.getText(), txtLapBrand.getText(),txtLapType.getText(),txtWarType.getText(),Double.parseDouble(txtLapPrice.getText()));

        if (btnAddLaptop.getText().equalsIgnoreCase("ADD LAPTOP")) {

            // Save Laptop
            try {
                String sql = "INSERT INTO laptop VALUES(?,?,?,?,?,?)";
                PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement(sql);
                stm.setString(1, l1.getId());
                stm.setString(2,l1.getSerialNo());
                stm.setString(3, l1.getBrand());
                stm.setString(4, l1.getLapType());
                stm.setString(5, l1.getWarType());
                stm.setDouble(6,l1.getLapPrice());


                if (stm.executeUpdate() > 0) {
                    SearchLaptops(searchText);
                    ClearFields();
                    new Alert(Alert.AlertType.INFORMATION, "Laptop Saved..!").show();
                } else {
                    new Alert(Alert.AlertType.WARNING, "Try Again..!").show();
                }
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }

        } else {

            // Update Laptop
            try {
                String sql = "UPDATE laptop SET Lap_SerialNo=?,Lap_Brand=?,Lap_Type=?, War_Type=?, Lap_Price=? WHERE Lap_Id=? ";
                PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement(sql);
                stm.setString(1, l1.getSerialNo());
                stm.setString(2, l1.getBrand());
                stm.setString(3, l1.getLapType());
                stm.setString(4,l1.getWarType());
                stm.setDouble(5,l1.getLapPrice());
                stm.setString(6,l1.getId() );
                if (stm.executeUpdate() > 0) {
                    SearchLaptops(searchText);
                    ClearFields();
                    new Alert(Alert.AlertType.INFORMATION, "Laptop Updated..!").show();
                } else {
                    new Alert(Alert.AlertType.WARNING, "Try Again..!").show();
                }
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void ClearFields(){
        txtLapID.clear();
        txtLapSerialNo.clear();
        txtLapBrand.clear();
        txtLapType.clear();
        txtWarType.clear();
        txtLapPrice.clear();
    }


    public void NewLaptopOnAction(ActionEvent actionEvent) {
        btnAddLaptop.setText("ADD LAPTOP");
    }

    public void BackToMenuOnAction(ActionEvent actionEvent) throws IOException {

        Stage stage = (Stage)LaptopFormContext.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../View/AdminDashBoardForm.fxml"))));
        stage.centerOnScreen();
    }
}
