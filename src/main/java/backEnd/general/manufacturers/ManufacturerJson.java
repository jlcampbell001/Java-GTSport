package backEnd.general.manufacturers;

/**
 * Json that represents the manufacturer.
 *
 * @author jonathan
 */
public class ManufacturerJson {

    private String primaryKey = "";
    private String name = "";
    private String countryKey = "";

    /**
     * Get the primary key.
     *
     * @return - the primary key
     */
    public String getPrimaryKey() {
        return primaryKey;
    }

    /**
     * Set the primary key for the manufacturer.
     *
     * @param primaryKey - the primary key to set to
     */
    public void setPrimaryKey(String primaryKey) {
        this.primaryKey = primaryKey;
    }

    /**
     * Get the name for the manufacturer.
     *
     * @return - the manufacturer name
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
     * Get the country key for the manufacturer.
     *
     * @return - the country key
     */
    public String getCountryKey() {
        return countryKey;
    }

    /**
     * Set the manufacturer country key.
     *
     * @param countryKey - the country key to set to
     */
    public void setCountryKey(String countryKey) {
        this.countryKey = countryKey;
    }

    @Override
    public String toString() {
        return "ManufacturerJson [" + "primaryKey=" + primaryKey + ", name=" + name
                + ", countryKey=" + countryKey + " ]";
    }
}
