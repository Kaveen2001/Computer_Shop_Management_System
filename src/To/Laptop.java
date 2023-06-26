package To;

public class Laptop {
    private String id;
    private String serialNo;
    private String brand;
    private String lapType;
    private String warType;
    private double lapPrice;

    public Laptop() {
    }

    public Laptop(String id, String serialNo, String brand, String lapType, String warType, double lapPrice) {
        this.id = id;
        this.serialNo = serialNo;
        this.brand = brand;
        this.lapType = lapType;
        this.warType = warType;
        this.lapPrice = lapPrice;
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

    @Override
    public String toString() {
        return "Laptop{" +
                "id='" + id + '\'' +
                ", serialNo='" + serialNo + '\'' +
                ", brand='" + brand + '\'' +
                ", lapType='" + lapType + '\'' +
                ", warType='" + warType + '\'' +
                ", lapPrice=" + lapPrice +
                '}';
    }
}
