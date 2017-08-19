package backEnd.general.cars;

/**
 * The json representing a car record.
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
    private DriveTrain driveTrain = DriveTrain.EMPTY;
    private Aspiration aspiration = Aspiration.EMPTY;

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
     * @return The dealer key.
     */
    public String getDealerKey() {
        return dealerKey;
    }

    /**
     * Set the dealer key.
     *
     * @param dealerKey the dealer key
     */
    public void setDealerKey(String dealerKey) {
        this.dealerKey = dealerKey;
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
     * Get the cars level.
     *
     * @return The cars level.
     */
    public int getLevel() {
        return level;
    }

    /**
     * Set the cars level.
     *
     * @param level the level
     */
    public void setLevel(int level) {
        this.level = level;
    }

    /**
     * Get the cars power points.
     *
     * @return The power points.
     */
    public int getPowerPoints() {
        return powerPoints;
    }

    /**
     * Set the cars power points.
     *
     * @param powerPoints the power points
     */
    public void setPowerPoints(int powerPoints) {
        this.powerPoints = powerPoints;
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
     * Set the cars power points.
     *
     * @param horsePower the power points
     */
    public void setHorsePower(int horsePower) {
        this.horsePower = horsePower;
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

    @Override
    public String toString() {
        return "CarJson[" + "primaryKey=" + primaryKey + ", name=" + name + ", dealerKey=" + dealerKey + ", displacementCC=" + displacementCC + ", powerRPM=" + powerRPM + ", torqueRPM=" + torqueRPM + ", driveTrain=" + driveTrain + ", apiration=" + aspiration + ", year=" + year + ", level=" + level + ", powerPoints=" + powerPoints + ", horsePower=" + horsePower + ", price=" + price + ", torqueFtLb=" + torqueFtLb + ", length=" + length + ", width=" + width + ", height=" + height + ", weight=" + weight + ']';
    }
}
