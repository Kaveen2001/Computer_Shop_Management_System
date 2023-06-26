package Controller;

import DB.DBConnection;
import To.Customer;
import View.TM.CustomerTM;
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
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Optional;

public class CustomerFormController {
    public AnchorPane CustomerFormContext;

    public JFXTextField txtID;
    public JFXTextField txtName;
    public JFXTextField txtAddress;
    public JFXTextField txtMobileNo;
    public JFXTextField txtEmail;
    public JFXTextField txtDate;

    public TableView<CustomerTM> tblCustomer;
    public TableColumn colCusID;
    public TableColumn colCusName;
    public TableColumn colCusAddress;
    public TableColumn colCusMobileNo;
    public TableColumn colCusEmail;
    public TableColumn colCusDate;
    public TableColumn colOption;

    public Button btnNewCustomer;
    public Button btnAddCustomer;
    public Button btnBackToMenu;
    public Button btnCustomerReport;


    private String searchText ="";

    public void initialize(){

        colCusID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colCusName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colCusAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colCusMobileNo.setCellValueFactory(new PropertyValueFactory<>("mobileNo"));
        colCusEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colCusDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colOption.setCellValueFactory(new PropertyValueFactory<>("btn"));
        SearchCustomers(searchText);

        tblCustomer.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(null!=newValue) {  // newValue!=null --> null!=newValue
                SetData(newValue);
            }
        });

        txtID.textProperty().addListener((observable, oldValue, newValue) -> {
            SearchCustomers(searchText);
        });
    }

    private void SetData(CustomerTM tm) {
        txtID.setText(tm.getId());
        txtName.setText(tm.getName());
        txtAddress.setText(tm.getAddress());
        txtMobileNo.setText(tm.getMobileNo());
        txtEmail.setText(tm.getEmail());
        txtDate.setText(tm.getDate());
        btnAddCustomer.setText("UPDATE CUSTOMER");

    }

    private void SearchCustomers(String text) {

        //Search Customer
        String searchText = "%"+text+"%";
        try{

            ObservableList<CustomerTM> tmList = FXCollections.observableArrayList();
            String sql = "SELECT * FROM customer WHERE Cus_Name LIKE ? || Cus_Address LIKE ?";
            PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement(sql);
            stm.setString(1,searchText);
            stm.setString(2,searchText);
            ResultSet set = stm.executeQuery();

            while (set.next()){

                Button btn = new Button("Delete");
                CustomerTM cusTM = new CustomerTM(set.getString(1),set.getString(2),set.getString(3),set.getString(4),set.getString(5),set.getString(6),btn);
                tmList.add(cusTM);

                btn.setOnAction(event ->{

                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Are You Sure Do You want To Delete This Customer?", ButtonType.YES,ButtonType.NO);
                    Optional<ButtonType> buttonType = alert.showAndWait();
                    if (buttonType.get()==ButtonType.YES) {

                        //Delete Customer
                        try {
                            String sql1 = "DELETE FROM customer WHERE Cus_Id=?";
                            PreparedStatement stm1 = DBConnection.getInstance().getConnection().prepareStatement(sql1);
                            stm1.setString(1, cusTM.getId());
                            if (stm1.executeUpdate()>0) {
                                SearchCustomers(searchText);
                                ClearFields();
                                new Alert(Alert.AlertType.INFORMATION, "Customer Deleted..!").show();
                            } else {
                                new Alert(Alert.AlertType.WARNING, "Try Again..!").show();
                            }
                        }catch (ClassNotFoundException | SQLException e){
                            e.printStackTrace();
                        }
                    }
                });
            }
            tblCustomer.setItems(tmList);
        }catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
    }

    public void AddCustomerOnAction(ActionEvent actionEvent) {

        Customer c1 = new Customer(txtID.getText(), txtName.getText(), txtAddress.getText(),txtMobileNo.getText(),txtEmail.getText(),txtDate.getText());

        if (btnAddCustomer.getText().equalsIgnoreCase("ADD CUSTOMER")) {

            // Save Customer
            try {
                String sql = "INSERT INTO customer VALUES(?,?,?,?,?,?)";
                PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement(sql);
                stm.setString(1, c1.getId());
                stm.setString(2, c1.getName());
                stm.setString(3, c1.getAddress());
                stm.setString(4, c1.getMobileNo());
                stm.setString(5, c1.getEmail());
                stm.setString(6, c1.getDate());


                if (stm.executeUpdate() > 0) {
                    SearchCustomers(searchText);
                    ClearFields();
                    new Alert(Alert.AlertType.INFORMATION, "Customer Saved..!").show();
                } else {
                    new Alert(Alert.AlertType.WARNING, "Try Again..!").show();
                }
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }

        } else {

            // Update Customer
            try {
                String sql = "UPDATE customer SET Cus_Name=?,Cus_Address=?,Cus_MobileNo=?, Cus_Email=?, Cus_Date=? WHERE Cus_Id=? ";
                PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement(sql);
                stm.setString(1, c1.getName());
                stm.setString(2, c1.getAddress());
                stm.setString(3, c1.getMobileNo());
                stm.setString(4, c1.getEmail());
                stm.setString(5, c1.getDate());
                stm.setString(6, c1.getId());
                if (stm.executeUpdate() > 0) {
                    SearchCustomers(searchText);
                    ClearFields();
                    new Alert(Alert.AlertType.INFORMATION, "Customer Updated..!").show();
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
        txtName.clear();
        txtAddress.clear();
        txtMobileNo.clear();
        txtEmail.clear();
        txtDate.clear();
    }

    public void NewCustomerOnAction(ActionEvent actionEvent) {
        btnAddCustomer.setText("ADD CUSTOMER");
    }

    public void SearchCustomerIDOnAction(ActionEvent actionEvent) {

    }

    public void CustomerReportOnAction(ActionEvent actionEvent) {



        try {
            HashMap<String, Object> hm = new HashMap<>();
            hm.put("Cashier_Name", "KAVEEN");
            InputStream inputStream = this.getClass().getResourceAsStream("/JasperReports/Computer.jrxml");
            JasperReport compileReport = JasperCompileManager.compileReport(inputStream);
            JasperPrint jasperPrint = JasperFillManager.fillReport(compileReport, null, DBConnection.getInstance().getConnection());
            //JasperPrintManager.printReport(jasperPrint,true);
            JasperViewer.viewReport(jasperPrint);

        } catch (JRException | SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void BackToMenuOnAction(ActionEvent actionEvent) throws IOException {

        Stage stage =(Stage)CustomerFormContext.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../View/UserDashBoardForm.fxml"))));
    }
}
