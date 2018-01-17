package backEnd.general.cars;

/**
 * The Json used for searching for cars by criteria.<br>
 * Values set to null will be ignored in the search.<br>
 * <p>
 * <b>category search (Range search):</b><br>
 * - Works with the value put in the data base for the ordering. - If
 * categoryFrom is set to null but a value is in categoryTo, categoryFrom will
 * be set to "".<br>
 * - If categoryTo is set to null but a value is in categoryFrom, categoryTo
 * will be set to Category.MAX.<br></p>
 * <p>
 * <b>Year search (Range search):</b><br>
 * - If yearFrom is set to null but a value is in yearTo, yearFrom will be set
 * to 0.<br>
 * - If yearTo is set to null but a value is in yearFrom, yearTo will be set to
 * Integer.MAX_VALUE.<br></p>
 * <p>
 * <b>Max Power search (Range search):</b><br>
 * - If maxPowerFrom is set to null but a value is in maxPowerTo, maxPowerFrom
 * will be set to 0.<br>
 * - If maxPowerTo is set to null but a value is in maxPowerFrom, maxPowerTo
 * will be set to Integer.MAX_VALUE.<br></p>
 * <p>
 * <b>Drivetrain search (Exact search):</b><br>
 * - Looks for an exact value of the drivetrain to match.
 * {@link backEnd.general.cars.DriveTrain DriveTrain}</p>
 * <p>
 * <b>Dealer Name search (Exact search):</b><br>
 * - Searches for cars using the dealer name exactly.<br></p>
 * <p>
 * <b>Country search (Exact search):</b><br>
 * - Searches for cars using the country exactly.<br></p>
 * <p>
 * <b>Region search (Exact search):</b><br>
 * - Searches for cars using the region exactly.<br></p>
 *
 * @author jonathan
 */
public class CarSearchJson {

    private Category categoryFrom = null;
    private Category categoryTo = null;
    private Integer yearFrom = null;
    private Integer yearTo = null;
    private Integer maxPowerFrom = null;
    private Integer maxPowerTo = null;

    private DriveTrain driveTrain = null;
    private String dealerName = null;
    private String countryDescription = null;
    private String regionDescription = null;

    /**
     * Get the category from.
     *
     * @return get the category from
     */
    public Category getCategoryFrom() {
        return categoryFrom;
    }

    /**
     * Set the category from.
     *
     * @param categoryFrom the category from
     */
    public void setCategoryFrom(Category categoryFrom) {
        this.categoryFrom = categoryFrom;
    }

    /**
     * Get the category to.
     *
     * @return the category to
     */
    public Category getCategoryTo() {
        return categoryTo;
    }

    /**
     * Set the category to.
     *
     * @param categoryTo the category to
     */
    public void setCategoryTo(Category categoryTo) {
        this.categoryTo = categoryTo;
    }

    /**
     * Get the year from value.
     *
     * @return The year from value.
     */
    public Integer getYearFrom() {
        return yearFrom;
    }

    /**
     * Set the year from value.
     *
     * @param yearFrom the year from value
     */
    public void setYearFrom(Integer yearFrom) {
        this.yearFrom = yearFrom;
    }

    /**
     * Get the year to value.
     *
     * @return The year to value.
     */
    public Integer getYearTo() {
        return yearTo;
    }

    /**
     * Set the year to value.
     *
     * @param yearTo the year to value
     */
    public void setYearTo(Integer yearTo) {
        this.yearTo = yearTo;
    }

    /**
     * Get the max power from value.
     *
     * @return The max power from value.
     */
    public Integer getMaxPowerFrom() {
        return maxPowerFrom;
    }

    /**
     * Set the max power from value.
     *
     * @param maxPowerFrom the max power from value
     */
    public void setMaxPowerFrom(Integer maxPowerFrom) {
        this.maxPowerFrom = maxPowerFrom;
    }

    /**
     * Get the max power to value.
     *
     * @return The max power to value.
     */
    public Integer getMaxPowerTo() {
        return maxPowerTo;
    }

    /**
     * Set the max power to value.
     *
     * @param maxPowerTo the max power to value
     */
    public void setMaxPowerTo(Integer maxPowerTo) {
        this.maxPowerTo = maxPowerTo;
    }

    /**
     * Get the drivetrain value.
     *
     * @return the drivetrain value.
     */
    public DriveTrain getDriveTrain() {
        return driveTrain;
    }

    /**
     * Set the drivetrain value using the DriveTrain object.
     *
     * @param driveTrain the drive train value
     */
    public void setDriveTrain(DriveTrain driveTrain) {
        this.driveTrain = driveTrain;
    }

    /**
     * Set the drivetrain value using a String value.<br>
     * It must match a value of {@link DriveTrain DriveTrain} or it will get set
     * to null instead.
     *
     * @param driveTrain the string representation of the drivetrain value.
     */
    public void setDriveTrain(String driveTrain) {
        this.driveTrain = DriveTrain.toDriveTrain(driveTrain);
    }

    /**
     * Get the dealer name value.
     *
     * @return The dealer name value.
     */
    public String getDealerName() {
        return dealerName;
    }

    /**
     * Set the dealer name value.
     *
     * @param dealerName the dealer name value
     */
    public void setDealerName(String dealerName) {
        this.dealerName = dealerName;
    }

    /**
     * Get the country description value.
     *
     * @return The country description value.
     */
    public String getCountryDescription() {
        return countryDescription;
    }

    /**
     * Set the country description value.
     *
     * @param countryDescription the country description value.
     */
    public void setCountryDescription(String countryDescription) {
        this.countryDescription = countryDescription;
    }

    /**
     * Get the region description value.
     *
     * @return The region description value.
     */
    public String getRegionDescription() {
        return regionDescription;
    }

    /**
     * Set the region description value.
     *
     * @param regionDescription the region description value
     */
    public void setRegionDescription(String regionDescription) {
        this.regionDescription = regionDescription;
    }
}
