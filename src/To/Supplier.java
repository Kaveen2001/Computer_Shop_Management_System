package To;

public class Supplier {
    private String supId;
    private String supName;
    private String supItem;
    private String supDate;

    public Supplier() {
    }

    public Supplier(String supId, String supName, String supItem, String supDate) {
        this.supId = supId;
        this.supName = supName;
        this.supItem = supItem;
        this.supDate = supDate;
    }

    public String getSupId() {
        return supId;
    }

    public void setSupId(String supId) {
        this.supId = supId;
    }

    public String getSupName() {
        return supName;
    }

    public void setSupName(String supName) {
        this.supName = supName;
    }

    public String getSupItem() {
        return supItem;
    }

    public void setSupItem(String supItem) {
        this.supItem = supItem;
    }

    public String getSupDate() {
        return supDate;
    }

    public void setSupDate(String supDate) {
        this.supDate = supDate;
    }

    @Override
    public String toString() {
        return "Supplier{" +
                "supId='" + supId + '\'' +
                ", supName='" + supName + '\'' +
                ", supItem='" + supItem + '\'' +
                ", supDate='" + supDate + '\'' +
                '}';
    }
}
