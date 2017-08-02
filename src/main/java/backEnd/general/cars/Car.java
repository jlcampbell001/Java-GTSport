package backEnd.general.cars;

import backEnd.general.dealers.Dealer;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * The entity that represents a car record.
 *
 * @author jonathan
 */
@Entity
@Table(name = "Cars")
public class Car implements Serializable {
    
    private static final long serialVersionUID = 100L;
    
    @Id
    @Column(name = "CarKey")
    private String primaryKey = "";
    
    @Column(name = "CarName")
    private String name = "";
    
    @Column(name = "CarDeaKey")
    private String dealerKey = "";
    
   // @Transient
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CarDeaKey", insertable = false, updatable = false)
    private Dealer dealer;
    
    @Column(name = "CarYear")
    private int year = 0;
    
    @Column(name = "CarLevel")
    private int level = 0;
    
    @Column(name = "CarPowerPoints")
    private int powerPoints = 0;
    
    @Column(name = "CarPrice")
    private double price = 0.00;
    
    @Column(name = "CarDisplacementCC")
    private String displacementCC = "";
    
    @Column(name = "CarHorsePower")
    private int horsePower = 0;
    
    @Column(name = "CarPowerRPM")
    private String powerRPM = "";
    
    @Column(name = "CarTorqueFtLb")
    private double torqueFtLb = 0.00;
    
    @Column(name = "CarTorqueRPM")
    private String torqueRPM = "";
    
    @Column(name = "CarDriveTrain")
    private String driveTrain = "";
    
    @Column(name = "CarAspiration")
    private String aspiration = "";
    
    @Column(name = "CarLength")
    private double length = 0.00;
    
    @Column(name = "CarWidth")
    private double width = 0.00;
    
    @Column(name = "CarHeight")
    private double height = 0.00;
    
    @Column(name = "CarWeight")
    private double weight = 0.00;
    
    public Car(String primaryKey, String name, String dealerKey, int year, int level,
            int powerPoints, double price, String displacementCC, int horsePower,
            String powerRPM, double torqueFtLb, String torqueRPM, String driveTrain,
            String aspiration, double length, double width, double height, double weight) {
        this.primaryKey = primaryKey;
        this.name = name;
        this.dealerKey = dealerKey;
        this.year = year;
        this.level = level;
        this.powerPoints = powerPoints;
        this.price = price;
        this.displacementCC = displacementCC;
        this.horsePower = horsePower;
        this.powerRPM = powerRPM;
        this.torqueFtLb = torqueFtLb;
        this.torqueRPM = torqueRPM;
        this.driveTrain = driveTrain;
        this.aspiration = aspiration;
        this.length = length;
        this.width = width;
        this.height = height;
        this.weight = weight;
    }
    
    public Car() {
        this("", "", "", 0, 0, 0, 0.00, "", 0, "", 0.00, "", "", "", 0.00, 0.00, 0.00, 0.00);
    }
    
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
    
    public double getPrice() {
        return price;
    }
    
    public void setPrice(double price) {
        this.price = price;
    }
    
    public String getDisplacementCC() {
        return displacementCC;
    }
    
    public void setDisplacementCC(String displacementCC) {
        this.displacementCC = displacementCC;
    }
    
    public int getHorsePower() {
        return horsePower;
    }
    
    public void setHorsePower(int horsePower) {
        this.horsePower = horsePower;
    }
    
    public String getPowerRPM() {
        return powerRPM;
    }
    
    public void setPowerRPM(String powerRPM) {
        this.powerRPM = powerRPM;
    }
    
    public double getTorqueFtLb() {
        return torqueFtLb;
    }
    
    public void setTorqueFtLb(double torqueFtLb) {
        this.torqueFtLb = torqueFtLb;
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
        return "Car[ " + "primaryKey=" + primaryKey + ", name=" + name
                + ", dealerKey=" + dealerKey + ", year=" + year + ", level="
                + level + ", powerPoints=" + powerPoints + ", price=" + price
                + ", displacementCC=" + displacementCC + ", horsePower=" + horsePower
                + ", powerRPM=" + powerRPM + ", torqueFtLb=" + torqueFtLb
                + ", torqueRPM=" + torqueRPM + ", driveTrain=" + driveTrain
                + ", aspiration=" + aspiration + ", length=" + length
                + ", width=" + width + ", height=" + height + ", weight=" + weight + " ]";
    }
}
