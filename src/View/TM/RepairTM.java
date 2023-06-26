package View.TM;

import javafx.scene.control.Button;

public class RepairTM {
    private String jobId;
    private String repairItemName;
    private String repairState;
    private String recivedDate;
    private String returnDate;
    private String repairDesc;
    private double repairItemPrice;
    private Button btn;

    public RepairTM() {
    }

    public RepairTM(String jobId, String repairItemName, String repairState, String recivedDate, String returnDate, String repairDesc, double repairItemPrice, Button btn) {
        this.jobId = jobId;
        this.repairItemName = repairItemName;
        this.repairState = repairState;
        this.recivedDate = recivedDate;
        this.returnDate = returnDate;
        this.repairDesc = repairDesc;
        this.repairItemPrice = repairItemPrice;
        this.btn = btn;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getRepairItemName() {
        return repairItemName;
    }

    public void setRepairItemName(String repairItemName) {
        this.repairItemName = repairItemName;
    }

    public String getRepairState() {
        return repairState;
    }

    public void setRepairState(String repairState) {
        this.repairState = repairState;
    }

    public String getRecivedDate() {
        return recivedDate;
    }

    public void setRecivedDate(String recivedDate) {
        this.recivedDate = recivedDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public String getRepairDesc() {
        return repairDesc;
    }

    public void setRepairDesc(String repairDesc) {
        this.repairDesc = repairDesc;
    }

    public double getRepairItemPrice() {
        return repairItemPrice;
    }

    public void setRepairItemPrice(double repairItemPrice) {
        this.repairItemPrice = repairItemPrice;
    }

    public Button getBtn() {
        return btn;
    }

    public void setBtn(Button btn) {
        this.btn = btn;
    }

    @Override
    public String toString() {
        return "RepairTM{" +
                "jobId='" + jobId + '\'' +
                ", repairItemName='" + repairItemName + '\'' +
                ", repairState='" + repairState + '\'' +
                ", recivedDate='" + recivedDate + '\'' +
                ", returnDate='" + returnDate + '\'' +
                ", repairDesc='" + repairDesc + '\'' +
                ", repairItemPrice=" + repairItemPrice +
                ", btn=" + btn +
                '}';
    }
}
