package backEnd.general.cars;

/**
 * The errors for a car.
 *
 * @author jonathan
 */
public class CarException extends Exception {

    /**
     * The error for the car name not being set.
     */
    public static final String NAME_NOT_SET = "The car name is not filled.";

    /**
     * The error for the manufacturer key not being set.
     */
    public static final String MANUFACTURER_KEY_NOT_SET = "The manufacturer key is not filled.";

    /**
     * The error for the drive train not being valid.
     */
    public static final String DRIVETRAIN_NOT_VALID = "The drivetrain is not valid.";

    /**
     * The error for the aspiration not being valid.
     */
    public static final String ASPIRATION_NOT_VALID = "The aspiration is not valid.";
    
    public static final String CATEGORY_NOT_VALID = "The category is not valid.";

    /**
     * The error for a car name already existing.
     */
    public static final String NAME_ALREADY_EXISTS = "The car name already exists.";

    /**
     * The error for a manufacturer key that is not in the dealer table.
     */
    public static final String MANUFACTURER_KEY_DOES_NOT_EXIST = "The manufacturer key does not exist: ";

    /**
     * The error for trying to delete a car but the car key can not be found in
     * the car table.
     */
    public static final String CAR_KEY_NOT_FOUND_TO_DELETE = "The car key can not be found to delete: ";

    /**
     * The error for trying to find a car key in the table but not finding it.
     */
    public static final String CAR_KEY_NOT_FOUND = "The car key can not be found: ";

    /**
     * The error for trying to find a car name in the table but not finding it.
     */
    public static final String CAR_NAME_NOT_FOUND = "The car name can not be found: ";

    /**
     * The error is no search criteria is provided to search against.
     */
    public static final String SEARCH_CRITERIA_NOT_PROVIDED = "No car search criteria provided.";

    /**
     * The error for not finding any car records based on the provided search
     * criteria.
     */
    public static final String NO_CARS_FOUND_FOR_CRITERIA = "No cars found for criteria.";

    /**
     * The error for not being able to delete the car because it is used in
     * another table.
     */
    public static final String CAR_IS_IN_USE = "The car can not be deleted as it is in use.";

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
