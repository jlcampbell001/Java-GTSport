package backEnd.general.cars;

/**
 *
 * @author jonathan
 */
public class CarException extends Exception {

    public static final String NAME_NOT_SET = "The car name is not filled.";
    public static final String DEALER_KEY_NOT_SET = "The dealer key is not filled.";
    public static final String DRIVETRAIN_NOT_VALID = "The drivetrain is not valid.";
    public static final String ASPIRATION_NOT_VALID = "The aspiration is not valid.";
    public static final String NAME_ALREADY_EXISTS = "The car name already exists.";
    public static final String DEALER_KEY_DOES_NOT_EXIST = "The dealer key does not exist: ";
    public static final String CAR_KEY_NOT_FOUND_TO_DELETE = "The car key can not be found to delete: ";
    public static final String CAR_KEY_NOT_FOUND = "The car key can not be found: ";
    public static final String CAR_NAME_NOT_FOUND = "The car name can not be found: ";
    public static final String SEARCH_CRITERIA_NOT_PROVIDED = "No car search criteria provided.";
    public static final String NO_CARS_FOUND_FOR_CRITERIA = "No cars found for criteria.";
    
    /**
     * Creates a new instance of <code>CarException</code> without detail
     * message.
     */
    public CarException() {
    }

    /**
     * Constructs an instance of <code>CarException</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public CarException(String msg) {
        super(msg);
    }
}
