package backEnd.general.ownerCars;

/**
 *
 * @author jonathan
 */
public class OwnerCarException extends Exception {
    
    public static final String ID_NOT_SET = "The owner car id must be filled.";
    public static final String OWNER_KEY_NOT_SET = "The owner car owner key must be filled.";
    public static final String CAR_KEY_NOT_SET = "The owner car car key must be filled.";
    public static final String OWNER_KEY_DOSE_NOT_EXIST = "The owner car owner key could not be found: ";
    public static final String CAR_KEY_DOSE_NOT_EXIST = "The owner car car key could not be found: ";
    public static final String OWNERCAR_KEY_CAN_NOT_BE_FOUND_TO_DELETE = "The owner car key cound not be found to delete: ";

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
