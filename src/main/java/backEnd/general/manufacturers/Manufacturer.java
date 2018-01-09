package backEnd.general.manufacturers;

import backEnd.general.countries.Country;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * The entity that represents a Manufacturer record.
 *
 * @author jonathan
 */
@Entity
@Table(name = "Manufacturers")
public class Manufacturer implements Serializable {

    private static final long serialVersionUID = 100L;

    @Id
    @Column(name = "ManKey")
    private String primaryKey = "";

    @Column(name = "ManName")
    private String name = "";

    @Column(name = "ManCouKey")
    private String countryKey = "";
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ManCouKey", insertable = false, updatable = false)
    private Country country;

    /**
     * Creates a manufacturer with the passed parameters.
     *
     * @param primaryKey - the primary key to set to
     * @param name - the name to set to
     * @param countryKey - the country key to set the manufacturer to
     */
    public Manufacturer(String primaryKey, String name, String countryKey) {
        this.primaryKey = primaryKey;
        this.name = name;
        this.countryKey = countryKey;
    }

    /**
     * Creates a manufacturer with default values.
     */
    public Manufacturer() {
        this("", "", "");
    }

    /**
     * Gets the manufacturer primary key.
     *
     * @return - the primary key
     */
    public String getPrimaryKey() {
        return primaryKey;
    }

    /**
     * Sets the manufacturer primary key.
     *
     * @param primaryKey - the primary key to set to
     */
    public void setPrimaryKey(String primaryKey) {
        this.primaryKey = primaryKey;
    }

    /**
     * Get the manufacturer name.
     *
     * @return - the name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the manufacturer name.
     *
     * @param name - the name to set to
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the country key the manufacturer is set to.
     *
     * @return - the country key
     */
    public String getCountryKey() {
        return countryKey;
    }

    /**
     * Set the country key for the manufacturer.
     *
     * @param countryKey - the country key to set to
     */
    public void setCountryKey(String countryKey) {
        this.countryKey = countryKey;
    }

    @Override
    public String toString() {
        return "Manufacturer[ primaryKey=" + primaryKey + ", name=" + name + ", countryKey=" + countryKey + " ]";
    }

}
