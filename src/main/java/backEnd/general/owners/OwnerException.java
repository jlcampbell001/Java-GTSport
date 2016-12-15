package backEnd.general.owners;

public class OwnerException extends Exception {

	private static final long serialVersionUID = 1L;

	public static final String OWNER_NOT_FOUND_KEY_ERROR = "Could not find owner by key: ";
	public static final String OWNER_NOT_FOUND_NAME_ERROR = "Could not find owner: ";
	public static final String OWNER_NOT_FOUND_KEY_DELETE_ERROR = "Could not find onwer by key to delete: ";
	public static final String OWNER_NAME_NOT_SET = "The owner name was not set.";
	public static final String OWNER_NAME_EXISTS_ALREADY_ERROR = "An owner with the name already exists: ";

	/**
	 * 
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
