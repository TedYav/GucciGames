package voogasalad.util.cloud.exception;

public class CloudException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5291882494488465468L;

	/**
	 * Create an exception based on an issue in our code.
	 */
	public CloudException(String message, Object... args) {
		super(format(message, args));
	}

	/**
	 * Create an exception based on a caught exception.
	 */
	public CloudException(Throwable exception) {
		super(exception);
	}

	/**
	 * Create an exception based on a caught exception with a different message.
	 */
	public CloudException(Throwable cause, String message, Object... args) {
		super(format(message, args), cause);
	}

	// remove duplicate code, also placeholder for future improvements (like
	// logging)
	private static String format(String message, Object... args) {
		return String.format(message, args);
	}

}
