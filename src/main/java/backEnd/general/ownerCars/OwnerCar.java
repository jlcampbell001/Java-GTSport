package backEnd.general.ownerCars;

import backEnd.general.cars.Car;
import backEnd.general.owners.Owner;
import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * The Owner Car entity.
 *
 * @author jonathan
 */
@Entity
@Table(name = "OwnerCars")
public class OwnerCar implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "OwcKey")
    private String primaryKey = "";

    @Column(name = "OwcOwnKey")
    private String ownerKey = "";

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "OwcOwnKey", insertable = false, updatable = false)
    private Owner owner;

    @Column(name = "OwcCarKey")
    private String carKey = "";

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "OwcCarKey", insertable = false, updatable = false)
    private Car car;

    @Column(name = "OwcCarId")
    private String carId = "";

    @Column(name = "OwcColour")
    private String carColour = "";

    @Column(name = "OwcPowerPoints")
    private int powerPoints = 0;

    @Column(name = "OwcDateAquired")
    private LocalDate acquiredDate = LocalDate.now();

    /**
     * Create an owner car with the passed data.
     *
     * @param primaryKey - the primary key
     * @param ownerKey - the owner that owns the car key
     * @param carKey - the car that is owned key
     * @param carId - the id of the owned car
     * @param colour - the color of the car
     * @param powerPoints - the cars power points
     * @param acquiredDate - the date the car was acquired
     */
    public OwnerCar(String primaryKey, String ownerKey, String carKey, String carId,
            String colour, int powerPoints, LocalDate acquiredDate) {
        this.primaryKey = primaryKey;
        this.ownerKey = ownerKey;
        this.carKey = carKey;
        this.carId = carId;
        this.carColour = colour;
        this.powerPoints = powerPoints;
        this.acquiredDate = acquiredDate;
    }

    /**
     * Create an owned car with default values.
     */
    public OwnerCar() {
        this("", "", "", "", "", 0, LocalDate.now());
    }

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
     * Get the colour of the car.
     *
     * @return The colour of the car.
     */
    public String getCarColour() {
        return carColour;
    }

    /**
     * Set the colour of the car.
     *
     * @param carColour the colour of the car.
     */
    public void setCarColour(String carColour) {
        this.carColour = carColour;
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
        return "ownerCar[" + "primaryKey=" + primaryKey + ", ownerKey=" + ownerKey
                + ", carKey=" + carKey + ", carId=" + carId + ", carColour=" + carColour
                + ", powerPoints=" + powerPoints + ", acquiredDate=" + acquiredDate + ']';
    }
}
