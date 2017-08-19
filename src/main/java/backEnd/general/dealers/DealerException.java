package backEnd.general.dealers;

/**
 * Exceptions for the dealer.
 *
 * @author jonathan
 */
public class DealerException extends Exception {

    /**
     * The error message for not filling in the name.
     */
    public static final String NAME_NOT_SET = "The dealer name was not set.";

    /**
     * The error message for not filling in the country key.
     */
    public static final String COUNTRY_KEY_NOT_SET = "The country key was not set.";

    /**
     * The error message for trying to save a dealer where another dealer has
     * the same name.
     */
    public static final String NAME_ALREADY_EXISTS = "The dealer name already exists.";

    /**
     * The error message for trying to save a dealer where the country key does
     * not exist.
     */
    public static final String COUNTRY_KEY_DOES_NOT_EXIST = "The country key was not found for the dealer: ";

    /**
     * The error message for trying to delete a dealer that does not exist.
     */
    public static final String DEALER_KEY_DOES_NOT_EXIST_DELETE = "The dealer key can not be found to delete: ";

    /**
     * The error message for looking for a dealer by the primary key but not
     * finding the dealer.
     */
    public static final String DEALER_KEY_NOT_FOUND = "The dealer key can not be found: ";

    /**
     * The error message for looking for a dealer by the name but not finding
     * the dealer.
     */
    public static final String DEALER_NAME_NOT_FOUND = "The dealer can not be found by the name: ";

    /**
     * The error message for trying to delete the dealer but it is used in
     * another table.
     */
    public static final String DEALER_IS_IN_USE = "The dealer can not be deleted as it is in use.";

    /**
     * Creates a new instance of <code>DealerException</code> without detail
     * message.
     */
    public DealerException() {
    }

    /**
     * Constructs an instance of <code>DealerException</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public DealerException(String msg) {
        super(msg);
    }
}
