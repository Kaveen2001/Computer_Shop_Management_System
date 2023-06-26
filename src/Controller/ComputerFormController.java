package Controller;

import DB.DBConnection;
import To.Computer;
import View.TM.ComputerTM;
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

public class ComputerFormController {
    public AnchorPane ComputerFormContext;

    public JFXTextField txtID;
    public JFXTextField txtComSerialNo;
    public JFXTextField txtComBrand;
    public JFXTextField txtComType;
    public JFXTextField txtWarType;
    public JFXTextField txtComPrice;

    public Button btnAddComputer;
    public Button btnNewComputer;
    public Button btnBackToMenu;

    public TableView<ComputerTM> tblComputer;
    public TableColumn colComID;
    public TableColumn colComSerialNo;
    public TableColumn colComBrand;
    public TableColumn colComType;
    public TableColumn colWarType;
    public TableColumn colComPrice;
    public TableColumn colOption;

    private String searchText ="";

    public void initialize() throws ClassNotFoundException, SQLException {

        colComID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colComSerialNo.setCellValueFactory(new PropertyValueFactory<>("serialNo"));
        colComBrand.setCellValueFactory(new PropertyValueFactory<>("brand"));
        colComType.setCellValueFactory(new PropertyValueFactory<>("comType"));
        colWarType.setCellValueFactory(new PropertyValueFactory<>("warType"));
        colComPrice.setCellValueFactory(new PropertyValueFactory<>("comPrice"));
        colOption.setCellValueFactory(new PropertyValueFactory<>("btn"));
        SearchComputers(searchText);

        tblComputer.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(null!=newValue) {  // newValue!=null --> null!=newValue
                SetData(newValue);
            }
        });

        txtID.textProperty().addListener((observable, oldValue, newValue) -> {
            SearchComputers(searchText);
        });
    }

    private void SetData(ComputerTM tm) {
        txtID.setText(tm.getId());
        txtComSerialNo.setText(tm.getSerialNo());
        txtComBrand.setText(tm.getBrand());
        txtComType.setText(tm.getComType());
        txtWarType.setText(tm.getWarType());
        txtComPrice.setText(String.valueOf(tm.getComPrice()));
        btnAddComputer.setText("UPDATE COMPUTER");

    }

    private void SearchComputers(String text) {

        //Search Computer
        String searchText = "%"+text+"%";
        try{

            ObservableList<ComputerTM> tmList = FXCollections.observableArrayList();
            String sql = "SELECT * FROM computer WHERE Com_SerialNo LIKE ? || Com_Brand LIKE ?";
            PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement(sql);
            stm.setString(1,searchText);
            stm.setString(2,searchText);
            ResultSet set = stm.executeQuery();

            while (set.next()){

                Button btn = new Button("Delete");
                ComputerTM computerTM = new ComputerTM(set.getString(1),set.getString(2),set.getString(3),set.getString(4),set.getString(5),set.getDouble(6),btn);
                tmList.add(computerTM);

                btn.setOnAction(event ->{

                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Are You Sure Do You want To Delete This Computer?", ButtonType.YES,ButtonType.NO);
                    Optional<ButtonType> buttonType = alert.showAndWait();
                    if (buttonType.get()==ButtonType.YES) {

                        //Delete Computer
                        try {
                            String sql1 = "DELETE FROM computer WHERE Com_Id=?";
                            PreparedStatement stm1 = DBConnection.getInstance().getConnection().prepareStatement(sql1);
                            stm1.setString(1, computerTM.getId());
                            if (stm1.executeUpdate()>0) {
                                SearchComputers(searchText);
                                ClearFields();
                                new Alert(Alert.AlertType.INFORMATION, "Computer Deleted..!").show();
                            } else {
                                new Alert(Alert.AlertType.WARNING, "Try Again..!").show();
                            }
                        }catch (ClassNotFoundException | SQLException e){
                            e.printStackTrace();
                        }
                    }
                });
            }
            tblComputer.setItems(tmList);
        }catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
    }


    public void AddComputerOnAction(ActionEvent actionEvent) {

        Computer c1 = new Computer(txtID.getText(), txtComSerialNo.getText(), txtComBrand.getText(),txtComType.getText(),txtWarType.getText(),Double.parseDouble(txtComPrice.getText()));

        if (btnAddComputer.getText().equalsIgnoreCase("ADD COMPUTER")) {

            // Save Computer
            try {
                String sql = "INSERT INTO Computer VALUES(?,?,?,?,?,?)";
                PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement(sql);
                stm.setString(1, c1.getId());
                stm.setString(2, c1.getSerialNo());
                stm.setString(3, c1.getBrand());
                stm.setString(4, c1.getComType());
                stm.setString(5, c1.getWarType());
                stm.setDouble(6, c1.getComPrice());


                if (stm.executeUpdate() > 0) {
                    SearchComputers(searchText);
                    ClearFields();
                    new Alert(Alert.AlertType.INFORMATION, "Computer Saved..!").show();
                } else {
                    new Alert(Alert.AlertType.WARNING, "Try Again..!").show();
                }
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }

        } else {

            // Update Computer
            try {
                String sql = "UPDATE computer SET Com_SerialNo=?,Com_Brand=?,Com_Type=?, War_Type=?, Com_Price=? WHERE Com_Id=? ";
                PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement(sql);
                stm.setString(1, c1.getSerialNo());
                stm.setString(2, c1.getBrand());
                stm.setString(3, c1.getComType());
                stm.setString(4, c1.getWarType());
                stm.setDouble(5, c1.getComPrice());
                stm.setString(6, c1.getId());
                if (stm.executeUpdate() > 0) {
                    SearchComputers(searchText);
                    ClearFields();
                    new Alert(Alert.AlertType.INFORMATION, "Computer Updated..!").show();
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
        txtComSerialNo.clear();
        txtComBrand.clear();
        txtComType.clear();
        txtWarType.clear();
        txtComPrice.clear();
    }



    public void NewComputerOnAction(ActionEvent actionEvent) {
         btnAddComputer.setText("ADD COMPUTER");
    }

    public void BackToMenuOnAction(ActionEvent actionEvent) throws IOException {

        Stage stage = (Stage)ComputerFormContext.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../View/AdminDashBoardForm.fxml"))));
        stage.centerOnScreen();
    }
}
