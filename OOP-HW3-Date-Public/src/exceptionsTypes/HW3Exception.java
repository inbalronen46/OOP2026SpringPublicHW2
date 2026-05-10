package exceptionsTypes;

public abstract class HW3Exception extends Exception {

	private static final long serialVersionUID = 1L;

	public HW3Exception() {
		super();
	}

	public HW3Exception(String message) {
		super(message);
	}

	public abstract String getExceptionName();

}
