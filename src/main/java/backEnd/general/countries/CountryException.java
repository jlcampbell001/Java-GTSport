package backEnd.general.countries;

/**
 * Exception errors for the country.
 *
 * @author jonathan
 */
public class CountryException extends Exception {

    /**
     * The error message for the description not filled.
     */
    public static final String DESCRIPTION_NOT_SET = "The country descirption is not filled.";

    /**
     * The error message for the region key not filled.
     */
    public static final String REGION_KEY_NOT_SET = "The country region key is not filled.";

    /**
     * The error message for trying to save a country with a description that
     * already exists.
     */
    public static final String DESCRIPTION_ALREADY_EXISTS = "The country description already exists.";

    /**
     * The error message for trying to save a country with a region key that
     * does not exist.
     */
    public static final String REGION_DOES_NOT_EXIST = "The country region key does not exist: ";

    /**
     * The error message for trying to delete a country key that does not exist.
     */
    public static final String COUNTRY_KEY_NOT_FOUND_DO_DELETE = "The country key could not be found to delete: ";

    /**
     * The error message for not finding a country key.
     */
    public static final String COUNTRY_KEY_NOT_FOUND = "The country key could not be found: ";

    /**
     * The error message for not finding a country description.
     */
    public static final String COUNTRY_DESCRIPTION_NOT_FOUND = "The country description could not be found: ";

    /**
     * The error message for not being able to delete the country because it is
     * used in another table.
     */
    public static final String COUNTRY_IS_IN_USE = "The country can not be deleted as it is in use.";

    /**
     * Creates a new instance of <code>CountryException</code> without detail
     * message.
     */
    public CountryException() {
    }

    /**
     * Constructs an instance of <code>CountryException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public CountryException(String msg) {
        super(msg);
    }
}
