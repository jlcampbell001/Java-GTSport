package backEnd.general.manufacturers;

/**
 * Exceptions for the manufacturer.
 *
 * @author jonathan
 */
public class ManufacturerException extends Exception {

    /**
     * The error message for not filling in the name.
     */
    public static final String NAME_NOT_SET = "The manufacturer name was not set.";

    /**
     * The error message for not filling in the country key.
     */
    public static final String COUNTRY_KEY_NOT_SET = "The country key was not set.";

    /**
     * The error message for trying to save a manufacturer where another manufacturer has
     * the same name.
     */
    public static final String NAME_ALREADY_EXISTS = "The manufacturer name already exists.";

    /**
     * The error message for trying to save a manufacturer where the country key does
     * not exist.
     */
    public static final String COUNTRY_KEY_DOES_NOT_EXIST = "The country key was not found for the manufacturer: ";

    /**
     * The error message for trying to delete a manufacturer that does not exist.
     */
    public static final String MANUFACTURER_KEY_DOES_NOT_EXIST_DELETE = "The manufacturer key can not be found to delete: ";

    /**
     * The error message for looking for a manufacturer by the primary key but not
     * finding the manufacturer.
     */
    public static final String MANUFACTURER_KEY_NOT_FOUND = "The manufacturer key can not be found: ";

    /**
     * The error message for looking for a manufacturer by the name but not finding
     * the manufacturer.
     */
    public static final String MANUFACTURER_NAME_NOT_FOUND = "The manufacturer can not be found by the name: ";

    /**
     * The error message for trying to delete the manufacturer but it is used in
     * another table.
     */
    public static final String MANUFACTURER_IS_IN_USE = "The manufacturer can not be deleted as it is in use.";

    /**
     * Creates a new instance of <code>ManufacturerException</code> without detail
     * message.
     */
    public ManufacturerException() {
    }

    /**
     * Constructs an instance of <code>ManufacturerException</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public ManufacturerException(String msg) {
        super(msg);
    }
}
