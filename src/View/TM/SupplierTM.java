package View.TM;

import javafx.scene.control.Button;

public class SupplierTM {
    private String supId;
    private String supName;
    private String supItem;
    private String supDate;
    private Button btn;

    public SupplierTM() {
    }

    public SupplierTM(String supId, String supName, String supItem, String supDate, Button btn) {
        this.supId = supId;
        this.supName = supName;
        this.supItem = supItem;
        this.supDate = supDate;
        this.btn = btn;
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

    public Button getBtn() {
        return btn;
    }

    public void setBtn(Button btn) {
        this.btn = btn;
    }

    @Override
    public String toString() {
        return "SupplierTM{" +
                "supId='" + supId + '\'' +
                ", supName='" + supName + '\'' +
                ", supItem='" + supItem + '\'' +
                ", supDate='" + supDate + '\'' +
                ", btn=" + btn +
                '}';
    }
}
