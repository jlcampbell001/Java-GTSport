package backEnd.general.cars;

/**
 * The json representing a car record.
 *
 * @author jonathan
 */
public class CarJson {

    private String primaryKey = "";
    private String name = "";
    private String manufacturerKey = "";
    private String displacementCC = "";
    private String powerRPM = "";
    private String torqueRPM = "";
    private DriveTrain driveTrain = DriveTrain.EMPTY;
    private Aspiration aspiration = Aspiration.EMPTY;
    private Category category = Category.EMPTY;

    private int year = 0;
    private int maxPower = 0;

    private double price = 0.00;
    private double torqueFtLb = 0.00;
    private double length = 0.00;
    private double width = 0.00;
    private double height = 0.00;
    private double weight = 0.00;
    private double maxSpeed = 0.0;
    private double acceleration = 0.0;
    private double braking = 0.0;
    private double cornering = 0.0;
    private double stability = 0.0;

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
     * @param primaryKey the primary key
     */
    public void setPrimaryKey(String primaryKey) {
        this.primaryKey = primaryKey;
    }

    /**
     * Get the name of the car.
     *
     * @return The name of the car.
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name of the car.
     *
     * @param name the name of the car
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the dealer key.
     *
     * @return The manufacturer key.
     */
    public String getManufacturerKey() {
        return manufacturerKey;
    }

    /**
     * Set the manufacturer key.
     *
     * @param manufacturerKey the manufacturer key
     */
    public void setManufacturerKey(String manufacturerKey) {
        this.manufacturerKey = manufacturerKey;
    }

    /**
     * Get the cars displacement CC.
     *
     * @return The displacement CC.
     */
    public String getDisplacementCC() {
        return displacementCC;
    }

    /**
     * Set the cars displacement CC.
     *
     * @param displacementCC the displacement CC
     */
    public void setDisplacementCC(String displacementCC) {
        this.displacementCC = displacementCC;
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
     * @param powerRPM the power RPM
     */
    public void setPowerRPM(String powerRPM) {
        this.powerRPM = powerRPM;
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
     * Get the cars drivetrain.
     *
     * @return The drivetrain.
     */
    public DriveTrain getDriveTrain() {
        return driveTrain;
    }

    /**
     * Set the cars drivetrain.
     *
     * @param driveTrain the drivetrain
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
     * Get the cars year.
     *
     * @return The year.
     */
    public int getYear() {
        return year;
    }

    /**
     * Set the cars year.
     *
     * @param year the year
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * Get the cars horse power.
     *
     * @return The horse power.
     */
    public int getMaxPower() {
        return maxPower;
    }

    /**
     * Set the cars power points.
     *
     * @param maxPower the power points
     */
    public void setMaxPower(int maxPower) {
        this.maxPower = maxPower;
    }

    /**
     * Get the cars price.
     *
     * @return The price.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Set the cars price.
     *
     * @param price the price
     */
    public void setPrice(double price) {
        this.price = price;
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
     * @param length the length
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
     * @param width the width
     */
    public void setWidth(double width) {
        this.width = width;
    }

    /**
     * Get the cars height.
     *
     * @return The height.
     */
    public double getHeight() {
        return height;
    }

    /**
     * Set the cars height.
     *
     * @param height the height
     */
    public void setHeight(double height) {
        this.height = height;
    }

    /**
     * Get the cars weight.
     *
     * @return The Weight.
     */
    public double getWeight() {
        return weight;
    }

    /**
     * Set the cars weight.
     *
     * @param weight the weight
     */
    public void setWeight(double weight) {
        this.weight = weight;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public double getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(double maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public double getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(double acceleration) {
        this.acceleration = acceleration;
    }

    public double getBraking() {
        return braking;
    }

    public void setBraking(double braking) {
        this.braking = braking;
    }

    public double getCornering() {
        return cornering;
    }

    public void setCornering(double cornering) {
        this.cornering = cornering;
    }

    public double getStability() {
        return stability;
    }

    public void setStability(double stability) {
        this.stability = stability;
    }

    @Override
    public String toString() {
        return "CarJson[" + "primaryKey=" + primaryKey + ", name=" + name + ", manufacturerKey=" + manufacturerKey + ", displacementCC=" + displacementCC + ", powerRPM=" + powerRPM + ", torqueRPM=" + torqueRPM + ", driveTrain=" + driveTrain + ", aspiration=" + aspiration + ", category=" + category + ", year=" + year + ", maxPower=" + maxPower + ", price=" + price + ", torqueFtLb=" + torqueFtLb + ", length=" + length + ", width=" + width + ", height=" + height + ", weight=" + weight + ", maxSpeed=" + maxSpeed + ", acceleration=" + acceleration + ", braking=" + braking + ", cornering=" + cornering + ", stability=" + stability + ']';
    }
}
