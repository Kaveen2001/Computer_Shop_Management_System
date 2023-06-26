package View.TM;

import javafx.scene.control.Button;

public class LaptopTM {
     private String id;
     private String serialNo;
     private String brand;
     private String lapType;
     private String warType;
     private double lapPrice;
     private Button btn;

    public LaptopTM() {
    }

    public LaptopTM(String id, String serialNo, String brand, String lapType, String warType, double lapPrice, Button btn) {
        this.id = id;
        this.serialNo = serialNo;
        this.brand = brand;
        this.lapType = lapType;
        this.warType = warType;
        this.lapPrice = lapPrice;
        this.btn = btn;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getLapType() {
        return lapType;
    }

    public void setLapType(String lapType) {
        this.lapType = lapType;
    }

    public String getWarType() {
        return warType;
    }

    public void setWarType(String warType) {
        this.warType = warType;
    }

    public double getLapPrice() {
        return lapPrice;
    }

    public void setLapPrice(double lapPrice) {
        this.lapPrice = lapPrice;
    }

    public Button getBtn() {
        return btn;
    }

    public void setBtn(Button btn) {
        this.btn = btn;
    }

    @Override
    public String toString() {
        return "LaptopTM{" +
                "id='" + id + '\'' +
                ", serialNo='" + serialNo + '\'' +
                ", brand='" + brand + '\'' +
                ", lapType='" + lapType + '\'' +
                ", warType='" + warType + '\'' +
                ", lapPrice=" + lapPrice +
                ", btn=" + btn +
                '}';
    }
}
