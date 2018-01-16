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

    private int maxPower = 0;
    private int powerLevel = 0;
    private int weightReductionLevel = 0;
    
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

    public int getMaxPower() {
        return maxPower;
    }

    public void setMaxPower(int maxPower) {
        this.maxPower = maxPower;
    }

    public int getPowerLevel() {
        return powerLevel;
    }

    public void setPowerLevel(int powerLevel) {
        this.powerLevel = powerLevel;
    }

    public int getWeightReductionLevel() {
        return weightReductionLevel;
    }

    public void setWeightReductionLevel(int weightReductionLevel) {
        this.weightReductionLevel = weightReductionLevel;
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
                + ", carKey=" + carKey + ", carId=" + carId + ", colour=" + colour 
                + ", maxPower=" + maxPower + ", powerLevel=" + powerLevel 
                + ", weightReductionLevel=" + weightReductionLevel 
                + ", acquiredDate=" + acquiredDate + ']';
    }


}
