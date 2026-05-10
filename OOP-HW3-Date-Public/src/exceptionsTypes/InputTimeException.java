package exceptionsTypes;

public class InputTimeException extends HW3Exception {

	private static final long serialVersionUID = 1L;
	
	public static String exceptionType = "InputTimeException";

	public InputTimeException() {
		super();
	}

	public InputTimeException(String message) {
		super(message);
	}

	@Override
	public String getExceptionName() {
		return exceptionType;
	}
}
