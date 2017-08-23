package backEnd.general.ownerCars;

import backEnd.general.cars.Car;
import backEnd.general.owners.Owner;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
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
    
    public OwnerCar() {
        this("", "", "", "", "", 0, LocalDate.now());
    }

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

    public String getCarColour() {
        return carColour;
    }

    public void setCarColour(String carColour) {
        this.carColour = carColour;
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
        return "ownerCar[" + "primaryKey=" + primaryKey + ", ownerKey=" + ownerKey 
                + ", carKey=" + carKey + ", carId=" + carId + ", carColour=" + carColour 
                + ", powerPoints=" + powerPoints + ", acquiredDate=" + acquiredDate + ']';
    }
}
