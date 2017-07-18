package backEnd.general.cars;

/**
 *
 * @author jonathan
 */
public class CarJson {
    private String primaryKey = "";
    private String name = "";
    private String dealerKey = "";
    private String displacementCC = "";
    private String powerRPM = "";
    private String torqueRPM = "";
    private String driveTrain = "";
    private String aspiration = "";
    
    private int year = 0;
    private int level = 0;
    private int powerPoints = 0;
    private int horsePower = 0;
    
    private double price = 0.00;
    private double torqueFtLb = 0.00;
    private double length = 0.00;
    private double width = 0.00;
    private double height = 0.00;
    private double weight = 0.00;

    public String getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(String primaryKey) {
        this.primaryKey = primaryKey;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDealerKey() {
        return dealerKey;
    }

    public void setDealerKey(String dealerKey) {
        this.dealerKey = dealerKey;
    }

    public String getDisplacementCC() {
        return displacementCC;
    }

    public void setDisplacementCC(String displacementCC) {
        this.displacementCC = displacementCC;
    }

    public String getPowerRPM() {
        return powerRPM;
    }

    public void setPowerRPM(String powerRPM) {
        this.powerRPM = powerRPM;
    }

    public String getTorqueRPM() {
        return torqueRPM;
    }

    public void setTorqueRPM(String torqueRPM) {
        this.torqueRPM = torqueRPM;
    }

    public String getDriveTrain() {
        return driveTrain;
    }

    public void setDriveTrain(String driveTrain) {
        this.driveTrain = driveTrain;
    }

    public String getAspiration() {
        return aspiration;
    }

    public void setAspiration(String aspiration) {
        this.aspiration = aspiration;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getPowerPoints() {
        return powerPoints;
    }

    public void setPowerPoints(int powerPoints) {
        this.powerPoints = powerPoints;
    }

    public int getHorsePower() {
        return horsePower;
    }

    public void setHorsePower(int horsePower) {
        this.horsePower = horsePower;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getTorqueFtLb() {
        return torqueFtLb;
    }

    public void setTorqueFtLb(double torqueFtLb) {
        this.torqueFtLb = torqueFtLb;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "CarJson[" + "primaryKey=" + primaryKey + ", name=" + name + ", dealerKey=" + dealerKey + ", displacementCC=" + displacementCC + ", powerRPM=" + powerRPM + ", torqueRPM=" + torqueRPM + ", driveTrain=" + driveTrain + ", apiration=" + aspiration + ", year=" + year + ", level=" + level + ", powerPoints=" + powerPoints + ", horsePower=" + horsePower + ", price=" + price + ", torqueFtLb=" + torqueFtLb + ", length=" + length + ", width=" + width + ", height=" + height + ", weight=" + weight + ']';
    }   
}
