package backEnd.general.ownerCars;

import java.time.LocalDate;

/**
 *
 * @author jonathan
 */
public class OwnerCarJson {

private String primaryKey = "";
private String ownerKey = "";
private String carKey = "";
private String carId = "";
private String Colour = "";

private int powerPoints = 0;
private LocalDate acquiredDate = LocalDate.now();

    public String getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(String primaryKey) {
        this.primaryKey = primaryKey;
    }

    public String getOwnerKey() {
        return ownerKey;
    }

    public void setOwnerKey(String ownerKey) {
        this.ownerKey = ownerKey;
    }

    public String getCarKey() {
        return carKey;
    }

    public void setCarKey(String carKey) {
        this.carKey = carKey;
    }

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    public String getColour() {
        return Colour;
    }

    public void setColour(String Colour) {
        this.Colour = Colour;
    }

    public int getPowerPoints() {
        return powerPoints;
    }

    public void setPowerPoints(int powerPoints) {
        this.powerPoints = powerPoints;
    }

    public LocalDate getAcquiredDate() {
        return acquiredDate;
    }

    public void setAcquiredDate(LocalDate acquiredDate) {
        this.acquiredDate = acquiredDate;
    }

    @Override
    public String toString() {
        return "OwnerCarJson[" + "primaryKey=" + primaryKey + ", ownerKey=" + ownerKey + ", carKey=" + carKey + ", carId=" + carId + ", Colour=" + Colour + ", powerPoints=" + powerPoints + ", acquiredDate=" + acquiredDate + ']';
    }


}
