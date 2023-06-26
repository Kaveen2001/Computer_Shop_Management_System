package View.TM;

import javafx.scene.control.Button;

import java.util.Date;

public class OrderTM {
    private String orderId;
    private String cusName;
    private Date date;
    private double total;
    private Button btn;

    public OrderTM() {
    }

    public OrderTM(String orderId, String cusName, Date date, double total, Button btn) {
        this.orderId = orderId;
        this.cusName = cusName;
        this.date = date;
        this.total = total;
        this.btn = btn;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCusName() {
        return cusName;
    }

    public void setCusName(String cusName) {
        this.cusName = cusName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Button getBtn() {
        return btn;
    }

    public void setBtn(Button btn) {
        this.btn = btn;
    }

    @Override
    public String toString() {
        return "OrderTM{" +
                "orderId='" + orderId + '\'' +
                ", cusName='" + cusName + '\'' +
                ", date=" + date +
                ", total=" + total +
                ", btn=" + btn +
                '}';
    }
}
