package assign2.exceptions;
public class DatabaseException extends Exception {

	public DatabaseException(String msg) {
		super(msg);
	}

	public DatabaseException(String msg, Throwable cause) {
		super(msg, cause);
	}

	private static final long serialVersionUID = 1L;

}
