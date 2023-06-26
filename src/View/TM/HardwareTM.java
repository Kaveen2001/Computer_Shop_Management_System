package View.TM;

import javafx.scene.control.Button;

public class HardwareTM extends CustomerTM {
     private String itemID;
     private String cusID;
     private String itemName;
     private int itemQty;
     private double itemPrice;
     private String warType;
     private String itemDesc;
     private Button btn;

    public HardwareTM() {
    }

    public HardwareTM(String itemID, String cusID, String itemName, int itemQty, double itemPrice, String warType, String itemDesc, Button btn) {
        this.itemID = itemID;
        this.cusID = cusID;
        this.itemName = itemName;
        this.itemQty = itemQty;
        this.itemPrice = itemPrice;
        this.warType = warType;
        this.itemDesc = itemDesc;
        this.btn = btn;
    }

    public String getItemID() {
        return itemID;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    public String getCusID() {
        return cusID;
    }

    public void setCusID(String cusID) {
        this.cusID = cusID;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getItemQty() {
        return itemQty;
    }

    public void setItemQty(int itemQty) {
        this.itemQty = itemQty;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public String getWarType() {
        return warType;
    }

    public void setWarType(String warType) {
        this.warType = warType;
    }

    public String getItemDesc() {
        return itemDesc;
    }

    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
    }

    public Button getBtn() {
        return btn;
    }

    public void setBtn(Button btn) {
        this.btn = btn;
    }

    @Override
    public String toString() {
        return "HardwareTM{" +
                "itemID='" + itemID + '\'' +
                ", cusID='" + cusID + '\'' +
                ", itemName='" + itemName + '\'' +
                ", itemQty=" + itemQty +
                ", itemPrice=" + itemPrice +
                ", warType='" + warType + '\'' +
                ", itemDesc='" + itemDesc + '\'' +
                ", btn=" + btn +
                '}';
    }
}
