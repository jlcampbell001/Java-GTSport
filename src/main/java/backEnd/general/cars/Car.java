package backEnd.general.cars;

import backEnd.general.manufacturers.Manufacturer;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
    private String manufacturerKey = "";

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CarDeaKey", insertable = false, updatable = false)
    private Manufacturer manufacturer;

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
    private DriveTrain driveTrain = DriveTrain.EMPTY;

    @Column(name = "CarAspiration")
    private Aspiration aspiration = Aspiration.EMPTY;

    @Column(name = "CarLength")
    private double length = 0.00;

    @Column(name = "CarWidth")
    private double width = 0.00;

    @Column(name = "CarHeight")
    private double height = 0.00;

    @Column(name = "CarWeight")
    private double weight = 0.00;

    /**
     * Creates a new car object with the passed values.
     *
     * @param primaryKey The primary key of the car record.
     * @param name The name of the car.
     * @param dealerKey The dealer foreign key.
     * @param year The year of the car.
     * @param level The level of the car.
     * @param powerPoints The power points of the car.
     * @param price The cost of the car.
     * @param displacementCC The displacement value.
     * @param horsePower The horse power value.
     * @param powerRPM The power rpms.
     * @param torqueFtLb The torque ft/lb.
     * @param torqueRPM The torque rpms.
     * @param driveTrain The drive train.
     * @param aspiration The aspiration.
     * @param length The length of the car.
     * @param width The width of the car.
     * @param height The height of the car.
     * @param weight The weight of the car.
     */
    public Car(String primaryKey, String name, String dealerKey, int year, int level,
            int powerPoints, double price, String displacementCC, int horsePower,
            String powerRPM, double torqueFtLb, String torqueRPM, DriveTrain driveTrain,
            Aspiration aspiration, double length, double width, double height, double weight) {
        this.primaryKey = primaryKey;
        this.name = name;
        this.manufacturerKey = dealerKey;
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

    /**
     * Creates a new car object with default values.
     */
    public Car() {
        this("", "", "", 0, 0, 0, 0.00, "", 0, "", 0.00, "", DriveTrain.EMPTY,
                Aspiration.EMPTY, 0.00, 0.00, 0.00, 0.00);
    }

    /**
     * Get the car primary key.
     *
     * @return The primary key.
     */
    public String getPrimaryKey() {
        return primaryKey;
    }

    /**
     * Set the car primary key.
     *
     * @param primaryKey the primary key value to set.
     */
    public void setPrimaryKey(String primaryKey) {
        this.primaryKey = primaryKey;
    }

    /**
     * Gets the name of the car.
     *
     * @return The name of the car.
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name of the car.
     *
     * @param name the car name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the dealer foreign key for the car.
     *
     * @return The dealer foreign key.
     */
    public String getManufacturerKey() {
        return manufacturerKey;
    }

    /**
     * Set the dealer foreign key for the car.
     *
     * @param manufacturerKey the dealer key to set to
     */
    public void setManufacturerKey(String manufacturerKey) {
        this.manufacturerKey = manufacturerKey;
    }

    /**
     * Get the year of the car.
     *
     * @return The year.
     */
    public int getYear() {
        return year;
    }

    /**
     * Set the year of the car.
     *
     * @param year the year of the car
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * Get the level of the car.
     *
     * @return The level of the car.
     */
    public int getLevel() {
        return level;
    }

    /**
     * Set the level of the car.
     *
     * @param level the level of the car
     */
    public void setLevel(int level) {
        this.level = level;
    }

    /**
     * Get the power points of the car.
     *
     * @return The power points.
     */
    public int getPowerPoints() {
        return powerPoints;
    }

    /**
     * Set the power points of the car.
     *
     * @param powerPoints the power points of the car
     */
    public void setPowerPoints(int powerPoints) {
        this.powerPoints = powerPoints;
    }

    /**
     * Get the price of the car.
     *
     * @return the price of the car.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Set the price of the car.
     *
     * @param price The price of the car.
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Get the cars displacement CC.
     *
     * @return The displacement.
     */
    public String getDisplacementCC() {
        return displacementCC;
    }

    /**
     * Set the cars displacement CC.
     *
     * @param displacementCC the displacement cc
     */
    public void setDisplacementCC(String displacementCC) {
        this.displacementCC = displacementCC;
    }

    /**
     * Get the cars horse power.
     *
     * @return The horse power.
     */
    public int getHorsePower() {
        return horsePower;
    }

    /**
     * Set the cars horse power.
     *
     * @param horsePower the horse power
     */
    public void setHorsePower(int horsePower) {
        this.horsePower = horsePower;
    }

    /**
     * Get the cars power RPM.
     *
     * @return The power RPM.
     */
    public String getPowerRPM() {
        return powerRPM;
    }

    /**
     * Set the cars power RPM.
     *
     * @param powerRPM the power rpm
     */
    public void setPowerRPM(String powerRPM) {
        this.powerRPM = powerRPM;
    }

    /**
     * Get the cars torque Ft/Lb.
     *
     * @return The torque Ft/Lb.
     */
    public double getTorqueFtLb() {
        return torqueFtLb;
    }

    /**
     * Set the cars torque Ft/Lb.
     *
     * @param torqueFtLb the torque Ft/Lb
     */
    public void setTorqueFtLb(double torqueFtLb) {
        this.torqueFtLb = torqueFtLb;
    }

    /**
     * Get the cars torque RPM.
     *
     * @return The torque RPM.
     */
    public String getTorqueRPM() {
        return torqueRPM;
    }

    /**
     * Set the cars torque RPM.
     *
     * @param torqueRPM the torque RPM
     */
    public void setTorqueRPM(String torqueRPM) {
        this.torqueRPM = torqueRPM;
    }

    /**
     * Get the cars drive train.
     *
     * @return The drive train.
     */
    public DriveTrain getDriveTrain() {
        return driveTrain;
    }

    /**
     * Set the cars drive train.
     *
     * @param driveTrain the drive train
     */
    public void setDriveTrain(DriveTrain driveTrain) {
        this.driveTrain = driveTrain;
    }

    /**
     * Get the cars aspiration.
     *
     * @return The aspiration.
     */
    public Aspiration getAspiration() {
        return aspiration;
    }

    /**
     * Set the cars aspiration.
     *
     * @param aspiration the aspiration
     */
    public void setAspiration(Aspiration aspiration) {
        this.aspiration = aspiration;
    }

    /**
     * Get the cars length.
     *
     * @return The length.
     */
    public double getLength() {
        return length;
    }

    /**
     * Set the cars length.
     *
     * @param length the cars length
     */
    public void setLength(double length) {
        this.length = length;
    }

    /**
     * Get the cars width.
     *
     * @return The width.
     */
    public double getWidth() {
        return width;
    }

    /**
     * Set the cars width.
     *
     * @param width the cars width
     */
    public void setWidth(double width) {
        this.width = width;
    }

    /**
     * Get the cars height.
     *
     * @return The cars height.
     */
    public double getHeight() {
        return height;
    }

    /**
     * Set the cars height.
     *
     * @param height the cars height
     */
    public void setHeight(double height) {
        this.height = height;
    }

    /**
     * Get the cars width.
     *
     * @return The cars width.
     */
    public double getWeight() {
        return weight;
    }

    /**
     * Set the cars weight.
     *
     * @param weight the cars weight
     */
    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Car[ " + "primaryKey=" + primaryKey + ", name=" + name
                + ", dealerKey=" + manufacturerKey + ", year=" + year + ", level="
                + level + ", powerPoints=" + powerPoints + ", price=" + price
                + ", displacementCC=" + displacementCC + ", horsePower=" + horsePower
                + ", powerRPM=" + powerRPM + ", torqueFtLb=" + torqueFtLb
                + ", torqueRPM=" + torqueRPM + ", driveTrain=" + driveTrain
                + ", aspiration=" + aspiration + ", length=" + length
                + ", width=" + width + ", height=" + height + ", weight=" + weight + " ]";
    }
}
