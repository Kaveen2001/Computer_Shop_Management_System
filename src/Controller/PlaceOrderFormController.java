package Controller;

import DB.DBConnection;
import To.ItemDetails;
import To.Order;
import Util.TopPane;
import View.TM.AddToCartTM;
import View.TM.ItemDetailsTM;
import View.TM.OrderTM;
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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

public class PlaceOrderFormController {
    public AnchorPane PlaceOrderContext;

    public JFXTextField txtOrderID;

    public JFXComboBox<String> cmbCusID;
    public JFXTextField txtCusName;
    public JFXTextField txtCusAddress;
    public JFXTextField txtCusMobileNo;
    public JFXTextField txtCusEmail;
    public JFXTextField txtCusDate;

    public JFXComboBox<String> cmbItemCode;
    public JFXTextField txtItemName;
    public JFXTextField txtItemUnitPrice;
    public JFXTextField txtItemQtyOnHand;
    public JFXTextField txtItemQty;

    public Button btnAddToCart;
    public Button btnBackToMenu;
    public Button btnPlaceOrder;

    public TableView<AddToCartTM> tblPlaceOrder;
    public TableColumn colItemCode;
    public TableColumn colItemName;
    public TableColumn colItemUnitPrice;
    public TableColumn colItemQty;
    public TableColumn colItemTotal;
    public TableColumn colOption;

    public Label lblSubTotal;
    public Label lblDate;
    public Label lblTime;

