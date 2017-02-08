package backEnd.general.regions;

/**
 * The region Json for passing data between the backend and front end.
 *
 * @author jonathan
 */
public class RegionJson {

    private String primaryKey = "";
    private String description = "";

    /**
     * Gets the primary key for the region.
     *
     * @return - the primary key
     */
    public String getPrimaryKey() {
        return primaryKey;
    }

    /**
     * Sets the primary key for the region.
     *
     * @param primaryKey - the primary key to set to
     */
    public void setPrimaryKey(String primaryKey) {
        this.primaryKey = primaryKey;
    }

    /**
     * Gets the description for the region.
     *
     * @return - the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description for the region.
     *
     * @param description - the description to set to
     */
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "RegionJson [" + "primaryKey=" + primaryKey + ", description=" + description + "]";
    }

}
