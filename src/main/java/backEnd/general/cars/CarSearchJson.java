package backEnd.general.cars;

/**
 * The Json used for searching for cars by criteria.<br>
 * Values set to null will be ignored in the search.<br>
 * <p>
 * <b>Level search (Range search):</b><br>
 * - If levelFrom is set to null but a value is in levelTo, levelFrom will be
 * set to 0.<br>
 * - If levelTo is set to null but a value is in levelFrom, levelTo will be set
 * to Integer.MAX_VALUE.<br></p>
 * <p>
 * <b>Year search (Range search):</b><br>
 * - If yearFrom is set to null but a value is in yearTo, yearFrom will be set
 * to 0.<br>
 * - If yearTo is set to null but a value is in yearFrom, yearTo will be set to
 * Integer.MAX_VALUE.<br></p>
 * <p>
 * <b>Power Points search (Range search):</b><br>
 * - If powerPointsFrom is set to null but a value is in powerPointsTo,
 * powerPointsFrom will be set to 0.<br>
 * - If powerPointsTo is set to null but a value is in powerPointsFrom,
 * powerPointsTo will be set to Integer.MAX_VALUE.<br></p>
 * <p>
 * <b>Horse Power search (Range search):</b><br>
 * - If horsePowerFrom is set to null but a value is in horsePowerTo,
 * horsePowerFrom will be set to 0.<br>
 * - If horsePowerTo is set to null but a value is in horsePowerFrom,
 * horsePowerTo will be set to Integer.MAX_VALUE.<br></p>
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

    private Integer levelFrom = null;
    private Integer levelTo = null;
    private Integer yearFrom = null;
    private Integer yearTo = null;
    private Integer powerPointsFrom = null;
    private Integer powerPointsTo = null;
    private Integer horsePowerFrom = null;
    private Integer horsePowerTo = null;

    private DriveTrain driveTrain = null;
    private String dealerName = null;
    private String countryDescription = null;
    private String regionDescription = null;

    /**
     * Get the level from search value.
     *
     * @return The level from value.
     */
    public Integer getLevelFrom() {
        return levelFrom;
    }

    /**
     * Set the level from search value.
     *
     * @param levelFrom the level from value
     */
    public void setLevelFrom(Integer levelFrom) {
        this.levelFrom = levelFrom;
    }

    /**
     * Get the level to search value.
     *
     * @return The level to value.
     */
    public Integer getLevelTo() {
        return levelTo;
    }

    /**
     * Set the level to search value.
     *
     * @param levelTo the level to value
     */
    public void setLevelTo(Integer levelTo) {
        this.levelTo = levelTo;
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
     * Get the power points from value.
     *
     * @return The power points from value.
     */
    public Integer getPowerPointsFrom() {
        return powerPointsFrom;
    }

    /**
     * set the power points from value.
     *
     * @param powerPointsFrom the power points from value
     */
    public void setPowerPointsFrom(Integer powerPointsFrom) {
        this.powerPointsFrom = powerPointsFrom;
    }

    /**
     * Get the power points to value.
     *
     * @return The power points value.
     */
    public Integer getPowerPointsTo() {
        return powerPointsTo;
    }

    /**
     * set the power points value.
     *
     * @param powerPointsTo the power points value
     */
    public void setPowerPointsTo(Integer powerPointsTo) {
        this.powerPointsTo = powerPointsTo;
    }

    /**
     * Get the horse power from value.
     *
     * @return The horse power from value.
     */
    public Integer getHorsePowerFrom() {
        return horsePowerFrom;
    }

    /**
     * Set the horse power from value.
     *
     * @param horsePowerFrom the horse power from value
     */
    public void setHorsePowerFrom(Integer horsePowerFrom) {
        this.horsePowerFrom = horsePowerFrom;
    }

    /**
     * Get the horse power to value.
     *
     * @return The horse power to value.
     */
    public Integer getHorsePowerTo() {
        return horsePowerTo;
    }

    /**
     * Set the horse power to value.
     *
     * @param horsePowerTo the horse power to value
     */
    public void setHorsePowerTo(Integer horsePowerTo) {
        this.horsePowerTo = horsePowerTo;
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
