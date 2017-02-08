package backEnd.general.regions;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The entity that represents a region record.
 *
 * @author jonathan
 */
@Entity
@Table(name = "Regions")
public class Region implements Serializable {

    private static final long serialVersionUID = 100L;

    @Id
    @Column(name = "RegKey")
    private String primaryKey = "";

    @Column(name = "RegDescription")
    private String description = "";

    /**
     * Create a region object with the passed values.
     *
     * @param primaryKey
     * @param description
     */
    public Region(String primaryKey, String description) {
        this.primaryKey = primaryKey;
        this.description = description;
    }

    /**
     * Create a region object with the default values.
     */
    public Region() {
        this("", "");
    }

    /**
     * Get the region's primary key.
     *
     * @return - the primary key.
     */
    public String getPrimaryKey() {
        return primaryKey;
    }

    /**
     * Set the region's primary key.
     *
     * @param primaryKey - the primary key to set to.
     */
    public void setPrimaryKey(String primaryKey) {
        this.primaryKey = primaryKey;
    }

    /**
     * Get the region's description.
     *
     * @return - the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set the region's description
     *
     * @param description - the description to set to
     */
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Region[ primaryKey=" + primaryKey + ", description=" + description + " ]";
    }

}
