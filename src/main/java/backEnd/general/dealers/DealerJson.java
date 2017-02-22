package backEnd.general.dealers;

/**
 * Json that represents the dealer.
 *
 * @author jonathan
 */
public class DealerJson {

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
     * Set the primary key for the dealer.
     *
     * @param primaryKey - the primary key to set to
     */
    public void setPrimaryKey(String primaryKey) {
        this.primaryKey = primaryKey;
    }

    /**
     * Get the name for the dealer.
     *
     * @return - the dealer name
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
     * Get the country key for the dealer.
     *
     * @return - the country key
     */
    public String getCountryKey() {
        return countryKey;
    }

    /**
     * Set the dealer country key.
     *
     * @param countryKey - the country key to set to
     */
    public void setCountryKey(String countryKey) {
        this.countryKey = countryKey;
    }

    @Override
    public String toString() {
        return "DealerJson [" + "primaryKey=" + primaryKey + ", name=" + name
                + ", countryKey=" + countryKey + " ]";
    }
}
