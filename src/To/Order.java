package To;

import java.util.ArrayList;
import java.util.Date;

public class Order  {
    private String orderId; // D-001
    private Date date;
    private double totalCost;
    private String customer;
    private ArrayList<ItemDetails> itemDetails;

    public Order() {
    }

    public Order(String orderId, Date date, double totalCost, String customer, ArrayList<ItemDetails> itemDetails) {
        this.orderId = orderId;
        this.date = date;
        this.totalCost = totalCost;
        this.customer = customer;
        this.itemDetails = itemDetails;
    }
    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public ArrayList<ItemDetails> getItemDetails() {
        return itemDetails;
    }

    public void setItemDetails(ArrayList<ItemDetails> itemDetails) {
        this.itemDetails = itemDetails;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", date=" + date +
                ", totalCost=" + totalCost +
                ", customer='" + customer + '\'' +
                ", itemDetails=" + itemDetails +
                '}';
    }
}