    public void initialize(){

        colItemCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colItemName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colItemUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colItemQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colItemTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        colOption.setCellValueFactory(new PropertyValueFactory<>("btn"));

        SetDateAndOrderID();
        LoadAllCustomerID();
        LoadAllItemID();
        SetOrderID();

        cmbCusID.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue!=null){
                SetCustomerDetails();
            }
        });

        cmbItemCode.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue!=null){
                SetItemDetails();
            }
        });
    }

    private void SetCustomerDetails() {

        //Set Customer Details Load
        try{
            String sql = "SELECT * FROM customer WHERE Cus_Id=?";
            PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement(sql);
            stm.setString(1,cmbCusID.getValue());
            ResultSet rst = stm.executeQuery();
            if (rst.next()){
                txtCusName.setText(rst.getString(2));
                txtCusAddress.setText(rst.getString(3));
                txtCusMobileNo.setText(rst.getString(4));
                txtCusEmail.setText(rst.getString(5));
                txtCusDate.setText(rst.getString(6));
            }
        }catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
    }

    private void LoadAllCustomerID(){

        //Load All Customer Ids
        try{
            String sql = "SELECT Cus_Id FROM customer ";
            PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement(sql);
            ResultSet rst = stm.executeQuery();
            ArrayList<String> idList = new ArrayList<>();
            while (rst.next()){
                idList.add(rst.getString(1));
            }
            ObservableList<String> obList = FXCollections.observableArrayList(idList);
            cmbCusID.setItems(obList);
        }catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
    }

    public void SetDateAndOrderID(){

        TopPane.setDateTime(lblTime,lblDate);
    }

    private void SetOrderID() {

        try{
            String sql = "SELECT Order_Id FROM `order_Details` ORDER BY Order_Id DESC LIMIT 1"; // 10 --> Not Working (UNSIGNED Using)
            PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement(sql);
            ResultSet rst = stm.executeQuery();

            if (rst.next()){
                //ID Generate
                String tempOrderId = rst.getString(1); //D-2
                String[] array = tempOrderId.split("-"); //[D,2]
                int tempNumber = Integer.parseInt(array[1]);
                int finalizeOrderId = tempNumber+1;
                txtOrderID.setText("D-"+finalizeOrderId);
            }else{
                //D- 1
                txtOrderID.setText("D-1");
                return;
            }

        }catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
    }

    private void LoadAllItemID() {

        //load All Item Ids
        try{
            String sql = "SELECT Item_Code FROM item ";
            PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement(sql);
            ResultSet rst = stm.executeQuery();
            ArrayList<String> idList = new ArrayList<>();
            while (rst.next()){
                idList.add(rst.getString(1));
            }
            ObservableList<String> obList = FXCollections.observableArrayList(idList);
            cmbItemCode.setItems(obList);

        }catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
    }

    private void SetItemDetails() {

        //Set Item Details Load
        try{
            String sql = "SELECT * FROM item WHERE Item_Code=?";
            PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement(sql);
            stm.setString(1,cmbItemCode.getValue());
            ResultSet rst = stm.executeQuery();
            if (rst.next()){
                txtItemName.setText(rst.getString(2));
                txtItemUnitPrice.setText(String.valueOf(rst.getDouble(3)));
                txtItemQtyOnHand.setText(String.valueOf(rst.getInt(4)));
            }

        }catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
    }

    private boolean CheckQty(String code,int qty){
        //Check Qty
        try {
            String sql = "SELECT Item_QtyOnHand FROM item WHERE Item_Code=? ";
            PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement(sql);
            stm.setString(1, code);
            ResultSet rst = stm.executeQuery();

            if (rst.next()){
                //Check
                int tempQty =  rst.getInt(1);
                if (tempQty>=qty){
                    return true;
                }else{
                    return  false;
                }
            }else{
                return false;
            }

        }catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    ObservableList<AddToCartTM> obList = FXCollections.observableArrayList();

    public void AddToCartOnAction(ActionEvent actionEvent) {

        if (!CheckQty(cmbItemCode.getValue(),Integer.parseInt(txtItemQty.getText()))){
            new Alert(Alert.AlertType.WARNING,"Invalid Qty").show();
            return;
        }

        double unitPrice = Double.parseDouble(txtItemUnitPrice.getText());
        int qty = Integer.parseInt(txtItemQty.getText());
        double total = unitPrice * qty;
        Button btn = new Button("Delete");

        int row = isAlreadyExists(cmbItemCode.getValue());
        if(row==-1){
            AddToCartTM tm = new AddToCartTM(cmbItemCode.getValue(),txtItemName.getText(),unitPrice,qty,total,btn);
            obList.add(tm);
            tblPlaceOrder.setItems(obList);
        }else{
            int tempQty = obList.get(row).getQty()+qty;
            double tempTotal = unitPrice * tempQty;

            if (!CheckQty(cmbItemCode.getValue(),tempQty)){
                new Alert(Alert.AlertType.WARNING,"Invalid Qty").show();
                return;
            }

            obList.get(row).setQty(tempQty);
            obList.get(row).getTotal();
            //tblCart.refresh(); --> Table Refresh.
            tblPlaceOrder.refresh();
        }

        CalculateTotal();
        ClearFields();
        //cmbItemID.requestFocus(); --> Redirect Cursor Focus
        cmbItemCode.requestFocus();

        btn.setOnAction(event -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Are You Sure?",ButtonType.YES,ButtonType.NO);
            Optional<ButtonType> buttonType = alert.showAndWait();
            if (buttonType.get()==ButtonType.YES){
                for (AddToCartTM tm:obList
                ) {
                    if (tm.getCode().equals(tm.getCode())){
                        obList.remove(tm);
                        CalculateTotal();
                        tblPlaceOrder.refresh();
                        return;
                    }
                }
            }
        });
    }

    private void ClearFields() {
        txtItemName.clear();
        txtItemUnitPrice.clear();
        txtItemQtyOnHand.clear();
        txtItemQty.clear();
    }

    private int isAlreadyExists(String code) {

        for(int i = 0; i < obList.size();i++){
            if(obList.get(i).getCode().equals(code)){
                return i;
            }
        }
        return -1;
    }

    private  void CalculateTotal(){

        double total = 0.00;
        for (AddToCartTM tm:obList
        ) {
            total+=tm.getTotal();
        }
        lblSubTotal.setText(String.valueOf(total));
    }

    public void PlaceOrderOnAction(ActionEvent actionEvent) throws SQLException {

        if (obList.isEmpty()) return;
        ArrayList<ItemDetails> details = new ArrayList<>();
        for (AddToCartTM tm:obList
        ) {
            details.add(new ItemDetails(tm.getCode(),tm.getUnitPrice(),tm.getQty()));
        }
        Order order = new Order(
                txtOrderID.getText(),new Date(),Double.parseDouble(lblSubTotal.getText()),cmbCusID.getValue(),details
        );

        //Place Order

        Connection con = null;
        try{
            con = DBConnection.getInstance().getConnection();
            con.setAutoCommit(false);

            String sql = "INSERT `order` VALUES(?,?,?,?)";
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1,order.getOrderId());
            stm.setString(2,order.getCustomer());
            stm.setString(3,lblDate.getText());
            stm.setDouble(4,order.getTotalCost());

            // ResultSet rst = stm.executeQuery();

            boolean isOrderSaved = stm.executeUpdate()>0;
            if (isOrderSaved){

                //boolean isOrderDetailsSaved = SaveOrderDetails(details);
                //Update Qty
                boolean isAllUpdated = ManageQty(details);
                if (isAllUpdated){
                    con.commit();
                    new Alert(Alert.AlertType.CONFIRMATION,"Order Placed..!").show();
                    ClearAll();
                }else {
                    con.setAutoCommit(true);
                    con.rollback();
                    new Alert(Alert.AlertType.WARNING,"Try Again..!").show();
                }

            }else {
                con.setAutoCommit(true);
                con.rollback();
                new Alert(Alert.AlertType.WARNING,"Try Again..!").show();
            }

        }catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }finally {
            con.setAutoCommit(true);
        }
    }

    private boolean ManageQty(ArrayList<ItemDetails> details) {

        try{
            for (ItemDetails d:details
            ) {
                String sql = "INSERT `order Details` VALUES(?,?,?,?)";
                PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement(sql);
                stm.setString(1,d.getCode());
                stm.setString(2,txtOrderID.getText());
                stm.setDouble(3,d.getUnitPrice());
                stm.setInt(4,d.getQty());

                boolean isOrderDetailsSaved = stm.executeUpdate()>0;
                if (isOrderDetailsSaved){
                    boolean isQtyUpdated = UpdateDetails(d);
                    if (!isQtyUpdated){
                        return false;
                    }
                }else {
                    return false;
                }
            }
        }catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
        return true;
    }

    private boolean UpdateDetails(ItemDetails d) {

        try{
            String sql = "UPDATE item SET Item_Qty=(Item_Qty-?) WHERE Item_Code=?";
            PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement(sql);
            stm.setInt(1,d.getQty());
            stm.setString(2,d.getCode());
            return stm.executeUpdate()>0;

        }catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    private void ClearAll() {

        obList.clear();
        CalculateTotal();
        txtCusName.clear();
        txtCusAddress.clear();
        txtCusMobileNo.clear();
        txtCusEmail.clear();
        txtCusDate.clear();

        cmbCusID.setValue(null);
        cmbItemCode.setValue(null);

        ClearFields();
        cmbCusID.requestFocus();
        SetOrderID();
    }

    public void BackToMenuOnAction(ActionEvent actionEvent) throws IOException {

        Stage stage =(Stage)PlaceOrderContext.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../View/UserDashBoardForm.fxml"))));
    }
}
