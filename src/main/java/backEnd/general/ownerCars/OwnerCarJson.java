package backEnd.general.ownerCars;

import java.time.LocalDate;

/**
 * The Json that represents the owner car.
 *
 * @author jonathan
 */
public class OwnerCarJson {

    private String primaryKey = "";
    private String ownerKey = "";
    private String carKey = "";
    private String carId = "";
    private String colour = "";

    private int powerPoints = 0;
    private LocalDate acquiredDate = LocalDate.now();

    /**
     * Get the primary key.
     *
     * @return The primary key.
     */
    public String getPrimaryKey() {
        return primaryKey;
    }

    /**
     * Set the primary key.
     *
     * @param primaryKey the primary key
     */
    public void setPrimaryKey(String primaryKey) {
        this.primaryKey = primaryKey;
    }

    /**
     * Get the owner key.
     *
     * @return The owner key.
     */
    public String getOwnerKey() {
        return ownerKey;
    }

    /**
     * Set the owner key.
     *
     * @param ownerKey the owner key
     */
    public void setOwnerKey(String ownerKey) {
        this.ownerKey = ownerKey;
    }

    /**
     * Get the car key.
     *
     * @return The car key.
     */
    public String getCarKey() {
        return carKey;
    }

    /**
     * Set the car key.
     *
     * @param carKey the car key
     */
    public void setCarKey(String carKey) {
        this.carKey = carKey;
    }

    /**
     * Get the car Id.
     *
     * @return The car Id.
     */
    public String getCarId() {
        return carId;
    }

    /**
     * Set the car Id.
     *
     * @param carId the car Id
     */
    public void setCarId(String carId) {
        this.carId = carId;
    }

    /**
     * get the colour of the car.
     *
     * @return The colour.
     */
    public String getColour() {
        return colour;
    }

    /**
     * Set the colour of the car.
     *
     * @param colour the colour
     */
    public void setColour(String colour) {
        this.colour = colour;
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
     * @param powerPoints the power points
     */
    public void setPowerPoints(int powerPoints) {
        this.powerPoints = powerPoints;
    }

    /**
     * Get the date the car was acquired.
     *
     * @return The acquired date.
     */
    public LocalDate getAcquiredDate() {
        return acquiredDate;
    }

    /**
     * Set the date the car was acquired.
     *
     * @param acquiredDate the acquired date
     */
    public void setAcquiredDate(LocalDate acquiredDate) {
        this.acquiredDate = acquiredDate;
    }

    @Override
    public String toString() {
        return "OwnerCarJson[" + "primaryKey=" + primaryKey + ", ownerKey=" + ownerKey
                + ", carKey=" + carKey + ", carId=" + carId + ", Colour=" + colour
                + ", powerPoints=" + powerPoints + ", acquiredDate=" + acquiredDate + ']';
    }

}
