package backEnd.general.dealers;

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
 * The entity that represents a Dealer record.
 *
 * @author jonathan
 */
@Entity
@Table(name = "Dealers")
public class Dealer implements Serializable {

    private static final long serialVersionUID = 100L;

    @Id
    @Column(name = "DeaKey")
    private String primaryKey = "";

    @Column(name = "DeaName")
    private String name = "";

    @Column(name = "DeaCouKey")
    private String countryKey = "";
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DeaCouKey", insertable = false, updatable = false)
    private Country country;

    /**
     * Creates a dealer with the passed parameters.
     *
     * @param primaryKey - the primary key to set to
     * @param name - the name to set to
     * @param countryKey - the country key to set the dealer to
     */
    public Dealer(String primaryKey, String name, String countryKey) {
        this.primaryKey = primaryKey;
        this.name = name;
        this.countryKey = countryKey;
    }

    /**
     * Creates a dealer with default values.
     */
    public Dealer() {
        this("", "", "");
    }

    /**
     * Gets the dealer primary key.
     *
     * @return - the primary key
     */
    public String getPrimaryKey() {
        return primaryKey;
    }

    /**
     * Sets the dealer primary key.
     *
     * @param primaryKey - the primary key to set to
     */
    public void setPrimaryKey(String primaryKey) {
        this.primaryKey = primaryKey;
    }

    /**
     * Get the dealer name.
     *
     * @return - the name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the dealer name.
     *
     * @param name - the name to set to
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the country key the dealer is set to.
     *
     * @return - the country key
     */
    public String getCountryKey() {
        return countryKey;
    }

    /**
     * Set the country key for the dealer.
     *
     * @param countryKey - the country key to set to
     */
    public void setCountryKey(String countryKey) {
        this.countryKey = countryKey;
    }

    @Override
    public String toString() {
        return "Dealer[ primaryKey=" + primaryKey + ", name=" + name + ", countryKey=" + countryKey + " ]";
    }

}
