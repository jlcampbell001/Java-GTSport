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

    @Column(name = "CarManKey")
    private String manufacturerKey = "";

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CarManKey", insertable = false, updatable = false)
    private Manufacturer manufacturer;

    @Column(name = "CarYear")
    private int year = 0;

    @Column(name = "CarCategory")
    private Category category = Category.EMPTY;

    @Column(name = "CarPrice")
    private double price = 0.00;

    @Column(name = "CarDisplacementCC")
    private String displacementCC = "";

    @Column(name = "CarMaxPower")
    private int maxPower = 0;

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

    @Column(name = "CarMaxSpeed")
    private double maxSpeed = 0.0;

    @Column(name = "CarAcceleration")
    private double acceleration = 0.0;

    @Column(name = "CarBraking")
    private double braking = 0.0;

    @Column(name = "CarCornering")
    private double cornering = 0.0;

    @Column(name = "CarStability")
    private double stability = 0.0;

    /**
     * Creates a new car object with the passed values.
     *
     * @param primaryKey The primary key of the car record.
     * @param name The name of the car.
     * @param dealerKey The dealer key.
     * @param year The year of the car.
     * @param category The category of the car.
     * @param price The cost of the car.
     * @param displacementCC The displacement value.
     * @param maxPower The max power value.
     * @param powerRPM The power rpms.
     * @param torqueFtLb The torque ft/lb.
     * @param torqueRPM The torque rpms.
     * @param driveTrain The drive train.
     * @param aspiration The aspiration.
     * @param length The length of the car.
     * @param width The width of the car.
     * @param height The height of the car.
     * @param weight The weight of the car.
     * @param maxSpeed The max speed rating of the car.
     * @param acceleration The acceleration rating of the car.
     * @param cornering The cornering rating of the car.
     * @param braking The braking rating of the car.
     * @param stability The stability rating of the car.
     */
    public Car(String primaryKey, String name, String dealerKey, int year, Category category,
            double price, String displacementCC, int maxPower,
            String powerRPM, double torqueFtLb, String torqueRPM, DriveTrain driveTrain,
            Aspiration aspiration, double length, double width, double height, double weight,
            double maxSpeed, double acceleration, double braking, double cornering, double stability) {
        this.primaryKey = primaryKey;
        this.name = name;
        this.manufacturerKey = dealerKey;
        this.year = year;
        this.category = category;
        this.price = price;
        this.displacementCC = displacementCC;
        this.maxPower = maxPower;
        this.powerRPM = powerRPM;
        this.torqueFtLb = torqueFtLb;
        this.torqueRPM = torqueRPM;
        this.driveTrain = driveTrain;
        this.aspiration = aspiration;
        this.length = length;
        this.width = width;
        this.height = height;
        this.weight = weight;
        this.maxSpeed = maxSpeed;
        this.acceleration = acceleration;
        this.braking = braking;
        this.cornering = cornering;
        this.stability = stability;
    }

    /**
     * Creates a new car object with default values.
     */
    public Car() {
        this("", "", "", 0, Category.EMPTY, 0.00, "", 0, "", 0.00, "", DriveTrain.EMPTY,
                Aspiration.EMPTY, 0.00, 0.00, 0.00, 0.00, 0.0, 0.0, 0.0, 0.0, 0.0);
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
     * Get the manufacturer foreign key for the car.
     *
     * @return The manufacturer foreign key.
     */
    public String getManufacturerKey() {
        return manufacturerKey;
    }

    /**
     * Set the manufacturer foreign key for the car.
     *
     * @param manufacturerKey the manufacturer key to set to
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
     * Get the category of the car.
     *
     * @return The category of the car.
     */
    public Category getCategory() {
        return category;
    }

    /**
     * Set the category of the car.
     *
     * @param category the category of the car
     */
    public void setCategory(Category category) {
        this.category = category;
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
     * Get the cars max power.
     *
     * @return The max power.
     */
    public int getMaxPower() {
        return maxPower;
    }

    /**
     * Set the cars max power.
     *
     * @param maxPower the max power
     */
    public void setMaxPower(int maxPower) {
        this.maxPower = maxPower;
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

    /**
     * Get the cars max speed rating.
     *
     * @return the cars max speed rating
     */
    public double getMaxSpeed() {
        return maxSpeed;
    }

    /**
     * Set the cars max speed rating.
     *
     * @param maxSpeed the cars max speed rating
     */
    public void setMaxSpeed(double maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    /**
     * Get the cars acceleration rating.
     *
     * @return the cars acceleration rating
     */
    public double getAcceleration() {
        return acceleration;
    }

    /**
     * Set the cars acceleration rating.
     *
     * @param acceleration the cars acceleration rating
     */
    public void setAcceleration(double acceleration) {
        this.acceleration = acceleration;
    }

    /**
     * Get the cars braking rating.
     *
     * @return the cars braking rating
     */
    public double getBraking() {
        return braking;
    }

    /**
     * Set the cars braking rating.
     *
     * @param braking the cars braking rating
     */
    public void setBraking(double braking) {
        this.braking = braking;
    }

    /**
     * Get the cars cornering rating.
     *
     * @return the cars cornering rating
     */
    public double getCornering() {
        return cornering;
    }

    /**
     * Set the cars cornering rating.
     *
     * @param cornering the cars cornering rating
     */
    public void setCornering(double cornering) {
        this.cornering = cornering;
    }

    /**
     * Get the cars stability rating.
     *
     * @return the cars stability rating
     */
    public double getStability() {
        return stability;
    }

    /**
     * Set the cars stability rating.
     *
     * @param stability the cars stability rating
     */
    public void setStability(double stability) {
        this.stability = stability;
    }

    @Override
    public String toString() {
        return "Car[ " + "primaryKey=" + primaryKey + ", name=" + name
                + ", dealerKey=" + manufacturerKey + ", year=" + year + ", category="
                + category + ", price=" + price
                + ", displacementCC=" + displacementCC + ", maxPower=" + maxPower
                + ", powerRPM=" + powerRPM + ", torqueFtLb=" + torqueFtLb
                + ", torqueRPM=" + torqueRPM + ", driveTrain=" + driveTrain
                + ", aspiration=" + aspiration + ", length=" + length
                + ", width=" + width + ", height=" + height + ", weight=" + weight
                + ", maxSpeed=" + maxSpeed + ", acceleration=" + acceleration
                + ", braking=" + braking + ", cornering=" + cornering
                + ", stability=" + stability + " ]";
    }
}
