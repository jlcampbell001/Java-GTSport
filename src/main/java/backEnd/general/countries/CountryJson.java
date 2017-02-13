package backEnd.general.countries;

/**
 * The Json that represents a country.
 *
 * @author jonathan
 */
public class CountryJson {

    private String primaryKey = "";
    private String description = "";
    private String regionKey = "";

    /**
     * Get the country primary key.
     *
     * @return - the primary key
     */
    public String getPrimaryKey() {
        return primaryKey;
    }

    /**
     * Set the country primary key.
     *
     * @param primaryKey - the primary key to set to
     */
    public void setPrimaryKey(String primaryKey) {
        this.primaryKey = primaryKey;
    }

    /**
     * Get the country description.
     *
     * @return - the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set the country description.
     *
     * @param description - the description to set to
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Get the country region key.
     *
     * @return - the region key
     */
    public String getRegionKey() {
        return regionKey;
    }

    /**
     * Set the country region key.
     *
     * @param regionKey - the region key to set to
     */
    public void setRegionKey(String regionKey) {
        this.regionKey = regionKey;
    }

    @Override
    public String toString() {
        return "CountryJson[ " + "primaryKey=" + primaryKey + ", description=" + description + ", regionKey=" + regionKey + " ]";
    }

}
