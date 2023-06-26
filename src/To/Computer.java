package To;

public class Computer{
    private String id;
    private String serialNo;
    private String brand;
    private String comType;
    private String warType;
    private double comPrice;

    public Computer() {
    }

    public Computer(String id, String serialNo, String brand, String comType, String warType, double comPrice) {
        this.id = id;
        this.serialNo = serialNo;
        this.brand = brand;
        this.comType = comType;
        this.warType = warType;
        this.comPrice = comPrice;
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

    public String getComType() {
        return comType;
    }

    public void setComType(String comType) {
        this.comType = comType;
    }

    public String getWarType() {
        return warType;
    }

    public void setWarType(String warType) {
        this.warType = warType;
    }

    public double getComPrice() {
        return comPrice;
    }

    public void setComPrice(double comPrice) {
        this.comPrice = comPrice;
    }

    @Override
    public String toString() {
        return "Computer{" +
                "id='" + id + '\'' +
                ", serialNo='" + serialNo + '\'' +
                ", brand='" + brand + '\'' +
                ", comType='" + comType + '\'' +
                ", warType='" + warType + '\'' +
                ", comPrice=" + comPrice +
                '}';
    }
}
