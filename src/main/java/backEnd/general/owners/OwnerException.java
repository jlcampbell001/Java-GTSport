package backEnd.general.owners;

/**
 * This is the exception errors for the owner.
 * @author jonathan
 */
public class OwnerException extends Exception {

	private static final long serialVersionUID = 100L;

    /**
     * The error message for not finding an owner record based on the primary key.
     */
    public static final String OWNER_NOT_FOUND_KEY_ERROR = "Could not find owner by key: ";

    /**
     * The error message for not finding an owner record based on the name.
     */
    public static final String OWNER_NOT_FOUND_NAME_ERROR = "Could not find owner: ";

    /**
     * The error message for not finding an owner record based on the primary key to delete.
     */
    public static final String OWNER_NOT_FOUND_KEY_DELETE_ERROR = "Could not find owner by key to delete: ";

    /**
     * The error message for trying to save an owner but not giving it a name.
     */
    public static final String OWNER_NAME_NOT_SET = "The owner name was not set.";

    /**
     * The error message for trying to save an owner record but another owner already has that name.
     */
    public static final String OWNER_NAME_EXISTS_ALREADY_ERROR = "An owner with the name already exists: ";

    public static final String OWNER_IS_IN_USE = "The owner can be be deleted as it is in use.";
	/**
	 * Exceptions error control and messages for the owner service.
	 */
	public OwnerException() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public OwnerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 */
	public OwnerException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 */
	public OwnerException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param cause
	 */
	public OwnerException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
}
