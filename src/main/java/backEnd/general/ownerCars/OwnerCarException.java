package backEnd.general.ownerCars;

/**
 * The exceptions for the owner cars.
 *
 * @author jonathan
 */
public class OwnerCarException extends Exception {

    /**
     * The error if the owner car Id was not filled.
     */
    public static final String ID_NOT_SET = "The owner car id must be filled.";

    /**
     * The error if the owner car owner key was not filled.
     */
    public static final String OWNER_KEY_NOT_SET = "The owner car owner key must be filled.";

    /**
     * The error if the owner car car key was not filled.
     */
    public static final String CAR_KEY_NOT_SET = "The owner car car key must be filled.";

    /**
     * The error if the owner key is not found in the owner table.
     */
    public static final String OWNER_KEY_DOSE_NOT_EXIST = "The owner car owner key could not be found: ";

    /**
     * The error if the car key is not found in the car table.
     */
    public static final String CAR_KEY_DOSE_NOT_EXIST = "The owner car car key could not be found: ";

    /**
     * The error if the owner car key can not be found to be deleted.
     */
    public static final String OWNERCAR_KEY_CAN_NOT_BE_FOUND_TO_DELETE = "The owner car key cound not be found to delete: ";

    /**
     * The error if the owner car key can not be found in the table.
     */
    public static final String OWNERCAR_KEY_NOT_FOUND = "The owner car key could not be found: ";

    /**
     * Creates a new instance of <code>OwnerCarException</code> without detail
     * message.
     */
    public OwnerCarException() {
    }

    /**
     * Constructs an instance of <code>OwnerCarException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public OwnerCarException(String msg) {
        super(msg);
    }
}
