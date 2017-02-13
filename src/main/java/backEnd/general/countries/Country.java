package backEnd.general.countries;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * This is the entity that represents a country record.
 *
 * @author jonathan
 */
@Entity
@Table(name = "Countries")
public class Country implements Serializable {

    private static final long serialVersionUID = 100L;

    @Id
    @Column(name = "CouKey")
    private String primaryKey = "";

    @Column(name = "CouDescription")
    private String description = "";

    @Column(name = "CouRegKey")
    private String regionKey = "";

    /**
     * Constructor to create the country with the passed values.
     *
     * @param primaryKey - the country primary key to set to
     * @param description - the country description to set to
     * @param regionKey - the country region key to set to
     */
    public Country(String primaryKey, String description, String regionKey) {
        this.primaryKey = primaryKey;
        this.description = description;
        this.regionKey = regionKey;
    }

    /**
     * Constructor to create the country with the default values.
     */
    public Country() {
        this("", "", "");
    }

    /**
     * Gets the country primary key.
     *
     * @return - the primary key
     */
    public String getPrimaryKey() {
        return primaryKey;
    }

    /**
     * Sets the country primary key.
     *
     * @param primaryKey - the primary key to set to
     */
    public void setPrimaryKey(String primaryKey) {
        this.primaryKey = primaryKey;
    }

    /**
     * Gets the country description.
     *
     * @return - the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the country description.
     *
     * @param description - the description to set to
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the country region key.
     *
     * @return - the region key
     */
    public String getRegionKey() {
        return regionKey;
    }

    /**
     * Sets the country region key.
     *
     * @param regionKey - the region key to set to
     */
    public void setRegionKey(String regionKey) {
        this.regionKey = regionKey;
    }

    @Override
    public String toString() {
        return "Country[ " + "primaryKey=" + primaryKey + ", description="
                + description + ", regionKey=" + regionKey + " ]";
    }

}
