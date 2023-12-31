package View.TM;

public class ItemDetailsTM {
    private String code;
    private double unitPrice;
    private int qty;
    private double total;

    public ItemDetailsTM() {
    }

    public ItemDetailsTM(String code, double unitPrice, int qty, double total) {
        this.code = code;
        this.unitPrice = unitPrice;
        this.qty = qty;
        this.total = total;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "ItemDetailsTM{" +
                "code='" + code + '\'' +
                ", unitPrice=" + unitPrice +
                ", qty=" + qty +
                ", total=" + total +
                '}';
    }
}
