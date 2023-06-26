package View.TM;

import javafx.scene.control.Button;

public class WarrantyTM {

    private String warID;
    private String itemID;
    private String itemName;
    private String warType;
    private String itemDesc;
    private double itemPrice;
    private Button btn;

    public WarrantyTM() {
    }

    public WarrantyTM(String warID, String itemID, String itemName, String warType, String itemDesc, double itemPrice, Button btn) {
        this.warID = warID;
        this.itemID = itemID;
        this.itemName = itemName;
        this.warType = warType;
        this.itemDesc = itemDesc;
        this.itemPrice = itemPrice;
        this.btn = btn;
    }

    public String getWarID() {
        return warID;
    }

    public void setWarID(String warID) {
        this.warID = warID;
    }

    public String getItemID() {
        return itemID;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
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

    public double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public Button getBtn() {
        return btn;
    }

    public void setBtn(Button btn) {
        this.btn = btn;
    }

    @Override
    public String toString() {
        return "WarrantyTM{" +
                "warID='" + warID + '\'' +
                ", itemID='" + itemID + '\'' +
                ", itemName='" + itemName + '\'' +
                ", warType='" + warType + '\'' +
                ", itemDesc='" + itemDesc + '\'' +
                ", itemPrice=" + itemPrice +
                ", btn=" + btn +
                '}';
    }
}

