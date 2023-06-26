package Model;

public class Item {
    private String itemCode;
    private String itemName;
    private double itemUnitPrice;
    private int itemQtyOnHand;

    public Item() {
    }

    public Item(String itemCode, String itemName, double itemUnitPrice, int itemQtyOnHand) {
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.itemUnitPrice = itemUnitPrice;
        this.itemQtyOnHand = itemQtyOnHand;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getItemUnitPrice() {
        return itemUnitPrice;
    }

    public void setItemUnitPrice(double itemUnitPrice) {
        this.itemUnitPrice = itemUnitPrice;
    }

    public int getItemQtyOnHand() {
        return itemQtyOnHand;
    }

    public void setItemQtyOnHand(int itemQtyOnHand) {
        this.itemQtyOnHand = itemQtyOnHand;
    }

    @Override
    public String toString() {
        return "Item{" +
                "itemCode='" + itemCode + '\'' +
                ", itemName='" + itemName + '\'' +
                ", itemUnitPrice=" + itemUnitPrice +
                ", itemQtyOnHand=" + itemQtyOnHand +
                '}';
    }
}
